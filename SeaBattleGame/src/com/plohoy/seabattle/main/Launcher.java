package com.plohoy.seabattle.main;

import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.player.HumanPlayer;

public class Launcher {

	public static void main(String[] args) {

		new Launcher().exec();
	}

	@SuppressWarnings("unused")
	public void exec() {

		StartView newStartView = new StartView();

		StartModel newStartModel = new StartModel();

		Player firstPlayer = new HumanPlayer(newStartModel.viewChoose(newStartView.getPlayerViewChoice()),
				newStartView.getShipsArrangementChoice(), newStartView.getPlayerName());
		Player secondPlayer = newStartModel.opponentChoose(newStartView.getOpponentChoice(),
				newStartModel.viewChoose(newStartView.getOpponentViewChoice()), newStartView.getAIPowerChoice(),
				newStartView.getShipsArrangementChoice(), newStartView.getOpponentName());

		Controller theController = new Controller(firstPlayer, secondPlayer);

	}
}
