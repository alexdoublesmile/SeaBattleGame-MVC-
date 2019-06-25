package com.plohoy.seabattle.view;

import com.plohoy.seabattle.model.*;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public interface SeaBattleView {

	void addShotListener(MouseListener shotListener);

	int getBattlefieldSize();

	void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots,
			Labels opponentLabels);

	JPanel getPlayerBattleFieldPanel();

	JPanel getOpponentBattleFieldPanel();

	void displayMessage(String message);


	int checkIsItLabel();


	int checkIsItShot();


	String getLOOSER_MESSAGE();


	String getWINNER_MESSAGE();


	void setBattlefieldSize(int battlefieldSize);

	void displayConfirmMessage(String message);

	String getAGAIN_MESSAGE();

	void setInvisible();

	void setVisible();

	int getCELL_PX_SIZE();

	int getFIELD_PX_SIZE();

	int getPlayAgainAnswer();

	void setPlayAgainAnswer(int answer);
}