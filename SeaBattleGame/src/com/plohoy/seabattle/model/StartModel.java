package com.plohoy.seabattle.model;

import com.plohoy.seabattle.view.SeaBattle3DView;
import com.plohoy.seabattle.view.SeaBattleConsoleView;
import com.plohoy.seabattle.view.SeaBattleView;

public class StartModel {


	public SeaBattleView viewModeChoose(int viewChoice) {
		SeaBattleView newView = null;

		switch(viewChoice) {
		case(0):
			newView = new SeaBattle3DView();
			break;
		case(1):
			newView = new SeaBattleConsoleView();
			break;
		case(2):
			newView = new SeaBattleConsoleView();
			System.exit(0);
			break;
		default:
			break;
		}
		return newView;
	}
}
