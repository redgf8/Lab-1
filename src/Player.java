import java.util.ArrayList;
import java.lang.String;

public class Player {

	ArrayList<String> rollList = new ArrayList<String>(21);
	int numPlayer, totalScore, numStrike, numSpare = 0;
	
	public Player(int numPlayer, int totalScore, int numStrike, int numSpare) { //class constructor
		
		this.setPlayerNum(numPlayer);
		this.setTotalScore(totalScore);
		this.setStrikeCount(numStrike);
		this.setSpareCount(numSpare);
		
	}
	
	public String getRoll(int rollNum) { //get methods
		
		return this.rollList.get(rollNum);
		
	}
	
	public String getPreviousRoll(int rollNum) {
		
		String prevRoll = "";
		
		if (rollNum == 0) {
			
			System.out.println("Error: No previous roll.");
			System.exit(0);
			
		} else {
			
			prevRoll = this.rollList.get(rollNum - 1);
			
		}
		
		return prevRoll;
		
	}
	
	public int getPlayerNum(){

		return this.numPlayer;
		
	}
	
	public int getTotalScore() {
		
		return this.totalScore;
		
	}
	
	public int getStrikeCount() {
		
		return this.numStrike;
		
	}
	
	public int getSpareCount() {
		
		return this.numSpare;
		
	}
	
	public void addRoll(String scoreValue) { //set methods
		
		this.rollList.add(new String(scoreValue));
		
	}
	
	public void setPlayerNum(int playerNum) {
		
		this.numPlayer = playerNum;
		
	}
	
	public void setTotalScore(int newTotal) {
		
		this.totalScore = newTotal;
		
	}
	
	public void setStrikeCount(int newTotal) {
		
		this.numStrike = newTotal;
		
	}
	
	public void setSpareCount(int newTotal) {
		
		this.numSpare = newTotal;
		
	}
	
	public void displayInfo() { //display required info about class
		
		System.out.println("\nPlayer number: " + (this.getPlayerNum() + 1));
		System.out.println("Total score: " + this.getTotalScore());
		System.out.println("Total strike count: " + this.getStrikeCount());
		System.out.println("Total spare count: " + this.getSpareCount());
		
	}
	
}
