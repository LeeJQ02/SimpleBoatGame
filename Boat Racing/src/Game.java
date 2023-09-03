import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
	
	private Scanner input = new Scanner(System.in);
	private Dice dice;
	private int turn = 0;
	private River river;
	private String Player1Name;
	private String Player2Name;
	private Leaderboard leaderboard;
	
	//Constructor
	public Game() {
		leaderboard = new Leaderboard();
		dice = new Dice(6);
		river = new River();
		Menu();
	}
	//Setter/Getters
	public void setTurn(int p) {
		turn = p;
	}
	public int getTurn() {
		return turn;
	}
	public void setPlayer1Name(String name){
		if (name.equals("")){
			System.out.println("Name cannot be empty");
			System.out.print("Enter Player 1 Name: ");
			setPlayer1Name(input.nextLine());
		}
		else Player1Name = name;
	}
	public String getPlayer1Name() {
		return Player1Name;
	}
	public void setPlayer2Name(String name){
		if (name.equals("")){
			System.out.println("Name cannot be empty");
			System.out.print("Enter Player 2 Name: ");
			setPlayer2Name(input.nextLine());
		}
		else Player2Name = name;
		
	}
	public String getPlayer2Name() {
		return Player2Name;
	}
	//Other Methods
	public void Menu() {
		System.out.println("TOP 5 HIGHSCORES");
		System.out.println("-------------------------------------");
		leaderboard.ShowHighscore();
		System.out.println("");
		System.out.println("Welcome To A 2-Player Boat Racing Game!");
		System.out.println("Press [1] Start Game");
		System.out.println("Press [2] Instructions");
		System.out.println("Press [3] 6 Sided Dice Mode [Default]");
		System.out.println("Press [4] 20 Sided Dice Mode");
		
		System.out.print("Enter your choice: ");
		String menu = input.nextLine();
		if (menu.equals("1") ) {
			System.out.print("Enter Player 1 Name: ");
			setPlayer1Name(input.nextLine());
			System.out.print("Enter Player 2 Name: ");
			setPlayer2Name(input.nextLine());
			river.resetRiver();
			setTurn(0);
			StartGame();
			Menu();
			
		}
		else if(menu.equals("2")) {
			Instruction();
			Menu();
		}
		else if(menu.equals("3")) {
			SixSidedDice();
			Menu();
		}
		else if(menu.equals("4")) {
			TwentySidedDice();
			Menu();
		}
		else {
			System.out.println("Invalid input");
			Menu();
		}
	}
	public void StartGame() {
		if (river.getBoat1Position()<100 && river.getBoat2Position()<100) {
		river.showRiver();
		System.out.println("Player 1 Position: "+river.getBoat1Position());
		System.out.println("Player 2 Position: "+river.getBoat2Position());
		System.out.println("");}
		while(river.getBoat1Position()<100 && river.getBoat2Position()<100){
			StartTurn();
		
		if (river.getBoat1Position()==100 || river.getBoat2Position()==100) {
				leaderboard.StoreHighscore();
			}
		}
		
	}
	public void StartTurn() {
		turn = turn + 1;
		System.out.println("");
		System.out.println("Turn "+ getTurn());
		Player1Turn();
		if (river.getBoat1Position()==100) {
			System.out.println("Congratulation! " + getPlayer1Name() +" has won the game");
			System.out.printf("You only took %d turns!\n",turn);
			leaderboard.StoreIntoLeaderboard(getPlayer1Name(), turn);
			return;
		}
		Player2Turn();
		if (river.getBoat2Position()==100) {
			System.out.println("Congratulation! "+ getPlayer2Name() +  " has won the game");
			System.out.printf("You only took %d turns!\n",turn);
			leaderboard.StoreIntoLeaderboard(getPlayer2Name(), turn);
			return;
		}
	
			
			
		
		
	}
	public void Player1Turn() {
		System.out.println("");
		System.out.println("Player 1's Turn");
		System.out.println("");
		System.out.print("Press Enter to Roll!");
		input.nextLine();
		dice.Roll();
		System.out.println("Player 1 rolled "+ dice);
		
		river.increaseBoat1(dice.getDiceValue());

		int newindex = river.getBoat1Position();
		
		
		while (river.getGameObject(newindex) instanceof Trap || river.getGameObject(newindex) instanceof Current) {
		if (river.getGameObject(newindex) instanceof Trap) {
			int backvalue = ((Trap) river.getGameObject(newindex)).getBack();
			System.out.println(river.getGameObject(newindex));
			river.decreaseBoat1(backvalue);
			river.setIndex(newindex, null);
			newindex = newindex - backvalue;
		}
		
		else if (river.getGameObject(newindex) instanceof Current) {
			int boostvalue = ((Current) river.getGameObject(newindex)).getBoost();
			System.out.println(river.getGameObject(newindex));
			river.increaseBoat1(boostvalue);
			river.setIndex(newindex, null);
			newindex = newindex + boostvalue;
		}
		}
		river.setIndex(river.getBoat1Position(), river.getBoat1());
		
		System.out.println("");
		river.showRiver();
		System.out.println("Player 1 Position: "+river.getBoat1Position());
		System.out.println("Player 2 Position: "+river.getBoat2Position());
		System.out.println("");
		}

	public void Player2Turn() {
		System.out.println("");
		System.out.println("Player 2's Turn");
		System.out.println("");
		System.out.println("Press Enter to Roll!");
		input.nextLine();
		dice.Roll();
		System.out.println("Player 2 rolled "+ dice);
		river.increaseBoat2(dice.getDiceValue());
		
		int newindex = river.getBoat2Position();
		while (river.getGameObject(newindex) instanceof Trap || river.getGameObject(newindex) instanceof Current) {
		if (river.getGameObject(newindex) instanceof Trap) {
			int backvalue = ((Trap) river.getGameObject(newindex)).getBack();
			System.out.println(river.getGameObject(newindex));
			river.decreaseBoat2(backvalue);
			river.setIndex(newindex, null);
			newindex = newindex - backvalue;
		}
		
		else if (river.getGameObject(newindex) instanceof Current) {			
			int boostvalue = ((Current) river.getGameObject(newindex)).getBoost();
			System.out.println(river.getGameObject(newindex));
			river.increaseBoat2(boostvalue);
			river.setIndex(newindex, null);
			newindex = newindex + boostvalue;
		}
		}
		river.setIndex(river.getBoat2Position(), river.getBoat2());
		System.out.println("");
		river.showRiver();
		System.out.println("");
		System.out.println("Player 1 Position: "+river.getBoat1Position());
		System.out.println("Player 2 Position: "+river.getBoat2Position());
		System.out.println("");
	}
	
	public void Instruction() {
		 System.out.println("\nInstruction: \nWelcome to the 2-Player Boat Racing Game where you will be allocated a boat and a dice.");
         System.out.println("Once players press [1] to start the game, all players will start at the same position and random traps and currents will be generated.");
         System.out.println("Currents will boost players making them move forward depending on the strength of the current.");
         System.out.println("Traps, however, will make the player's boat go backward depending on the strength of the trap.");
         System.out.println("The first player who reaches the 100th position will win the boat race. \nGood Luck!");
         System.out.println("");
	}
	public void TwentySidedDice() {
		System.out.println("Setting dice sides to 20...");
		dice.setSides(20);
		System.out.println("Dice set to "+dice.getSides()+" number of sides.");
	}
	public void SixSidedDice() {
		System.out.println("Setting dice sides to 6...");
		dice.setSides(6);
		System.out.println("Dice set to "+dice.getSides()+" number of sides.");
	}
	
	
	}