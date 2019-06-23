package com.plohoy.seabattle.model;

public class Shot {

	private int xCoord, yCoord;
	private boolean shot;
	
	Shot(int x, int y, boolean shot) {
		
		this.xCoord = x;	
		this.yCoord = y;	
		this.shot = shot;
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
	public boolean isShot() {
		return shot;
	}
	
}
