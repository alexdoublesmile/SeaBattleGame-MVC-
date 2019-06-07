
public class Controller {
	
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
