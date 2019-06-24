package com.plohoy.seabattle.model;

public class SeaBattleModel {
	
	Field playerShips;
	Field opponentShips;
	Shots playerShots;
	Shots opponentShots;
	Labels playerLabels;
	Labels opponentLabels;
	
//	private boolean gameOver = false;
	
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
	
//	public boolean isGameOver() {
//		return gameOver;
//	}
//
//	public void setGameOver(boolean gameOver) {
//		this.gameOver = gameOver;
//	}
//	
//	public boolean getGameOver() {
//		return gameOver;
//	}
}
