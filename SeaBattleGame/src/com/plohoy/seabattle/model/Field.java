package com.plohoy.seabattle.model;

import java.util.*;

public class Field {

	private ArrayList<Ship> battleField = new ArrayList<Ship>();
	private int[] templateOfBattleField = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
	private Random random;

	public void setPattern(int[] pattern) {
		this.templateOfBattleField = pattern;
	}

	Field(int fieldSize) {	
		random = new Random();
		for(int i = 0; i < templateOfBattleField.length; i++) {
			Ship ship;
			do {
				int x = random.nextInt(fieldSize);
				int y = random.nextInt(fieldSize);
				int position = random.nextInt(2);
				ship = new Ship(x, y, templateOfBattleField[i], position, fieldSize);
			} while(ship.isShipOutOfField(0, fieldSize - 1) || isShipNotDetached(ship));
			battleField.add(ship);
		}
	}

	public ArrayList<Ship> getBattleField() {
		return battleField;
	}

	public boolean isShipNotDetached(Ship ctrlShip) {
		for(Ship ship : battleField) {
			if(ship.isShipNotDetached(ctrlShip)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkHit(int x, int y) {
		for(Ship ship : battleField) {
			if(ship.checkShipHit(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkAnyShipAlive() {
		for(Ship ship : battleField) {
			if(ship.isShipAlive()) {
				return true;
			}
		}
		return false;
	}
}
