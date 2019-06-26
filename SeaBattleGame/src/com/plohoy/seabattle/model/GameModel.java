package com.plohoy.seabattle.model;

import com.plohoy.seabattle.ai.AIMakeShot;

public class GameModel {

	private AIMakeShot aIShoots;
	private Field playerShips;
	private Field opponentShips;
	private Shots playerShots;
	private Shots opponentShots;
	private Labels playerLabels;
	private Labels opponentLabels;

	public void createShips(int fieldSize) {
		playerShips = new Field(fieldSize);
		opponentShips = new Field(fieldSize);
	}
	
	public void createShots() {
		playerShots = new Shots();
		opponentShots = new Shots();
	}
	
	public void createLabels() {
		playerLabels = new Labels();
		opponentLabels = new Labels();
	}
	public void createAI() {
		aIShoots = new AIMakeShot();
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

	public AIMakeShot getAIShoots() {
		return aIShoots;
	}
	
	public AIMakeShot aIShootsRandomly(int fieldSize) {
		do { aIShoots.makeRandomShot(fieldSize); }
		while (opponentShots.shotSamePlace(aIShoots.getXShotCoord(), 
											aIShoots.getYShotCoord()));
		return aIShoots;
	}
}
