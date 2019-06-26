package com.plohoy.seabattle.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StartView extends JFrame {

	private int viewChoice;
	private String CONFIRM_VIEW = "В какой тип Морского Боя хотите сыграть?";

	public StartView() {	
		viewChoice = displayConfirmMessage(CONFIRM_VIEW);
	}

	public int displayConfirmMessage(String message) {
		String[] views = {"3D Version",
                "Console version"};
		return JOptionPane.showOptionDialog(
				this, message, "Выбор режима", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, views, views[1]);
	}
	
	public int getViewChoice() {
		return viewChoice;
	}
}
