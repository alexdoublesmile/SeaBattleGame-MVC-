package com.plohoy.seabattle.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

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
			theView.setVisible();
			theView.viewGame(theModel.getPlayerShips(), theModel.getPlayerShots(), theModel.getPlayerLabels(), theModel.getOpponentShips(), theModel.getOpponentShots(), theModel.getOpponentLabels());

			theView.addShotListener(new shotListener());
		
	}
		
	class shotListener extends MouseAdapter {

		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			int x = e.getX()/(theView.getCELL_PX_SIZE());
			int	y = e.getY()/(theView.getCELL_PX_SIZE());
			if(e.getButton() == theView.checkIsItShot()) {
				while(theModel.getPlayerShots().shotSamePlace(x, y)) {
					theView.displayMessage("�� ���� ��� ��������. ���� ����� ���������� � ������ �����..");
					return;
				}
				theModel.getPlayerShots().add(x, y, true);
				if(theModel.getOpponentShips().checkHit(x, y)) {
					for(Ship ship : theModel.getOpponentShips().getBattleField()) {
						if(!ship.isShipAlive() && ship.checkShipHit(x, y)){
							for(Cell cell : ship.getAroundCells()) {
								theModel.getPlayerShots().add(cell.getXCoord(), cell.getYCoord(), true);
							}
							theView.getOpponentBattleFieldPanel().repaint();
							if(!theModel.getOpponentShips().checkAnyShipAlive()) {
								theView.displayMessage(theView.getWINNER_MESSAGE());
								playAgain();
							} else {
								theView.displayMessage("�� �������� ��������� �������!");
							}
						}
					}				
				} else {
					opponentShoots();
					theView.getOpponentBattleFieldPanel().repaint();
					theView.getPlayerBattleFieldPanel().repaint();
				}
				theView.getOpponentBattleFieldPanel().repaint();
			}
			if(e.getButton() == theView.checkIsItLabel()) {
				System.out.println("������ ������ ���������!");
			}
		}		
	}
	
	public void opponentShoots() {
		Random random = new Random();
		int x, y;
		do {
			x = random.nextInt(theView.getBattlefieldSize());
			y = random.nextInt(theView.getBattlefieldSize());
		} while(theModel.getOpponentShots().shotSamePlace(x, y));
		theModel.getOpponentShots().add(x, y, true);
		if (!theModel.getPlayerShips().checkHit(x, y)) {
			System.out.println("�������� ��������!!!");
			return;
		} else {
			System.out.println("�������� ����� ����� � �������!!!");
			if(!theModel.getPlayerShips().checkAnyShipAlive()) {
				theView.displayMessage("��� ���� ���������! ��� ���������...");
				playAgain();
			} else {
				opponentShoots();
			}
		}
		
	}
	
	public void playAgain() {
		theView.displayConfirmMessage(theView.getAGAIN_MESSAGE());
		if(theView.getPlayAgainAnswer() == 0) {
			theView.setInvisible();
			new Launcher().exec();
		} else {
			System.exit(0);
		}
	}
}
