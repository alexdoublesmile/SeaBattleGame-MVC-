package com.plohoy.seabattle.view;

import com.plohoy.seabattle.model.*;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public interface SeaBattleView {

//	void showBattleField(Object obj);
//	void displayErrorMessage(String message);
	void addShotListener(MouseListener shotListener);


	int getBattlefieldSize();

//	void paintShips(Graphics g, JPanel panel, Field field);

//	JPanel getPlayerBattleFieldPanel();
//
//	JPanel getAiBattleFieldPanel();

//	void paintField(Field humanField, Field aIField);
	
	int getMOUSE_BUTTON_LEFT();

	int getMOUSE_BUTTON_RIGHT();

//	void repaintAIField(Graphics2D g2);

//	void repaintPlayerField(Graphics2D g2);

//	void repaintWithShips(Graphics2D g2, boolean hide);

//	Graphics2D getFieldView();

//	void viewPlayer(Field playerField, Shots playerShots, Labels playerLabels);
//
//	void viewOpponent(Field opponentField, Shots opponentShots, Labels opponentLabels);

	void viewGame(Field playerField, Shots playerShots, Labels playerLabels, Field opponentField, Shots opponentShots,
			Labels opponentLabels);

	JPanel getPlayerBattleFieldPanel();

	JPanel getOpponentBattleFieldPanel();

	int getFIELD_PLAYER_PX_SIZE();

	int getFIELD_OPP_PX_SIZE();

	int getCELL_PLAYER_PX_SIZE();

	int getCELL_OPP_PX_SIZE();



	void displayMessage(String message);
}

/*
  	void startGame();
	void chooseEnemy();
	void setShipsLocation();
	void startBattle();
	void showBattleField(HashMap<String, Integer> field);
	void doubleVisit();
	void dryShot();
	void niceShot();
	void deathShot();
	void tempResult(Player player, View view, BattleField field);
	void result();
	void playAgain();
	
	void repeatPlease();
	void setNumberPlease();
	void incorrectChoice();
	
	
	class Input {
	
	private int someNumber = 0;
	private String someString = "";
	
	public String getSomeString() {
		return someString;
	}

	public void setSomeString(String someString) {
		this.someString = someString;
	}
		
    public int getSomeNumber() {
		return someNumber;
	}

	public void setSomeNumber(int someNumber) {
		this.someNumber = someNumber;
	}
	
	public Scanner getScanner() {
		  Scanner scanString = new Scanner(System.in);
		  return scanString;
		}
	
	public String anyString() {
	  String s = getScanner().nextLine();
	  return s;
	}
		
	public void closeScanner() {
		getScanner().close();
		}
	
	
	public void checkStringIsReal(View view) {
		setSomeString(anyString());
		while (someString.equals("") || someString.equals(" ")) {
        	view.repeatPlease();
        	setSomeString(anyString());
        }
	}
	
	public void checkStringIsOnlyNumber(View view) {
		setSomeString(anyString());
	  	while (!(getSomeString().matches("[0-9]+"))) {
	  		view.setNumberPlease();
	  		setSomeString(anyString());
	  	} 
	  	setSomeNumber(getNumberFromSomeString(getSomeString()));
	} 
	
	public int getNumberFromSomeString(String someString) {
		int number = Integer.parseInt(someString);
		return number;
	}
}



class Controller {
	
	private static Input input = new Input();
	
	void execute() {
		View view = new ConsoleView();
		Player player = new Player();
		view.startGame();	
		startSettings(player, view);		
	}
	
	void startSettings(Player player, View view) {
		Input input = new Input();
		Mode mode = new Mode();	
		input.checkStringIsOnlyNumber(view);
		mode.setPlayMode(input.getSomeNumber());
		if(!(mode.getPlayMode()  == 1 || mode.getPlayMode()  == 2)) {
        	view.incorrectChoice();
        	startSettings(player, view);
        } else if (mode.getPlayMode()  == 2) {
        	newGameWithAI(player, view);
        } else  {
            newGameWithHuman(player, view);
        }
	}
	
	void newGameWithHuman(Player player, View view) {
		// ToDo
	}
	
	void newGameWithAI(Player player, View view) {
		BattleField field = new BattleField();
		field.initField();
		view.setShipsLocation();
		field.createField();
		field.createEnemyField();
		startBattle(player, view, field);
	}
	
	void startBattle(Player player, View view, BattleField field) {
		view.startBattle();
		while(field.checkBattleForce()) {
			view.showBattleField(field.getField());
			field.makeShot();
			field.takeShot(field.getField(), input.getSomeString(), view);
			view.tempResult(player, view, field);
		}
		finalResult(player, view);
	}
	
	void finalResult(Player player, View view) {
		view.result();
		view.playAgain();
		playAgain();
	}
	
	void playAgain() {
		// ToDo
		input.closeScanner();
		System.exit(0);
	}
}


	void createField() {
		//TODO
	}
	
	void createEnemyField() {
		//TODO
	}
	
	void makeShot() {
		//TODO
		
	}

	void takeShot(HashMap<String, Integer> field, String s, View view) {
		view.showBattleField(field);
		switch(field.get(s)) {
		case(0):	
			if(!(checkNearbyPlaces(field, s))) {
				view.deathShot();
				field.put(s, 3);
				takeShot(field, s, view);
				break;				
			} else {
				view.niceShot();
				field.put(s, 2);
				takeShot(field, s, view);
				break;
			}
		case(1):
			view.dryShot();
			break;
		case(2):
		case(3):
			view.doubleVisit();
			takeShot(field, s, view);
			break;
		default:
			view.repeatPlease();
			takeShot(field, s, view);
			break;
		}
	}
	
	boolean checkNearbyPlaces(HashMap<String, Integer> field, String s) {
		//TODO
		return true;
	}
	
	void showField() {
		for (HashMap.Entry<String, Integer> entry : getField().entrySet()) {
			System.out.println(" Поле " + entry.getKey () + " - " + entry.getValue());
	    }
	}
	
	boolean checkBattleForce() {
		for (HashMap.Entry<String, Integer> entry : getField().entrySet()) {
	        setBattleForce(entry.getValue());  
	        if(getBattleForce() == 0) {
	        	return true;
	        }
	    }
		return false;
	}
	
	
*/