package com.plohoy.seabattle.main;

import com.plohoy.seabattle.ai.AIPlayer;
import com.plohoy.seabattle.model.GameModel;
import com.plohoy.seabattle.view.*;

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
