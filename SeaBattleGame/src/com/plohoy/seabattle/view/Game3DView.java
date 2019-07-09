package com.plohoy.seabattle.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.interfaces.View;
import com.plohoy.seabattle.main.Launcher;
import com.plohoy.seabattle.model.Cell;
import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.Labels;
import com.plohoy.seabattle.model.Ship;
import com.plohoy.seabattle.model.Shot;
import com.plohoy.seabattle.model.Shots;

@SuppressWarnings("serial")
public class Game3DView extends JFrame implements View {

//	private int battlefieldSize = 10;
	private int xShotCoord = Player.FIELD_SIZE + 1;
	private int yShotCoord = Player.FIELD_SIZE + 1;
	private int xPressCoord;
	private int yPressCoord;
	private boolean mouseLeftButtonIsPressed = false;

	private final int FIELD_PX_SIZE = 300;
	private final int CELL_PX_SIZE = FIELD_PX_SIZE / Player.FIELD_SIZE;
	private final int SHADOW_OFFSET_FACTOR = CELL_PX_SIZE / 30;
	private final float LINE_FACTOR = CELL_PX_SIZE / 28f;
	private final float LINE_OFFSET_FACTOR = CELL_PX_SIZE * 0.15f;

	private final int WINDOW_WIDTH = 900;
	private final int WINDOW_HEIGHT = 400;
	private final int MOUSE_BUTTON_LEFT = 1;
	private final int MOUSE_BUTTON_RIGHT = 3;

	private final String TITLE = "Морской Бой c Искусственным Интеллектом";
	private final String START_MESSAGE = "Битва начинается!.\n По результатам жеребьевки первым стреляет ";
	private final String WINNER_MESSAGE = "Флот уничтожен! \n В этой славной битве побеждает ";
	private final String LOOSER_MESSAGE = "Ваш флот уничтожен! Это поражение... \n Сегодня Искусственный Интеллект оказался умнее Вас.";
	private final String AGAIN_MESSAGE = "Может быть желаете сыграть еще разок?";
	private final String NEXT_PLAYER_TURN_MESSAGE = "Переход хода.\n Ходит ";
	private final String REPEAT_SHOT_MESSAGE = "Вы сюда уже стреляли. Есть смысл выстрелить в другое место..";
	private final String SINK_THE_SHIP_MESSAGE = "Вы потопили вражеский корабль!";
	private int playAgainAnswer;
	private String scoreLabel = "0";

	private BattleFieldPlayerPanel playerBattleFieldPanel;
	private BattleFieldOpponentPanel opponentBattleFieldPanel;
	private JPanel middlePanel;
	private JLabel scorePanel = new JLabel("Кол-во ходов: " + scoreLabel);
//	private JTextField scoreField = new JTextField(3);
//
//	public JTextField getScoreField() {
//		return scoreField;
//	}
//
//	public void setScoreField(JTextField scoreField) {
//		this.scoreField = scoreField;
//	}

	private Field playerShips;
	private Field opponentShips;
	private Shots playerShots;
	private Shots opponentShots;
	private Labels playerLabels;
	private Labels opponentLabels;

	public Game3DView() {
		UIManager.getSystemLookAndFeelClassName();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle(TITLE);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
	}

	@Override
	public void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField,
			Shots opponentShots, Labels opponentLabels, String playerName, String opponentName) {

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
		this.setLocationRelativeTo(null);

		opponentBattleFieldPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);

				if (e.getButton() == MOUSE_BUTTON_LEFT) {
					System.out.println("mouseLeftButtonIsPressed: " + mouseLeftButtonIsPressed);
					System.out.println("xPressCoord: " + xPressCoord);
					System.out.println("yPressCoord: " + yPressCoord);
					xPressCoord = e.getX() / CELL_PX_SIZE;
					yPressCoord = e.getY() / CELL_PX_SIZE;
					mouseLeftButtonIsPressed = true;
					System.out.println("нажимается  левая кнопка");
					System.out.println("mouseLeftButtonIsPressed: " + mouseLeftButtonIsPressed);
					System.out.println("xPressCoord: " + xPressCoord);
					System.out.println("yPressCoord: " + yPressCoord);
				}
				if (e.getButton() == MOUSE_BUTTON_RIGHT) {
					System.out.println("click mouse right-button");
				}
			}
		});

		xPressCoord = Player.FIELD_SIZE + 1;
		yPressCoord = Player.FIELD_SIZE + 1;
	}

	public void createPlayerPanel() {
		playerBattleFieldPanel = new BattleFieldPlayerPanel();
		playerBattleFieldPanel.setPreferredSize(new Dimension(FIELD_PX_SIZE, FIELD_PX_SIZE));
		playerBattleFieldPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		playerBattleFieldPanel.setBackground(Color.white);
	}

	public void createOpponentPanel() {
		opponentBattleFieldPanel = new BattleFieldOpponentPanel();
		opponentBattleFieldPanel.setPreferredSize(new Dimension(FIELD_PX_SIZE, FIELD_PX_SIZE));
		opponentBattleFieldPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		opponentBattleFieldPanel.setBackground(Color.white);
	}

	public void createMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setBackground(Color.white);
		middlePanel.add(scorePanel);
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
		for (int i = 0; i <= Player.FIELD_SIZE; i++) {
			Line2D horizontalLines = new Line2D.Double(0, i * CELL_PX_SIZE, Player.FIELD_SIZE * CELL_PX_SIZE,
					i * CELL_PX_SIZE);
			Line2D verticalLines = new Line2D.Double(i * CELL_PX_SIZE, 0, i * CELL_PX_SIZE,
					Player.FIELD_SIZE * CELL_PX_SIZE);
			g2.draw(horizontalLines);
			g2.draw(verticalLines);
		}
	}

	public void drawShots(Graphics2D g2, Shots shots) {
		for (Shot shot : shots.getShots()) {
			g2.setColor(Color.darkGray);
			drawTheShot(shot, g2);
		}
	}

	public void drawShips(Graphics2D g2, Field ships, boolean isVisible) {
		for (Ship ship : ships.getBattleField()) {
			if (ship.checkShipAlive()) {
				for (Cell cell : ship.getCells()) {
					g2.setColor(Color.lightGray);
					if (isVisible) {
						drawTheCell(cell, g2);
					}
					if (!cell.checkCellAlive()) {
						drawTheCell(cell, g2);
						drawTheCross(cell, g2, Color.red);
					}
				}
			} else {
				for (Cell cell : ship.getCells()) {
					g2.setColor(Color.gray);
					drawTheCell(cell, g2);
					drawTheCross(cell, g2, Color.darkGray);
				}
			}
		}
	}

	public void drawTheCell(Cell cell, Graphics2D g2) {
		g2.fill3DRect(cell.getXCoord() * CELL_PX_SIZE + SHADOW_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + SHADOW_OFFSET_FACTOR, CELL_PX_SIZE - 2 * SHADOW_OFFSET_FACTOR,
				CELL_PX_SIZE - 2 * SHADOW_OFFSET_FACTOR, true);
	}

	public void drawTheShot(Shot shot, Graphics2D g2) {
		g2.fill3DRect(shot.getXCoord() * CELL_PX_SIZE + CELL_PX_SIZE / 2 - 3 * SHADOW_OFFSET_FACTOR,
				shot.getYCoord() * CELL_PX_SIZE + CELL_PX_SIZE / 2 - 3 * SHADOW_OFFSET_FACTOR,
				CELL_PX_SIZE / 6 - 2 * SHADOW_OFFSET_FACTOR, CELL_PX_SIZE / 6 - 2 * SHADOW_OFFSET_FACTOR, true);
	}

	public void drawTheCross(Cell cell, Graphics2D g2, Color color) {
		Line2D topLine = new Line2D.Double(cell.getXCoord() * CELL_PX_SIZE + LINE_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + LINE_OFFSET_FACTOR,
				cell.getXCoord() * CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR);
		Line2D bottomLine = new Line2D.Double(cell.getXCoord() * CELL_PX_SIZE + LINE_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR,
				cell.getXCoord() * CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + LINE_OFFSET_FACTOR);
		Line2D topShadowLine = new Line2D.Double(
				cell.getXCoord() * CELL_PX_SIZE + LINE_OFFSET_FACTOR + SHADOW_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + LINE_OFFSET_FACTOR,
				cell.getXCoord() * CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR + SHADOW_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR);
		Line2D bottomShadowLine = new Line2D.Double(
				cell.getXCoord() * CELL_PX_SIZE + LINE_OFFSET_FACTOR + SHADOW_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR,
				cell.getXCoord() * CELL_PX_SIZE + CELL_PX_SIZE - LINE_OFFSET_FACTOR + SHADOW_OFFSET_FACTOR,
				cell.getYCoord() * CELL_PX_SIZE + LINE_OFFSET_FACTOR);
		g2.setColor(Color.gray);
		g2.setStroke(new BasicStroke(LINE_FACTOR / 2));
		g2.draw(topShadowLine);
		g2.draw(bottomShadowLine);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(LINE_FACTOR));
		g2.draw(topLine);
		g2.draw(bottomLine);
	}

	public void addShotListener(MouseListener listenForShot) {
		opponentBattleFieldPanel.addMouseListener(listenForShot);
	}

	@Override
	public void repaintView(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField,
			Shots opponentShots, Labels opponentLabels) {
		playerShips = playerField;
		this.playerShots = playerShots;
		this.playerLabels = playerLabels;
		opponentShips = opponentField;
		this.opponentShots = opponentShots;
		this.opponentLabels = opponentLabels;

		repaintPlayerView();
		repaintOpponentView();
	}

	public void repaintPlayerView() {
		playerBattleFieldPanel.repaint();
		scorePanel.repaint();
		middlePanel.repaint();
	}

	public void repaintOpponentView() {
		opponentBattleFieldPanel.repaint();
		scorePanel.repaint();
		middlePanel.repaint();
	}

	public int getCELL_PX_SIZE() {
		return CELL_PX_SIZE;
	}

	@Override
	public void displayMessage(String message) {
		String[] answers = { "Okay.." };
		JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				answers, answers[0]);
	}

	@Override
	public void displayConfirmMessage(String message) {
		String[] answers = { "Разве что разок", "Хватит пока.." };
		playAgainAnswer = JOptionPane.showOptionDialog(this, message, "", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}

//	@Override
//	public int getBattlefieldSize() {
//		return battlefieldSize;
//	}
//
//	@Override
//	public void setBattlefieldSize(int battlefieldSize) {
//		this.battlefieldSize = battlefieldSize;
//	}

	@Override
	public String getWINNER_MESSAGE(String playerName) {
		String s = WINNER_MESSAGE + playerName;
		return s;
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
	public String getSTART_MESSAGE(String playerName) {
		String s = START_MESSAGE + playerName;
		return s;
	}

	@Override
	public String getNEXT_PLAYER_TURN_MESSAGE(String playerName) {
		String s = NEXT_PLAYER_TURN_MESSAGE + playerName;
		return s;
	}

	@Override
	public String getREPEAT_SHOT_MESSAGE() {
		return REPEAT_SHOT_MESSAGE;
	}

	@Override
	public String getSINK_THE_SHIP_MESSAGE() {
		return SINK_THE_SHIP_MESSAGE;
	}

	@Override
	public int getPlayAgainAnswer() {
		return playAgainAnswer;
	}

	@Override
	public int getXShotCoord() {
		return xShotCoord;
	}

	@Override
	public int getYShotCoord() {
		return yShotCoord;
	}

	public void setXShotCoord(int xShotCoord) {
		this.xShotCoord = xShotCoord;
	}

	public void setYShotCoord(int yShotCoord) {
		this.yShotCoord = yShotCoord;
	}

	public int checkIsItShot() {
		return MOUSE_BUTTON_LEFT;
	}

	public int checkIsItLabel() {
		return MOUSE_BUTTON_RIGHT;
	}

	public int getxPressCoord() {
		return xPressCoord;
	}

	public void setxPressCoord(int xPressCoord) {
		this.xPressCoord = xPressCoord;
	}

	public int getyPressCoord() {
		return yPressCoord;
	}

	public void setyPressCoord(int yPressCoord) {
		this.yPressCoord = yPressCoord;
	}

	@Override
	public void setVisible() {
		setVisible(true);
	}

	@Override
	public void setInvisible() {
		setVisible(false);
	}

	@Override
	public String getScoreLabel() {
		return scoreLabel;
	}

	@Override
	public void setScoreLabel(String scoreLabel) {
		this.scoreLabel = scoreLabel;
	}

	@Override
	public void playAgain() {
		displayConfirmMessage(AGAIN_MESSAGE);
		if (playAgainAnswer == 0) {
			setInvisible();
			new Launcher().exec();
		} else {
			System.exit(0);
		}
	}

	@Override
	public void setShotCoords() {
		if (mouseLeftButtonIsPressed) {
			xShotCoord = xPressCoord;
			yShotCoord = yPressCoord;
			mouseLeftButtonIsPressed = false;
		}
	}

	@Override
	public void resetShotCoords() {
		xShotCoord = Player.FIELD_SIZE + 1;
		yShotCoord = Player.FIELD_SIZE + 1;
	}

	@Override
	public boolean shotIsDone() {
		if (xShotCoord < Player.FIELD_SIZE) {
			return true;
		} else {
			return false;
		}
	}
}
