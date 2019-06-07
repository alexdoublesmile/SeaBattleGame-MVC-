import java.util.HashMap;

public class ConsoleView implements View {

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
	public void showBattleField(HashMap<String, Integer> field) {
		
	}
	
	@ Override
	public void doubleVisit() {
        System.out.println("Мы сюда уже стреляли сегодня.. )");
	}
	
	@ Override
	public void dryShot() {
        System.out.println("Мимо, мазила!:)");
	}
	
	@ Override
	public void niceShot() {
        System.out.println("Отличный выстрел! Ранил!");
	}
	
	@ Override
	public void deathShot() {
        System.out.println("Прекрасный выстрел! Убил!");
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
