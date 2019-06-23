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
	
}
