
public interface View {

	void startGame();
	void chooseEnemy();
	void setShipsLocation();
	void startBattle();
	void showBattleField();
	void tempResult(Player player, View view, BattleField field);
	void result();
	void playAgain();
	
	void repeatPlease();
	void setNumberPlease();
	void incorrectChoice();
}
