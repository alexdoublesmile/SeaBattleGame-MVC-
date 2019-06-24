package com.plohoy.seabattle.model;

import java.util.*;

public class Field {

//	private int cellSize;
	private ArrayList<Ship> battleField = new ArrayList<Ship>();
	private int[] pattern = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
	private Random random;
	
//	Field() {
//		
//	}
	
//	public int getCELL_SIZE() {
//		return cellSize;
//	}
//
//	public void setCELL_SIZE(int cellSize) {
//		this.cellSize = cellSize;
//	}

	public void setPattern(int[] pattern) {
		this.pattern = pattern;
	}

	Field(int fieldSize) {	
//		System.out.println("------------------------------------------------");
//		System.out.println("размер ячейки: " + cellSize);	
//		System.out.println("ширина поля: " + fieldSize);
//		if(hide) {
//			System.out.println("корабль скрыт");
//		} else {
//			System.out.println("корабль виден");
//		}
//		
//		System.out.println("------------------------------------------------");
//		System.out.println("начинается загрузка кораблей...");
		random = new Random();
		for(int i = 0; i < pattern.length; i++) {
			Ship ship;
//				int n = i + 1;
//				int z = 1;
//				System.out.println("создан " + n + " корабль...");
			do {
				int x = random.nextInt(fieldSize);
				int y = random.nextInt(fieldSize);
				int position = random.nextInt(2);
				ship = new Ship(x, y, pattern[i], position, fieldSize);
//					System.out.println("проверка " + n + "." + z + " корабля на соответствие...");	
//					z++;
			} while(ship.isShipOutOfField(0, fieldSize - 1) || isShipNotDetached(ship));
//				System.out.println("Корабль " + n + "." + (z - 1) + " соответствует...");
			battleField.add(ship);
		}
		
//		System.out.println("загрузка кораблей окончена...");
//		System.out.println("------------------------------------------------");	
//		System.out.println("Проверку прошли Корабли:");
//		for(Ship ship : battleField) {
//			int n = 1;
//			for(Cell cell : ship.getCells()) {
//				System.out.println("Корабль: координаты ячейки № " + n + ": " + cell.getxCoord() + ", " + cell.getyCoord());
//				n++;				
//			}
//			System.out.println("------------------------------------------------");	
//		}
//			System.out.println("------------------------------------------------");	
//		}
//		System.out.println("------------------------------------------------");	
		
//		this.cellSize = cellSize;
	}

	public ArrayList<Ship> getBattleField() {
		return battleField;
	}

	public boolean isShipNotDetached(Ship ctrlShip) {
		for(Ship ship : battleField) {
			if(ship.isShipNotDetached(ctrlShip)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkHit(int x, int y) {
		for(Ship ship : battleField) {
			if(ship.checkShipHit(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkAnyShipAlive() {
		for(Ship ship : battleField) {
			if(ship.isShipAlive()) {
				return true;
			}
		}
		return false;
	}
}
