import java.util.Scanner;

public class Input {
	
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
