package com.plohoy.seabattle.main;

import java.util.Random;

import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.model.Cell;
import com.plohoy.seabattle.model.Ship;
import com.plohoy.seabattle.player.AIPlayer;

public class Controller {

	private Player player;
	private Player opponent;

	private Random random;

	public Controller(Player firstPlayer, Player secondPlayer) {

		setTheFirstPlayer(firstPlayer, secondPlayer);

		player.createShips();
		opponent.createShips();

		if (!opponent.isAI()) {
			opponent.getTheView().viewGame(opponent.getShips(), opponent.getShots(), opponent.getLabels(),
					player.getShips(), player.getShots(), player.getLabels(), opponent.getName(), player.getName());
		}
		if (!player.isAI()) {
			player.getTheView().viewGame(player.getShips(), player.getShots(), player.getLabels(), opponent.getShips(),
					opponent.getShots(), opponent.getLabels(), player.getName(), opponent.getName());
			player.getTheView().displayMessage(player.getTheView().getSTART_MESSAGE(player.getName()));
			player.getTheView().setVisible();
		} else {
			opponent.getTheView().displayMessage(opponent.getTheView().getSTART_MESSAGE(player.getName()));
			opponent.getTheView().setVisible();
		}

		startTheBattle(player, opponent);
	}

	public void setTheFirstPlayer(Player firstPlayer, Player secondPlayer) {
		random = new Random();
		int numberOfFirstPlayer = random.nextInt(2);
		if (numberOfFirstPlayer == 1) {
			player = firstPlayer;
			opponent = secondPlayer;
		} else {
			opponent = firstPlayer;
			player = secondPlayer;
		}
	}

	public void startTheBattle(Player attackingPlayer, Player defensivePlayer) {
		if (!attackingPlayer.isAI()) {
			setShotCoords(attackingPlayer);
			makeShot(attackingPlayer, defensivePlayer, attackingPlayer.getTheView().getXShotCoord(),
					attackingPlayer.getTheView().getYShotCoord());
		} else {
			attackingPlayer.setCoords(defensivePlayer.getShips());
			makeShot(attackingPlayer, defensivePlayer, attackingPlayer.getXCoord(), attackingPlayer.getYCoord());
		}
	}

	public void setShotCoords(Player attackingPlayer) {
		do {
			System.out.print("");
			attackingPlayer.getTheView().setShotCoords();
		} while (!attackingPlayer.getTheView().shotIsDone());
	}

	public void makeShot(Player attackingPlayer, Player defensivePlayer, int x, int y) {
		if (!attackingPlayer.isAI() && attackingPlayer.getShots().shotSamePlace(x, y)) {
			attackingPlayer.getTheView().displayMessage(attackingPlayer.getTheView().getREPEAT_SHOT_MESSAGE());
			attackingPlayer.getTheView().resetShotCoords();
			startTheBattle(attackingPlayer, defensivePlayer);
		}
		System.out.println("координаты нового выстрела в контроллере: " + x + ", " + y);
		attackingPlayer.getShots().add(x, y, true);
		if (defensivePlayer.getShips().checkHit(x, y)) {
			if (attackingPlayer.isAI()) {
				((AIPlayer) attackingPlayer).getAIShot().setLastCell(new Cell(x, y));
			}
			for (Ship ship : defensivePlayer.getShips().getBattleField()) {
				if (!ship.checkShipAlive() && ship.checkShipHit(x, y)) {
					for (Cell cell : ship.getAroundCells()) {
						attackingPlayer.getShots().add(cell.getXCoord(), cell.getYCoord(), true);
//						defensivePlayer.getTheView().repaintView(defensivePlayer.getShips(), defensivePlayer.getShots(),
//								defensivePlayer.getLabels(), attackingPlayer.getShips(), attackingPlayer.getShots(),
//								attackingPlayer.getLabels());
					}
					if (!defensivePlayer.getShips().checkAnyShipAlive()) {
						if (!attackingPlayer.isAI()) {
							attackingPlayer.getTheView().repaintView(attackingPlayer.getShips(),
									attackingPlayer.getShots(), attackingPlayer.getLabels(), defensivePlayer.getShips(),
									defensivePlayer.getShots(), defensivePlayer.getLabels());
							attackingPlayer.getTheView().displayMessage(
									attackingPlayer.getTheView().getWINNER_MESSAGE(attackingPlayer.getName()));
							attackingPlayer.getTheView().playAgain();
						} else {
							defensivePlayer.getTheView().repaintView(defensivePlayer.getShips(),
									defensivePlayer.getShots(), defensivePlayer.getLabels(), attackingPlayer.getShips(),
									attackingPlayer.getShots(), attackingPlayer.getLabels());
							defensivePlayer.getTheView()
									.displayMessage(defensivePlayer.getTheView().getLOOSER_MESSAGE());
							defensivePlayer.getTheView().playAgain();
						}
					} else {
//						theView.displayMessage(theView.getSINK_THE_SHIP_MESSAGE());
					}
				}
			}
			if (!attackingPlayer.isAI()) {
				attackingPlayer.getTheView().repaintView(attackingPlayer.getShips(), attackingPlayer.getShots(),
						attackingPlayer.getLabels(), defensivePlayer.getShips(), defensivePlayer.getShots(),
						defensivePlayer.getLabels());
				attackingPlayer.getTheView().resetShotCoords();
				setShotCoords(attackingPlayer);
				makeShot(attackingPlayer, defensivePlayer, attackingPlayer.getTheView().getXShotCoord(),
						attackingPlayer.getTheView().getYShotCoord());
			} else {
				defensivePlayer.getTheView().repaintView(defensivePlayer.getShips(), defensivePlayer.getShots(),
						defensivePlayer.getLabels(), attackingPlayer.getShips(), attackingPlayer.getShots(),
						attackingPlayer.getLabels());

				attackingPlayer.setCoords(defensivePlayer.getShips());
				makeShot(attackingPlayer, defensivePlayer, attackingPlayer.getXCoord(), attackingPlayer.getYCoord());
			}
		} else {
			if (!attackingPlayer.isAI() && !defensivePlayer.isAI()) {

				attackingPlayer.getTheView().resetShotCoords();
				attackingPlayer.getTheView().setInvisible();
				attackingPlayer.getTheView().displayMessage(
						attackingPlayer.getTheView().getNEXT_PLAYER_TURN_MESSAGE(defensivePlayer.getName()));

				defensivePlayer.getTheView().repaintView(defensivePlayer.getShips(), defensivePlayer.getShots(),
						defensivePlayer.getLabels(), attackingPlayer.getShips(), attackingPlayer.getShots(),
						attackingPlayer.getLabels());
				defensivePlayer.getTheView().setVisible();
				setShotCoords(defensivePlayer);
				makeShot(defensivePlayer, attackingPlayer, defensivePlayer.getTheView().getXShotCoord(),
						defensivePlayer.getTheView().getYShotCoord());
			} else if (defensivePlayer.isAI()) {
				attackingPlayer.getTheView().resetShotCoords();
				attackingPlayer.getTheView().repaintView(attackingPlayer.getShips(), attackingPlayer.getShots(),
						attackingPlayer.getLabels(), defensivePlayer.getShips(), defensivePlayer.getShots(),
						defensivePlayer.getLabels());
				defensivePlayer.setCoords(attackingPlayer.getShips());
				makeShot(defensivePlayer, attackingPlayer, defensivePlayer.getXCoord(), defensivePlayer.getYCoord());
			} else {
				defensivePlayer.getTheView().repaintView(defensivePlayer.getShips(), defensivePlayer.getShots(),
						defensivePlayer.getLabels(), attackingPlayer.getShips(), attackingPlayer.getShots(),
						attackingPlayer.getLabels());
				setShotCoords(defensivePlayer);
				makeShot(defensivePlayer, attackingPlayer, defensivePlayer.getTheView().getXShotCoord(),
						defensivePlayer.getTheView().getYShotCoord());
			}
		}
	}
}
