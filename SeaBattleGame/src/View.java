import java.util.HashMap;

public interface View {

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
}
