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
        System.out.println("�� ���� ��� �������� �������.. )");
	}
	
	@ Override
	public void dryShot() {
        System.out.println("����, ������!:)");
	}
	
	@ Override
	public void niceShot() {
        System.out.println("�������� �������! �����!");
	}
	
	@ Override
	public void deathShot() {
        System.out.println("���������� �������! ����!");
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
        System.out.println("�������, ����������, ���-�� ����� �������..");
    }
	
	@ Override
    public void setNumberPlease() {
        System.out.println("�������, ���������, �������� � ���� �����");
    }
	
	@ Override
	public void incorrectChoice() {
        System.out.println("��������, ����������, ���� �� ������������ ��������");
	}
	
	
}
