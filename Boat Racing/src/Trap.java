
public class Trap extends GameObject {
	
	private int back;
	//Constructor
	public Trap(int p, int b) {
		super(p);
		setBack(b);
	}
	//Setter/Getters
	public void setBack(int b) {
		back = b;
	}
	
	public int getBack() {
		return back;
	}
	//To String
	public String toString() {
		return String.format("Trap PUSHED the boat %d steps BACK!", back);
	}
	
}
