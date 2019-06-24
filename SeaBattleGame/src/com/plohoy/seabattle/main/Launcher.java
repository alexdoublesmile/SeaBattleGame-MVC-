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
		
		SeaBattleView theView = newStartModel.viewModeChoose(newStartView.getViewChoice());
		
		SeaBattleModel theModel = new SeaBattleModel();
			
		SeaBattleController theController = new SeaBattleController(theModel, theView);
		
		
	}
}
