import java.util.Random;

public class Dice {
	Random randomNum = new Random();
	private int sides;
	private int dicevalue;
	
	//Constructor
	public Dice(int s){
		setSides(s);
		dicevalue=1;
	}
	//Setter/Getters
	public int getDiceValue() {
		return dicevalue;
	}
	
	public void setSides(int s) {
		sides = s;
	}
	public int getSides() {
		return sides;
	}
	public int Roll() {
		System.out.println("Rolling...");
		dicevalue = (1 + randomNum.nextInt(sides));
		return dicevalue;
	}
	//To String
	public String toString() {
		return String.format("rolled %d", dicevalue);
	}
}
