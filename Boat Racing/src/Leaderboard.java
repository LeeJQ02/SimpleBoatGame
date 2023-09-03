import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Leaderboard {
	private ArrayList<Score> leaderboard;
	
	public Leaderboard(){
		leaderboard = new ArrayList();
		Scanner read;
		try {
		read = new Scanner(new File("highscores.txt"));
		while (read.hasNext()) {
			read.nextInt();
			leaderboard.add(new Score(read.next(),read.nextInt()));
		}
		if (read != null){
			read.close();
			}
		} 
	catch (FileNotFoundException fe){
		System.out.println("File does not exist.");
		}
	catch (NoSuchElementException ex){
		System.out.println("File improperly formed.");
	}
	}
	
	public void StoreIntoLeaderboard(String name, int score) {
		if (score < leaderboard.get(0).getScore()){
			leaderboard.add(0,(new Score(name, score)));
		}
		else if (score < leaderboard.get(1).getScore()) {
			leaderboard.add(1,(new Score(name, score)));
		}
		else if (score < leaderboard.get(2).getScore()) {
			leaderboard.add(2,(new Score(name, score)));
		}
		else if (score < leaderboard.get(3).getScore()) {
			leaderboard.add(3,(new Score(name, score)));
		}
		else if (score < leaderboard.get(4).getScore()) {
			leaderboard.add(4,(new Score(name, score)));
		}
		else leaderboard.add(new Score(name, score));
	}
	
	
	public void ShowHighscore() {
		Scanner read;
		try{
			read = new Scanner(new File("highscores.txt"));
			while (read.hasNext()){
				System.out.printf("%-10d%-12s%10d\n", read.nextInt(),
				read.next(), read.nextInt());
				}
			if (read != null){
				read.close();
				}
			} 
		catch (FileNotFoundException fe){
			System.out.println("File does not exist.");
			}
		catch (NoSuchElementException ex){
			System.out.println("File improperly formed.");
			}

	}
	public void StoreHighscore() {
		Formatter output;
		try{
			output = new Formatter("highscores.txt");
			for (int i=0; i<5;i++) {
			output.format("%d %s %d\n", i+1, leaderboard.get(i).getName(), leaderboard.get(i).getScore());
			}
			if (output!= null)
				{
				output.close();
				}
		} 
		catch (SecurityException se)
			{
			System.out.println("You do not have write access");
			System.exit(1);
			} 
		catch (FileNotFoundException fe)
			{
			System.out.println("Error opening/creating file.");
			System.exit(1);
			}

	}
}
