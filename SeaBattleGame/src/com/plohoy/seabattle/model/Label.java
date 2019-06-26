package com.plohoy.seabattle.model;

public class Label {
	
	private int xCoord, yCoord;
	private boolean label;
	
	Label(int x, int y, boolean label) {
		
		this.xCoord = x;	
		this.yCoord = y;	
		this.label = label;
	}
	public int getXCoord() {
		return xCoord;
	}
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getYCoord() {
		return yCoord;
	}
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public boolean isLabel() {
		return label;
	}

}
