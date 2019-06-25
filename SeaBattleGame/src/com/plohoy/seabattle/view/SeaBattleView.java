package com.plohoy.seabattle.view;

import com.plohoy.seabattle.model.*;

public interface SeaBattleView {

	void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots,
			Labels opponentLabels);

	void repaintPlayerView();

	void repaintOpponentView();
		
	void setBattlefieldSize(int battlefieldSize);

	int getBattlefieldSize();

	int getCELL_PX_SIZE();

	int getFIELD_PX_SIZE();

	int checkIsItShot();

	int checkIsItLabel();

	int getPlayAgainAnswer();

	void displayMessage(String message);

	void displayConfirmMessage(String message);

	String getAGAIN_MESSAGE();

	String getREPEAT_SHOT_MESSAGE();

	String getSINK_THE_SHIP_MESSAGE();

	String getLOOSER_MESSAGE();

	String getWINNER_MESSAGE();

	void setInvisible();

	void setVisible();
}