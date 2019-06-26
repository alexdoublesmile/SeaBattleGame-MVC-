package com.plohoy.seabattle.ai;

import java.util.Random;

public class AIMakeShot {

	private int xShotCoord;
	private int yShotCoord;
	private Random random;
	
	public int getXShotCoord() {
		return xShotCoord;
	}
	
	public int getYShotCoord() {
		return yShotCoord;
	}
	
	public void makeRandomShot(int fieldSize) {
		random = new Random();
		xShotCoord = random.nextInt(fieldSize);
		yShotCoord = random.nextInt(fieldSize);
	}
}
