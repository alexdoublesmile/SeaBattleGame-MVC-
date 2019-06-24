package com.plohoy.seabattle.model;

import java.util.ArrayList;

public class Shots {

	private ArrayList<Shot> shots;
	
	Shots() {
			
		shots = new ArrayList<Shot>();
	}
	
	public ArrayList<Shot> getShots() {
		return shots;
	}
	
	public void add(int x, int y, boolean shot) {
		shots.add(new Shot(x, y, shot));
	}
	
	public boolean shotSamePlace(int x, int y) {
		for(Shot shot : shots) {
			if(shot.getXCoord() == x && shot.getYCoord() == y) {
				return true;
			}
		}
		return false;
	}
}
