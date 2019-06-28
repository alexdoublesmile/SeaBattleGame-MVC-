package com.plohoy.seabattle.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StartView extends JFrame {

	private int viewChoice;
	private int aIPowerChoice;
	private int playModeChoice;
	private String CONFIRM_VIEW = "В какой тип Морского Боя желаете сыграть?";
	private String CONFIRM_AI_POWER = "С насколько мощным соперником Вы желаете сразиться?";

	public StartView() {	
		viewChoice = displayViewConfirmMessage(CONFIRM_VIEW);
		aIPowerChoice = displayOpponentPowerConfirmMessage(CONFIRM_AI_POWER);
	}

	public int displayViewConfirmMessage(String message) {
		String[] views = {"3D Version",
                "Console version"};
		return JOptionPane.showOptionDialog(
				this, message, "Выбор режима отображения", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, views, views[1]);
	}
	
	public int displayOpponentPowerConfirmMessage(String message) {
		String[] views = {"Никакой",
                "Середнячок (в раработке)", "Гроссмейстер (в раработке)"};
		return JOptionPane.showOptionDialog(
				this, message, "Выбор соперника", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, views, views[2]);
	}
	
	public int getViewChoice() {
		return viewChoice;
	}
	
	public int getAIPowerChoice() {
		return aIPowerChoice;
	}
	
	public int getPlayModeChoice() {
		return playModeChoice;
	}
}
