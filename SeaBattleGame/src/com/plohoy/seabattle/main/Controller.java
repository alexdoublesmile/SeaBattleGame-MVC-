package com.plohoy.seabattle.main;

import java.util.Random;

import com.plohoy.seabattle.interfaces.Player;
import com.plohoy.seabattle.model.Cell;
import com.plohoy.seabattle.model.Ship;
import com.plohoy.seabattle.player.AIPlayer;
import com.plohoy.seabattle.player.HumanPlayer;

public class Controller {

	private Player player;
	private Player opponent;

	private Random random;

	public Controller(Player firstPlayer, Player secondPlayer) {

		setTheFirstPlayer(firstPlayer, secondPlayer);

		createShips();

		viewBattleField();

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

	public void createShips() {

		if (!player.isAI() && ((HumanPlayer) player).isCreateShipsManually()) {
			createShipsManually(player, opponent);
		} else if (!opponent.isAI() && ((HumanPlayer) opponent).isCreateShipsManually()) {
			createShipsManually(opponent, player);
		} else {
			player.createShipsAuto();
			opponent.createShipsAuto();
		}
	}

	public void viewBattleField() {

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

	public void makeShot(Player attackingPlayer, Player defensivePlayer, int x, int y) {

		if (!attackingPlayer.isAI() && attackingPlayer.getShots().shotSamePlace(x, y)) {
			attackingPlayer.getTheView().displayMessage(attackingPlayer.getTheView().getREPEAT_SHOT_MESSAGE());
			attackingPlayer.getTheView().resetShotCoords();
			startTheBattle(attackingPlayer, defensivePlayer);
		}
		attackingPlayer.getShots().add(x, y, true);
		if (defensivePlayer.getShips().checkHit(x, y)) {
			if (attackingPlayer.isAI()) {
				((AIPlayer) attackingPlayer).getAIShot().setLastCell(new Cell(x, y));
			}
			for (Ship ship : defensivePlayer.getShips().getBattleField()) {
				if (!ship.checkShipAlive() && ship.checkShipHit(x, y)) {
					for (Cell cell : ship.getAroundCells()) {
						attackingPlayer.getShots().add(cell.getXCoord(), cell.getYCoord(), true);
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

	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////// Test New Feature
	/////////////////////////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////////
	public void createShipsManually(Player player, Player otherPlayer) {
		player.createField();
		player.getTheView().viewGame(player.getShips(), player.getShots(), player.getLabels(), otherPlayer.getShips(),
				otherPlayer.getShots(), otherPlayer.getLabels(), player.getName(), otherPlayer.getName());
		player.getTheView().setVisible();
		player.getTheView().displayMessage(player.getTheView().getARRANGE_YOUR_SHIPS());
		do {
			System.out.println("начинаем вводить координаты..");
			makeLastCellFromProperCoords(player);
			System.out.println("----- координаты выбраны: (" + (player.getShips().getLastCell().getXCoord() + 1) + ", "
					+ (player.getShips().getLastCell().getYCoord() + 1) + ")");
			System.out.println("проверяем отдельно ли стоит клетка..");

			if (checkLastCellIsAlone(player)) {
				System.out.println("клетка стоит отдельно ..");
				System.out.println("создаем из нее временный однопалубный ..");
				makeTempShipFromLastCell(player);
				System.out.println("----- однопалубный создан:");
				for (Cell cell : player.getShips().getTempShip().getCells()) {
					System.out.println("----- координаты ячейки: (" + (cell.getXCoord() + 1) + ", "
							+ (cell.getYCoord() + 1) + ")");
				}
				System.out.println("проверяем соответствует ли последний оставленный корабль паттерну..");

				if (player.getShips().checkLastShipIsProper()) {
					System.out.println("корабль соответствует ..");
					System.out.println("тогда добавляем новый корабль в коллекцию ..");

					addTempShipToCollection(player, otherPlayer);
					System.out.println("------------ Вся коллекция -------------");
					int n = 1;
					for (Ship ship : player.getShips().getBattleField()) {
						System.out.println("корабль " + n);
						int z = 1;
						for (Cell cell : ship.getCells()) {
							System.out.println("----- координаты ячейки №" + z + ": (" + (cell.getXCoord() + 1) + ", "
									+ (cell.getYCoord() + 1) + ")");
							z++;
						}
						n++;
						System.out.println("");
					}
					System.out.println("копируем значение временного однопалубного в lastShip ..");
					makeLastShipFromTempShip(player);
					System.out.println("----- lastShip обновлен:");
					for (Cell cell : player.getShips().getLastShip().getCells()) {
						System.out.println("----- координаты ячейки: (" + (cell.getXCoord() + 1) + ", "
								+ (cell.getYCoord() + 1) + ")");
					}
				} else {
					player.getTheView().displayMessage(player.getTheView().getLAST_SHIP_IS_EXCESS_MESSAGE());
				}
			} else {
				System.out.println("клетка стоит рядом с какой-то еще ..");
				System.out.println("проверяем соответствует ли создаваемый корабль паттерну..");

				if (player.getShips().checkLastShipIncreasedIsProper()) {

					player.getTheView().repaintView(player.getShips(), player.getShots(), player.getLabels(),
							otherPlayer.getShips(), otherPlayer.getShots(), otherPlayer.getLabels());

					System.out.println("корабль соответствует паттерну ..");

					System.out.println("------------ Вся коллекция -------------");
					int n = 1;
					for (Ship ship : player.getShips().getBattleField()) {
						System.out.println("корабль " + n);
						int z = 1;
						for (Cell cell : ship.getCells()) {
							System.out.println("----- координаты ячейки №" + z + ": (" + (cell.getXCoord() + 1) + ", "
									+ (cell.getYCoord() + 1) + ")");
							z++;
						}
						n++;
						System.out.println("");
					}
//					System.out.println("------------ -------------");
//					System.out.println("------------ -------------");
//					System.out.println("удаляем из коллекции TempShip ..");
//					System.out.println("------------ -------------");
//					System.out.println("------------ -------------");
//
//					removeTempShipFromCollection(player);
//
//					System.out.println("------------ Вся коллекция -------------");
//					int n2 = 1;
//					for (Ship ship : player.getShips().getBattleField()) {
//						System.out.println("корабль " + n);
//						int z2 = 1;
//						for (Cell cell : ship.getCells()) {
//							System.out.println("----- координаты ячейки №" + z2 + ": (" + (cell.getXCoord() + 1) + ", "
//									+ (cell.getYCoord() + 1) + ")");
//							z2++;
//						}
//						n2++;
//						System.out.println("");
//					}
//					System.out.println("------------ -------------");
//					System.out.println("------------ -------------");
//					System.out.println("удаляем из коллекции LastShip ..");
//					System.out.println("------------ -------------");
//					System.out.println("------------ -------------");
//
//					removeLastShipFromCollection(player);
//
//					System.out.println("------------ Вся коллекция -------------");
//					int n3 = 1;
//					for (Ship ship : player.getShips().getBattleField()) {
//						System.out.println("корабль " + n);
//						int z3 = 1;
//						for (Cell cell : ship.getCells()) {
//							System.out.println("----- координаты ячейки №" + z3 + ": (" + (cell.getXCoord() + 1) + ", "
//									+ (cell.getYCoord() + 1) + ")");
//							z3++;
//						}
//						n3++;
//						System.out.println("");
//					}
//
//					addLastCellToLastShip(player, otherPlayer);
//
//					System.out.println("------------ -------------");
//					System.out.println("------------ -------------");
//					System.out.println("добавляем в коллекцию увеличенный LastShip ..");
//					System.out.println("------------ -------------");
//					System.out.println("------------ -------------");
//
//					addLastShipToCollection(player);
//
//					System.out.println("------------ Вся коллекция -------------");
//					int n4 = 1;
//					for (Ship ship : player.getShips().getBattleField()) {
//						System.out.println("корабль " + n);
//						int z4 = 1;
//						for (Cell cell : ship.getCells()) {
//							System.out.println("----- координаты ячейки №" + z4 + ": (" + (cell.getXCoord() + 1) + ", "
//									+ (cell.getYCoord() + 1) + ")");
//							z4++;
//						}
//						n4++;
//						System.out.println("");
//					}

				} else {
					player.getTheView().displayMessage(player.getTheView().getSHIP_IS_EXCESS_MESSAGE());
				}
			}
		} while (!player.getShips().patternIsProper());
		player.getTheView().setInvisible();

	}

	public void makeLastCellFromProperCoords(Player player) {
		do {
			player.getTheView().resetManuallyCoords();
			setManuallyCoords(player);
			if (!player.getShips().checkCellIsTheOne(player.getTheView().getManuallyXCoord(),
					player.getTheView().getManuallyYCoord())) {
				player.getTheView().displayMessage(player.getTheView().getCELL_SHOULD_BE_NEW());
			}
			if (!player.getShips().checkCellIsNotTouchAnyShip(player.getTheView().getManuallyXCoord(),
					player.getTheView().getManuallyYCoord())) {
				player.getTheView().displayMessage(player.getTheView().getCELL_SHOULD_NOT_TOUCH_SHIP());
			}
		} while (!player.getShips().checkCellIsTheOne(player.getTheView().getManuallyXCoord(),
				player.getTheView().getManuallyYCoord())
				|| !player.getShips().checkCellIsNotTouchAnyShip(player.getTheView().getManuallyXCoord(),
						player.getTheView().getManuallyYCoord()));
		player.getShips().setLastCell(
				new Cell(player.getTheView().getManuallyXCoord(), player.getTheView().getManuallyYCoord()));

	}

	public void makeTempShipFromLastCell(Player player) {
		player.getShips().setTempShip(
				new Ship(player.getTheView().getManuallyXCoord(), player.getTheView().getManuallyYCoord()));
	}

	public void makeLastShipFromTempShip(Player player) {
		if (!(player.getShips().getTempShip() == null)) {
			player.getShips().setLastShip(player.getShips().getTempShip());

		}
	}

	public void addTempShipToCollection(Player player, Player otherPlayer) {
		player.getShips().getBattleField().add(player.getShips().getTempShip());
		player.getTheView().repaintView(player.getShips(), player.getShots(), player.getLabels(),
				otherPlayer.getShips(), otherPlayer.getShots(), otherPlayer.getLabels());
	}

	public void addLastCellToLastShip(Player player, Player otherPlayer) {
		player.getShips().getLastShip().getCells().add(player.getShips().getLastCell());
		player.getTheView().repaintView(player.getShips(), player.getShots(), player.getLabels(),
				otherPlayer.getShips(), otherPlayer.getShots(), otherPlayer.getLabels());
	}

	public void removeTempShipFromCollection(Player player) {
		player.getShips().getBattleField().remove(player.getShips().getTempShip());
	}

	public void removeLastShipFromCollection(Player player) {
		player.getShips().getBattleField().remove(player.getShips().getLastShip());
	}

	public void addLastShipToCollection(Player player) {
		player.getShips().getBattleField().add(player.getShips().getLastShip());
	}

	public boolean checkLastCellIsAlone(Player player) {
		for (Ship ship : player.getShips().getBattleField()) {
			if (player.getShips().getLastCell().checkCellTouch(ship.getCells())) {
				return false;
			}
		}
		return true;
	}

	public void setManuallyCoords(Player attackingPlayer) {
		do {
			System.out.print("");
			attackingPlayer.getTheView().setManuallyCoords();
		} while (!attackingPlayer.getTheView().clickIsDone());
	}

	public void setShotCoords(Player attackingPlayer) {
		do {
			System.out.print("");
			attackingPlayer.getTheView().setShotCoords();
		} while (!attackingPlayer.getTheView().shotIsDone());
	}
}
