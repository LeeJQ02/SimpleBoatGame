import java.util.Random;
import java.util.ArrayList;
import java.util.Collections; 

public class River {
	Random random = new Random();
	private ArrayList<GameObject> river=new ArrayList();
	private Boat boat1 =new Boat(0, "B1");
	private Boat boat2 =new Boat(0, "B2");
	
	//Constructor
	public River() {
		for (int i=0;i<=100;i++) {
			river.add(null);
		}
		boat1.setPosition(0);
		boat2.setPosition(0);
		river.set(0, boat1);
		river.set(0, boat2);
		generateGameObjects();
	}
	//Setter/Getters
	public Boat getBoat1() {
		return boat1;
	}
	public Boat getBoat2() {
		return boat2;
	}
	public int getBoat1Position() {
		return boat1.getPosition();
	}
	
	public void setBoat1Position(int p) {
		boat1.setPosition(p);
	}
	
	public int getBoat2Position() {
		return boat2.getPosition();
	}
	
	public void setBoat2Position(int p) {
		boat2.setPosition(p);
	}
	
	public GameObject getGameObject(int p) {
		if (p>100) {
			p=100;
		}
		if (p<0) {
			p=0;
		}
		return river.get(p);
	}
	//Other Methods
	public void generateGameObjects(){
		//Generate random number without duplication
		ArrayList<Integer> positions = new ArrayList();
		for (int i=1;i<100;i++) {
			positions.add(i);
		}
		//Shuffle the list of 1 to 100 and take the first n values as the generated random numbers
		Collections.shuffle(positions);
		
		//Determine the number of objects to be generated 10-15 each
		for (int i=0;i<(11 +random.nextInt(5));i++) {
			river.set(positions.get(i),new Trap((positions.get(i)),1+random.nextInt(5)));
		}
		
		for (int i=16;i<(27 +random.nextInt(5));i++){
			river.set(positions.get(i),new Current(positions.get(i),1+random.nextInt(5)));
		}
	}
	
	public void resetRiver() {
		for (int i=0;i<100;i++) {
			river.set(i, null);
			}
		river.set(boat1.getPosition(), null);
		river.set(boat2.getPosition(), null);
		boat1.setPosition(0);
		boat2.setPosition(0);
		river.set(0, boat1);
		river.set(0, boat2);
		generateGameObjects();
	}
	
	public void setIndex(int p,Boat s) {
		river.set(p,s);
	}
	

	public void increaseBoat1(int p) {
		if (boat1.getPosition()!=boat2.getPosition()) {
			river.set(boat1.getPosition(),null);
			}
		boat1.increasePosition(p);
		
	}
	
	public void increaseBoat2(int p) {
		if (boat1.getPosition()!=boat2.getPosition()) {
			river.set(boat2.getPosition(),null);
			}
		boat2.increasePosition(p);
	}
	public void decreaseBoat1(int p) {
		if (boat1.getPosition()!=boat2.getPosition()) {
			river.set(boat1.getPosition(),null);
			}
		boat1.decreasePosition(p);
	}
	
	public void decreaseBoat2(int p) {
		if (boat1.getPosition()!=boat2.getPosition()) {
			river.set(boat2.getPosition(),null);
			}
		boat2.decreasePosition(p);
	}
	
	public void showRiver() {
		for (Object r: river) {
			if(r instanceof Boat) {
				if(((Boat) r).getName().equals("B1"))
				System.out.print("B1 ");
				else if(((Boat) r).getName().equals("B2"))
				System.out.print("B2 ");
			}
			else if(r instanceof Trap) {
				System.out.print("T ");
			}
			else if (r instanceof Current) {
				System.out.print("C ");
			}
			else System.out.print("_ ");
		}
		System.out.println("");
		
	}
	}
	

	
