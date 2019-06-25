package com.plohoy.seabattle.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.plohoy.seabattle.model.Field;
import com.plohoy.seabattle.model.Labels;
import com.plohoy.seabattle.model.Ship;
import com.plohoy.seabattle.model.Shots;

public class SeaBattleConsoleView implements SeaBattleView{

	public SeaBattleConsoleView() {
		System.out.println("Консольный вид...");

	}
//	@Override
//	public void displayErrorMessage(String message) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void addShotListener(MouseListener shotListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBattlefieldSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField,
			Shots opponentShots, Labels opponentLabels) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPlayerBattleFieldPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getOpponentBattleFieldPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int checkIsItLabel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkIsItShot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLOOSER_MESSAGE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWINNER_MESSAGE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBattlefieldSize(int battlefieldSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayConfirmMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAGAIN_MESSAGE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInvisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCELL_PX_SIZE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFIELD_PX_SIZE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlayAgainAnswer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPlayAgainAnswer(int answer) {
		// TODO Auto-generated method stub
		
	}

	
}
