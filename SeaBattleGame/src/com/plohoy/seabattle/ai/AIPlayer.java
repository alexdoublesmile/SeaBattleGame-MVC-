package com.plohoy.seabattle.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

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
	private int fieldSize;

	private ArrayList<Cell> cellTerritory = new ArrayList<Cell>();
	private ArrayList<Cell> fieldTerritory = new ArrayList<Cell>();
	private ArrayList<Cell> copyFieldTerritory = new ArrayList<Cell>();
	private ArrayList<Cell> tempBestShots = new ArrayList<Cell>();

	private HashSet<Cell> removeDuplicatesFromCellList;
	private int xShotCoord;
	private int yShotCoord;
	private boolean coordsAreReady = false;
	private Cell lastCell;

	private boolean mainAxisIsX;
	private boolean mainAxisIsY;
	private int randomIndex;

	private int[] pattern;
//	private TreeSet<Integer> typesOfShips = new TreeSet<Integer>();
 
	private int numberOfStartOneDeckShips;
	private int numberOfStartTwoDeckShips;
	private int numberOfStartThreeDeckShips;
	private int numberOfStartFourDeckShips;
	private int numberOfShotDownOneDeckShips;
	private int numberOfShotDownTwoDeckShips;
	private int numberOfShotDownThreeDeckShips;
	private int numberOfShotDownFourDeckShips;
	private int numberOfAliveOneDeckShips;
	private int numberOfAliveTwoDeckShips;
	private int numberOfAliveThreeDeckShips;
	private int numberOfAliveFourDeckShips;

	private int thePower = 0;
	private int score;

	private Random random = new Random();
	
	public AIPlayer(int power, int fieldSize, int[] pattern) {
		
		this.thePower = power;
		this.pattern = pattern;
		this.fieldSize = fieldSize;
		setNumberOfAllTypesShips();
		initializeAllTerritory();
//		for(int i = 0; i < pattern.length; i++) {
//			typesOfShips.add(pattern[i]);
//		}
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
	
	public void aIMakesShot(Shots opponentShots, Field playerShips) {
		this.playerShips = playerShips;
		this.aIShots = opponentShots;
		
		switch(thePower) {
		case(0):
			randomShot();
			break;
		case(1):
			coordsAreReady = false;
			smartShot();
			if (!coordsAreReady) {	
				randomShot();
			}
			break;
		case(2):
			coordsAreReady = false;
			smartShot();
			if (!coordsAreReady) {	
				intuitiveShot();
//				System.out.println("----------------------------------------------------------");
//				System.out.println("------ ����� ������������: " + numberOfStartOneDeckShips);
//				System.out.println("------ ����� ������������: " + numberOfStartTwoDeckShips);
//				System.out.println("------ ����� ������������: " + numberOfStartThreeDeckShips);
//				System.out.println("------ ����� ���������������: " + numberOfStartFourDeckShips);
//				System.out.println("----------------------------------------------------------");
//				System.out.println("------ ����������� ������������: " + numberOfShotDownOneDeckShips);
//				System.out.println("------ ����������� ������������: " + numberOfShotDownTwoDeckShips);
//				System.out.println("------ ����������� ������������: " + numberOfShotDownThreeDeckShips);
//				System.out.println("------ ����������� ���������������: " + numberOfShotDownFourDeckShips);
				System.out.println("----------------------------------------------------------");
				System.out.println("------ ���������� ����� 1-��������: " + numberOfAliveOneDeckShips);
				System.out.println("------ ���������� ����� 2-��������: " + numberOfAliveTwoDeckShips);
				System.out.println("------ ���������� ����� 3-��������: " + numberOfAliveThreeDeckShips);
				System.out.println("------ ���������� ����� 4-��������: " + numberOfAliveFourDeckShips);
//				randomShot();
			}
			break;
		default:
			System.exit(0);
		}
	}

	public void randomShot() {
		xShotCoord = random.nextInt(fieldSize);
		yShotCoord = random.nextInt(fieldSize);
	}
	
	public void smartShot() {		
		checkShipsTerritory();
		for(Ship ship : playerShips.getBattleField()) {
			if(ship.checkShipIsShotDown()) {
				if(moreThanOneCellHit(ship)) {

					System.out.println("----------------------------------------------------------");
					System.out.println("---------------------- ������� ��������� ����� ---------");
					
					whatIsMainAxis(ship);
					setExtremeShotDownCellsTerritoty(ship);
					
					randomIndex = new Random().nextInt(cellTerritory.size());
					xShotCoord = cellTerritory.get(randomIndex).getXCoord();
					yShotCoord = cellTerritory.get(randomIndex).getYCoord();
					coordsAreReady = true;
					cellTerritory.clear();
					
					
					System.out.println("----------------------------------------------------------");
					System.out.println("------------------- ������������ ����� ����������: (" + (xShotCoord + 1) + ", " + (yShotCoord + 1) + ")");
				
				} else {

					System.out.println("----------------------------------------------------------");
					System.out.println("---------------------- ������� ������ ���� ������ ---------");
					
					setShotDownCellTerritory(lastCell);
					
					
					System.out.println("----------------------------------------------------------");
					System.out.print("--- ������������ ������� ������ ������ ������ (" + (lastCell.getXCoord() + 1) + ", " + (lastCell.getYCoord() + 1) + ") ������� ");
					for(Cell cellShip : ship.getCells()) {
						System.out.print("(" + (cellShip.getXCoord() + 1) + ", " + (cellShip.getYCoord() + 1) + ") ");
					}
					System.out.println("");
					int n = 1;
					for(Cell cellTerr : cellTerritory) {
						System.out.println("������ " + n + ": (" + (cellTerr.getXCoord() + 1) + ", " + (cellTerr.getYCoord() + 1) + ")");
						n++;
					}
								
					
					randomIndex = new Random().nextInt(cellTerritory.size());
					xShotCoord = cellTerritory.get(randomIndex).getXCoord();
					yShotCoord = cellTerritory.get(randomIndex).getYCoord();
					coordsAreReady = true;
					cellTerritory.clear();
					
					
					System.out.println("----------------------------------------------------------");
					System.out.println("------------------- ������������ ����� ����������: (" + (xShotCoord + 1) + ", " + (yShotCoord + 1) + ")");
				
					
				}
			}
		}	
	}
	
	public void checkShipsTerritory() {
		int n = 1;
		for(Ship ship : playerShips.getBattleField()) {
			if(!ship.checkShipAlive()){
				for(Cell cell : ship.getAroundCells()) {
					if(!checkCellIsShot(cell)) {
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("----------------- ��������� ���������� ������ ����� ������������ ������� - " + n + " ��������");
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
	
	 public boolean cellTerritoryIsNotOutOfField(Cell cell, int xCell, int yCell) {
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
		
	public void setShotDownCellTerritory(Cell cell) {
		if(mainAxisIsY) {
			
			System.out.println("----------------------------------------------------------");
			System.out.println("------ ���������� ���������� ������ (����� ��� Y).. ");
			System.out.println("-------��� ������: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");

			
			
			for(int yCell = -1; yCell < 2; yCell++) {
				if(cellTerritoryIsNotThisCell(cell, 0, yCell) && 
						cellTerritoryIsNotOutOfField(cell, 0, yCell) && 
						cellTerritoryIsNotDiagonal(0, yCell)) {
					cellTerritory.add(new Cell(cell.getXCoord(), cell.getYCoord() + yCell));
				}
			}
			
			System.out.println("----------------------------------------------------------");
			System.out.println("------ ��� �� ����������: ");
			int n = 1;
			for(Cell cellTerr : cellTerritory) {
				System.out.println("������ ���������� �" + n + ": (" + (cellTerr.getXCoord() + 1) + ", " + (cellTerr.getYCoord() + 1) + ")");
				n++;
			}
			System.out.println("----------------------------------------------------------");
			
			
			
		} else if(mainAxisIsX) {
			
			System.out.println("----------------------------------------------------------");
			System.out.println("------ ���������� ���������� ������ (����� ��� X).. ");
			System.out.println("-------��� ������: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");

			
			for(int xCell = -1; xCell < 2; xCell++) {
				if(cellTerritoryIsNotThisCell(cell, xCell, 0) && 
						cellTerritoryIsNotOutOfField(cell, xCell, 0) && 
						cellTerritoryIsNotDiagonal(xCell, 0)) {
					cellTerritory.add(new Cell(cell.getXCoord() + xCell, cell.getYCoord()));
				}
			}
			
			System.out.println("----------------------------------------------------------");
			System.out.println("------ ��� �� ����������: ");
			int n = 1;
			for(Cell cellTerr : cellTerritory) {
				System.out.println("������ ���������� �" + n + ": (" + (cellTerr.getXCoord() + 1) + ", " + (cellTerr.getYCoord() + 1) + ")");
				n++;
			}
			System.out.println("----------------------------------------------------------");
			
			
		} else {
			for(int xCell = -1; xCell < 2; xCell++) {
				for(int yCell = -1; yCell < 2; yCell++) {
					if(cellTerritoryIsNotThisCell(cell, xCell, yCell) && 
							cellTerritoryIsNotOutOfField(cell, xCell, yCell) && 
							cellTerritoryIsNotDiagonal(xCell, yCell)) {
						cellTerritory.add(new Cell(cell.getXCoord() + xCell, cell.getYCoord() + yCell));
					}
				}
			}
		}
	}
		
	public void setExtremeShotDownCellsTerritoty(Ship ship) {

		System.out.println("----------------------------------------------------------");
		System.out.println("------ ������� ������ ������ � �������� �������.. ");
		
		setShotDownCellTerritory(findFirstExtremeShotDownCell(ship));
		
		System.out.println("----------------------------------------------------------");
		System.out.println("------ ������� ��������� ������ � �������� �������.. ");
		
		setShotDownCellTerritory(findLastExtremeShotDownCell(ship));

		System.out.println("----------------------------------------------------------");
		System.out.println("------ ������� ����������: ");
		int n = 1;
		for(Cell cell : cellTerritory) {
			System.out.println("������ ���������� �" + n + ": (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
			n++;
		}
		System.out.println("----------------------------------------------------------");
		System.out.println("------- ������� ���������� ������ �� ������ ����������..");
		
//		removeShotsFromTerritory();
		removeDuplicatesFromCellList(cellTerritory);
		
		System.out.println("----------------------------------------------------------");
		System.out.println("------ �������� ����������: ");
		int num = 1;
		for(Cell cell : cellTerritory) {
			System.out.println("������ ���������� �" + num + ": (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
			num++;
		}
		
		mainAxisIsY = false;
		mainAxisIsX = false;

		
	}
		
	public Cell findFirstExtremeShotDownCell(Ship ship) {
		int x = fieldSize - 1;
		int y = fieldSize - 1;

		if(mainAxisIsX) {
			for(Cell cell : ship.getCells()) {
				if(!cell.checkCellAlive()) {

					System.out.println("������ cell: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
					System.out.println("������ firstExtremeCell �� ���������: (" + (x + 1) + ", " + (y + 1) + ")");
					
					if(cell.getXCoord() < x) {
						x = cell.getXCoord();
					}
				y = cell.getYCoord();

				System.out.println("������ firstExtremeCell ����� ���������: (" + (x + 1) + ", " + (y + 1) + ")");

				}
			}
		} else {
			for(Cell cell : ship.getCells()) {
				if(!cell.checkCellAlive()) {
					
					System.out.println("������ cell: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
					System.out.println("������ firstExtremeCell �� ���������: (" + (x + 1) + ", " + (y + 1) + ")");
					
					if(cell.getYCoord() < y) {
						y = cell.getYCoord();
					}
				x = cell.getXCoord();
				
				System.out.println("������ firstExtremeCell ����� ���������: (" + (x + 1) + ", " + (y + 1) + ")");

				}
			}
		}
		
		Cell firstExtremeCell = new Cell(x, y);
		System.out.println("������: (" + (firstExtremeCell.getXCoord() + 1) + ", " + (firstExtremeCell.getYCoord() + 1) + ")");
		return firstExtremeCell;
	}
	
	public Cell findLastExtremeShotDownCell(Ship ship) {
		int x = 0;
		int y = 0;

		if(mainAxisIsX) {
			for(Cell cell : ship.getCells()) {
				if(!cell.checkCellAlive()) {

					System.out.println("������ cell: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
					System.out.println("������ lastExtremeCell �� ���������: (" + (x + 1) + ", " + (y + 1) + ")");
					
					if(cell.getXCoord() > x) {
						x = cell.getXCoord();
					}
				y = cell.getYCoord();
				
				System.out.println("������ lastExtremeCell ����� ���������: (" + (x + 1) + ", " + (y + 1) + ")");
				
				}
			}
		} else {
			for(Cell cell : ship.getCells()) {
				if(!cell.checkCellAlive()) {
					
					System.out.println("������ cell: (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
					System.out.println("������ lastExtremeCell �� ���������: (" + (x + 1) + ", " + (y + 1) + ")");
					
					if(cell.getYCoord() > y) {
						y = cell.getYCoord();
					}
				x = cell.getXCoord();

				System.out.println("������ lastExtremeCell ����� ���������: (" + (x + 1) + ", " + (y + 1) + ")");

				}
			}
		}
		
		
		Cell lastExtremeCell = new Cell(x, y);
		
		System.out.println("������: (" + (lastExtremeCell.getXCoord() + 1) + ", " + (lastExtremeCell.getYCoord() + 1) + ")");
		return lastExtremeCell;

	}
	
	public void removeDuplicatesFromCellList(ArrayList<Cell> cellList) {
		removeDuplicatesFromCellList = new HashSet<Cell>(cellList);
		cellList.clear();
		cellList.addAll(removeDuplicatesFromCellList);
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
		
	public void whatIsMainAxis(Ship ship) {	

		
		System.out.println("----------------------------------------------------------");
		System.out.println("--- ��������� ��� ������������ �������: ");
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
					System.out.println("������� ���������� ����� ��� Y");
					
					mainAxisIsY = true;
				} else if(cell.getYCoord() == firstCellYCoord) {
					
					System.out.println("------------------------------");
					System.out.println("������� ���������� ����� ��� X");
					
					mainAxisIsX = true;
				}
				firstCellXCoord = cell.getXCoord();
				firstCellYCoord = cell.getYCoord();
			}
		}
		firstCellXCoord = fieldSize;
		firstCellYCoord = fieldSize;
	}
	
	public void intuitiveShot() {
		calcNumberOfAliveShips();
			
		if(numberOfAliveFourDeckShips == 0 && 
				numberOfAliveThreeDeckShips == 0 &&
				numberOfAliveTwoDeckShips == 0) {
			System.out.println("------------------------------");
			System.out.println("�������� ������..");
			randomShot();
		} else {
			masterShot();
		}
	}
	
	public void masterShot() {
		if(!(numberOfAliveFourDeckShips == 0)) {
			System.out.println("------------------------------");
			System.out.println("���� ����� 4 ��������..");
			initializeFreeTerritory();
			copyFreeTerritory();
			findXLineOfFourCells();
			findYLineOfFourCells();
			removeDuplicatesFromCellList(tempBestShots);
			findBestCoordinates();		
		} else {
			if(!(numberOfAliveThreeDeckShips == 0)) {
				System.out.println("------------------------------");
				System.out.println("���� ����� 3 ��������..");

				initializeFreeTerritory();
				copyFreeTerritory();
				findXLineOfThreeCells();
				findYLineOfThreeCells();
				removeDuplicatesFromCellList(tempBestShots);
				findBestCoordinates();				} 
			else {
				if(!(numberOfAliveTwoDeckShips == 0)) {
					System.out.println("------------------------------");
					System.out.println("���� ����� 2 ��������..");

					initializeFreeTerritory();
					copyFreeTerritory();
					findXLineOfTwoCells();
					findYLineOfTwoCells();
					removeDuplicatesFromCellList(tempBestShots);
					findBestCoordinates();					
				} 
			}
		}	
	}
	
	public void setNumberOfAllTypesShips() {
		for(int i = 0; i < pattern.length; i++) {
			switch(pattern[i]) {
			case(1):
				numberOfStartOneDeckShips++; 
				break;
			case(2):
				numberOfStartTwoDeckShips++;
				break;
			case(3):
				numberOfStartThreeDeckShips++;
				break;
			case(4):
				numberOfStartFourDeckShips++;
				break;
			default:
				break;
			}
		}
	}

	public void calcNumberOfShotDownShips() {
		
		int numberOfDecks = 0;
		numberOfShotDownOneDeckShips = 0;
		numberOfShotDownTwoDeckShips = 0;
		numberOfShotDownThreeDeckShips = 0;
		numberOfShotDownFourDeckShips = 0;
		for(Ship ship : playerShips.getBattleField()) {
			if(!ship.checkShipAlive()) {
				for(Cell cell : ship.getCells()) {
					numberOfDecks++;
				}
			}
			switch(numberOfDecks) {
			case(1):
				numberOfShotDownOneDeckShips++; 
				break;
			case(2):
				numberOfShotDownTwoDeckShips++;
				break;
			case(3):
				numberOfShotDownThreeDeckShips++;
				break;
			case(4):
				numberOfShotDownFourDeckShips++;
				break;
			default:
				break;
			}
			numberOfDecks = 0;
		}
	}
	
	public void calcNumberOfAliveShips() {
		calcNumberOfShotDownShips();
		numberOfAliveOneDeckShips = numberOfStartOneDeckShips - numberOfShotDownOneDeckShips;
		numberOfAliveTwoDeckShips = numberOfStartTwoDeckShips - numberOfShotDownTwoDeckShips;
		numberOfAliveThreeDeckShips = numberOfStartThreeDeckShips - numberOfShotDownThreeDeckShips;
		numberOfAliveFourDeckShips = numberOfStartFourDeckShips - numberOfShotDownFourDeckShips;
		
	}
	
	public void findBestCoordinatesOfFourDeckShip() {
		initializeFreeTerritory();
		copyFreeTerritory();
		findXLineOfFourCells();
		findYLineOfFourCells();
		removeDuplicatesFromCellList(tempBestShots);
		findBestCoordinates();
	}
	
	public void findBestCoordinatesOfThreeDeckShip() {
		initializeFreeTerritory();

	}
	
	public void findBestCoordinatesOfTwoDeckShip() {
		initializeFreeTerritory();

	}
	
	public void initializeFreeTerritory() {
		for(int i = 0; i < fieldTerritory.size(); i++) {
			if(checkCellIsShot(fieldTerritory.get(i))) {
				fieldTerritory.remove(fieldTerritory.get(i));
			}
		}
//		int n = 1;
//		for(Cell cell : fieldTerritory) {
//			System.out.println("������ ��������� ���������� �" + n + ": (" + (cell.getXCoord() + 1) + ", " + (cell.getYCoord() + 1) + ")");
//			n++;
//		}
		
		
		
	}
	
	public void initializeAllTerritory() {

//		System.out.println("------------------------------");
//		System.out.println("�������������� ��� ����..");
//
//		System.out.println("------------------------------");
//		System.out.println("������ ����: " + fieldSize);
		
		for(int x = 0; x < fieldSize; x++) {
			for(int y = 0; y < fieldSize; y++) {
				fieldTerritory.add(new Cell(x, y));
			}
		}
	}
	
	public void copyFreeTerritory() {
		copyFieldTerritory = fieldTerritory;
	}
	
	public void findXLineOfFourCells() {
		int n = 1;
		for(Cell firstcell : fieldTerritory) {
			for(Cell secondCell : copyFieldTerritory) {
				if(secondCell.getXCoord() - 1 == firstcell.getXCoord() && 
						firstcell.getYCoord() == secondCell.getYCoord()) {
					System.out.println("------------------------------");
					System.out.println("������� ��� �� ���� �����..");
					for(Cell thirdCell : fieldTerritory) {
						if(thirdCell.getXCoord() - 1 == secondCell.getXCoord() && 
								secondCell.getYCoord() == thirdCell.getYCoord()) {
							System.out.println("------------------------------");
							System.out.println("������� ��� �� ���� �����..");
							for(Cell fourthCell : copyFieldTerritory) {
								if(fourthCell.getXCoord() - 1 == thirdCell.getXCoord() && 
										fourthCell.getYCoord() == thirdCell.getYCoord()) {
									System.out.println("------------------------------");
									System.out.println("������� ��� �� ������� �����..");
									
									System.out.println("������ ��� 4-���������: (" + (firstcell.getXCoord() + 1) + ", " + (firstcell.getYCoord() + 1) + ")");
									System.out.println("������ ��� 4-���������: (" + (secondCell.getXCoord() + 1) + ", " + (secondCell.getYCoord() + 1) + ")");
									System.out.println("������ ��� 4-���������: (" + (thirdCell.getXCoord() + 1) + ", " + (thirdCell.getYCoord() + 1) + ")");
									System.out.println("������ ��� 4-���������: (" + (fourthCell.getXCoord() + 1) + ", " + (fourthCell.getYCoord() + 1) + ")");
									switch(n) {
									case(1):
										tempBestShots.add(secondCell);
										break;
									case(2):
										tempBestShots.add(thirdCell);
									default:
										break;
									}
									n++;
									if(n > 2) {
										n = 1;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void findYLineOfFourCells() {
		int n = 1;
		for(Cell firstcell : fieldTerritory) {
			for(Cell secondCell : copyFieldTerritory) {
				if(secondCell.getYCoord() - 1 == firstcell.getYCoord() && 
						firstcell.getXCoord() == secondCell.getXCoord()) {
					for(Cell thirdCell : fieldTerritory) {
						if(thirdCell.getYCoord() - 1 == secondCell.getYCoord() && 
								secondCell.getXCoord() == thirdCell.getXCoord()) {
							for(Cell fourthCell : copyFieldTerritory) {
								if(fourthCell.getYCoord() - 1 == thirdCell.getYCoord() && 
										fourthCell.getXCoord() == thirdCell.getXCoord()) {
									switch(n) {
									case(1):
										tempBestShots.add(secondCell);
										break;
									case(2):
										tempBestShots.add(thirdCell);
									default:
										break;
									}
									n++;
									if(n > 2) {
										n = 1;
									}							
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void findXLineOfThreeCells() {
		for(Cell firstcell : fieldTerritory) {
			for(Cell secondCell : copyFieldTerritory) {
				if(secondCell.getXCoord() - 1 == firstcell.getXCoord() && 
						firstcell.getYCoord() == secondCell.getYCoord()) {
					System.out.println("------------ ��� �������� ------------------");
					System.out.println("������� ��� �� ���� �����..");
					for(Cell thirdCell : fieldTerritory) {
						if(thirdCell.getXCoord() - 1 == secondCell.getXCoord() && 
								secondCell.getYCoord() == thirdCell.getYCoord()) {
							System.out.println("------------- ��� �������� -----------------");
							System.out.println("������� ��� �� ���� �����..");
							

							System.out.println("������ ��� 3-���������: (" + (firstcell.getXCoord() + 1) + ", " + (firstcell.getYCoord() + 1) + ")");
							System.out.println("������ ��� 3-���������: (" + (secondCell.getXCoord() + 1) + ", " + (secondCell.getYCoord() + 1) + ")");
							System.out.println("������ ��� 3-���������: (" + (thirdCell.getXCoord() + 1) + ", " + (thirdCell.getYCoord() + 1) + ")");

							
							tempBestShots.add(secondCell);
						}
					}
				}
			}
		}
	}
	
	public void findYLineOfThreeCells() {
		for(Cell firstcell : fieldTerritory) {
			for(Cell secondCell : copyFieldTerritory) {
				if(secondCell.getYCoord() - 1 == firstcell.getYCoord() && 
						firstcell.getXCoord() == secondCell.getXCoord()) {
					for(Cell thirdCell : fieldTerritory) {
						if(thirdCell.getYCoord() - 1 == secondCell.getYCoord() && 
								secondCell.getXCoord() == thirdCell.getXCoord()) {
							

							System.out.println("������ ��� 3-���������: (" + (firstcell.getXCoord() + 1) + ", " + (firstcell.getYCoord() + 1) + ")");
							System.out.println("������ ��� 3-���������: (" + (secondCell.getXCoord() + 1) + ", " + (secondCell.getYCoord() + 1) + ")");
							System.out.println("������ ��� 3-���������: (" + (thirdCell.getXCoord() + 1) + ", " + (thirdCell.getYCoord() + 1) + ")");

							
							tempBestShots.add(secondCell);
						}
					}
				}
			}
		}
	}
	
	public void findXLineOfTwoCells() {
		for(Cell firstcell : fieldTerritory) {
			for(Cell secondCell : copyFieldTerritory) {
				if(secondCell.getXCoord() - 1 == firstcell.getXCoord() && 
						firstcell.getYCoord() == secondCell.getYCoord()) {
					System.out.println("-------- ��� �������� ----------------------");
					System.out.println("������� ��� �� ���� �����..");
					

					System.out.println("������ ��� 2-���������: (" + (firstcell.getXCoord() + 1) + ", " + (firstcell.getYCoord() + 1) + ")");
					System.out.println("������ ��� 2-���������: (" + (secondCell.getXCoord() + 1) + ", " + (secondCell.getYCoord() + 1) + ")");

					
					tempBestShots.add(secondCell);
					
				}
			}
		}
	}
	
	public void findYLineOfTwoCells() {
		for(Cell firstcell : fieldTerritory) {
			for(Cell secondCell : copyFieldTerritory) {
				if(secondCell.getYCoord() - 1 == firstcell.getYCoord() && 
						firstcell.getXCoord() == secondCell.getXCoord()) {
					

					System.out.println("������ ��� 2-���������: (" + (firstcell.getXCoord() + 1) + ", " + (firstcell.getYCoord() + 1) + ")");
					System.out.println("������ ��� 2-���������: (" + (secondCell.getXCoord() + 1) + ", " + (secondCell.getYCoord() + 1) + ")");

					
					tempBestShots.add(secondCell);
				}
			}
		}
	}
	

	public void findBestCoordinates() {
		randomIndex = new Random().nextInt(tempBestShots.size());
		xShotCoord = tempBestShots.get(randomIndex).getXCoord();
		yShotCoord = tempBestShots.get(randomIndex).getYCoord();
		coordsAreReady = true;
		tempBestShots.clear();
	}
}
