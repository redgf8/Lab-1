import java.util.ArrayList;

public class Lab1 {
	
	public static void main(String[] args) {
		
		ArrayList<Player> dataList = new ArrayList<Player>(6);
		Reader inputReader = new Reader("C:\\Users\\Daniel\\Documents\\School\\Rowan\\Junior Year\\Semester 1\\DSA\\Lab 1 Files\\Lab 1\\log.txt");
		InputChecker checker = new InputChecker();
		String rawInput = "";
		int playerNumber = 0;
		
		System.out.println("Loading data from log file...");
		rawInput = inputReader.read();
		playerNumber = checker.PlayerNumber(rawInput); //find number of players specified in log file
		
		for (int i = 0; i <= (playerNumber - 1); i++) { //instantiate the correct number of players with blank data in the arraylist of players
			
			dataList.add(new Player(i, 0, 0, 0));
			
		}
		
		for(int i = 0; i < 10; i++) { //grab each new line in the log file and parse it through the inputChecker class
			
			rawInput = "";
			rawInput = inputReader.read();
			checker.processLine(dataList, rawInput, playerNumber, i);
			
		}
		
		checker.calculateData(dataList, playerNumber); //add up scores after all lines have been parsed and display in for for each player
		
		for (int i = 0; i <= (playerNumber - 1); i++) {
			
			dataList.get(i).displayInfo();
			
		}
				
	}
	
}

