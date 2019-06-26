package com.plohoy.seabattle.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.plohoy.seabattle.ai.AIMakeShot;
import com.plohoy.seabattle.model.*;
import com.plohoy.seabattle.view.*;

public class SeaBattleController {
	
	SeaBattleModel theModel;	
	SeaBattleView theView;
	
	SeaBattleController(SeaBattleModel theModel, SeaBattleView theView) {
				
			this.theModel = theModel;		
			this.theView = theView;

			theModel.createShips(theView.getBattlefieldSize());
			theModel.createShots();
			theModel.createLabels();
			theModel.createAI();
			theView.viewGame(theModel.getPlayerShips(), theModel.getPlayerShots(), theModel.getPlayerLabels(), 
					theModel.getOpponentShips(), theModel.getOpponentShots(), theModel.getOpponentLabels());
			if(this.theView instanceof SeaBattle3DView) {
				((SeaBattle3DView) theView).setVisible();
				((SeaBattle3DView) theView).addShotListener(new shotListener());
			} else {
				do {
					((SeaBattleConsoleView) theView).makeShot();
					theBattle(theView.getXShotCoord(), theView.getYShotCoord());
					theView.viewGame(theModel.getPlayerShips(), theModel.getPlayerShots(), theModel.getPlayerLabels(), 
							theModel.getOpponentShips(), theModel.getOpponentShots(), theModel.getOpponentLabels());
				} while(theModel.getOpponentShips().checkAnyShipAlive());
			}
	}
	
	class shotListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			((SeaBattle3DView) theView).setXShotCoord(e.getX()/(((SeaBattle3DView) theView).getCELL_PX_SIZE()));
			((SeaBattle3DView) theView).setYShotCoord(e.getY()/(((SeaBattle3DView) theView).getCELL_PX_SIZE()));
			if(e.getButton() == ((SeaBattle3DView) theView).checkIsItShot()) {
				theBattle(theView.getXShotCoord(), theView.getYShotCoord());
			}
			if(e.getButton() == ((SeaBattle3DView) theView).checkIsItLabel()) {
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
				if(!ship.isShipAlive() && ship.checkShipHit(x, y)){
					for(Cell cell : ship.getAroundCells()) {
						theModel.getPlayerShots().add(cell.getXCoord(), cell.getYCoord(), true);
					}
					if(this.theView instanceof SeaBattle3DView) {
						((SeaBattle3DView) theView).repaintOpponentView();
					}
					if(!theModel.getOpponentShips().checkAnyShipAlive()) {
						theView.displayMessage(theView.getWINNER_MESSAGE());
						playAgain();
					} else {
//						theView.displayMessage(theView.getSINK_THE_SHIP_MESSAGE());
					}
				}
			}				
		} else {
			opponentShoots(aIShootsRandomly().getXShotCoord(), aIShootsRandomly().getYShotCoord());
			if(this.theView instanceof SeaBattle3DView) {
				((SeaBattle3DView) theView).repaintOpponentView();
				((SeaBattle3DView) theView).repaintPlayerView();
			}
		}
		if(this.theView instanceof SeaBattle3DView) {
			((SeaBattle3DView) theView).repaintOpponentView();
		}		
	}
	
	public void opponentShoots(int x, int y) {	
		theModel.getOpponentShots().add(x, y, true);
		if (!theModel.getPlayerShips().checkHit(x, y)) {
			return;
		} else {
			if(!theModel.getPlayerShips().checkAnyShipAlive()) {
				theView.displayMessage(theView.getLOOSER_MESSAGE());
				playAgain();
			} else {
				opponentShoots(aIShootsRandomly().getXShotCoord(), aIShootsRandomly().getYShotCoord());
			}
		}
	}
	
	public AIMakeShot aIShootsRandomly() {
		do {
			theModel.getAIShoots().makeRandomShot(theView.getBattlefieldSize());	
		}
		while (theModel.getOpponentShots().shotSamePlace(theModel.getAIShoots().getXShotCoord(), 
														theModel.getAIShoots().getYShotCoord()));
		return theModel.getAIShoots();
	}
	
	public void playAgain() {
		theView.displayConfirmMessage(theView.getAGAIN_MESSAGE());
		if(theView.getPlayAgainAnswer() == 0) {
			if(this.theView instanceof SeaBattle3DView) {
				((SeaBattle3DView) theView).setInvisible();
			}
			new Launcher().exec();
		} else {
			if(theView instanceof SeaBattleConsoleView) {
				((SeaBattleConsoleView)theView).closeScanner();
			}
			System.exit(0);
		}
	}
}
