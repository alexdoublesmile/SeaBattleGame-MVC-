package com.plohoy.seabattle.model;

import java.util.LinkedList;

public class Shots {

	private LinkedList<Shot> shots;

	public Shots() {

		shots = new LinkedList<Shot>();
	}

	public void add(int x, int y, boolean shot) {
		shots.add(new Shot(x, y, shot));
	}

	public boolean shotSamePlace(int x, int y) {
		for (Shot shot : shots) {
			if (shot.getXCoord() == x && shot.getYCoord() == y) {
				return true;
			}
		}
		return false;
	}

	public LinkedList<Shot> getShots() {
		return shots;
	}
}
