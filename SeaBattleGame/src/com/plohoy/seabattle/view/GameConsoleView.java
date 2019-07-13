package com.plohoy.seabattle.view;

import java.util.Scanner;

import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.interfaces.View;
import com.plohoy.seabattle.main.Launcher;
import com.plohoy.seabattle.model.Cell;
import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.Label;
import com.plohoy.seabattle.model.Labels;
import com.plohoy.seabattle.model.Ship;
import com.plohoy.seabattle.model.Shot;
import com.plohoy.seabattle.model.Shots;

public class GameConsoleView implements View {

	private int[] battleFieldLegend;
	private String[][] playerBattleField;
	private String[][] opponentBattleField;
	private final String LIVE_CELL = "O";
	private final String WOUNDED_CELL = "%";
	private final String DEAD_CELL = "X";
	private final String SHOT_CELL = "*";
	private final String LABEL_CELL = "?";
	private final String START_MESSAGE = "Битва начинается!.\n По результатам жеребьевки первым стреляет ";
	private final String WINNER_MESSAGE = "Флот уничтожен! \n В этой славной битве побеждает ";
	private final String LOOSER_MESSAGE = "Ваш флот уничтожен! Это поражение... \n Сегодня Искусственный Интеллект оказался умнее Вас.";
	private final String AGAIN_MESSAGE = "Желаете сыграть снова?\n -0- Да\n -1- Нет";
	private final String NEXT_PLAYER_TURN_MESSAGE = "Переход хода.\n Ходит ";
	private final String REPEAT_SHOT_MESSAGE = "Вы сюда уже стреляли. Есть смысл выстрелить в другое место..";
	private final String SINK_THE_SHIP_MESSAGE = "Вы потопили вражеский корабль!";
	private final String ARRANGE_YOUR_SHIPS = "Выберите клетки, в которых будут стоять Ваши корабли";
	private final String CELL_SHOULD_BE_NEW = "Вы уже, кажется, выбирали эту клетку..";
	private final String CELL_SHOULD_NOT_TOUCH_SHIP = "Корабли должны быть прямыми и не должны касаться друг друга вообще-то..";
	private final String LAST_SHIP_IS_EXCESS_MESSAGE = "Вы не можете выбрать эту клетку, т.к. последний корабль не соответствует правилам.\n Создать можно только: \n 4 - однопалубных шлюпки \n 3 - двухпалубных брига \n 2 - трехпалубных корвета \n и один четырехпалубный фрегат";
	private final String SHIP_IS_EXCESS_MESSAGE = "Создаваемый корабль не соответствует правилам: он слишком велик.\n Создать можно только: \n 4 - однопалубных шлюпки \n 3 - двухпалубных брига \n 2 - трехпалубных корвета \n и один четырехпалубный фрегат";
	private final String NUMBER_EXCEPTION_MESSAGE = "Следует ввести число";
	private final String MAKE_CHOICE = "Выберите действие:";
	private final String TAKE_THE_SHOT = "1. Я буду стрелять";
	private final String TAKE_THE_LABEL = "2. Я поставлю метку";
	private final String TAKE_THE_ONE = "Выберите, пожалуйста, один из предложенных вариантов";
	private final String XCOORD_ENTER = "Введите координату по горизонтали";
	private final String YCOORD_ENTER = "Введите координату по вертикали";
	private final String INCORRECT_COORD = "Введите, пожалуйста подходящие координаты";
	private final String LABEL_XCOORD_ENTER = "тут можно будет ввести метку, но не сегодня..";
	private final String MAKE_YOUR_SHOT = "Делайте Ваш выстрел!";

	private Field playerShips;
	private Field opponentShips;
	private Shots playerShots;
	private Shots opponentShots;
	private Labels playerLabels;
	private Labels opponentLabels;
	private Scanner scan;

	private int xShotCoord = Player.FIELD_SIZE + 1;
	private int yShotCoord = Player.FIELD_SIZE + 1;
	private int manuallyXCoord;
	private int manuallyYCoord;

	private String playerName;
	private String opponentName;
	private int playAgainAnswer;
	private int num;

	public GameConsoleView() {
//		battleFieldLegend = new String[] {"A","B","C","D","E","F","G","H","I","J"};

		battleFieldLegend = new int[Player.FIELD_SIZE];
		playerBattleField = new String[Player.FIELD_SIZE][Player.FIELD_SIZE];
		opponentBattleField = new String[Player.FIELD_SIZE][Player.FIELD_SIZE];

		for (int x = 0; x < Player.FIELD_SIZE; x++) {
			battleFieldLegend[x] = x + 1;
		}

		for (int y = 0; y < Player.FIELD_SIZE; y++) {
			for (int x = 0; x < Player.FIELD_SIZE; x++) {
				playerBattleField[x][y] = " ";
			}
		}

		for (int y = 0; y < Player.FIELD_SIZE; y++) {
			for (int x = 0; x < Player.FIELD_SIZE; x++) {
				opponentBattleField[x][y] = " ";
			}
		}
	}

	@Override
	public void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField,
			Shots opponentShots, Labels opponentLabels, String playerName, String opponentName) {

		this.playerName = playerName;
		this.opponentName = opponentName;
		playerShips = playerField;
		this.playerShots = playerShots;
		this.playerLabels = playerLabels;
		opponentShips = opponentField;
		this.opponentShots = opponentShots;
		this.opponentLabels = opponentLabels;

		paintPlayerView();
		paintOpponentView();
	}

	public void paintPlayerView() {
		System.out.println("");
		System.out.println("--- " + playerName + " ---");

		drawShots(playerBattleField, opponentShots);
		if (!(playerShips == null)) {
			drawShips(playerBattleField, playerShips, true);
		}
		drawBackground(playerBattleField);
	}

	public void paintOpponentView() {
		System.out.println("");
		System.out.println("--- " + opponentName + " ---");
		drawShots(opponentBattleField, playerShots);
		if (!(opponentShips == null)) {
			drawShips(opponentBattleField, opponentShips, false);
		}
//		drawLabels(playerLabels);
		drawBackground(opponentBattleField);
	}

	public void drawShips(String[][] field, Field ships, boolean isVisible) {
		for (Ship ship : ships.getBattleField()) {
			if (ship.checkShipAlive()) {
				for (Cell cell : ship.getCells()) {
					if (isVisible) {
						drawTheCell(field, cell, LIVE_CELL);
					}
					if (!cell.checkCellAlive()) {
						drawTheCell(field, cell, WOUNDED_CELL);
					}
				}
			} else {
				for (Cell cell : ship.getCells()) {
					drawTheCell(field, cell, DEAD_CELL);
				}
			}
		}
	}

	public void drawTheCell(String[][] field, Cell cell, String typeOfCell) {
		field[cell.getXCoord()][cell.getYCoord()] = typeOfCell;
	}

	public void drawShots(String[][] field, Shots shots) {
		for (Shot shot : shots.getShots()) {
			drawTheShot(field, shot);
		}
	}

	public void drawTheShot(String[][] field, Shot shot) {
		field[shot.getXCoord()][shot.getYCoord()] = SHOT_CELL;
	}

	public void drawLabels(Labels labels) {
		for (Label label : labels.getLabels()) {
			drawTheLabel(label);
		}
	}

	public void drawTheLabel(Label label) {
		opponentBattleField[label.getXCoord()][label.getYCoord()] = LABEL_CELL;
	}

	public void drawBackground(String[][] field) {
		drawLegend();
		resetNum();
		System.out.println();
		for (int y = 0; y < Player.FIELD_SIZE; y++) {
			if (y < 9) {
				System.out.print(" ");
			}
			System.out.print(num++ + "|");
			for (int x = 0; x < Player.FIELD_SIZE; x++) {
				System.out.print(" ");
				System.out.print(field[x][y] + "  ");
			}
			System.out.println("|");
			if (y < Player.FIELD_SIZE - 1) {
				System.out.println("");
			}
		}
		drawBottomBorder();
		System.out.println("");
	}

	public void drawLegend() {
		System.out.print(" ");
		for (int x = 0; x < Player.FIELD_SIZE; x++) {
			if (x < 10) {
				System.out.print(" ");
			}
			System.out.print("  ");
			System.out.print(battleFieldLegend[x]);
		}
		System.out.println("");
		drawBottomBorder();
	}

	public void drawBottomBorder() {
		System.out.print("   ");
		for (int i = 0; i < Player.FIELD_SIZE * 2; i++) {
			System.out.print("--");
		}
	}

	public int makeChoice() {
		String s;
		System.out.println(TAKE_THE_SHOT);
		System.out.println(TAKE_THE_LABEL);
		do {
			System.out.println(TAKE_THE_ONE);
			s = inputAnyString();
		} while (!(s.equals("1") || s.equals("2")));
		return Integer.parseInt(s);
	}

	public void setXShotCoord(String s) {
		try {
			xShotCoord = Integer.parseInt(s) - 1;
		} catch (NumberFormatException e) {
			displayMessage(NUMBER_EXCEPTION_MESSAGE);
			setXShotCoord(inputAnyString());
		}
		if ((xShotCoord + 1) < 1 || (xShotCoord + 1) > Player.FIELD_SIZE) {
			displayMessage(INCORRECT_COORD);
			setXShotCoord(inputAnyString());
		}
	}

	public void setYShotCoord(String s) {
		try {
			yShotCoord = Integer.parseInt(s) - 1;
		} catch (NumberFormatException e) {
			displayMessage(NUMBER_EXCEPTION_MESSAGE);
			setYShotCoord(inputAnyString());
		}
		if ((yShotCoord + 1) < 1 || (yShotCoord + 1) > Player.FIELD_SIZE) {
			displayMessage(INCORRECT_COORD);
			setYShotCoord(inputAnyString());
		}
	}

	public void setManuallyXCoord(String s) {
		try {
			manuallyXCoord = Integer.parseInt(s) - 1;
		} catch (NumberFormatException e) {
			displayMessage(NUMBER_EXCEPTION_MESSAGE);
			setManuallyXCoord(inputAnyString());
		}
		if ((manuallyXCoord + 1) < 1 || (manuallyXCoord + 1) > Player.FIELD_SIZE) {
			displayMessage(INCORRECT_COORD);
			setManuallyXCoord(inputAnyString());
		}
	}

	public void setManuallyYCoord(String s) {
		try {
			manuallyYCoord = Integer.parseInt(s) - 1;
		} catch (NumberFormatException e) {
			displayMessage(NUMBER_EXCEPTION_MESSAGE);
			setManuallyYCoord(inputAnyString());
		}
		if ((manuallyYCoord + 1) < 1 || (manuallyYCoord + 1) > Player.FIELD_SIZE) {
			displayMessage(INCORRECT_COORD);
			setManuallyYCoord(inputAnyString());
		}
	}

	public void setAnyCoord(int someCoord, String someString) {
		try {
			someCoord = Integer.parseInt(someString) - 1;
		} catch (NumberFormatException e) {
			displayMessage(NUMBER_EXCEPTION_MESSAGE);
			setAnyCoord(someCoord, inputAnyString());
		}
		if ((someCoord + 1) < 1 || (someCoord + 1) > Player.FIELD_SIZE) {
			displayMessage(INCORRECT_COORD);
			setAnyCoord(someCoord, inputAnyString());
		}
	}

	@Override
	public int getXShotCoord() {
		return xShotCoord;
	}

	@Override
	public int getYShotCoord() {
		return yShotCoord;
	}

	@Override
	public int getPlayAgainAnswer() {
		return playAgainAnswer;
	}

	@Override
	public void displayConfirmMessage(String message) {
		System.out.println(message);

	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);

	}

	@Override
	public String getARRANGE_YOUR_SHIPS() {
		return ARRANGE_YOUR_SHIPS;
	}

	@Override
	public String getAGAIN_MESSAGE() {
		return AGAIN_MESSAGE;
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
	public String getLOOSER_MESSAGE() {
		return LOOSER_MESSAGE;
	}

	@Override
	public String getNEXT_PLAYER_TURN_MESSAGE(String playerName) {
		String s = NEXT_PLAYER_TURN_MESSAGE + playerName;
		return s;
	}

	@Override
	public String getSHIP_IS_EXCESS_MESSAGE() {
		return SHIP_IS_EXCESS_MESSAGE;
	}

	public void resetNum() {
		this.num = 1;
	}

	public Scanner getScanner() {
		scan = new Scanner(System.in);
		return scan;
	}

	public String inputAnyString() {
		String s = getScanner().nextLine();
		return s;
	}

	public void closeScanner() {
		getScanner().close();
	}

	@Override
	public void playAgain() {
		displayConfirmMessage(AGAIN_MESSAGE);
		while (!(inputAnyString().equals("0") || inputAnyString().equals("1"))) {
			displayConfirmMessage(TAKE_THE_ONE);
		}
		if (inputAnyString().equals("1")) {
			playAgainAnswer++;
		}
		if (playAgainAnswer == 0) {
			new Launcher().exec();
		} else {
			closeScanner();
			System.exit(0);
		}
	}

	@Override
	public void setScoreLabel(String scoreLabel) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getScoreLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void repaintView(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField,
			Shots opponentShots, Labels opponentLabels) {
		viewGame(playerField, playerShots, playerLabels, opponentField, opponentShots, opponentLabels, playerName,
				opponentName);

	}

	@Override
	public void setShotCoords() {

		System.out.println(MAKE_YOUR_SHOT);
		System.out.println(XCOORD_ENTER);
		setXShotCoord(inputAnyString());
		System.out.println(YCOORD_ENTER);
		setYShotCoord(inputAnyString());

	}

	@Override
	public void setVisible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInvisible() {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");

	}

	@Override
	public boolean shotIsDone() {
		if (xShotCoord < Player.FIELD_SIZE) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void resetShotCoords() {

		xShotCoord = Player.FIELD_SIZE + 1;
		yShotCoord = Player.FIELD_SIZE + 1;

	}

	@Override
	public String getSTART_MESSAGE(String playerName) {
		String s = START_MESSAGE + playerName;
		return s;
	}

	@Override
	public String getWINNER_MESSAGE(String playerName) {
		String s = WINNER_MESSAGE + playerName;
		return s;
	}

	@Override
	public String getCELL_SHOULD_NOT_TOUCH_SHIP() {
		return CELL_SHOULD_NOT_TOUCH_SHIP;
	}

	@Override
	public int getManuallyXCoord() {
		return manuallyXCoord;
	}

	@Override
	public int getManuallyYCoord() {
		return manuallyYCoord;
	}

	@Override
	public void setManuallyCoords() {

		System.out.println(XCOORD_ENTER);
		setManuallyXCoord(inputAnyString());
		System.out.println(YCOORD_ENTER);
		setManuallyYCoord(inputAnyString());
	}

	@Override
	public void resetManuallyCoords() {

		manuallyXCoord = Player.FIELD_SIZE + 1;
		manuallyYCoord = Player.FIELD_SIZE + 1;
	}

	@Override
	public boolean clickIsDone() {
		if (manuallyXCoord < Player.FIELD_SIZE) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getCELL_SHOULD_BE_NEW() {
		return CELL_SHOULD_BE_NEW;
	}

	@Override
	public String getLAST_SHIP_IS_EXCESS_MESSAGE() {
		return LAST_SHIP_IS_EXCESS_MESSAGE;
	}
}
