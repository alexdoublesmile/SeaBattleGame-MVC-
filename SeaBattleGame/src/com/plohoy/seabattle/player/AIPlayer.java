package com.plohoy.seabattle.player;

import com.plohoy.seabattle.ai.AIShot;
import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.interfaces.View;
import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.Labels;
import com.plohoy.seabattle.model.Shots;

public class AIPlayer implements Player {

	private final String AI_NAME = "ÈÈ";
	private final int thePower;
	private final int MAX_AROUND_CELLS = 52;
	private Field ships;
	private Shots shots;
	private AIShot aIShot;
	private int xCoord;
	private int yCoord;

	public AIPlayer(int thePower) {

		this.thePower = thePower;
		shots = new Shots();
		aIShot = new AIShot(thePower, FIELD_SIZE, PATTERN);
	}

	@Override
	public void createShipsAuto() {
		if (thePower > 1) {
			do {
				ships = new Field(FIELD_SIZE, PATTERN);
				ships.setShipsAuto(thePower);
			} while (ships.getCountAroundCells() > MAX_AROUND_CELLS);
		} else {
			ships = new Field(FIELD_SIZE, PATTERN);
			ships.setShipsAuto(thePower);
		}
	}

	@Override
	public void setCoords(Field playerShips) {
		do {
			aIShot.aIMakesShot(shots, playerShips);
		} while (shots.shotSamePlace(aIShot.getXShotCoord(), aIShot.getYShotCoord()));
		xCoord = aIShot.getXShotCoord();
		yCoord = aIShot.getYShotCoord();
	}

	@Override
	public String getName() {
		return AI_NAME;
	}

	@Override
	public Field getShips() {
		return ships;
	}

	@Override
	public boolean isAI() {
		return true;
	}

	@Override
	public Shots getShots() {
		return shots;
	}

	@Override
	public int getXCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	@Override
	public int getYCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public AIShot getAIShot() {
		return aIShot;
	}

	@Override
	public View getTheView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Labels getLabels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createField() {
		// TODO Auto-generated method stub

	}
}
