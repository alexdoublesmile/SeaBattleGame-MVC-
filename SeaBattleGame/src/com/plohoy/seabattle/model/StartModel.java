package com.plohoy.seabattle.model;

import com.plohoy.seabattle.view.SeaBattle3DView;
import com.plohoy.seabattle.view.SeaBattleConsoleView;
import com.plohoy.seabattle.view.SeaBattleView;

public class StartModel {


	public SeaBattleView viewModeChoose(int viewChoice) {
		SeaBattleView newView;

		 
		if(viewChoice == 0) {
			newView = new SeaBattle3DView();
		} else {
			newView = new SeaBattleConsoleView();
		}
		return newView;
	}
}
