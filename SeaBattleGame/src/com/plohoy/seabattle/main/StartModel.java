package com.plohoy.seabattle.main;

import com.plohoy.seabattle.view.Game3DView;
import com.plohoy.seabattle.view.GameConsoleView;
import com.plohoy.seabattle.view.GameView;

public class StartModel {

	public GameView viewModeChoose(int viewChoice) {
		GameView newView = null;

		switch(viewChoice) {
			case(0):
				newView = new Game3DView();
				break;
			case(1):
				newView = new GameConsoleView();
				break;
			default:
				System.exit(0);
		}
		return newView;
	}
}
