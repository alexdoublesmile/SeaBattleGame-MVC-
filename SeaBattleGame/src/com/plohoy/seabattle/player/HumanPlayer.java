package com.plohoy.seabattle.player;

import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.interfaces.View;
import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.Labels;
import com.plohoy.seabattle.model.Shots;

public class HumanPlayer implements Player {

	private String name;
	private Field ships;
	private Shots shots;
	private Labels labels;
	private int xCoord;
	private int yCoord;
	private boolean createShipsManually;

	View theView;

	public HumanPlayer(View theView, int createShipsManually, String name) {

		this.theView = theView;
		this.name = name;
		setCreateShipsManually(createShipsManually);

		shots = new Shots();
		labels = new Labels();

	}

	public boolean isCreateShipsManually() {
		return createShipsManually;
	}

	public void setCreateShipsManually(int createShipsManually) {
		if (createShipsManually == 1) {
			this.createShipsManually = true;
		}
		this.createShipsManually = false;
	}

	@Override
	public void createField() {
		ships = new Field(FIELD_SIZE, PATTERN);
	}

	@Override
	public void createShipsAuto() {
		ships = new Field(FIELD_SIZE, PATTERN);
		ships.setShipsAuto();
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
