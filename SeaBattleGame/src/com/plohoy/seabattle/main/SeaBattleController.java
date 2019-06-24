package com.plohoy.seabattle.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.plohoy.seabattle.model.*;
import com.plohoy.seabattle.view.*;

public class SeaBattleController {
	SeaBattleModel theModel;	
	SeaBattleView theView;
//	Field theField;
	
	SeaBattleController(SeaBattleModel theModel, SeaBattleView theView) {
		
		newGame(theModel, theView);
	}
	
	public void newGame(SeaBattleModel theModel, SeaBattleView theView) {
		
		this.theModel = theModel;		
		this.theView = theView;
		theModel.setGameOver(false);
		theModel.createShips(theView.getBattlefieldSize());
		theModel.createShots();
		theModel.createLabels();
		theView.viewGame(theModel.getPlayerShips(), theModel.getPlayerShots(), theModel.getPlayerLabels(), theModel.getOpponentShips(), theModel.getOpponentShots(), theModel.getOpponentLabels());

		theView.addShotListener(new shotListener());
	}
	
	class shotListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			
			
//			System.out.println("------------------------------------------------");
//			System.out.println("координата х: " + x);	
//			System.out.println("координата y: " + y);
//			System.out.println("------------------------------------------------");
//			int x, y;
//			do {
//				x = e.getX()/theView.getCELL_PLAYER_PX_SIZE();
//				y = e.getY()/theView.getCELL_PLAYER_PX_SIZE();
//			} while(theModel.getPlayerShots().hitSamePlace(x, y));
//			System.out.println("------------------------------------------------");
//			System.out.println("размер €чейки: " + theView.getCELL_PLAYER_PX_SIZE());	
//			System.out.println("мои координаты y: (" + e.getX() + ", " + e.getY() + ")");
//			System.out.println("------------------------------------------------");
			int x = e.getX()/(theView.getCELL_PLAYER_PX_SIZE() * 9 / 10);
			int	y = e.getY()/(theView.getCELL_PLAYER_PX_SIZE() * 9 / 10);
//			System.out.println("------------------------------------------------");
//			System.out.println("размер €чейки: " + theView.getCELL_PLAYER_PX_SIZE());	
//			System.out.println("мои координаты y: (" + e.getX() + ", " + e.getY() + ")");
//			System.out.println("------------------------------------------------");
			if(e.getButton() == theView.checkIsItShot() && !theModel.isGameOver()) {
//				System.out.println("клацаю левой кнопочкой!");
				while(theModel.getPlayerShots().shotSamePlace(x, y)) {
					theView.displayMessage("¬ы сюда уже стрел€ли. ≈сть смысл выстрелить в другое место..");
					return;
				}
				theModel.getPlayerShots().add(x, y, true);
//				System.out.println("------------------------------------------------");
//				System.out.println("мо€ координата х: " + x);	
//				System.out.println("мо€ координата y: " + y);
//				System.out.println("------------------------------------------------");
//				if(!theModel.getPlayerShots().hitSamePlace(x, y)) {
//					
//				} else {
//					theView.displayErrorMessage("¬ы сюда уже стрел€ли. ≈сть смысл выстрелить в другое место..");
//					
//					return;
//				}

				
				if(theModel.getOpponentShips().checkHit(x, y)) {
					for(Ship ship : theModel.getOpponentShips().getBattleField()) {
						if(!ship.isShipAlive() && ship.checkShipHit(x, y)){
							for(Cell cell : ship.getAroundCells()) {
								theModel.getPlayerShots().add(cell.getXCoord(), cell.getYCoord(), true);
							}
							theView.getOpponentBattleFieldPanel().repaint();
							if(!theModel.getOpponentShips().checkAnyShipAlive()) {
								theView.displayMessage(theView.getWINNER_MESSAGE());
								theModel.setGameOver(true);
//								if(theModel.getGameOver()) {
//									theView.displayConfirmMessage(theView.getAGAIN_MESSAGE());
//									if(theView.getAnswerChoice() == 0) {
//										newGame(theModel, theView);
//									} else {
//										System.exit(0);
//									}
//								}
							} else {
								theView.displayMessage("¬ы потопили вражеский корабль!");
							}
						}
					}
					
					
				} else {
//					System.out.println("------------------------------------------------");
//					System.out.println("не попал!");
//					System.out.println("тут ходит оппонент");
					opponentShoots();
					theView.getOpponentBattleFieldPanel().repaint();
					theView.getPlayerBattleFieldPanel().repaint();
//					System.out.println("------------------------------------------------");
				}
//				System.out.println("--------------------- тут должна быть перерисовка ---------------------------");
				theView.getOpponentBattleFieldPanel().repaint();
//				theView.repaintAll();
			}
			if(e.getButton() == theView.checkIsItLabel()) {
				System.out.println("клацаю правой кнопочкой!");
			}
		}		
	}
	
	public void opponentShoots() {
		Random random = new Random();
		int x, y;
		do {
			x = random.nextInt(theView.getBattlefieldSize());
			y = random.nextInt(theView.getBattlefieldSize());
			
			System.out.println("------------------------------------------------");
			System.out.println("координата х: " + x);	
			System.out.println("координата y: " + y);
			System.out.println("------------------------------------------------");
			

			
		} while(theModel.getOpponentShots().shotSamePlace(x, y));
		theModel.getOpponentShots().add(x, y, true);
		
		System.out.println("------------------------------------------------");	
		System.out.println("—писок выстрелов:");
		int n = 1;
		for(Shot shot : theModel.getOpponentShots().getShots()) {
			System.out.println(" оординаты " + n + " выстрела: (" + shot.getXCoord() + ", " + shot.getYCoord() + ").");
			n++;				
		}
		System.out.println("------------------------------------------------");	
		
		if (!theModel.getPlayerShips().checkHit(x, y)) {
			System.out.println("оппонент промазал!!!");
			return;
		} else {
			System.out.println("оппонент попал пр€мо в €блочко!!!");
			if(!theModel.getPlayerShips().checkAnyShipAlive()) {
				theView.displayMessage("¬аш флот уничтожен! Ёто поражение...");
				theModel.setGameOver(true);
			} else {
				opponentShoots();
			}
		}
		
	}
}
