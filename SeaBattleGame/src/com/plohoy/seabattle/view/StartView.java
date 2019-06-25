package com.plohoy.seabattle.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StartView extends JFrame {

	private int viewChoice;
	private String CONFIRM_VIEW = "Будете играть в 3D версию?";

	public StartView() {
		
		viewChoice = displayConfirmMessage(CONFIRM_VIEW);
	}
	
	public int getViewChoice() {
		return viewChoice;
	}

	public int displayConfirmMessage(String message) {
		
		return JOptionPane.showConfirmDialog(this, message, "", 2);
	}
}
