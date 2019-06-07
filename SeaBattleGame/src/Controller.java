
public class Controller {
	void execute() {
		View view1 = new ConsoleView();
		View view2 = new RealView();
		Player player1 = new Player();
		Player player2 = new Player();
		Mode mode = new Mode();
		view1.startGame();
		startSettings();
		view1.chooseEnemy();
		
	}
	
	void startSettings(Player player, Mode mode, View view) {
		
	}
}
