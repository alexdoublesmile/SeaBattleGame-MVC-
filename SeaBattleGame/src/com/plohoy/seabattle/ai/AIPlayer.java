package com.plohoy.seabattle.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import com.plohoy.seabattle.model.Cell;
import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.GameModel;
import com.plohoy.seabattle.model.Ship;
import com.plohoy.seabattle.model.Shot;
import com.plohoy.seabattle.model.Shots;
import com.plohoy.seabattle.player.Player;
import com.plohoy.seabattle.view.Game3DView;

public class AIPlayer implements Player {
	
	private Shots aIShots;
	private Field playerShips;

	private ArrayList<Cell> cellTerritory = new ArrayList<Cell>();
	private HashSet<Cell> removeDuplicatesFromCellTerritory;
	private int xShotCoord;
	private int yShotCoord;
	private boolean coordsAreReady = false;
	private Cell lastCell;

	private boolean mainAxisIsX;
	private boolean mainAxisIsY;
	private int randomIndex;
	private int thePower = 0;
	private int score;

	private Random random = new Random();
	
	public AIPlayer(int power) {
		
		this.thePower = power;
	}
	
	public int getXShotCoord() {
		return xShotCoord;
	}
	
	public int getYShotCoord() {
		return yShotCoord;
	}
	
	public void setLastCell(Cell lastCell) {
		this.lastCell = lastCell;
	}
	
	public void aIMakesShot(int fieldSize, Shots opponentShots, Field playerShips) {
		this.playerShips = playerShips;
		this.aIShots = opponentShots;
		switch(thePower) {
		case(0):
			xShotCoord = random.nextInt(fieldSize);
			yShotCoord = random.nextInt(fieldSize);
			break;
		case(1):
			coordsAreReady = false;
			smartShot(fieldSize);
			if (!coordsAreReady) {
				
				xShotCoord = random.nextInt(fieldSize);
				yShotCoord = random.nextInt(fieldSize);
				
				System.out.println("----------------------------------------------------------");
				System.out.println("------------------- сформированы координаты рандом: (" + (xShotCoord + 1) + ", " + (yShotCoord + 1) + ")");
				
			}
			break;
		case(2):
			System.out.println("------------------------ лучший выстрел (пока нет) ----------------------------------");
			xShotCoord = random.nextInt(fieldSize);
			yShotCoord = random.nextInt(fieldSize);
			
//			amazingShot(fieldSize);
			break;
		default:
			;
		}
	}

	public void smartShot(int fieldSize) {		
		checkShipsTerritory();
		for(Ship ship : playerShips.getBattleField()) {
			if(ship.checkShipIsShotDown()) {
				if(moreThanOneCellHit(ship)) {

					System.out.println("----------------------------------------------------------");
					System.out.println("---------------------- подбито несколько палуб ---------");
					
					whatIsMainAxis(ship, fieldSize);
					setExtremeShotDownCellsTerritoty(ship, fieldSize);
					
					randomIndex = new Random().nextInt(cellTerritory.size());
					xShotCoord = cellTerritory.get(randomIndex).getXCoord();
					yShotCoord = cellTerritory.get(randomIndex).getYCoord();
					coordsAreReady = true;
					cellTerritory.clear();
					
					
					System.out.println("----------------------------------------------------------");
					System.out.println("------------------- сформированы умные координаты: (" + (xShotCoord + 1) + ", " + (yShotCoord + 1) + ")");
				
				} else {

					System.out.println("----------------------------------------------------------");
					System.out.println("---------------------- подбита только одна палуба ---------");
					
					setShotDownCellTerritory(lastCell, fieldSize);
					
					
					System.out.println("----------------------------------------------------------");
					System.out.print("--- сформирована область вокруг убитой €чейки (" + (lastCell.getXCoord() + 1) + ", " + (lastCell.getYCoord() + 1) + ") корабл€ ");
					for(Cell cellShip : ship.getCells()) {
						System.out.print("(" + (cellShip.getXCoord() + 1) + ", " + (cellShip.getYCoord() + 1) + ") ");
					}
					System.out.println("");
					int n = 1;
					for(Cell cellTerr : cellTerritory) {
						System.out.println("€чейка " + n + ": (" + (cellTerr.getXCoord() + 1) + ", " + (cellTerr.getYCoord() + 1) + ")");
						n++;
					}
								
					
					randomIndex = new Random().nextInt(cellTerritory.size());
					xShotCoord = cellTerritory.get(randomIndex).getXCoord();
					yShotCoord = cellTerritory.get(randomIndex).getYCoord();
					coordsAreReady = true;
					cellTerritory.clear();
					
					
					System.out.println("----------------------------------------------------------");
					System.out.println("------------------- сформированы умные координаты: (" + (xShotCoord + 1) + ", " + (yShotCoord + 1) + ")");
				
					
				}
			}
		}	
	}
	
	public void amazingShot(int fieldSize) {
		
	}
	
	public void checkShipsTerritory() {
		int n = 1;
		for(Ship ship : playerShips.getBattleField()) {
			if(!ship.checkShipAlive()){
				for(Cell cell : ship.getAroundCells()) {
					if(!checkCellIsShot(cell)) {
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("----------------- формируем территорию вокруг вновь потопленного корабл€ - " + n + " клеточек");
						System.out.println("---------------------------------------------------------------------------");
						n++;
						aIShots.add(cell.getXCoord(), cell.getYCoord(), true);
					}
				}
			}
		}
	}
	
	public boolean checkCellIsShot(Cell cell) {
		for(Shot shot : aIShots.getShots()) {
			if(shot.getXCoord() == cell.getXCoord() && shot.getYCoord() == cell.getYCoord()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean cellTerritoryIsNotThisCell(Cell cell, int xCell, int yCell) {
		if(cell.getXCoord() == cell.getXCoord() + xCell && 
				cell.getYCoord() == cell.getYCoord() + yCell) {
			return false;
		}
		return true;
	}
	
	 public boolean cellTerritoryIsNotOutOfField(Cell cell, int xCell, int yCell, int fieldSize) {
		if(cell.getXCoord() + xCell < 0 || cell.getXCoord() + xCell > fieldSize - 1 ||
			cell.getYCoord() + yCell < 0 || cell.getYCoord() + yCell > fieldSize - 1) {
			return false;
		}
		return true;
	}
	 
	 public boolean cellTerritoryIsNotDiagonal(int xCell, int yCell) {
		if(xCell == 0 || yCell == 0) {
			return true;
		}
		return false;
	}
		
	public void setShotDownCellTerritory(Cell cell, int fieldSize) {
		if(mainAxisIsY) {
			
			System.out.println("----------------------------------------------------------");
			System.out.println("------ определ€ем территорию вокруг (вдоль оси Y).. ");
			System.out.println("-------дл€ €чейки: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");

			
			
			for(int yCell = -1; yCell < 2; yCell++) {
				if(cellTerritoryIsNotThisCell(cell, 0, yCell) && 
						cellTerritoryIsNotOutOfField(cell, 0, yCell, fieldSize) && 
						cellTerritoryIsNotDiagonal(0, yCell)) {
					cellTerritory.add(new Cell(cell.getXCoord(), cell.getYCoord() + yCell));
				}
			}
			
			System.out.println("----------------------------------------------------------");
			System.out.println("------ ¬от ее территори€: ");
			int n = 1;
			for(Cell cellTerr : cellTerritory) {
				System.out.println("ячейка территории є" + n + ": (" + (cellTerr.getXCoord() + 1) + ", " + (cellTerr.getYCoord() + 1) + ")");
				n++;
			}
			System.out.println("----------------------------------------------------------");
			
			
			
		} else if(mainAxisIsX) {
			
			System.out.println("----------------------------------------------------------");
			System.out.println("------ определ€ем территорию вокруг (вдоль оси X).. ");
			System.out.println("-------дл€ €чейки: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");

			
			for(int xCell = -1; xCell < 2; xCell++) {
				if(cellTerritoryIsNotThisCell(cell, xCell, 0) && 
						cellTerritoryIsNotOutOfField(cell, xCell, 0, fieldSize) && 
						cellTerritoryIsNotDiagonal(xCell, 0)) {
					cellTerritory.add(new Cell(cell.getXCoord() + xCell, cell.getYCoord()));
				}
			}
			
			System.out.println("----------------------------------------------------------");
			System.out.println("------ ¬от ее территори€: ");
			int n = 1;
			for(Cell cellTerr : cellTerritory) {
				System.out.println("ячейка территории є" + n + ": (" + (cellTerr.getXCoord() + 1) + ", " + (cellTerr.getYCoord() + 1) + ")");
				n++;
			}
			System.out.println("----------------------------------------------------------");
			
			
		} else {
			for(int xCell = -1; xCell < 2; xCell++) {
				for(int yCell = -1; yCell < 2; yCell++) {
					if(cellTerritoryIsNotThisCell(cell, xCell, yCell) && 
							cellTerritoryIsNotOutOfField(cell, xCell, yCell, fieldSize) && 
							cellTerritoryIsNotDiagonal(xCell, yCell)) {
						cellTerritory.add(new Cell(cell.getXCoord() + xCell, cell.getYCoord() + yCell));
					}
				}
			}
		}
	}
		
	public void setExtremeShotDownCellsTerritoty(Ship ship, int fieldSize) {

		System.out.println("----------------------------------------------------------");
		System.out.println("------ находим первую €чейку в подбитом корабле.. ");
		
		setShotDownCellTerritory(findFirstExtremeShotDownCell(ship, fieldSize), fieldSize);
		
		System.out.println("----------------------------------------------------------");
		System.out.println("------ находим последнюю €чейку в подбитом корабле.. ");
		
		setShotDownCellTerritory(findLastExtremeShotDownCell(ship, fieldSize), fieldSize);

		System.out.println("----------------------------------------------------------");
		System.out.println("------ »скома€ территори€: ");
		int n = 1;
		for(Cell cell : cellTerritory) {
			System.out.println("ячейка территории є" + n + ": (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
			n++;
		}
		System.out.println("----------------------------------------------------------");
		System.out.println("------- удал€ем одинаковые €чейки из списка территории..");
		
//		removeShotsFromTerritory();
		removeDuplicatesFromCellTerritory();
		
		System.out.println("----------------------------------------------------------");
		System.out.println("------  онечна€ территори€: ");
		int num = 1;
		for(Cell cell : cellTerritory) {
			System.out.println("ячейка территории є" + n + ": (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
			n++;
		}
		
		mainAxisIsY = false;
		mainAxisIsX = false;

		
	}
		
	public Cell findFirstExtremeShotDownCell(Ship ship, int fieldSize) {
		int x = fieldSize - 1;
		int y = fieldSize - 1;

		if(mainAxisIsX) {
			for(Cell cell : ship.getCells()) {
				if(!cell.checkCellAlive()) {

					System.out.println("ячейка cell: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
					System.out.println("ячейка firstExtremeCell до обработки: (" + (x + 1) + ", " + (y + 1) + ")");
					
					if(cell.getXCoord() < x) {
						x = cell.getXCoord();
					}
				y = cell.getYCoord();

				System.out.println("ячейка firstExtremeCell после обработки: (" + (x + 1) + ", " + (y + 1) + ")");

				}
			}
		} else {
			for(Cell cell : ship.getCells()) {
				if(!cell.checkCellAlive()) {
					
					System.out.println("ячейка cell: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
					System.out.println("ячейка firstExtremeCell до обработки: (" + (x + 1) + ", " + (y + 1) + ")");
					
					if(cell.getYCoord() < y) {
						y = cell.getYCoord();
					}
				x = cell.getXCoord();
				
				System.out.println("ячейка firstExtremeCell после обработки: (" + (x + 1) + ", " + (y + 1) + ")");

				}
			}
		}
		
		Cell firstExtremeCell = new Cell(x, y);
		System.out.println("ячейка: (" + (firstExtremeCell.getXCoord() + 1) + ", " + (firstExtremeCell.getYCoord() + 1) + ")");
		return firstExtremeCell;
	}
	
	public Cell findLastExtremeShotDownCell(Ship ship, int fieldSize) {
		int x = 0;
		int y = 0;

		if(mainAxisIsX) {
			for(Cell cell : ship.getCells()) {
				if(!cell.checkCellAlive()) {

					System.out.println("ячейка cell: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
					System.out.println("ячейка lastExtremeCell до обработки: (" + (x + 1) + ", " + (y + 1) + ")");
					
					if(cell.getXCoord() > x) {
						x = cell.getXCoord();
					}
				y = cell.getYCoord();
				
				System.out.println("ячейка lastExtremeCell после обработки: (" + (x + 1) + ", " + (y + 1) + ")");
				
				}
			}
		} else {
			for(Cell cell : ship.getCells()) {
				if(!cell.checkCellAlive()) {
					
					System.out.println("ячейка cell: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
					System.out.println("ячейка lastExtremeCell до обработки: (" + (x + 1) + ", " + (y + 1) + ")");
					
					if(cell.getYCoord() > y) {
						y = cell.getYCoord();
					}
				x = cell.getXCoord();

				System.out.println("ячейка lastExtremeCell после обработки: (" + (x + 1) + ", " + (y + 1) + ")");

				}
			}
		}
		
		
		Cell lastExtremeCell = new Cell(x, y);
		
		System.out.println("ячейка: (" + (lastExtremeCell.getXCoord() + 1) + ", " + (lastExtremeCell.getYCoord() + 1) + ")");
		return lastExtremeCell;

	}
	
	public void removeDuplicatesFromCellTerritory() {
		removeDuplicatesFromCellTerritory = new HashSet<Cell>(cellTerritory);
		cellTerritory.clear();
		cellTerritory.addAll(removeDuplicatesFromCellTerritory);
	}
	
	public boolean moreThanOneCellHit(Ship ship) {
		int num = 0;
		for(Cell cell : ship.getCells()) {
			if(!cell.checkCellAlive()) {
				num++;
			}
		}
		if(num > 1) {
			return true;
		}
		return false;
	}
		
	public void whatIsMainAxis(Ship ship, int fieldSize) {	

		
		System.out.println("----------------------------------------------------------");
		System.out.println("--- оцениваем ось расположени€ корабл€: ");
		for(Cell cellShip : ship.getCells()) {
			System.out.print("(" + (cellShip.getXCoord() + 1) + ", " + (cellShip.getYCoord() + 1) + ") ");
		}
		System.out.println("");
		
		

		int firstCellXCoord = fieldSize;
		int firstCellYCoord = fieldSize;
		for(Cell cell : ship.getCells()) {
			if(!cell.checkCellAlive()) {
				if(cell.getXCoord() == firstCellXCoord) {
					
					System.out.println("------------------------------");
					System.out.println("корабль расположен вдоль оси Y");
					
					mainAxisIsY = true;
				} else if(cell.getYCoord() == firstCellYCoord) {
					
					System.out.println("------------------------------");
					System.out.println("корабль расположен вдоль оси X");
					
					mainAxisIsX = true;
				}
				firstCellXCoord = cell.getXCoord();
				firstCellYCoord = cell.getYCoord();
			}
		}
		firstCellXCoord = fieldSize;
		firstCellYCoord = fieldSize;
	}
 
}
