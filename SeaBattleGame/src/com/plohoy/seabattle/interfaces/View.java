package com.plohoy.seabattle.interfaces;

import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.Labels;
import com.plohoy.seabattle.model.Shots;

public interface View {

//	void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots,
//			Labels opponentLabels);

	void playAgain();

//	void setBattlefieldSize(int battlefieldSize);

//	int getBattlefieldSize();

	int getPlayAgainAnswer();

	void displayMessage(String message);

	void displayConfirmMessage(String message);

	String getAGAIN_MESSAGE();

	String getREPEAT_SHOT_MESSAGE();

	String getSINK_THE_SHIP_MESSAGE();

	String getLOOSER_MESSAGE();

	int getYShotCoord();

	int getXShotCoord();

	void setScoreLabel(String scoreLabel);

	String getScoreLabel();

	void repaintView(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField,
			Shots opponentShots, Labels opponentLabels);

	void setShotCoords();

	void setVisible();

	void setInvisible();

	boolean shotIsDone();

	void resetShotCoords();

	String getNEXT_PLAYER_TURN_MESSAGE(String playerName);

	String getSTART_MESSAGE(String playerName);

	String getWINNER_MESSAGE(String playerName);

	void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots,
			Labels opponentLabels, String playerName, String opponentName);

	String getARRANGE_YOUR_SHIPS();

//	String getCELL_SHOULD_BE_NEW();
//
//	String getCELL_SHOULD_NOT_TOUCH_DIAGONAL();

	String getCELL_SHOULD_NOT_TOUCH_SHIP();

	int getManuallyXCoord();

	int getManuallyYCoord();

	void setManuallyCoords();

	void resetManuallyCoords();

	boolean clickIsDone();

	String getCELL_SHOULD_BE_NEW();

	String getSHIP_IS_EXCESS_MESSAGE();

	String getLAST_SHIP_IS_EXCESS_MESSAGE();

//	void resetManuallyCoords();

//	void resetPressCoords();
}