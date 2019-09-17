
import java.lang.String;
import java.util.ArrayList;
import java.util.regex.*;

/**
 * Parser class
 * Scores a game of bowling from a formatted log file
 * Provides access to information about the game represented by the log file
 * @author Daniel Haluszka
 */
public class bowlingParser {

	private Reader reader;
	private int playerCount;
	private ArrayList<Player> players;
	private int[][] frames;

	public bowlingParser(String filePath) {

		reader = new Reader(filePath);
		this.playerCount = Integer.parseInt(reader.readLine());
		// Check for valid player count
		if (this.playerCount < 1 || this.playerCount > 6) throw new IllegalArgumentException("Invalid player count: " + this.playerCount);
		this.players = new ArrayList<Player>();
		for (int i = 0; i < this.playerCount; i++) {

			this.players.add(i, new Player());

		}
		this.frames = new int[10][this.playerCount];

	}

	/**
	 * Parses game info from log file and populates frame and player info in current instance
	 */
	public void parseGame() {

		Pattern pattern = Pattern.compile("");
		Matcher matcher = pattern.matcher(this.reader.readLine());

		// Parse all ten frames
		for (int i = 0; i < 10; i++) {

			// If the last frame is being parsed, look for three rolls
			if (i == 9) {



			// Else, look for two rolls
			} else {



			}

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

	public int getPlayerCount() {

		return this.playerCount;

	}

	public ArrayList<Player> getPlayerList() {

		return this.players;

	}

	public int[][] getFrameList() {

		return this.frames;

	}
	
}


