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
//			System.out.println("размер ячейки: " + theView.getCELL_PLAYER_PX_SIZE());	
//			System.out.println("мои координаты y: (" + e.getX() + ", " + e.getY() + ")");
//			System.out.println("------------------------------------------------");
			int x = e.getX()/(theView.getCELL_PLAYER_PX_SIZE() * 9 / 10);
			int	y = e.getY()/(theView.getCELL_PLAYER_PX_SIZE() * 9 / 10);
//			System.out.println("------------------------------------------------");
//			System.out.println("размер ячейки: " + theView.getCELL_PLAYER_PX_SIZE());	
//			System.out.println("мои координаты y: (" + e.getX() + ", " + e.getY() + ")");
//			System.out.println("------------------------------------------------");
			if(e.getButton() == theView.getMOUSE_BUTTON_LEFT() && !theModel.isGameOver()) {
//				System.out.println("клацаю левой кнопочкой!");
				while(theModel.getPlayerShots().hitSamePlace(x, y)) {
					theView.displayMessage("Вы сюда уже стреляли. Есть смысл выстрелить в другое место..");
					return;
				}
				theModel.getPlayerShots().add(x, y, true);
//				System.out.println("------------------------------------------------");
//				System.out.println("моя координата х: " + x);	
//				System.out.println("моя координата y: " + y);
//				System.out.println("------------------------------------------------");
//				if(!theModel.getPlayerShots().hitSamePlace(x, y)) {
//					
//				} else {
//					theView.displayErrorMessage("Вы сюда уже стреляли. Есть смысл выстрелить в другое место..");
//					
//					return;
//				}

				
				if(theModel.getOpponentShips().checkHit(x, y)) {
					for(Ship ship : theModel.getOpponentShips().getBattleField()) {
						if(!ship.isShipAlive() && ship.checkShipHit(x, y)){
							for(Cell cell : ship.getAroundCells()) {
								theModel.getPlayerShots().add(cell.getxCoord(), cell.getyCoord(), true);
							}
							theView.getOpponentBattleFieldPanel().repaint();
							theView.displayMessage("Вы потопили вражеский корабль!");
						}
					}
					if(!theModel.getOpponentShips().checkSurvivors()) {
						theView.displayMessage("Вражеский флот уничтожен! Вы победили!");
						theModel.setGameOver(true);
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
			if(e.getButton() == theView.getMOUSE_BUTTON_RIGHT()) {
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
			

			
		} while(theModel.getOpponentShots().hitSamePlace(x, y));
		theModel.getOpponentShots().add(x, y, true);
		
		System.out.println("------------------------------------------------");	
		System.out.println("Список выстрелов:");
		int n = 1;
		for(Shot shot : theModel.getOpponentShots().getShots()) {
			System.out.println("Координаты " + n + " выстрела: (" + shot.getxCoord() + ", " + shot.getyCoord() + ").");
			n++;				
		}
		System.out.println("------------------------------------------------");	
		
		if (!theModel.getPlayerShips().checkHit(x, y)) {
			System.out.println("оппонент промазал!!!");
			return;
		} else {
			System.out.println("оппонент попал прямо в яблочко!!!");
			if(!theModel.getPlayerShips().checkSurvivors()) {
				theView.displayMessage("Ваш флот уничтожен! Это поражение...");
				theModel.setGameOver(true);
			} else {
				opponentShoots();
			}
		}
		
	}
}
