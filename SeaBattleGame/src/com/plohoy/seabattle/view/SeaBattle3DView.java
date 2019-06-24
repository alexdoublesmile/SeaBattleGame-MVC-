package com.plohoy.seabattle.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.*;
import com.plohoy.seabattle.model.*;

@SuppressWarnings("serial")
public class SeaBattle3DView extends JFrame implements SeaBattleView {

	private int battlefieldSize = 10;

	private final int FIELD_PLAYER_PX_SIZE = 500;
	private final int FIELD_OPP_PX_SIZE = 450;

	private final int CELL_PLAYER_PX_SIZE = FIELD_PLAYER_PX_SIZE / battlefieldSize;
	private final int CELL_OPP_PX_SIZE = FIELD_OPP_PX_SIZE / battlefieldSize;

	private final int WINDOW_WIDTH = 1000;
	private final int WINDOW_HEIGHT = 700;
	private final int MOUSE_BUTTON_LEFT = 1;
	private final int MOUSE_BUTTON_RIGHT = 3;

	private final String TITLE = "Морской Бой cо слабым ИИ на простейшем поле (расстановка кораблей автоматическая)";
	private final String WINNER_MESSAGE = "Вражеский флот уничтожен! Вы победили!";
	private final String LOOSER_MESSAGE = "Ваш флот уничтожен! Это поражение...";
	private final String AGAIN_MESSAGE = "Желаете сыграть снова?";
	private int answerChoice;


	@Override
	public int getAnswerChoice() {
		return answerChoice;
	}

	@Override
	public void setAnswerChoice(int answer) {
		this.answerChoice = answer;
	}

	BattleFieldPanel playerBattleFieldPanel;
	BattleFieldPanel opponentBattleFieldPanel;
	
	Field playerShips;
	Field opponentShips;
	
	Shots playerShots;
	Shots opponentShots;
	
	Labels playerLabels;
	Labels opponentLabels;	

	@Override
	public int checkIsItShot() {
		return MOUSE_BUTTON_LEFT;
	}

	@Override
	public int checkIsItLabel() {
		return MOUSE_BUTTON_RIGHT;
	}
	
	@Override
	public int getBattlefieldSize() {
		return battlefieldSize;
	}

	@Override
	public void setBattlefieldSize(int battlefieldSize) {
		this.battlefieldSize = battlefieldSize;
	}

	@Override
	public int getFIELD_PLAYER_PX_SIZE() {
		return FIELD_PLAYER_PX_SIZE;
	}
	
	@Override
	public int getFIELD_OPP_PX_SIZE() {
		return FIELD_OPP_PX_SIZE;
	}

	@Override
	public int getCELL_PLAYER_PX_SIZE() {
		return CELL_PLAYER_PX_SIZE;
	}

	@Override
	public int getCELL_OPP_PX_SIZE() {
		return CELL_OPP_PX_SIZE;
	}

	@Override
	public String getWINNER_MESSAGE() {
		return WINNER_MESSAGE;
	}

	@Override
	public String getLOOSER_MESSAGE() {
		return LOOSER_MESSAGE;
	}
	
	@Override
	public String getAGAIN_MESSAGE() {
		return AGAIN_MESSAGE;
	}
	
	@Override
	public JPanel getPlayerBattleFieldPanel() {
		return playerBattleFieldPanel;
	}

	@Override
	public JPanel getOpponentBattleFieldPanel() {
		return opponentBattleFieldPanel;
	}
	
	@Override
	public void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots, Labels opponentLabels) {
		playerShips = playerField;
		this.playerShots = playerShots;
		this.playerLabels = playerLabels;
		opponentShips = opponentField;
		this.opponentShots = opponentShots;
		this.opponentLabels = opponentLabels;

		playerBattleFieldPanel = new BattleFieldPanel();
		playerBattleFieldPanel.setPreferredSize(new Dimension(FIELD_PLAYER_PX_SIZE, FIELD_PLAYER_PX_SIZE));
		playerBattleFieldPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		playerBattleFieldPanel.setBackground(Color.blue);
		opponentBattleFieldPanel = new BattleFieldPanel();
		opponentBattleFieldPanel.setPreferredSize(new Dimension(FIELD_OPP_PX_SIZE, FIELD_OPP_PX_SIZE));
		opponentBattleFieldPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		opponentBattleFieldPanel.setBackground(Color.blue);
		JPanel middlePanel = new JPanel();
	
		UIManager.getSystemLookAndFeelClassName();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle(TITLE);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		this.add(playerBattleFieldPanel);
		this.add(middlePanel);
		middlePanel.setBackground(Color.blue);
		this.add(opponentBattleFieldPanel);
		this.pack();
		this.setLocationRelativeTo(null);
				
	}

	class BattleFieldPanel extends JPanel {
		BattleFieldPanel() {	
		}		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			int cellSize = (int) getSize().getWidth() / battlefieldSize;
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.lightGray);
			
			for(int i = 0; i <= battlefieldSize; i++) {
				Line2D horizontalLines = new Line2D.Double(0, i*cellSize, battlefieldSize*cellSize, i*cellSize);
				Line2D verticalLines = new Line2D.Double(i*cellSize, 0, i*cellSize, battlefieldSize*cellSize);
				g2.draw(horizontalLines);
				g2.draw(verticalLines);
			}
			// paint of ships and shots
			if(cellSize == CELL_OPP_PX_SIZE) {
				for(Shot shot : playerShots.getShots()) {
					g2.setColor(Color.gray);
					g2.fillRect(shot.getXCoord()*cellSize + cellSize/2 - 22, shot.getYCoord()*cellSize + cellSize/2 - 22, 44, 44);
				}
				g2.setColor(Color.green);
				for(Ship ship : opponentShips.getBattleField()) {
					for(Cell cell : ship.getCells()) {
						if(!cell.isCellAlive()) {
							g2.fill3DRect(cell.getXCoord()*cellSize + 1, cell.getYCoord()*cellSize + 1, cellSize - 2, cellSize - 2, true);
						} 
					}
				}
			} else {
				for(Shot shot : opponentShots.getShots()) {
					for(Ship ship : playerShips.getBattleField()) {
						for(Cell cell : ship.getCells()) {
							if(!cell.isCellAlive()) {
								g2.setColor(Color.red);
								g2.fillRect(shot.getXCoord()*cellSize + cellSize/2 - 24, shot.getYCoord()*cellSize + cellSize/2 - 24, 48, 48);
							}
						}
					}
					g2.setColor(Color.gray);
					g2.fillRect(shot.getXCoord()*cellSize + cellSize/2 - 24, shot.getYCoord()*cellSize + cellSize/2 - 24, 48, 48);
				}
				g2.setColor(Color.green);
				for(Ship ship : playerShips.getBattleField()) {
					for(Cell cell : ship.getCells()) {
						if((cell.isCellAlive())) {
							g2.fill3DRect(cell.getXCoord()*cellSize + 1, cell.getYCoord()*cellSize + 1, cellSize - 2, cellSize - 2, true);
						} else {
							
							g2.setColor(Color.red);
							g2.fill3DRect(cell.getXCoord()*cellSize + 1, cell.getYCoord()*cellSize + 1, cellSize - 2, cellSize - 2, true);
							g2.setColor(Color.green);
						}
					}
				}
			}
		}
	}

	
	public void addShotListener(MouseListener listenForShot) {	
		opponentBattleFieldPanel.addMouseListener(listenForShot);
	}
	
	@Override
	public void displayMessage(String message) {
		
		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void displayConfirmMessage(String message) {
		
		answerChoice = JOptionPane.showConfirmDialog(this, message, "Можем повторить бой", 2);
	}

	
//	@Override
//	public Graphics2D getFieldView() {
//		return fieldView;
//	}
	
//	@Override
//	public void repaintWithShips (Graphics2D g2, boolean hide) {
//		if(!hide) {
//			repaintPlayerField(g2);
//		} else {
//			repaintAIField(g2);
//		}	
//	}
//	
//	@Override
//	public void repaintAIField (Graphics2D g2) {
//		
//		g2.setColor(Color.green);
//		for(Shot shot : playerShots.getShots()) {
//			if(shot.isShot()) {
//				g2.setColor(Color.gray);
//				g2.fillRect(shot.getXCoord()*CELL_PX_SIZE/2 - 3, shot.getYCoord()*CELL_PX_SIZE/2 - 3, 8, 8);
//			} else {
//				g2.setColor(Color.gray);
//				g2.drawRect(shot.getXCoord()*CELL_PX_SIZE/2 - 3, shot.getYCoord()*CELL_PX_SIZE/2 - 3, 8, 8);
//			}
//		}
//		for(Ship ship : aIShips.getBattleField()) {
//			for(Cell cell : ship.getCells()) {
//				if(!(cell.isCellAlive())) {
//					g2.fill3DRect(cell.getXCoord()*CELL_PX_SIZE + 1, cell.getYCoord()*CELL_PX_SIZE + 1, CELL_PX_SIZE - 2, CELL_PX_SIZE - 2, true);
//				}
//			}
//		}		
//		this.paintView = g2;
//	}
//	
//	@Override
//	public void repaintPlayerField (Graphics2D g2) {
//		for(Shot shot : aIShots.getShots()) {
//			if(shot.isShot()) {
//				g2.setColor(Color.gray);
//				g2.fillRect(shot.getXCoord()*CELL_PX_SIZE/2 - 3, shot.getYCoord()*CELL_PX_SIZE/2 - 3, 8, 8);
//			} else {
//				g2.setColor(Color.gray);
//				g2.drawRect(shot.getXCoord()*CELL_PX_SIZE/2 - 3, shot.getYCoord()*CELL_PX_SIZE/2 - 3, 8, 8);
//			}
//		}
//		for(Ship ship : playerShips.getBattleField()) {
//			for(Cell cell : ship.getCells()) {
//					g2.fill3DRect(cell.getXCoord()*CELL_PX_SIZE + 1, cell.getYCoord()*CELL_PX_SIZE + 1, CELL_PX_SIZE - 2, CELL_PX_SIZE - 2, true);
//			}
//		}
//		this.paintView = g2;	
//	}
	
//	@Override
//	public void paintShips(Field playerField, Field opponentField, Shots playerShots, Shots opponentShots, Labels playerLabels, Labels opponentLabels) {
//		
//		playerShips = playerField;
//		opponentShips = opponentField;
//		playerShots = playerShots;
//		this.opponentShots = opponentShots;
//		paintingAll();
//	}
	
//	public void paintingAll() {
//		playerBattleFieldPanel = new BattleFieldPanel(false);
//		opponentBattleFieldPanel = new BattleFieldPanel(false);
//		JPanel middlePanel = new JPanel();
//		
//		UIManager.getSystemLookAndFeelClassName();
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//		this.setTitle(TITLE);
//		this.setResizable(false);
//		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
//		
//		this.add(playerBattleFieldPanel);
//		this.add(middlePanel);
//		middlePanel.setBackground(Color.lightGray);
//		this.add(opponentBattleFieldPanel);
//		this.pack();
//		this.setLocationRelativeTo(null);
//	}

//	@Override
//	public void makeShot() {
//		addShotListener(listenForShot);
//	}
//	
//
//	

}
