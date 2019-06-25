package com.plohoy.seabattle.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import javax.swing.*;
import com.plohoy.seabattle.model.*;

@SuppressWarnings("serial")
public class SeaBattle3DView extends JFrame implements SeaBattleView {

	private int battlefieldSize = 10;
	private final int FIELD_PX_SIZE = 400;
	private final int CELL_PX_SIZE = FIELD_PX_SIZE / battlefieldSize;
	private final int SHADOW_OFFSET_FACTOR = CELL_PX_SIZE / 30;
	private final float LINE_FACTOR = CELL_PX_SIZE / 28f;
	private final float LINE_OFFSET_FACTOR = CELL_PX_SIZE * 0.15f;

	private final int WINDOW_WIDTH = 900;
	private final int WINDOW_HEIGHT = 400;
	private final int MOUSE_BUTTON_LEFT = 1;
	private final int MOUSE_BUTTON_RIGHT = 3;

	private final String TITLE = "Морской Бой cо слабым ИИ на простейшем поле (расстановка кораблей автоматическая)";
	private final String WINNER_MESSAGE = "Вражеский флот уничтожен! Вы победили!";
	private final String LOOSER_MESSAGE = "Ваш флот уничтожен! Это поражение...";
	private final String AGAIN_MESSAGE = "Желаете сыграть снова?";
	private int playAgainAnswer;

	BattleFieldPlayerPanel playerBattleFieldPanel;
	BattleFieldOpponentPanel opponentBattleFieldPanel;	
	JPanel middlePanel;
	Field playerShips;
	Field opponentShips;	
	Shots playerShots;
	Shots opponentShots;	
	Labels playerLabels;
	Labels opponentLabels;	
	
	public SeaBattle3DView() {
		
		UIManager.getSystemLookAndFeelClassName();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle(TITLE);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setLocationRelativeTo(null);

	}
	@Override
	public void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots, Labels opponentLabels) {
		playerShips = playerField;
		this.playerShots = playerShots;
		this.playerLabels = playerLabels;
		opponentShips = opponentField;
		this.opponentShots = opponentShots;
		this.opponentLabels = opponentLabels;

		createPlayerPanel();
		createOpponentPanel();
		createMiddlePanel();
		
		this.add(playerBattleFieldPanel);
		this.add(middlePanel);
		this.add(opponentBattleFieldPanel);
		this.pack();				
	}
	
	public void createPlayerPanel() {
		playerBattleFieldPanel = new BattleFieldPlayerPanel();
		playerBattleFieldPanel.setPreferredSize(new Dimension(FIELD_PX_SIZE, FIELD_PX_SIZE));
		playerBattleFieldPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		playerBattleFieldPanel.setBackground(Color.blue);
	}

	public void createOpponentPanel() {
		opponentBattleFieldPanel = new BattleFieldOpponentPanel();
		opponentBattleFieldPanel.setPreferredSize(new Dimension(FIELD_PX_SIZE, FIELD_PX_SIZE));
		opponentBattleFieldPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		opponentBattleFieldPanel.setBackground(Color.blue);
	}
	
	public void createMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setBackground(Color.blue);
	}
	
	class BattleFieldPlayerPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.lightGray);
			drawBackground(g2);
			drawShots(g2, opponentShots);
			drawShips(g2, playerShips, true);	
		}
	}
			
	class BattleFieldOpponentPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.lightGray);
			drawBackground(g2);
			drawShots(g2, playerShots);
			drawShips(g2, opponentShips, false);
		}
	}

	public void drawBackground(Graphics2D g2) {			
		for(int i = 0; i <= battlefieldSize; i++) {
			Line2D horizontalLines = new Line2D.Double(
					0, i*CELL_PX_SIZE, 
					battlefieldSize*CELL_PX_SIZE, i*CELL_PX_SIZE);
			Line2D verticalLines = new Line2D.Double(
					i*CELL_PX_SIZE, 0, 
					i*CELL_PX_SIZE, battlefieldSize*CELL_PX_SIZE);
			g2.draw(horizontalLines);
			g2.draw(verticalLines);
		}
	}
	
	public void drawShots(Graphics2D g2, Shots shots) {			
		for(Shot shot : shots.getShots()) {
			g2.setColor(Color.gray);
			fillTheCell(shot, g2);
		}
	}
	
	public void drawShips(Graphics2D g2, Field ships, boolean isVisible) {			
		for(Ship ship : ships.getBattleField()) {
			for(Cell cell : ship.getCells()) {
				g2.setColor(Color.green);
				if(isVisible) {
					fillTheCell(cell, g2);
				}
				if(!cell.isCellAlive()) {
					fillTheCell(cell, g2);
					drawTheRedCross(cell, g2);
				} 
			}
		}
	}
	
	public void fillTheCell(Object obj, Graphics2D g2) {		
		if(obj instanceof Shot) {
			Shot shot = (Shot) obj;
			g2.fill3DRect(
					shot.getXCoord()*CELL_PX_SIZE + SHADOW_OFFSET_FACTOR, 
					shot.getYCoord()*CELL_PX_SIZE + SHADOW_OFFSET_FACTOR, 
					CELL_PX_SIZE - 2 * SHADOW_OFFSET_FACTOR, 
					CELL_PX_SIZE - 2 * SHADOW_OFFSET_FACTOR, 
					true);
		} else {
			Cell cell = (Cell) obj;
			g2.fill3DRect(
					cell.getXCoord()*CELL_PX_SIZE + SHADOW_OFFSET_FACTOR, 
					cell.getYCoord()*CELL_PX_SIZE + SHADOW_OFFSET_FACTOR, 
					CELL_PX_SIZE - 2 * SHADOW_OFFSET_FACTOR, 
					CELL_PX_SIZE - 2 * SHADOW_OFFSET_FACTOR, 
					true);
		}
	}
	
	public void drawTheRedCross(Cell cell, Graphics2D g2) {
		Line2D topLine = new Line2D.Double(
				cell.getXCoord()*CELL_PX_SIZE + LINE_OFFSET_FACTOR, 
				cell.getYCoord()*CELL_PX_SIZE + LINE_OFFSET_FACTOR, 
				cell.getXCoord()*CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR, 
				cell.getYCoord()*CELL_PX_SIZE + CELL_PX_SIZE  - LINE_OFFSET_FACTOR);
		Line2D bottomLine = new Line2D.Double(
				cell.getXCoord()*CELL_PX_SIZE + LINE_OFFSET_FACTOR, 
				cell.getYCoord()*CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR, 
				cell.getXCoord()*CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR, 
				cell.getYCoord()*CELL_PX_SIZE + LINE_OFFSET_FACTOR);				
		Line2D topShadowLine = new Line2D.Double(
				cell.getXCoord()*CELL_PX_SIZE + LINE_OFFSET_FACTOR + SHADOW_OFFSET_FACTOR, 
				cell.getYCoord()*CELL_PX_SIZE + LINE_OFFSET_FACTOR, 
				cell.getXCoord()*CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR + SHADOW_OFFSET_FACTOR, 
				cell.getYCoord()*CELL_PX_SIZE + CELL_PX_SIZE  - LINE_OFFSET_FACTOR);
		Line2D bottomShadowLine = new Line2D.Double(
				cell.getXCoord()*CELL_PX_SIZE + LINE_OFFSET_FACTOR + SHADOW_OFFSET_FACTOR, 
				cell.getYCoord()*CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR, 
				cell.getXCoord()*CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR + SHADOW_OFFSET_FACTOR, 
				cell.getYCoord()*CELL_PX_SIZE + LINE_OFFSET_FACTOR);
		g2.setColor(Color.gray);
		g2.setStroke(new BasicStroke(LINE_FACTOR / 2));
		g2.draw(topShadowLine);
		g2.draw(bottomShadowLine);
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke(LINE_FACTOR));
		g2.draw(topLine);
		g2.draw(bottomLine);
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
		playAgainAnswer = JOptionPane.showConfirmDialog(this, message, "", 2);
	}

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
	public int getFIELD_PX_SIZE() {
		return FIELD_PX_SIZE;
	}

	@Override
	public int getCELL_PX_SIZE() {
		return CELL_PX_SIZE;
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
	public int getPlayAgainAnswer() {
		return playAgainAnswer;
	}

	@Override
	public void setPlayAgainAnswer(int answer) {
		this.playAgainAnswer = answer;
	}
	
	@Override
	public void setVisible() {
		setVisible(true);
	}
	
	@Override
	public void setInvisible() {
		setVisible(false);
	}
}
