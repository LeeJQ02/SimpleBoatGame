
public class GameObject {
	private int position;
	//Constructor
	public GameObject() {
		position=0;
	}
	public GameObject(int p) {
		position = p;
	}
	//Setter/Getter
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int p) {
		position = p;
	}
	//To String
	public String toString() {
		return String.format("Position: %d",position);
	}
}
