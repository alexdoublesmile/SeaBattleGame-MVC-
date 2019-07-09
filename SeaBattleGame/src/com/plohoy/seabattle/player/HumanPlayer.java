package com.plohoy.seabattle.player;

import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.interfaces.View;
import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.Labels;
import com.plohoy.seabattle.model.Shots;

public class HumanPlayer implements Player {

	private String name;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Field ships;
	private Shots shots;
	private Labels labels;
	private int xCoord;
	private int yCoord;

	private int createShipsManually;

	View theView;

	public HumanPlayer(View theView, int createShipsManually, String name) {

		this.theView = theView;
		this.createShipsManually = createShipsManually;
		this.name = name;

		shots = new Shots();
		labels = new Labels();

	}

	@Override
	public void createShips() {
		if (createShipsManually == 0) {
			createShipsAuto();
		} else {
			createShipsManually(xCoord, yCoord);
		}
	}

	public void createShipsAuto() {
		ships = new Field(FIELD_SIZE, PATTERN);
		ships.setShipsAuto();
	}

	public void createShipsManually(int x, int y) {
		ships = new Field(FIELD_SIZE, PATTERN);
		ships.setShipsManually(x, y);
	}

	@Override
	public View getTheView() {
		return theView;
	}

	@Override
	public Field getShips() {
		return ships;
	}

	@Override
	public Shots getShots() {
		return shots;
	}

	@Override
	public Labels getLabels() {
		return labels;
	}

	@Override
	public boolean isAI() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getYCoord() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getXCoord() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCoords(Field playerShips) {
		// TODO Auto-generated method stub

	}

//	private String name;
//	private String password;
//	private String email;
//
//	private int age;
//
//	private int score;
//	private int scoreRecord;

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public int getScore() {
//		return score;
//	}
//
//	public void setScore(int score) {
//		this.score = score;
//	}
//
//	public int getScoreRecord() {
//		return scoreRecord;
//	}
//
//	public void setScoreRecord(int scoreRecord) {
//		this.scoreRecord = scoreRecord;
//	}

}
