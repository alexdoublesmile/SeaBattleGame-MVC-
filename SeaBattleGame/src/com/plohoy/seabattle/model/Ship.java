package com.plohoy.seabattle.model;
import java.util.ArrayList;
import java.util.HashSet;

public class Ship {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Cell> aroundCells = new ArrayList<Cell>();
	private HashSet<Cell> removeDuplicateCellsFromTerritory;

	Ship(int x, int y, int lenght, int position, int fieldSize) {

		createShip(x, y, lenght, position);
		createShipTerritory(fieldSize);	
	}
	
	public void createShip(int x, int y, int lenght, int position) {
		for(int i = 0; i < lenght; i++) {
			cells.add(new Cell(x + i*((position == 1)? 0 : 1),
							   y + i*((position == 1)? 1 : 0)));
		}
	}
	
	public void createShipTerritory(int fieldSize) {
		for(Cell cell : cells) {
			for(int xCell = -1; xCell < 2; xCell++) {
				for(int yCell = -1; yCell < 2; yCell++) {
					if(!(cell.getXCoord() == cell.getXCoord() + xCell && 
						cell.getYCoord() == cell.getYCoord() + yCell ||
						 (cell.getXCoord() + xCell < 0 || cell.getXCoord() + xCell > fieldSize - 1) ||
						 (cell.getYCoord() + yCell < 0 || cell.getYCoord() + yCell > fieldSize - 1)
						 ))
					aroundCells.add(new Cell(cell.getXCoord() + xCell, cell.getYCoord() + yCell));
				}
			}
		}
		removeShipCellsFromTerritory();
		removeDuplicateCellsFromTerritory();
	}
	
	public void removeShipCellsFromTerritory() {
		for(Cell cell : cells) {
			for(int i = 0; i < aroundCells.size(); i++) {
				if((cell.getXCoord() == aroundCells.get(i).getXCoord() &&
				  cell.getYCoord() == aroundCells.get(i).getYCoord())) {
					aroundCells.remove(aroundCells.get(i));
				}
			}
		}
	}
	
	public void removeDuplicateCellsFromTerritory() {
		removeDuplicateCellsFromTerritory = new HashSet<Cell>(aroundCells);
		aroundCells.clear();
		aroundCells.addAll(removeDuplicateCellsFromTerritory);
	}
	
	public boolean checkShipOutOfField(int bottom, int top) {
		for(Cell cell : cells) {
			if(cell.getXCoord() < bottom || cell.getXCoord() > top ||
			   cell.getYCoord() < bottom || cell.getYCoord() > top	) {
				return true;
			}
		}
		return false;
	}

	public boolean checkShipTouch(Ship ctrlShip) {		
		for (Cell cell : cells) {		
			if (cell.checkCellTouch(ctrlShip.getCells(), cell)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkShipHit(int x, int y) {		
		for(Cell cell : cells) {
			if(cell.checkCellHit(x, y)){
				return true;				
			}
		}
		return false;
	}
	
	public boolean checkShipAlive() {
		for(Cell cell : cells) {
			if(cell.checkCellAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Cell> getCells() {
		return cells;
	}

	public ArrayList<Cell> getAroundCells() {
		return aroundCells;
	}
}
