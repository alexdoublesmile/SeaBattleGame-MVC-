package com.plohoy.seabattle.view;

import com.plohoy.seabattle.model.*;

public class SeaBattleConsoleView implements SeaBattleView{

	private int battlefieldSize = 10;
	private int coordX = 1;
	private int coordY = 1;

	private int [] battleFieldLegend;
	private String [][] playerBattleField;
	private String [][] opponentBattleField;

	
	private final int FIELD_PX_SIZE = 10;
	private final int CELL_PX_SIZE = FIELD_PX_SIZE / battlefieldSize;

	private final String WINNER_MESSAGE = "Поздравляем! Вражеский флот уничтожен! Вы победили!!!";
	private final String LOOSER_MESSAGE = "Ваш флот уничтожен! Это поражение...";
	private final String AGAIN_MESSAGE = "Желаете сыграть снова?";
	private final String REPEAT_SHOT_MESSAGE = "Вы сюда уже стреляли. Есть смысл выстрелить в другое место..";
	private final String SINK_THE_SHIP_MESSAGE = "Вы потопили вражеский корабль!";
	private int playAgainAnswer;
	

	Field playerShips;
	Field opponentShips;	
	Shots playerShots;
	Shots opponentShots;	
	Labels playerLabels;
	Labels opponentLabels;	
	
	public SeaBattleConsoleView() {
		System.out.println("Консольный вид...");
		
//		battleFieldLegend = new String[] {"A","B","C","D","E","F","G","H","I","J"};
		battleFieldLegend = new int[battlefieldSize + 1];
		playerBattleField = new String[battlefieldSize][battlefieldSize];
		opponentBattleField = new String[battlefieldSize][battlefieldSize];
		
		for(int x = 0; x < battlefieldSize; x++) {
			battleFieldLegend[x] = x;
		}
		
		for(int y = 0; y < battlefieldSize; y++) {
			for(int x = 0; x < battlefieldSize; x++) {
				playerBattleField[x][y] = "*";
			}
		}
		
		for(int y = 0; y < battlefieldSize; y++) {
			for(int x = 0; x < battlefieldSize; x++) {
				opponentBattleField[x][y] = "*";
			}
		}	
	}

	@Override
	public void setVisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInvisible() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots, Labels opponentLabels) {
		
		playerShips = playerField;
		this.playerShots = playerShots;
		this.playerLabels = playerLabels;
		opponentShips = opponentField;
		this.opponentShots = opponentShots;
		this.opponentLabels = opponentLabels;		

		repaintOpponentView();
		repaintPlayerView();
		
	}
	
	@Override
	public void repaintPlayerView() {
		System.out.println("");
		System.out.println("--- My field ---");
		drawLegend();
		System.out.println();
		for(int y = 0; y < battlefieldSize; y++) {
			if(y < 9) {
				System.out.print(" ");
			}
			System.out.print(coordY++ + "|");
			for(int x = 0; x < battlefieldSize; x++) {
				if(!(x == 0 && coordY > 10)) {
					System.out.print(" ");
				} else {
					System.out.print(" ");
				}
				System.out.print(playerBattleField[x][y] + "  ");
			}
			System.out.println("|");
			if(y < battlefieldSize - 1) {
				System.out.println("");
			}
		}
		System.out.print("   ");
		for(int i = 0; i < battlefieldSize * 2; i++) {
			System.out.print("--");
		}
		coordY = 1;
		System.out.println("");
	}

	@Override
	public void repaintOpponentView() {
		System.out.println("");
		System.out.println("--- Opponent field ---");
		drawLegend();
		System.out.println();
		for(int y = 0; y < battlefieldSize; y++) {
			if(y < 9) {
				System.out.print(" ");
			}
			System.out.print(coordY++ + "|");
			for(int x = 0; x < battlefieldSize; x++) {
				if(!(x == 0 && coordY > 10)) {
					System.out.print(" ");
				} else {
					System.out.print(" ");
				}
				System.out.print(opponentBattleField[x][y] + "  ");
			}
			System.out.println("|");
			if(y < battlefieldSize - 1) {
				System.out.println("");
			}
		}
		System.out.print("   ");
		for(int i = 0; i < battlefieldSize * 2; i++) {
			System.out.print("--");
		}
		coordY = 1;
		System.out.println("");	
	}

	public void drawLegend() {
		System.out.print(" ");
		for(int x = 0; x < battlefieldSize; x++) {
			if(x < 10) {
				System.out.print(" ");
			}
			System.out.print("  ");
			System.out.print(battleFieldLegend [x] + 1);
		}	
		System.out.println("");
		System.out.print("   ");
		for(int i = 0; i < battlefieldSize * 2; i++) {
			System.out.print("--");
		}
	}

	@Override
	public int checkIsItLabel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkIsItShot() {
		// TODO Auto-generated method stub
		return 0;
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
	public int getCELL_PX_SIZE() {
		return CELL_PX_SIZE;
	}

	@Override
	public int getFIELD_PX_SIZE() {
		return FIELD_PX_SIZE;
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
}
