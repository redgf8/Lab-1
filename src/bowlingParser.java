
import java.lang.String;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.regex.*;

/**
 * Parser class
 * Scores a game of bowling from a formatted log file
 * Provides access to information about the game represented by the log file
 * @author Daniel Haluszka
 */
public class BowlingParser {

	private Reader reader;
	private int playerCount;
	private ArrayList<Player> players;
	private int[][] frames;

	public BowlingParser(String filePath) {

		reader = new Reader(filePath);
		String count = reader.readLine();
		// Check for non-numeric player number
		try {

			this.playerCount = Integer.parseInt(count);

		} catch(Exception e) {

			throw new IllegalArgumentException("Invalid player count specified: " + count);

		}
		// Check for valid player count
		if (this.playerCount < 1 || this.playerCount > 6) throw new IllegalArgumentException("Invalid player count: " + this.playerCount);
		this.players = new ArrayList<Player>();
		// Initialize player list
		for (int i = 0; i < this.playerCount; i++) {

			this.players.add(i, new Player());

		}
		this.frames = new int[10][this.playerCount];

	}

	/**
	 * Parses game info from log file and populates frame and player info in current instance
	 */
	public void parseGame() {

		String currFrame = reader.readLine();
		if (currFrame == null) throw new IllegalArgumentException("Invalid format: log file too short");
		String currFramePattern;
		String currChar;
		int currPlayer;

		// Parse all ten frames
		for (int i = 0; i < 10; i++) {

			System.out.println("Current frame: '" + currFrame + "'");
			currFramePattern = "(\\[[0-9X]\\,\\s[0-9\\/]\\](\\s?))+";
			currPlayer = 0;
			// If the last frame is being parsed, change the pattern to look for three rolls
			if (i == 9) currFramePattern = "(\\[[0-9X]\\,\\s[0-9\\/]\\,\\s[0-9X]\\](\\s?))+";
			// If the current frame doesn't match the current pattern, there is a formatting error in the log file
			if (!currFrame.matches(currFramePattern)) throw new IllegalArgumentException("Invalid format for frame: " + (i + 1));

			for (int j = 0; j < currFrame.length(); j++) {

				currChar = currFrame.substring(j, (j + 1));
				// Check for unexpected EOL
				if (currChar == null) throw new IllegalArgumentException("Invalid format: info for frame: " + (i + 1) + " too short");

				if (currChar.matches("\\d")) {

					this.players.get(currPlayer).setTotalScore(this.players.get(currPlayer).getTotalScore() + Integer.parseInt(currChar));

				} else if (currChar.equals("]")) {

					currPlayer++;

				} else if (currChar.equals("X")) {

					this.players.get(currPlayer).setStrikeCount(this.players.get(currPlayer).getStrikeCount() + 1);
					//TODO: Figure out code for scoring a strike

				} else if (currChar.equals("/")) {

					this.players.get(currPlayer).setSpareCount(this.players.get(currPlayer).getSpareCount() + 1);
					//TODO: Figure out code for scoring a spare

				}

			}

			// Read in the next frame after parsing the current
			currFrame = reader.readLine();
			// Check for unexpected EOL
			if (i != 9 && currFrame == null) throw new IllegalArgumentException("Invalid format: log file too short");

		}
		
	}

	/**
	 * Prints the final scores
	 */
	public void printScores() {

		for (int i = 0; i < this.playerCount; i++) {

			System.out.println("\nPlayer number: " + (i + 1));
			this.players.get(i).printPlayerInfo();

		}

	}


	/**
	 * Get method for playerCount
	 * @return The number of players specified in the current game
	 */
	public int getPlayerCount() {

		return this.playerCount;

	}

	/**
	 * Get method for player list
	 * @return The list of Player objects in the current game
	 */
	public ArrayList<Player> getPlayerList() {

		return this.players;

	}

	/**
	 * Get method for frame list
	 * @return The array of frames in the current game
	 */
	public int[][] getFrameList() {

		return this.frames;

	}
	
}


