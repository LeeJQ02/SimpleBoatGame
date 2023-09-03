
public class Boat extends GameObject{
	private String name;
	//Constructor
	public Boat(){
		setPosition(0);
		setName(null);
	}
	public Boat(int p, String nm ) {
		super(p);
		setName(nm);
	}
	//Setter/Getters
	public void setName(String nm) {
		name = nm;
	}
	public String getName() {
		return name;
	}
	//Other Methods
	public void increasePosition(int p){
		setPosition(getPosition()+p);
		if (getPosition()>=100) {
			setPosition(100);}
	}
	
	public void decreasePosition(int p) {
		setPosition(getPosition()-p);
		if (getPosition()<0) {
			setPosition(0);
		}
	}
	//To String
	public String toString() {
		return String.format("Boat %s", super.toString());
	}
}
