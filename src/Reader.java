
import java.io.*;
import java.lang.String;

/**
 * Wrapper class for using a BufferedReader to read data from the log file
 */
public class Reader {
	
	BufferedReader br = null;
	FileReader fr = null;
	
	public Reader(String filePath) { // Link reader class to appropriate file
		
		try {
			
			fr = new FileReader(filePath);
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			
		}
		
		br = new BufferedReader(fr);
		
	}

	public String readLine() { // Get the next full line of input from the input file
		
		String textInput = "";
		
		try {

			textInput = br.readLine();
			
			if (br == null) {
				
				br.close();
				
			} 
			
			if (fr == null) {
				
				fr.close();
				
			}

		} catch (IOException ex) {

			ex.printStackTrace();
			
		}
		
			return textInput;
			
	}

}

	

