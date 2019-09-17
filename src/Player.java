
/**
 * Player class
 * Stores information about a particular player in a game of bowling
 * @author Daniel Haluszka
 */
public class Player {

	private int totalScore, numStrike, numSpare;
	
	public Player() {

		this.totalScore = 0;
		this.numStrike = 0;
		this.numSpare = 0;
		
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
	
	public void setTotalScore(int newTotal) {
		
		this.totalScore = newTotal;
		
	}
	
	public void setStrikeCount(int newTotal) {
		
		this.numStrike = newTotal;
		
	}
	
	public void setSpareCount(int newTotal) {
		
		this.numSpare = newTotal;
		
	}
	
	public void printPlayerInfo() {

		System.out.println("Total score: " + this.getTotalScore());
		System.out.println("Total strike count: " + this.getStrikeCount());
		System.out.println("Total spare count: " + this.getSpareCount());
		
	}
	
}
