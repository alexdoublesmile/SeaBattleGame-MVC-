package com.plohoy.seabattle.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StartView extends JFrame {

	private int viewChoice;
	private String CONFIRM_VIEW = "В какую версию желаете сыграть?";

	public StartView() {
		
		viewChoice = displayConfirmMessage(CONFIRM_VIEW);
	}
	
	public int getViewChoice() {
		return viewChoice;
	}

	public int displayConfirmMessage(String message) {
		Object[] views = {"3D Version",
                "Console version"};
		return JOptionPane.showOptionDialog(
				this, message, "Выбор режима", JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE, null, views, views[1]);
	}
	
	
}
