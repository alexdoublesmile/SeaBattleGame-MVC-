package com.plohoy.seabattle.model;

import java.util.ArrayList;

public class Labels {

private ArrayList<Label> labels;
	
	Labels() {
			
		labels = new ArrayList<Label>();
	}
	
	Label getLabel(int x, int y) {
		for(Label label : labels) {
			if(label.getxCoord() == x && label.getyCoord() == y && (!label.isLabel())) {
				return label;
			}
		}
		return null;
	}
	
	void removeLabel(Label label) {
		labels.remove(label);
	}
}
