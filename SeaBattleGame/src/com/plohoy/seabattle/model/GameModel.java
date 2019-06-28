package com.plohoy.seabattle.model;

import com.plohoy.seabattle.ai.AIModel;

public class GameModel {

	private AIModel aIShoots;
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
		aIShoots = new AIModel(power);
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

	public AIModel getAIShoots() {
		return aIShoots;
	}
	
	public AIModel aIShoots(int fieldSize) {
		do { aIShoots.aIMakesShot(fieldSize); }
		while (opponentShots.shotSamePlace(aIShoots.getXShotCoord(), 
											aIShoots.getYShotCoord()));
		return aIShoots;
	}
}
