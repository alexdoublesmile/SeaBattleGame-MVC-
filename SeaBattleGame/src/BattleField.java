import java.util.HashMap;

public class BattleField {

	private HashMap<String, Integer> field = new HashMap<String, Integer>(100);
	private HashMap<String, Integer> Enemyfield = new HashMap<String, Integer>(100);
	private int battleForce = 0;
	
	public int getBattleForce() {
		return battleForce;
	}

	public void setBattleForce(int battleForce) {
		this.battleForce = battleForce;
	}

	public HashMap<String, Integer> getField() {
		return field;
	}

	public void setField(HashMap<String, Integer> field) {
		this.field = field;
	}

	public HashMap<String, Integer> getEnemyfield() {
		return Enemyfield;
	}

	public void setEnemyfield(HashMap<String, Integer> enemyfield) {
		Enemyfield = enemyfield;
	}
	
	void createField() {
		//ToDo
	}
	
	void createEnemyField() {
		//ToDo
	}
	
	void makeShot() {
		
		
	}

	void takeShot(HashMap<String, Integer> field, String s) {
		field.put(s, 1);
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
	
	void initField() {
        field.put("a1", 1);
		field.put("a2", 1);
		field.put("a3", 1);
		field.put("a4", 1);
		field.put("a5", 1);
		field.put("a6", 1);
		field.put("a7", 1);
		field.put("a8", 1);
		field.put("a9", 1);
		field.put("a10", 1);
		field.put("b1", 0);
		field.put("b2", 0);
		field.put("b3", 0);
		field.put("b4", 0);
		field.put("b5", 1);
		field.put("b6", 1);
		field.put("b7", 1);
		field.put("b8", 1);
		field.put("b9", 1);
		field.put("b10", 1);
		field.put("c1", 1);
		field.put("c2", 1);
		field.put("c3", 1);
		field.put("c4", 1);
		field.put("c5", 1);
		field.put("c6", 1);
		field.put("c7", 1);
		field.put("c8", 1);
		field.put("c9", 1);
		field.put("c10", 1);
		field.put("d1", 1);
		field.put("d2", 1);
		field.put("d3", 1);
		field.put("d4", 1);
		field.put("d5", 1);
		field.put("d6", 1);
		field.put("d7", 1);
		field.put("d8", 1);
		field.put("d9", 1);
		field.put("d10", 1);
		field.put("e1", 1);
		field.put("e2", 1);
		field.put("e3", 1);
		field.put("e4", 1);
		field.put("e5", 1);
		field.put("e6", 1);
		field.put("e7", 1);
		field.put("e8", 1);
		field.put("e9", 1);
		field.put("e10", 1);
		field.put("f1", 1);
		field.put("f2", 1);
		field.put("f3", 1);
		field.put("f4", 1);
		field.put("f5", 1);
		field.put("f6", 1);
		field.put("f7", 1);
		field.put("f8", 1);
		field.put("f9", 1);
		field.put("f10", 1);
		field.put("g1", 1);
		field.put("g2", 1);
		field.put("g3", 1);
		field.put("g4", 1);
		field.put("g5", 1);
		field.put("g6", 1);
		field.put("g7", 1);
		field.put("g8", 1);
		field.put("g9", 1);
		field.put("g10", 1);
		field.put("h1", 1);
		field.put("h2", 1);
		field.put("h3", 1);
		field.put("h4", 1);
		field.put("h5", 1);
		field.put("h6", 1);
		field.put("h7", 1);
		field.put("h8", 1);
		field.put("h9", 1);
		field.put("h10", 1);
		field.put("i1", 1);
		field.put("i2", 1);
		field.put("i3", 1);
		field.put("i4", 1);
		field.put("i5", 1);
		field.put("i6", 1);
		field.put("i7", 1);
		field.put("i8", 1);
		field.put("i9", 1);
		field.put("i10", 1);
		field.put("j1", 1);
		field.put("j2", 1);
		field.put("j3", 1);
		field.put("j4", 1);
		field.put("j5", 1);
		field.put("j6", 1);
		field.put("j7", 1);
		field.put("j8", 1);
		field.put("j9", 1);
		field.put("j10", 1);
	}
}
