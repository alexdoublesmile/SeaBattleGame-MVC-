package com.plohoy.seabattle.model;

import java.util.ArrayList;

public class Cell {

	private int xCoord, yCoord;
	private int cellLife = 1;
	
	Cell (int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}

	public int getXCoord() {
		return xCoord;
	}

	public int getYCoord() {
		return yCoord;
	}

	public int getCellLife() {
		return cellLife;
	}
	
	public boolean checkCellTouch(ArrayList<Cell> cells, Cell someCell) {		
		for(Cell cell : cells) {			
			for(int xCell = -1; xCell < 2; xCell++) {				
				for(int yCell = -1; yCell < 2; yCell++) {				
					if(someCell.getXCoord() == cell.getXCoord() + xCell &&
						someCell.getYCoord() == cell.getYCoord() + yCell) {
						return true;
					}
				}
			}		
		}
		return false;
	}
	
	public boolean checkCellHit(int x, int y) {	
		if (x == xCoord && y == yCoord) {
			cellLife = 0;
			return true;
		}
		return false;
	}
	
	public boolean checkCellAlive() {	return cellLife != 0; }

	@Override
	public boolean equals(Object obj) {
		if(obj == this) { return true; }
		if (obj == null || obj.getClass() != this.getClass()) { return false; }
		Cell cell = (Cell) obj;
		return cell.xCoord == this.xCoord &&
				 cell.yCoord == this.yCoord;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}	
}
