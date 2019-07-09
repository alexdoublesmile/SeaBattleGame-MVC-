package com.plohoy.seabattle.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Field {

	private ArrayList<Cell> fieldTerritory = new ArrayList<Cell>();
	private ArrayList<Cell> fieldFreeTerritory = new ArrayList<Cell>();
	private HashSet<Cell> differentAroundCells = new HashSet<Cell>();
	private ArrayList<Ship> battleField = new ArrayList<Ship>();
	private int fieldSize;
	private int countAroundCells;
	private int[] pattern;

	private Random random;

	public Field(int fieldSize, int[] pattern) {
		this.fieldSize = fieldSize;
		this.pattern = pattern;
	}

	public void setShipsAuto() {
		random = new Random();
		for (int i = 0; i < pattern.length; i++) {
			Ship ship;
			do {
				int x = random.nextInt(fieldSize);
				int y = random.nextInt(fieldSize);
				int position = random.nextInt(2);
				ship = new Ship(x, y, pattern[i], position, fieldSize);
			} while (ship.checkShipOutOfField(0, fieldSize - 1) || checkShipTouch(ship));
			battleField.add(ship);
		}
	}

	public void setShipsAuto(int power) {
		random = new Random();
		for (int i = 0; i < pattern.length; i++) {
			Ship ship;
			do {
				int x = random.nextInt(fieldSize);
				int y = random.nextInt(fieldSize);
				int position = random.nextInt(2);
				ship = new Ship(x, y, pattern[i], position, fieldSize);
			} while (ship.checkShipOutOfField(0, fieldSize - 1) || checkShipTouch(ship));
			battleField.add(ship);
		}

		this.countAroundCells();
		System.out.println("------------------------------");
		System.out.println("количество клеток территории: " + countAroundCells);
	}

	public HashSet<Cell> getDifferentAroundCells() {
		return differentAroundCells;
	}

	public int getCountAroundCells() {
		return countAroundCells;
	}

	public void setShipsManually(int x, int y) {
		Ship ship = new Ship(x, y, fieldSize);
		battleField.add(ship);
		if (ship.checkShipOutOfField(0, fieldSize - 1) || checkShipTouch(ship)) {
			battleField.remove(ship);
		}
	}

	public void initializeAllTerritory() {
		for (int x = 0; x < fieldSize; x++) {
			for (int y = 0; y < fieldSize; y++) {
				fieldTerritory.add(new Cell(x, y));
			}
		}
	}

	public void initializeFreeTerritory() {
		fieldFreeTerritory = fieldTerritory;
		for (Ship ship : battleField) {
			for (Cell shipCell : ship.getCells()) {
				for (Cell cell : fieldFreeTerritory) {
					if (cell.getXCoord() == shipCell.getXCoord() && cell.getYCoord() == shipCell.getYCoord()) {
						fieldFreeTerritory.remove(cell);
					}
				}

			}
		}
	}

	public int countFreeCells() {
		int count = 0;
		for (Cell cell : fieldTerritory) {
			count++;
		}
		return count;
	}

	public void countAroundCells() {
		int count = 0;
		for (Ship ship : battleField) {
			for (Cell cell : ship.getAroundCells()) {
				differentAroundCells.add(cell);
			}
		}
		countAroundCells = differentAroundCells.size();
	}

	public boolean checkShipTouch(Ship someShip) {
		for (Ship ship : battleField) {
			if (ship.checkShipTouch(someShip)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkHit(int x, int y) {
		for (Ship ship : battleField) {
			if (ship.checkShipHit(x, y)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkAnyShipAlive() {
		for (Ship ship : battleField) {
			if (ship.checkShipAlive()) {
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

	public int[] getPattern() {
		return pattern;
	}
}
