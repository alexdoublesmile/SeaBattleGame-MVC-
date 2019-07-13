package com.plohoy.seabattle.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Field {

	private final int MAX_NUMBER_OF_ONE_DECK_SHIPS = 4;
	private final int MAX_NUMBER_OF_TWO_DECK_SHIPS = 3;
	private final int MAX_NUMBER_OF_THREE_DECK_SHIPS = 2;
	private final int MAX_NUMBER_OF_FOUR_DECK_SHIPS = 1;
	private int numberOfOneDeckShips;
	private int numberOfTwoDeckShips;
	private int numberOfThreeDeckShips;
	private int numberOfFourDeckShips;
	private ArrayList<Cell> fieldTerritory = new ArrayList<Cell>();
	private ArrayList<Cell> fieldFreeTerritory = new ArrayList<Cell>();
	private HashSet<Cell> differentAroundCells = new HashSet<Cell>();
	private ArrayList<Ship> manuallyBattleField = new ArrayList<Ship>();
	private ArrayList<Ship> battleField = new ArrayList<Ship>();
	private int fieldSize;
	private int countAroundCells;
	private int[] pattern;

	private Random random;
	private Ship tempShip;
	private Ship lastShip;
	private Cell lastCell;

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

	public boolean checkCellIsTheOne(int x, int y) {
		Cell tryCell = new Cell(x, y);
		for (Ship ship : battleField) {
			for (Cell cell : ship.getCells()) {
				if (cell.getXCoord() == tryCell.getXCoord() && cell.getYCoord() == tryCell.getYCoord()) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkCellIsNotTouchAnyShip(int x, int y) {
		Cell tryCell = new Cell(x, y);
		for (Ship ship : battleField) {
			if (tryCell.checkCellTouchDiagonal(ship.getCells())) {
				return false;
			}
		}
		return true;
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

	public boolean checkLastShipIsProper() {
		calcNumberOfShips();
		if (numberOfOneDeckShips <= MAX_NUMBER_OF_ONE_DECK_SHIPS && numberOfTwoDeckShips <= MAX_NUMBER_OF_TWO_DECK_SHIPS
				&& numberOfThreeDeckShips <= MAX_NUMBER_OF_THREE_DECK_SHIPS
				&& numberOfFourDeckShips <= MAX_NUMBER_OF_FOUR_DECK_SHIPS) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkLastShipIncreasedIsProper() {
		if (checkLastShipIncreasedByOneIsProper() || checkLastShipIncreasedByTwoIsProper()
				|| checkLastShipIncreasedByThreeIsProper()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkLastShipIncreasedByOneIsProper() {
		battleField.remove(tempShip);
		battleField.remove(lastShip);
		if (!(lastShip == null)) {
			lastShip.getCells().add(lastCell);

		}
		battleField.add(lastShip);
		calcNumberOfShips();
		if (numberOfOneDeckShips <= MAX_NUMBER_OF_ONE_DECK_SHIPS && numberOfTwoDeckShips <= MAX_NUMBER_OF_TWO_DECK_SHIPS
				&& numberOfThreeDeckShips <= MAX_NUMBER_OF_THREE_DECK_SHIPS
				&& numberOfFourDeckShips <= MAX_NUMBER_OF_FOUR_DECK_SHIPS) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkLastShipIncreasedByTwoIsProper() {
		battleField.remove(tempShip);
		battleField.remove(lastShip);
		lastShip.getCells().add(lastCell);
		lastShip.getCells().add(new Cell(lastCell.getXCoord() + 1, lastCell.getYCoord()));
		battleField.add(lastShip);
		calcNumberOfShips();
		if (numberOfOneDeckShips <= MAX_NUMBER_OF_ONE_DECK_SHIPS && numberOfTwoDeckShips <= MAX_NUMBER_OF_TWO_DECK_SHIPS
				&& numberOfThreeDeckShips <= MAX_NUMBER_OF_THREE_DECK_SHIPS
				&& numberOfFourDeckShips <= MAX_NUMBER_OF_FOUR_DECK_SHIPS) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkLastShipIncreasedByThreeIsProper() {
		battleField.remove(tempShip);
		battleField.remove(lastShip);
		lastShip.getCells().add(lastCell);
		lastShip.getCells().add(new Cell(lastCell.getXCoord() + 1, lastCell.getYCoord()));
		lastShip.getCells().add(new Cell(lastCell.getXCoord() + 2, lastCell.getYCoord()));
		battleField.add(lastShip);
		calcNumberOfShips();
		if (numberOfOneDeckShips <= MAX_NUMBER_OF_ONE_DECK_SHIPS && numberOfTwoDeckShips <= MAX_NUMBER_OF_TWO_DECK_SHIPS
				&& numberOfThreeDeckShips <= MAX_NUMBER_OF_THREE_DECK_SHIPS
				&& numberOfFourDeckShips <= MAX_NUMBER_OF_FOUR_DECK_SHIPS) {
			return true;
		} else {
			return false;
		}

	}

	public boolean patternIsProper() {
		calcNumberOfShips();
		if (numberOfOneDeckShips == MAX_NUMBER_OF_ONE_DECK_SHIPS && numberOfTwoDeckShips == MAX_NUMBER_OF_TWO_DECK_SHIPS
				&& numberOfThreeDeckShips == MAX_NUMBER_OF_THREE_DECK_SHIPS
				&& numberOfFourDeckShips == MAX_NUMBER_OF_FOUR_DECK_SHIPS) {
			return true;
		} else {
			return false;
		}
	}

	public void calcNumberOfShips() {

		int numberOfDecks = 0;
		numberOfOneDeckShips = 0;
		numberOfTwoDeckShips = 0;
		numberOfThreeDeckShips = 0;
		numberOfFourDeckShips = 0;
		for (Ship ship : battleField) {
			for (Cell cell : ship.getCells()) {
				numberOfDecks++;
			}
			switch (numberOfDecks) {
			case (1):
				numberOfOneDeckShips++;
				break;
			case (2):
				numberOfTwoDeckShips++;
				break;
			case (3):
				numberOfThreeDeckShips++;
				break;
			case (4):
				numberOfFourDeckShips++;
				break;
			default:
				break;
			}
			numberOfDecks = 0;
		}
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

	public Cell getLastCell() {
		return lastCell;
	}

	public void setLastCell(Cell lastCell) {
		this.lastCell = lastCell;
	}

	public Ship getLastShip() {
		return lastShip;
	}

	public void setLastShip(Ship lastShip) {
		this.lastShip = lastShip;
	}

	public Ship getTempShip() {
		return tempShip;
	}

	public void setTempShip(Ship tempShip) {
		this.tempShip = tempShip;
	}

	public HashSet<Cell> getDifferentAroundCells() {
		return differentAroundCells;
	}

	public int getCountAroundCells() {
		return countAroundCells;
	}
}
