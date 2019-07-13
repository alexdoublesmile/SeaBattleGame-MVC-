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

	private final String CONFIRM_OPPONENT = "С кем желаете сыграть партию?";
	private final String CONFIRM_PLAYER_VIEW = "В каком виде отрисовывать поле Морского Боя?";
	private final String CONFIRM_OPPONENT_VIEW = "В каком виде отрисовывать поле Морского Боя для второго Игрока?";
	private final String CONFIRM_AI_POWER = "С насколько мощным соперником Вы желаете сразиться?";
	private final String CONFIRM_SHIPS_ARRANGMENT = "Как будете расставлять корабли?";
	private final String START_THE_GAME = "Добро Пожаловать в игру Морской Бой! \n Введите Ваше Имя";
	private final String INPUT_SECOND_PLAYER_NAME = "Введите Имя второго игрока";
	private final String ERROR_INPUT_MESSAGE = "Необходимо ввести имя игрока или его псевдоним в поле";
	private final String ERROR_MESSAGE = "Этот выбор уже скоро будет доступен :)";

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
		String[] answers = { "С компьютером", "С человеком", };
		return JOptionPane.showOptionDialog(this, message, "Выбор соперника", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}

	public int displayViewConfirmMessage(String message) {
		String[] answers = { "3D Version", "Console version" };
		return JOptionPane.showOptionDialog(this, message, "Выбор режима отображения", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}

	public int displayAIPowerConfirmMessage(String message) {
		String[] answers = { "Очень глупенький ИИ", "Середнячок", "Гроссмейстер" };
		return JOptionPane.showOptionDialog(this, message, "Выбор Искусственного Интеллекта",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, answers, answers[2]);
	}

	public int displayCreateShipMethodConfirmMessage(String message) {
		String[] answers = { "Автоматически", "Вручную (в разработке)" };
		return JOptionPane.showOptionDialog(this, message, "Выбор расстановки", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, answers, answers[1]);
	}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Выбор соперника", JOptionPane.PLAIN_MESSAGE);
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
