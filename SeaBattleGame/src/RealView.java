
public class RealView implements View {

	@ Override
	public void startGame() {
		
	}
	@ Override
	public void chooseEnemy() {
		
	}
	@ Override
	public void setShipsLocation() {
		
	}
	@ Override
	public void startBattle() {
		
	}
	@ Override
	public void showBattleField() {
		
	}
	
	@ Override
	public void tempResult(Player player, View view, BattleField field) {
		
	}
	
	@ Override
	public void result() {
		
	}
	
	@ Override
	public void playAgain() {
		
	}
	
	@ Override
    public void repeatPlease() {
        System.out.println("Введите, пожалуйста, что-то более внятное..");
    }
	
	@ Override
    public void setNumberPlease() {
        System.out.println("Введите, пожлуйста, значение в виде числа");
    }
	
	@ Override
	public void incorrectChoice() {
        System.out.println("Выберите, пожалуйста, одно из предложенных значений");
	}
}