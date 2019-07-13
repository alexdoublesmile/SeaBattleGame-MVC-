package com.plohoy.seabattle.interfaces;

import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.Labels;
import com.plohoy.seabattle.model.Shots;

public interface Player {

	final static int FIELD_SIZE = 10;

	final static int[] PATTERN = { 4, 3, 3, 2, 2, 2, 1, 1, 1, 1 };

//	void makeShot();

	Field getShips();

	Shots getShots();

	View getTheView();

	Labels getLabels();

	boolean isAI();

	String getName();

	int getYCoord();

	int getXCoord();

	void setCoords(Field playerShips);

	void createShipsAuto();

	void createField();
}
