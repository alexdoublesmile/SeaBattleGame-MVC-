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
	private Graphics2D fieldView;
//	private boolean hide = false;

	@Override
	public int getMOUSE_BUTTON_LEFT() {
		return MOUSE_BUTTON_LEFT;
	}

	@Override
	public int getMOUSE_BUTTON_RIGHT() {
		return MOUSE_BUTTON_RIGHT;
	}

	private final String TITLE = "������� ��� c� ������ �� �� ���������� ���� (����������� �������� ��������������)";
	private final String WINNER_MESSAGE = "�����������! ��  - ����������!";
	private final String LOOSER_MESSAGE = "�� ���������!";
	
	BattleFieldPanel playerBattleFieldPanel;
	BattleFieldPanel opponentBattleFieldPanel;
	
	Field playerShips;
	Field opponentShips;
	
	Shots playerShots;
	Shots opponentShots;
	
	Labels playerLabels;
	Labels opponentLabels;
	
//	private int drawHidden = 1;
	
	private Color cellColour = Color.gray;
	private Color cellHitColour = Color.red;
	

	@Override
	public int getBattlefieldSize() {
		return battlefieldSize;
	}

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
		
//	public Color getCellColour() {
//		return cellColour;
//	}
//
//	public void setCellColour(Color cellColour) {
//		this.cellColour = cellColour;
//	}

	public SeaBattle3DView() {
		
		
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
//		setHide(true);
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
	
	
//	public boolean isHide() {
//		return hide;
//	}
//
//	public void setHide(boolean hide) {
//		this.hide = hide;
//	}

	public void viewShips() {

		
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
			
//			System.out.println("------------------------------------------------");
//			System.out.println("������ �� �����: " + getCELL_OPP_PX_SIZE());
//			System.out.println("������ ��: " + getCELL_OPP_PX_SIZE());
//			System.out.println("------------------------------------------------");
			
			
			if(cellSize == CELL_OPP_PX_SIZE) {
				for(Shot shot : playerShots.getShots()) {
					g2.setColor(Color.gray);
					g2.fillRect(shot.getxCoord()*cellSize + cellSize/2 - 22, shot.getyCoord()*cellSize + cellSize/2 - 22, 44, 44);
				}
				g2.setColor(Color.green);
				for(Ship ship : opponentShips.getBattleField()) {
					for(Cell cell : ship.getCells()) {
						if(!cell.isCellAlive()) {
							g2.fill3DRect(cell.getxCoord()*cellSize + 1, cell.getyCoord()*cellSize + 1, cellSize - 2, cellSize - 2, true);
						} 
					}
//					if(!ship.isShipAlive() && ship.isJustBeenHit()) {
//						for(Cell cell : ship.getCells()) {
//							g2.setColor(Color.gray);
//							g2.fillRect(cell.getxCoord()*cellSize + cellSize/2 - 22, cell.getyCoord()*cellSize + cellSize/2 - 22, 44, 44);
//						}
//					}
				}
//				setHide(false);

			} else {
				for(Shot shot : opponentShots.getShots()) {
					g2.setColor(Color.red);
					g2.fillRect(shot.getxCoord()*cellSize + cellSize/2 - 24, shot.getyCoord()*cellSize + cellSize/2 - 24, 48, 48);
				}
				g2.setColor(Color.green);
				for(Ship ship : playerShips.getBattleField()) {
					for(Cell cell : ship.getCells()) {
						if((cell.isCellAlive())) {
							g2.fill3DRect(cell.getxCoord()*cellSize + 1, cell.getyCoord()*cellSize + 1, cellSize - 2, cellSize - 2, true);
						} else {
							
							g2.setColor(Color.red);
							g2.fill3DRect(cell.getxCoord()*cellSize + 1, cell.getyCoord()*cellSize + 1, cellSize - 2, cellSize - 2, true);
							g2.setColor(Color.green);
						}
					}
				}
			}
//			setFieldView(g2);
//			viewOpponent(opponentShips, opponentShots, opponentLabels, g2);
//			repaintWithShips (g2, hide);
		}
	}

	
	public void addShotListener(MouseListener listenForShot) {	
		opponentBattleFieldPanel.addMouseListener(listenForShot);
	}
	
	@Override
	public JPanel getPlayerBattleFieldPanel() {
		return playerBattleFieldPanel;
	}

	@Override
	public JPanel getOpponentBattleFieldPanel() {
		return opponentBattleFieldPanel;
	}

	public void setFieldView(Graphics2D g2) {
		this.fieldView = g2;
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
//				g2.fillRect(shot.getxCoord()*CELL_PX_SIZE/2 - 3, shot.getyCoord()*CELL_PX_SIZE/2 - 3, 8, 8);
//			} else {
//				g2.setColor(Color.gray);
//				g2.drawRect(shot.getxCoord()*CELL_PX_SIZE/2 - 3, shot.getyCoord()*CELL_PX_SIZE/2 - 3, 8, 8);
//			}
//		}
//		for(Ship ship : aIShips.getBattleField()) {
//			for(Cell cell : ship.getCells()) {
//				if(!(cell.isCellAlive())) {
//					g2.fill3DRect(cell.getxCoord()*CELL_PX_SIZE + 1, cell.getyCoord()*CELL_PX_SIZE + 1, CELL_PX_SIZE - 2, CELL_PX_SIZE - 2, true);
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
//				g2.fillRect(shot.getxCoord()*CELL_PX_SIZE/2 - 3, shot.getyCoord()*CELL_PX_SIZE/2 - 3, 8, 8);
//			} else {
//				g2.setColor(Color.gray);
//				g2.drawRect(shot.getxCoord()*CELL_PX_SIZE/2 - 3, shot.getyCoord()*CELL_PX_SIZE/2 - 3, 8, 8);
//			}
//		}
//		for(Ship ship : playerShips.getBattleField()) {
//			for(Cell cell : ship.getCells()) {
//					g2.fill3DRect(cell.getxCoord()*CELL_PX_SIZE + 1, cell.getyCoord()*CELL_PX_SIZE + 1, CELL_PX_SIZE - 2, CELL_PX_SIZE - 2, true);
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
	@Override
	public void displayMessage(String message) {
		
		JOptionPane.showMessageDialog(this, message);
	}
//
//	

}
