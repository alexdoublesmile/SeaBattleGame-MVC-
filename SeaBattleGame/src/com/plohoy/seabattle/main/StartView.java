package com.plohoy.seabattle.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StartView extends JFrame {

	private int viewChoice;
	private int aIPowerChoice;
	private int playModeChoice;
	private int shipsArrangementChoice;
	private String CONFIRM_VIEW = "В какой тип Морского Боя желаете сыграть?";
	private String CONFIRM_AI_POWER = "С насколько мощным соперником Вы желаете сразиться?";
	private String CONFIRM_HUMAN_OPPONENT = "С кем желаете сыграть партию?";
	private String CONFIRM_SHIPS_ARRANGMENT = "Как будете расставлять корабли?";
	private String ERROR_MESSAGE = "Этот выбор уже скоро будет доступен :)";

	public StartView() {
		viewChoice = displayViewConfirmMessage(CONFIRM_VIEW);
		playModeChoice = displayOpponentHumanConfirmMessage(CONFIRM_HUMAN_OPPONENT);
		while(playModeChoice > 0) {
				displayErrorMessage(ERROR_MESSAGE);
				playModeChoice = displayOpponentHumanConfirmMessage(CONFIRM_HUMAN_OPPONENT);
		}
		if(playModeChoice == 0 ) {
			aIPowerChoice = displayOpponentPowerConfirmMessage(CONFIRM_AI_POWER);
			while(aIPowerChoice > 0) {
				displayErrorMessage(ERROR_MESSAGE);
				aIPowerChoice = displayOpponentPowerConfirmMessage(CONFIRM_AI_POWER);
			}
		}
		shipsArrangementChoice = displayCreateShipMethodConfirmMessage(CONFIRM_SHIPS_ARRANGMENT);
		while(shipsArrangementChoice > 0) {
			displayErrorMessage(ERROR_MESSAGE);
			shipsArrangementChoice = displayCreateShipMethodConfirmMessage(CONFIRM_SHIPS_ARRANGMENT);
		}
	}

	public int displayViewConfirmMessage(String message) {
		String[] answers = {"3D Version", "Console version"};
		return JOptionPane.showOptionDialog(
				this, message, "Выбор режима отображения", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}
	
	public int displayOpponentPowerConfirmMessage(String message) {
		String[] answers = {"Очень глупенький ИИ", "Середнячок (в раработке)", "Гроссмейстер (в раработке)"};
		return JOptionPane.showOptionDialog(
				this, message, "Выбор Искусственного Интеллекта", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[2]);
	}
	
	public int displayCreateShipMethodConfirmMessage(String message) {
		String[] answers = {"Автоматически", "Вручную (в раработке)"};
		return JOptionPane.showOptionDialog(
				this, message, "Выбор расстановки", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}
	
	public int displayOpponentHumanConfirmMessage(String message) {
		String[] answers = {"С компьютером", "С человеком (в раработке)", };
		return JOptionPane.showOptionDialog(
				this, message, "Выбор соперника", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}
	
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(
				this, message, "Выбор соперника",
				JOptionPane.PLAIN_MESSAGE);
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
	
	public int getShipsArrangementChoice() {
		return shipsArrangementChoice;
	}
}
