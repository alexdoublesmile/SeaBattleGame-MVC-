package com.plohoy.seabattle.model;

import com.plohoy.seabattle.ai.AIPlayer;

public class GameModel {

	private AIPlayer aIShoots;
	private Field playerShips;
	private Field opponentShips;
	private Shots playerShots;
	private Shots opponentShots;
	private Labels playerLabels;
	private Labels opponentLabels;

	public void createShipsAuto(int fieldSize) {
		playerShips = new Field();
		playerShips.setShipsAuto(fieldSize);
		opponentShips = new Field();
		opponentShips.setShipsAuto(fieldSize);
	}
	
	public void createShipsManually(int x, int y, int fieldSize) {
		playerShips = new Field();
		playerShips.setShipsAuto(fieldSize);
		opponentShips = new Field();
		opponentShips.setShipsManually(x, y, fieldSize);
	}
	
	public void createShots() {
		playerShots = new Shots();
		opponentShots = new Shots();
	}
	
	public void createLabels() {
		playerLabels = new Labels();
		opponentLabels = new Labels();
	}
	public void createAI(int power) {
		aIShoots = new AIPlayer(power);
	}
	
	public Field getPlayerShips() {
		return playerShips;
	}
	
	public Field getOpponentShips() {
		return opponentShips;
	}
	
	public Shots getPlayerShots() {
		return playerShots;
	}
	
	public Shots getOpponentShots() {
		return opponentShots;
	}
	
	public Labels getPlayerLabels() {
		return playerLabels;
	}
	
	public Labels getOpponentLabels() {
		return opponentLabels;
	}

	public AIPlayer getAIShoots() {
		return aIShoots;
	}
	
	public AIPlayer aIShoots(int fieldSize, Shots opponentShots, Field playerShips) {
		int n = 1;
		do { 
			aIShoots.aIMakesShot(fieldSize, opponentShots, playerShips); 
			System.out.println("----------------------------------------------------------");
			System.out.println("----------------------------------------------------------");
			System.out.println(n + " попытка выстрела: (" + (aIShoots.getXShotCoord() + 1) + ", " + (aIShoots.getYShotCoord() + 1) + ") ");
			n++;
		}
		while (opponentShots.shotSamePlace(aIShoots.getXShotCoord(), 
											aIShoots.getYShotCoord()));
		System.out.println("координаты выстрела: (" + (aIShoots.getXShotCoord() + 1) + ", " + (aIShoots.getYShotCoord() + 1) + ") ");
		System.out.println("----------------------------------------------------------");
		return aIShoots;
	}
}
