package com.plohoy.seabattle.view;

import java.util.Scanner;

import com.plohoy.seabattle.model.*;

public class SeaBattleConsoleView implements SeaBattleView{

	private int battlefieldSize = 10;
	private int xShotCoord;
	private int yShotCoord;
	private int playAgainAnswer;
	private int num;

	private int [] battleFieldLegend;
	private String [][] playerBattleField;
	private String [][] opponentBattleField;
	private final String LIVE_CELL = "O";
	private final String WOUNDED_CELL = "%";
	private final String DEAD_CELL = "X";
	private final String SHOT_CELL = "*";
	private final String LABEL_CELL = "?";
	private final String WINNER_MESSAGE = "Поздравляем! Вражеский флот уничтожен! Вы победили!!!";
	private final String LOOSER_MESSAGE = "Ваш флот уничтожен! Это поражение...";
	private final String AGAIN_MESSAGE = "Желаете сыграть снова?";
	private final String REPEAT_SHOT_MESSAGE = "Вы сюда уже стреляли. Есть смысл выстрелить в другое место..";
	private final String SINK_THE_SHIP_MESSAGE = "Вы потопили вражеский корабль!";
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
	
	public SeaBattleConsoleView() {
//		battleFieldLegend = new String[] {"A","B","C","D","E","F","G","H","I","J"};
		battleFieldLegend = new int[battlefieldSize];
		playerBattleField = new String[battlefieldSize][battlefieldSize];
		opponentBattleField = new String[battlefieldSize][battlefieldSize];
		
		for(int x = 0; x < battlefieldSize; x++) {
			battleFieldLegend[x] = x + 1;
		}
		
		for(int y = 0; y < battlefieldSize; y++) {
			for(int x = 0; x < battlefieldSize; x++) {
				playerBattleField[x][y] = " ";
			}
		}
		
		for(int y = 0; y < battlefieldSize; y++) {
			for(int x = 0; x < battlefieldSize; x++) {
				opponentBattleField[x][y] = " ";
			}
		}	
	}
	
	@Override
	public void viewGame(Field playerField, Shots playerShots, Labels playerLabels, 
			Field opponentField, Shots opponentShots, Labels opponentLabels) {
		
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
		System.out.println("--- My field ---");
		
		drawShots(playerBattleField, opponentShots );
		
		drawShips(playerBattleField, playerShips, true);
		drawBackground(playerBattleField);
	}
	
	public void paintOpponentView() {
		System.out.println("");
		System.out.println("--- Opponent field ---");
		drawShots(opponentBattleField, playerShots);
		drawShips(opponentBattleField, opponentShips, false);
//		drawLabels(playerLabels);
		drawBackground(opponentBattleField);
	}
	
	public void drawShips(String[][] field, Field ships, boolean isVisible) {
		for(Ship ship : ships.getBattleField()) {
			if(ship.isShipAlive()) {
				for(Cell cell : ship.getCells()) {
					if(isVisible) {
						drawTheCell(field, cell, LIVE_CELL);
					}
					if(!cell.isCellAlive()) {
						drawTheCell(field, cell, WOUNDED_CELL);
					} 
				}
			} else {
				for(Cell cell : ship.getCells()) {
					drawTheCell(field, cell, DEAD_CELL);
				}				
			}			
		}
	}
	
	public void drawTheCell(String[][] field, Cell cell, String typeOfCell) {
		field[cell.getXCoord()][cell.getYCoord()] = typeOfCell;	
	}
	
	public void drawShots(String[][] field, Shots shots) {
		for(Shot shot : shots.getShots()) {
			drawTheShot(field, shot);
		}
	}
	
	public void drawTheShot(String[][] field, Shot shot) {
		field[shot.getXCoord()][shot.getYCoord()] = SHOT_CELL;	
	}

	public void drawLabels(Labels labels) {
		for(Label label : labels.getLabels()) {
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
		for(int y = 0; y < battlefieldSize; y++) {
			if(y < 9) {
				System.out.print(" ");
			}
			System.out.print(num++ + "|");
			for(int x = 0; x < battlefieldSize; x++) {
				System.out.print(" ");
				System.out.print(field[x][y] + "  ");
			}
			System.out.println("|");
			if(y < battlefieldSize - 1) {
				System.out.println("");
			}
		}
		drawBottomBorder();
		System.out.println("");
	}

	public void drawLegend() {
		System.out.print(" ");
		for(int x = 0; x < battlefieldSize; x++) {
			if(x < 10) {
				System.out.print(" ");
			}
			System.out.print("  ");
			System.out.print(battleFieldLegend [x]);
		}	
		System.out.println("");
		drawBottomBorder();
	}
	
	public void drawBottomBorder() {
		System.out.print("   ");
		for(int i = 0; i < battlefieldSize * 2; i++) {
			System.out.print("--");
		}
	}
	
	public void makeShot() {
//		System.out.println(MAKE_CHOICE);
//		if(makeChoice() == 1) {
//			System.out.println(MAKE_YOUR_SHOT);
//			System.out.println(XCOORD_ENTER);
//			setXShotCoord(inputAnyString());
//			System.out.println(YCOORD_ENTER);
//			setYShotCoord(inputAnyString());
//		} else {
//			System.out.println(LABEL_XCOORD_ENTER);
//		}
		System.out.println(MAKE_YOUR_SHOT);
		System.out.println(XCOORD_ENTER);
		setXShotCoord(inputAnyString());
		System.out.println(YCOORD_ENTER);
		setYShotCoord(inputAnyString());
	}
	
	public int makeChoice() {
		String s;
		System.out.println(TAKE_THE_SHOT);
		System.out.println(TAKE_THE_LABEL);
		do {
			System.out.println(TAKE_THE_ONE);
			s = inputAnyString();	
		}
		while(!(s.equals("1") || s.equals("2")));
		return Integer.parseInt(s);
	}
	
	public void setXShotCoord(String s) {
		try {
			xShotCoord = Integer.parseInt(s) - 1;
		} catch(NumberFormatException e) {
			displayMessage(NUMBER_EXCEPTION_MESSAGE);
			setXShotCoord(inputAnyString());
		}
		if((xShotCoord + 1) < 1 || (xShotCoord + 1) > battlefieldSize) {
			displayMessage(INCORRECT_COORD);
			setXShotCoord(inputAnyString());
		}
	}

	public void setYShotCoord(String s) {
		try {
			yShotCoord = Integer.parseInt(s) - 1;
		} catch(NumberFormatException e) {
			displayMessage(NUMBER_EXCEPTION_MESSAGE);
			setYShotCoord(inputAnyString());
		}
		if((yShotCoord + 1) < 1 || (yShotCoord + 1) > battlefieldSize) {
			displayMessage(INCORRECT_COORD);
			setYShotCoord(inputAnyString());
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
	public void setBattlefieldSize(int battlefieldSize) {
		this.battlefieldSize = battlefieldSize; 
		
	}

	@Override
	public int getBattlefieldSize() {
		return battlefieldSize;
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
	public String getWINNER_MESSAGE() {
		return WINNER_MESSAGE;
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
}
