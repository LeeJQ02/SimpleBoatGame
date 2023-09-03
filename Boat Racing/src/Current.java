
public class Current extends GameObject {
	
	private int boost;
	//Constructor
	public Current(int p, int b) {
		super(p);
		setBoost(b);
	}
	//Setter/Getters
	public void setBoost(int b) {
		boost = b;
	}
	public int getBoost() {
		return boost;
	}
	//To String
	public String toString() {
		return String.format("Current BOOSTED the boat %d steps FORWARD!", boost);
	}
}
