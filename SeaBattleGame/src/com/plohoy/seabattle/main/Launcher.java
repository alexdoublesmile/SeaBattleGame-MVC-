package com.plohoy.seabattle.main;

import com.plohoy.seabattle.model.*;
import com.plohoy.seabattle.view.*;

public class Launcher {
	
	
	public static void main (String[] args) {
	
		new Launcher().exec();		
	}

	@SuppressWarnings("unused")
	public void exec() {
		StartView newStartView = new StartView();
		StartModel newStartModel = new StartModel();
		
		GameView theView = newStartModel.viewModeChoose(newStartView.getViewChoice());
		
		GameModel theModel = new GameModel();
			
		GameController theController = new GameController(theModel, theView);
		
		
	}
}
