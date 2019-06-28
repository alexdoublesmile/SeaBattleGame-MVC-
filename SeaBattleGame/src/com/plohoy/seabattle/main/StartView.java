package com.plohoy.seabattle.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StartView extends JFrame {

	private int viewChoice;
	private int aIPowerChoice;
	private int playModeChoice;
	private int shipsArrangementChoice;
	private String CONFIRM_VIEW = "� ����� ��� �������� ��� ������� �������?";
	private String CONFIRM_AI_POWER = "� ��������� ������ ���������� �� ������� ���������?";
	private String CONFIRM_HUMAN_OPPONENT = "� ��� ������� ������� ������?";
	private String CONFIRM_SHIPS_ARRANGMENT = "��� ������ ����������� �������?";
	private String ERROR_MESSAGE = "���� ����� ��� ����� ����� �������� :)";

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
				this, message, "����� ������ �����������", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}
	
	public int displayOpponentPowerConfirmMessage(String message) {
		String[] answers = {"����� ���������� ��", "���������� (� ���������)", "������������ (� ���������)"};
		return JOptionPane.showOptionDialog(
				this, message, "����� �������������� ����������", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[2]);
	}
	
	public int displayCreateShipMethodConfirmMessage(String message) {
		String[] answers = {"�������������", "������� (� ���������)"};
		return JOptionPane.showOptionDialog(
				this, message, "����� �����������", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}
	
	public int displayOpponentHumanConfirmMessage(String message) {
		String[] answers = {"� �����������", "� ��������� (� ���������)", };
		return JOptionPane.showOptionDialog(
				this, message, "����� ���������", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}
	
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(
				this, message, "����� ���������",
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
