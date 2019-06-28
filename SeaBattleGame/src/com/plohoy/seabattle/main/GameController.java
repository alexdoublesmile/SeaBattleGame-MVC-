package com.plohoy.seabattle.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.plohoy.seabattle.model.*;
import com.plohoy.seabattle.view.*;

public class GameController {
	
	private GameModel theModel;	
	private GameView theView;
	
	GameController(GameModel theModel, GameView theView, int aIPower) {
				
			this.theModel = theModel;		
			this.theView = theView;

			theModel.createShipsAuto(theView.getBattlefieldSize());
			theModel.createShots();
			theModel.createLabels();
			theModel.createAI(aIPower);
			theView.viewGame(theModel.getPlayerShips(), theModel.getPlayerShots(), theModel.getPlayerLabels(), 
					theModel.getOpponentShips(), theModel.getOpponentShots(), theModel.getOpponentLabels());
			if(this.theView instanceof Game3DView) {
				((Game3DView) theView).setVisible();
				((Game3DView) theView).addShotListener(new ShotListener());
			} else {
				do {
					((GameConsoleView) theView).makeShot();
					theBattle(theView.getXShotCoord(), theView.getYShotCoord());
					theView.viewGame(theModel.getPlayerShips(), theModel.getPlayerShots(), theModel.getPlayerLabels(), 
							theModel.getOpponentShips(), theModel.getOpponentShots(), theModel.getOpponentLabels());
				} while(theModel.getOpponentShips().checkAnyShipAlive());
			}
	}
	
	class ShotListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			((Game3DView) theView).setXShotCoord(e.getX()/(((Game3DView) theView).getCELL_PX_SIZE()));
			((Game3DView) theView).setYShotCoord(e.getY()/(((Game3DView) theView).getCELL_PX_SIZE()));
			if(e.getButton() == ((Game3DView) theView).checkIsItShot()) {
				theBattle(theView.getXShotCoord(), theView.getYShotCoord());
			}
			if(e.getButton() == ((Game3DView) theView).checkIsItLabel()) {
				System.out.println("click mouse right-button");
			}
		}		
	}
		
	public void theBattle(int x, int y) {
		while(theModel.getPlayerShots().shotSamePlace(x, y)) {
			theView.displayMessage(theView.getREPEAT_SHOT_MESSAGE());
			return;
		}
		theModel.getPlayerShots().add(x, y, true);
		if(theModel.getOpponentShips().checkHit(x, y)) {
			for(Ship ship : theModel.getOpponentShips().getBattleField()) {
				if(!ship.checkShipAlive() && ship.checkShipHit(x, y)){
					for(Cell cell : ship.getAroundCells()) {
						theModel.getPlayerShots().add(cell.getXCoord(), cell.getYCoord(), true);
					}
					if(this.theView instanceof Game3DView) {
						((Game3DView) theView).repaintOpponentView();
					}
					if(!theModel.getOpponentShips().checkAnyShipAlive()) {
						theView.displayMessage(theView.getWINNER_MESSAGE());
						theView.playAgain();
					} else {
//						theView.displayMessage(theView.getSINK_THE_SHIP_MESSAGE());
					}
				}
			}				
		} else {
			opponentShoots(theModel.aIShoots(theView.getBattlefieldSize()).getXShotCoord(), 
							theModel.aIShoots(theView.getBattlefieldSize()).getYShotCoord());
			if(this.theView instanceof Game3DView) {
				((Game3DView) theView).repaintOpponentView();
				((Game3DView) theView).repaintPlayerView();
			}
		}
		if(this.theView instanceof Game3DView) {
			((Game3DView) theView).repaintOpponentView();
		}		
	}
	
	public void opponentShoots(int x, int y) {	
		theModel.getOpponentShots().add(x, y, true);
		if (!theModel.getPlayerShips().checkHit(x, y)) {
			return;
		} else {
			if(!theModel.getPlayerShips().checkAnyShipAlive()) {
				theView.displayMessage(theView.getLOOSER_MESSAGE());
				theView.playAgain();
			} else {
				opponentShoots(theModel.aIShoots(theView.getBattlefieldSize()).getXShotCoord(), 
						theModel.aIShoots(theView.getBattlefieldSize()).getYShotCoord());
			}
		}
	}
}
