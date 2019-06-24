package com.plohoy.seabattle.model;

import java.util.ArrayList;
import java.util.Objects;

public class Cell {

	private int xCoord, yCoord;
	private int cellLife = 1;
	
	Cell (int x, int y) {
//		System.out.println("Координаты ячейки: " + x + ", " + y);
		this.xCoord = x;
		this.yCoord = y;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
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
			for(int dx = -1; dx < 2; dx++) {				
				for(int dy = -1; dy < 2; dy++) {				
					if(ctrlCell.getxCoord() == cell.getxCoord() + dx &&
						ctrlCell.getyCoord() == cell.getyCoord() + dy) {
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
