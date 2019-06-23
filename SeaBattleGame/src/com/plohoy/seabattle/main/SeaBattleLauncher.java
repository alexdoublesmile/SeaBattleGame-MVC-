package com.plohoy.seabattle.main;

import com.plohoy.seabattle.model.SeaBattleModel;
import com.plohoy.seabattle.view.SeaBattle3DView;

public class SeaBattleLauncher {
	
	@SuppressWarnings("unused")
	public static void main (String[] args) {

		SeaBattle3DView theView = new SeaBattle3DView();
		
		SeaBattleModel theModel = new SeaBattleModel();
			
		SeaBattleController theController = new SeaBattleController(theModel, theView);
		
		theView.setVisible(true);
	}
}
