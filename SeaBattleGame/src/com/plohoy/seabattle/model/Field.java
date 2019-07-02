package com.plohoy.seabattle.model;

import java.util.*;

public class Field {

	private ArrayList<Ship> battleField = new ArrayList<Ship>();
	private int[] pattern = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
	private Random random;

	public Field() {	
	}
	
	public void setShipsAuto(int fieldSize) {
		random = new Random();
		for(int i = 0; i < pattern.length; i++) {
			Ship ship;
			do {
				int x = random.nextInt(fieldSize);
				int y = random.nextInt(fieldSize);
				int position = random.nextInt(2);
				ship = new Ship(x, y, pattern[i], position, fieldSize);
			} while(ship.checkShipOutOfField(0, fieldSize - 1) || checkShipTouch(ship));
			battleField.add(ship);
		}
	}

	public void setShipsManually(int x, int y, int fieldSize) {
		Ship ship = new Ship(x, y, fieldSize);
		battleField.add(ship);
		if(ship.checkShipOutOfField(0, fieldSize - 1) || checkShipTouch(ship)) {
			battleField.remove(ship);
		}
	}

	public boolean checkShipTouch(Ship someShip) {
		for(Ship ship : battleField) {
			if(ship.checkShipTouch(someShip)) {
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
			if(ship.checkShipAlive()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Ship> getBattleField() {
		return battleField;
	}

	public void setPattern(int[] pattern) {
		this.pattern = pattern;
	}
}
