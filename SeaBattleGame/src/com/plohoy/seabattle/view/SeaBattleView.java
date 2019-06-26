package com.plohoy.seabattle.view;

import com.plohoy.seabattle.model.*;

public interface SeaBattleView {

	void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots,
			Labels opponentLabels);
		
	void setBattlefieldSize(int battlefieldSize);

	int getBattlefieldSize();

	int getPlayAgainAnswer();

	void displayMessage(String message);

	void displayConfirmMessage(String message);

	String getAGAIN_MESSAGE();

	String getREPEAT_SHOT_MESSAGE();

	String getSINK_THE_SHIP_MESSAGE();

	String getLOOSER_MESSAGE();

	String getWINNER_MESSAGE();

	int getYShotCoord();

	int getXShotCoord();
}