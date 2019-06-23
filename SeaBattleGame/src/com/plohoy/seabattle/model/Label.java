package com.plohoy.seabattle.model;

public class Label {
	
	private int xCoord, yCoord;
	private boolean label;
	
	Label(int x, int y, boolean label) {
		
		this.xCoord = x;	
		this.yCoord = y;	
		this.label = label;
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
	public boolean isLabel() {
		return label;
	}

}
