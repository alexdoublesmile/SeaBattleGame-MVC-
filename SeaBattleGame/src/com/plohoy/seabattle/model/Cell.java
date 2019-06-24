package com.plohoy.seabattle.model;

import java.util.ArrayList;

public class Cell {

	private int xCoord, yCoord;
	private int cellLife = 1;
	
	Cell (int x, int y) {
//		System.out.println("Координаты ячейки: " + x + ", " + y);
		this.xCoord = x;
		this.yCoord = y;
	}

	public int getXCoord() {
		return xCoord;
	}

	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getYCoord() {
		return yCoord;
	}

	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getCellLife() {
		return cellLife;
	}
	
	public void setCellLife(int i) {
		this.cellLife = i;
	}
	
	public boolean isCellNotDetached(ArrayList<Cell> cells, Cell ctrlCell) {		
		for(Cell cell : cells) {			
			for(int xCell = -1; xCell < 2; xCell++) {				
				for(int yCell = -1; yCell < 2; yCell++) {				
					if(ctrlCell.getXCoord() == cell.getXCoord() + xCell &&
						ctrlCell.getYCoord() == cell.getYCoord() + yCell) {
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
	
	public boolean isCellAlive() {	return cellLife != 0; }

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
