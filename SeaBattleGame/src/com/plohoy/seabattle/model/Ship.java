package com.plohoy.seabattle.model;
import java.util.ArrayList;
import java.util.HashSet;

public class Ship {

	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Cell> aroundCells = new ArrayList<Cell>();

	private HashSet<Cell> killAroundCellsDuplicate;
	
	
//	public void createAroundCells() {
//		for(Cell cell : cells) {
//			for(int xCell = -1; xCell < 2; xCell++) {
//				for(int yCell = -1; yCell < 2; yCell++) {
//					if(!(cell.getXCoord() == xCell && cell.getYCoord() == yCell))
//					aroundCells.add(new Cell(xCell, yCell));
//				}
//			}
//		}
//	}
	
	
//	private boolean justBeenHit = false;
//	
//	public boolean isJustBeenHit() {
//		return justBeenHit;
//	}
//
//	public void setJustBeenHit(boolean justBeenHit) {
//		this.justBeenHit = justBeenHit;
//	}

	Ship(int x, int y, int lenght, int position, int fieldSize) {
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
		
		for(Cell cell : cells) {
			for(int i = 0; i < aroundCells.size(); i++) {
				if((cell.getXCoord() == aroundCells.get(i).getXCoord() &&
				  cell.getYCoord() == aroundCells.get(i).getYCoord())) {
					aroundCells.remove(aroundCells.get(i));
				}
			}
		}
		
		killAroundCellsDuplicate = new HashSet<Cell>(aroundCells);
		aroundCells.clear();
		aroundCells.addAll(killAroundCellsDuplicate);
		
//		System.out.println("------------------------------------------------");	
//		System.out.println("Проверка Корабля");
//		int n = 1;
//		for(Cell cell : cells) {
//			System.out.println("Корабль: координаты ячейки № " + n + ": " + cell.getXCoord() + ", " + cell.getYCoord());
//			n++;
//		}
//		System.out.println("------------------------------------------------");	
//		
//		System.out.println("------------------------------------------------");	
//		System.out.println("Проверка окружения Корабля");
//		int q = 1;
//		for(Cell cell : aroundCells) {
//			System.out.println("Координаты ячейки № " + q + ": " + cell.getXCoord() + ", " + cell.getYCoord());
//			q++;
//		}
//		System.out.println("------------------------------------------------");
	}
	
	public ArrayList<Cell> getCells() {
		return cells;
	}

	public ArrayList<Cell> getAroundCells() {
		return aroundCells;
	}

	public boolean isShipOutOfField(int bottom, int top) {
		for(Cell cell : cells) {
			if(cell.getXCoord() < bottom || cell.getXCoord() > top ||
			   cell.getYCoord() < bottom || cell.getYCoord() > top	) {
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
