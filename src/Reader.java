import java.io.*;
import java.lang.String;

public class Reader {
	
	BufferedReader br = null;
	FileReader fr = null;
	
	public Reader(String filePath) { //link reader class to appropriate file
		
		try {
			
			fr = new FileReader(filePath);
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
			
		}
		
		br = new BufferedReader(fr);
		
	}

	public String read() { //get the next full line of input from the input file
		
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

	

