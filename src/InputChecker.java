import java.lang.String;
import java.util.ArrayList;

public class InputChecker {
	
	public int PlayerNumber(String numberString) { //method to get the number of players from the log file
		
		int number = 0;
		
		number = Integer.parseInt(numberString);
		
		if(number < 1 || number > 6) { //check to make sure that the number of players specified is between 1 and 6
			
			System.out.println("Input file syntax error: Invalid number of players specified: " + number);
			System.exit(0);
			
		}
		
		return number;
		
	}
	
	public void processLine(ArrayList<Player> dataList, String lineInput, int numberPlayer, int frameNumber) { //parse each line of the log file
		
		String currentChar = "";
		String previousChar = "";
		int maxValue = 0;
		int currentRoll = 0;
		int currentPlayer = 0;
			
		if (frameNumber == 9) { //account for the difference in number of characters in the last frame
			
			maxValue = 10;
			
		}else {
			
			maxValue = 7;
			
		}
		
		if (lineInput.length() != ((maxValue * numberPlayer) - 1)) { //make sure the number of characters is what we're expecting for the number of players specified
			
			System.out.println("Input file synatx error: Number of players in frame " + (frameNumber + 1) + " does not match number of players specified.");
			System.exit(0);
				
		}
			
			for(int i = 0; i < ((maxValue * numberPlayer) - 1); i++) { //run this loop for each character on the line input
				
				currentChar = lineInput.substring(i, i+1);
				
				if(currentChar.equals("0") //check for valid score values
						|| currentChar.equals("1") 
						|| currentChar.equals("2") 
						|| currentChar.equals("3") 
						|| currentChar.equals("4") 
						|| currentChar.equals("5") 
						|| currentChar.equals("6") 
						|| currentChar.equals("7") 
						|| currentChar.equals("8") 
						|| currentChar.equals("9")
						|| currentChar.equals("/")
						|| currentChar.equals("X"))
						{
					
					if (currentRoll == 1 && currentChar.equals("X") && frameNumber != 9) { //check for syntax errors in log file
							
						System.out.println("Input file syntax error: Cannot roll a strike on roll 2 of a frame.");
						System.exit(0);
						
					} else if (currentRoll == 0 && currentChar.equals("/")) {
						
						System.out.println("Input file syntax error: Cannot roll a spare on roll 1 of a frame.");
						System.exit(0);
						
					} else if (currentRoll == 1 && currentChar.equals("X") == false && currentChar.equals("/") == false && currentChar.equals("0") == false) {
					
						if ((10 - Integer.parseInt(previousChar)) < Integer.parseInt(currentChar)) {
							
							System.out.println("Log file syntax error: Score value for second roll is too large.");
							System.exit(0);
							
						} else if ((10 - Integer.parseInt(previousChar)) == Integer.parseInt(currentChar)) {
							
							System.out.println("Log file syntax error: Score value for second roll results in a spare, but spare was not received.");
							System.exit(0);
							
						}
						
						dataList.get(currentPlayer).addRoll(currentChar);
					
					} else {
						
						previousChar = currentChar;
						dataList.get(currentPlayer).addRoll(currentChar);
					
					}

					
				} else if (currentChar.equals("]")) { //increment to the next player once the rolls of the previous are processed
					
					currentPlayer++;
					currentRoll = 0;

					
				} else if (currentChar.equals(",")){ //increment to the next roll when we get a comma
					
					currentRoll++;

					
				} else if (currentChar.equals("[") == false && currentChar.equals(" ") == false) { //if none of the above or neither other character the log file should contain, invalid input
					
					System.out.println("Input file syntax error: Invalid score entered: " + currentChar);
					System.exit(0);
					
				}
			
		}
		
	}
	
	public void calculateData(ArrayList<Player> dataList, int numberPlayer) { //calculate each player's score
		
		String currentChar = "";
		int currentScore = 0;
			
		for (int i = 0; i < numberPlayer; i++) { //run this loop for each player
			
			for (int j = 0; j < 19; j++) { //run this loop for each roll score
				
				currentChar = dataList.get(i).getRoll(j);
				
				if (currentChar.equals("X")) { //if the current roll score is a strike
					
					if (j < 18) { //if it's not the 10th frame, do this code
						
						if (dataList.get(i).getRoll(j + 3).equals("/")) { //if the second roll after the current roll is a spare
							
							dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + 20);
							
						} else if (dataList.get(i).getRoll(j + 2).equals("X")) { //if the next roll after the current roll is a strike
							
							if (dataList.get(i).getRoll(j + 4).equals("X")) { //if the second roll after the current roll is a strike
								
								dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + 30);
								
							} else {
								
								if (j == 16) { //different code for the 9th frame, look for second strike in different position to account for the three rolls in 10th frame
									
									dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + Integer.parseInt(dataList.get(i).getRoll(j + 3)) + 20);
									
								} else {
									
									dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + Integer.parseInt(dataList.get(i).getRoll(j + 4)) + 20);
									
								}
								
							}
							
							if (j == 18) { //add two to strike count if it's the last frame
								
								dataList.get(i).setStrikeCount(dataList.get(i).getStrikeCount() + 2);
								
							}
						
						} else {
						
							currentScore = (Integer.parseInt(dataList.get(i).getRoll(j + 2)) + Integer.parseInt(dataList.get(i).getRoll(j + 3)) + 10);
							dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + currentScore);
						
						}
						
					} else { //code for 10th frame
						
							if (dataList.get(i).getRoll(j + 2).equals("/")) {
							dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + 20);
							
						} else if (dataList.get(i).getRoll(j + 2).equals("X")) {
							
							dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + 30);
							dataList.get(i).setStrikeCount(dataList.get(i).getStrikeCount() + 2);
								
						
						} else {
						
							currentScore = (Integer.parseInt(dataList.get(i).getRoll(j + 1)) + Integer.parseInt(dataList.get(i).getRoll(j + 2)) + 10);
							dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + currentScore);
						
						}
						
					}
					
					dataList.get(i).setStrikeCount(dataList.get(i).getStrikeCount() + 1);
					currentScore = 0;
					
				} else if (currentChar.equals("/")) { //if the current roll is a spare
						
					if (dataList.get(i).getRoll(j + 1).equals("X")) { //if the next roll after the current roll is a strike
						
						dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + 20);
						
					} else { //otherwise, add numeric value
					
						currentScore = (Integer.parseInt(dataList.get(i).getRoll(j + 1)) + 10);
						dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + currentScore);
					
					}
					
					//subtract the last numeric score before the spare and add to spare count
					dataList.get(i).setTotalScore((dataList.get(i).getTotalScore() - Integer.parseInt(dataList.get(i).getRoll(j - 1))));
					dataList.get(i).setSpareCount(dataList.get(i).getSpareCount() + 1);
					currentScore = 0;
					
				} else { //if current roll is numeric pin value
					
					if (j == 18) { //if it's the last frame, look for spares/strikes on second/third rolls of frame
						
						if (dataList.get(i).getRoll(j + 1).equals("/")) { //if the second roll of last frame is a spare
							
							if (dataList.get(i).getRoll(j + 2).equals("X")) { //if the last roll of last frame is a strike
								
								dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + 20);
								dataList.get(i).setStrikeCount(dataList.get(i).getStrikeCount() + 1);
								
							} else {

								currentScore = (Integer.parseInt(dataList.get(i).getRoll(j + 2)) + 10);
								dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + currentScore);

							}
							
							dataList.get(i).setSpareCount(dataList.get(i).getSpareCount() + 1);
							
						} else { //if a spare or strike was not rolled
							
							if (dataList.get(i).getRoll(j + 2).equals("0") == false) { //check for disallowed thrid frame
								
								System.out.println("Input file syntax error: Can't have a 3rd roll in 10th frame if spare or strike was not rolled first.");
								System.exit(0);
								
							} else { //otherwise parse score of last frame
							
								dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + Integer.parseInt(dataList.get(i).getRoll(j + 1)) + Integer.parseInt(currentChar));
								
							}
							
						}
						
					} else { //if it's not the last frame, parse score
										
						dataList.get(i).setTotalScore(dataList.get(i).getTotalScore() + Integer.parseInt(currentChar));
					
					}
					
					currentScore = 0;
					
				}
				
			}
			
		}
		
	}
	
}


