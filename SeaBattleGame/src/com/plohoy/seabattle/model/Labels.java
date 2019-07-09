package com.plohoy.seabattle.model;

import java.util.ArrayList;

public class Labels {

	private ArrayList<Label> labels;

	public Labels() {

		labels = new ArrayList<Label>();
	}

	public ArrayList<Label> getLabels() {
		return labels;
	}

	Label getLabel(int x, int y) {
		for (Label label : labels) {
			if (label.getXCoord() == x && label.getYCoord() == y && (!label.isLabel())) {
				return label;
			}
		}
		return null;
	}

	void removeLabel(Label label) {
		labels.remove(label);
	}

}
