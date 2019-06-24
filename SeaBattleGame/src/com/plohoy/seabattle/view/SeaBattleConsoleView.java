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
	public int getMOUSE_BUTTON_LEFT() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMOUSE_BUTTON_RIGHT() {
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
	public int getFIELD_PLAYER_PX_SIZE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFIELD_OPP_PX_SIZE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCELL_PLAYER_PX_SIZE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCELL_OPP_PX_SIZE() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void displayMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	
}
