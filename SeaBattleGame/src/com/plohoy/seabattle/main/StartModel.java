package com.plohoy.seabattle.main;

import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.interfaces.View;
import com.plohoy.seabattle.player.AIPlayer;
import com.plohoy.seabattle.player.HumanPlayer;
import com.plohoy.seabattle.view.Game3DView;
import com.plohoy.seabattle.view.GameConsoleView;

public class StartModel {

	public Player opponentChoose(int opponentChoice, View theView, int thePower, int shipsArrangement, String name) {
		Player newPlayer = null;
		switch (opponentChoice) {
		case (0):
			newPlayer = new AIPlayer(thePower);
			break;
		case (1):
			newPlayer = new HumanPlayer(theView, shipsArrangement, name);
			break;
		default:
			System.exit(0);
		}
		return newPlayer;
	}

	public View viewChoose(int viewChoice) {
		View newView = null;
		switch (viewChoice) {
		case (0):
			newView = new Game3DView();
			break;
		case (1):
			newView = new GameConsoleView();
			break;
		default:
			System.exit(0);
		}
		return newView;
	}
}
