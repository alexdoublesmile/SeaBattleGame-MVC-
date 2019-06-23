package com.plohoy.seabattle.model;
import java.util.ArrayList;
import java.util.Random;

public class Ship {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
	
	Ship(int x, int y, int lenght, int position) {
//		System.out.println("------------------------------------------------");	
//		System.out.println("начальные координаты корабля: " + x + ", " + y);
//		System.out.println("размер корабля: " + lenght);
//		if(position == 1) {
//			System.out.println("корабль расположен вертикально");
//		} else {
//			System.out.println("корабль расположен горизонтально");
//		}
//		
//		System.out.println("------------------------------------------------");
		for(int i = 0; i < lenght; i++) {
			cells.add(new Cell(x + i*((position == 1)? 0 : 1),
							   y + i*((position == 1)? 1 : 0)));
		}		

//		System.out.println("------------------------------------------------");	
//		System.out.println("Проверка Корабля");
//		int n = 1;
//		for(Cell cell : cells) {
//			System.out.println("Корабль: координаты ячейки № " + n + ": " + cell.getxCoord() + ", " + cell.getyCoord());
//			n++;
//		}
//		System.out.println("------------------------------------------------");	
	}
	
	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public boolean isShipOutOfField(int bottom, int top) {
		for(Cell cell : cells) {
			if(cell.getxCoord() < bottom || cell.getxCoord() > top ||
			   cell.getyCoord() < bottom || cell.getyCoord() > top	) {
				return true;
			}
		}
		return false;
	}

	public boolean isShipNotDetached(Ship ctrlShip) {		
		for (Cell cell : cells) {		
			if (cell.isCellNotDetached(ctrlShip.getCells(), cell)) {
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
	
	public boolean isShipAlive() {
		for(Cell cell : cells) {
			if(cell.isCellAlive()) {
				return true;
			}
		}
		return false;
	}
}
