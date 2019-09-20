
/**
 * Driver class for Lab1
 * @author Daniel Haluszka
 */
public class Lab1 {

	public static final String LOG_FILE_PATH = new String("./log/log.txt");
	
	public static void main(String[] args) {

		BowlingParser parser = new BowlingParser(LOG_FILE_PATH);
		System.out.println(parser.getPlayerCount());
		parser.parseGame();
		parser.printScores();
				
	}
	
}

