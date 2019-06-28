package com.plohoy.seabattle.ai;

import java.util.Random;

public class AIModel {
	
	

	private int xShotCoord;
	private int yShotCoord;
	private Random random;
	private int thePower = 0;
	
	public AIModel(int power) {
		
		this.thePower = power;
	}
	
	public int getXShotCoord() {
		return xShotCoord;
	}
	
	public int getYShotCoord() {
		return yShotCoord;
	}
	
	public void aIMakesShot(int fieldSize) {
		switch(thePower) {
		case(0):
			random = new Random();
			xShotCoord = random.nextInt(fieldSize);
			yShotCoord = random.nextInt(fieldSize);
			break;
		case(1):
			random = new Random();
			xShotCoord = random.nextInt(fieldSize);
			yShotCoord = random.nextInt(fieldSize);
			
//			smartShot(fieldSize);
			break;
		case(2):
			random = new Random();
			xShotCoord = random.nextInt(fieldSize);
			yShotCoord = random.nextInt(fieldSize);
			
//			amazingShot(fieldSize);
			break;
		default:
			;
		}
	}
	
	public void smartShot(int fieldSize) {
		
	}
	
	public void amazingShot(int fieldSize) {
		
	}
}
