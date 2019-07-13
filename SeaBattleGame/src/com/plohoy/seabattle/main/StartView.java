package com.plohoy.seabattle.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StartView extends JFrame {

	private int opponentChoice;
	private int playerViewChoice;
	private int opponentViewChoice;
	private int aIPowerChoice;
	private int shipsArrangementChoice;
	private String playerName;
	private String opponentName;

	private final String CONFIRM_OPPONENT = "� ��� ������� ������� ������?";
	private final String CONFIRM_PLAYER_VIEW = "� ����� ���� ������������ ���� �������� ���?";
	private final String CONFIRM_OPPONENT_VIEW = "� ����� ���� ������������ ���� �������� ��� ��� ������� ������?";
	private final String CONFIRM_AI_POWER = "� ��������� ������ ���������� �� ������� ���������?";
	private final String CONFIRM_SHIPS_ARRANGMENT = "��� ������ ����������� �������?";
	private final String START_THE_GAME = "����� ���������� � ���� ������� ���! \n ������� ���� ���";
	private final String INPUT_SECOND_PLAYER_NAME = "������� ��� ������� ������";
	private final String ERROR_INPUT_MESSAGE = "���������� ������ ��� ������ ��� ��� ��������� � ����";
	private final String ERROR_MESSAGE = "���� ����� ��� ����� ����� �������� :)";

	public StartView() {
		playerName = inputPlayerName(START_THE_GAME);

		opponentChoice = displayOpponentConfirmMessage(CONFIRM_OPPONENT);
		if (opponentChoice == 0) {
			aIPowerChoice = displayAIPowerConfirmMessage(CONFIRM_AI_POWER);
		} else {
			opponentName = inputPlayerName(INPUT_SECOND_PLAYER_NAME);
		}

		playerViewChoice = displayViewConfirmMessage(CONFIRM_PLAYER_VIEW);
		if (opponentChoice > 0) {
			opponentViewChoice = displayViewConfirmMessage(CONFIRM_OPPONENT_VIEW);
		}

//		shipsArrangementChoice = displayCreateShipMethodConfirmMessage(CONFIRM_SHIPS_ARRANGMENT);
//		while (shipsArrangementChoice > 0) {
//			displayErrorMessage(ERROR_MESSAGE);
//			shipsArrangementChoice = displayCreateShipMethodConfirmMessage(CONFIRM_SHIPS_ARRANGMENT);
//		}
	}

	public String inputPlayerName(String message) {
		String name = null;
		while (name == null || name.equals("")) {
			name = JOptionPane.showInputDialog(this, message, "");
			if (name == null || name.equals("")) {
				displayErrorMessage(ERROR_INPUT_MESSAGE);
			}
		}
		return name;
	}

	public int displayOpponentConfirmMessage(String message) {
		String[] answers = { "� �����������", "� ���������", };
		return JOptionPane.showOptionDialog(this, message, "����� ���������", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}

	public int displayViewConfirmMessage(String message) {
		String[] answers = { "3D Version", "Console version" };
		return JOptionPane.showOptionDialog(this, message, "����� ������ �����������", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}

	public int displayAIPowerConfirmMessage(String message) {
		String[] answers = { "����� ���������� ��", "����������", "������������" };
		return JOptionPane.showOptionDialog(this, message, "����� �������������� ����������",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, answers, answers[2]);
	}

	public int displayCreateShipMethodConfirmMessage(String message) {
		String[] answers = { "�������������", "������� (� ����������)" };
		return JOptionPane.showOptionDialog(this, message, "����� �����������", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "����� ���������", JOptionPane.PLAIN_MESSAGE);
	}

	public int getPlayerViewChoice() {
		return playerViewChoice;
	}

	public int getOpponentViewChoice() {
		return opponentViewChoice;
	}

	public int getAIPowerChoice() {
		return aIPowerChoice;
	}

	public int getOpponentChoice() {
		return opponentChoice;
	}

	public int getShipsArrangementChoice() {
		return shipsArrangementChoice;
	}

	public String getOpponentName() {
		return opponentName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getSTART_THE_GAME() {
		return START_THE_GAME;
	}

	public String getINPUT_SECOND_PLAYER_NAME() {
		return INPUT_SECOND_PLAYER_NAME;
	}
}
