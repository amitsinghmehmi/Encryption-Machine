import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Aryaman, Amit
 * Date: February 25th, 2023
 * Description: Class is for reading from a file or writing to a new saved file
 * Method List: public static String [] fileLoader (String fileName) throws IOException -  Method to open a file and read from it, returning an array with phrases
 * 				public static void saveFile(String [] formatted) throws IOException - Saves information to a new file when called in FileModeUI
 * 				public static void saveFileLive(String [] outputTextArray, String [] keys, int numOfPhrases) - Save information to a new file when called in LiveModeUI
 * 
 */

public class FileAccess {

	/**
	 * Method to open a file and read from it, returning an array with phrases
	 */
	public static String [] fileLoader (String fileName) throws IOException {

		// Open the file in fileName to read
		BufferedReader input = new BufferedReader(new FileReader (fileName));

		// Read the line with the number of phrases
		int numPhrases = Integer.parseInt(input.readLine());

		// Create a string array containing phrases with number of phrases specified
		String phrases[] = new String[numPhrases];

		// Read the rest of the file
		for (int i = 0; i < numPhrases; i++) {
			// Read each line of the file
			phrases[i] = input.readLine();

		}

		// close the stream of the file
		input.close();

		// Return the phrases
		return phrases;

	}


	/**
	 * Method to save information to a new file when called in FileModeUI
	 * @throws IOException 
	 */
	public static void saveFile(String [] phrasesWithoutKey, String [] keys, int numOfPhrases) throws IOException {

		// String to store the desired output
		String outputText = "";

		// Saving icon used in window
		ImageIcon save = new ImageIcon("save.png");

		// Ask the user for the name of the file to save the information
		String nameOfFile = (String) JOptionPane.showInputDialog(null, "Enter the Name of File to Save to:", "FILE NAME", JOptionPane.INFORMATION_MESSAGE, save, null, "Name of File:");

		// Write to a new file formatted differently 
		FileWriter fileW = new FileWriter(nameOfFile);	// Open the file to write to 
		PrintWriter output = new PrintWriter(fileW);

		outputText = outputText + numOfPhrases + "\n";	// Make the first line the number of phrases

		// Loop through each row of phrases and store it to the output with the keys in front
		for (int i = 0; i < phrasesWithoutKey.length; i++) {

			outputText = outputText + keys[i].substring(0,11) + phrasesWithoutKey[i] + "\n" ;
		}

		// Write all information to new file
		output.println(outputText);	

		fileW.close(); // Close the file writer

		// Display friendly message to user
		JOptionPane.showMessageDialog(null, "File Saved Successfully!", "SAVED", JOptionPane.INFORMATION_MESSAGE, save);

	};

	/**
	 * Method to save information to a new file when called in LiveModeUI
	 * @throws IOException 
	 */
	public static void saveFileLive(String phrase, String key) throws IOException {

		// Saving icon used in window
		ImageIcon save = new ImageIcon("save.png");

		// Ask the user for the name of the file to save the information
		String nameOfFile = (String) JOptionPane.showInputDialog(null, "Enter the Name of File to Save to:", "FILE NAME", JOptionPane.INFORMATION_MESSAGE, save, null, "Name of File:");

		// Write to a new file formatted differently 
		FileWriter fileW = new FileWriter(nameOfFile);	// Open the file to write to 
		PrintWriter output = new PrintWriter(fileW);

		// The number of zeros to put in front of the phrase depends on the length of the number
		String zeros = "";
		int numZeros = 10 - key.length();

		// loop through the number of zeros and add a zero 
		for (int i = 0; i < numZeros; i++) {
			zeros = zeros + "0";

		}

		// If the key contains a + or a - dont put a + or - when saving
		if (key.contains("+") || key.contains("-")) {

			// Write all information to new file
			output.println(1 + "\n" + zeros+key + phrase);	

		}

		// If the key is greater than 0 add a + when saving to a new file 
		else if (Integer.parseInt(key) > 0) {

			// Write all information to new file
			output.println(1 + "\n+" + zeros+key + phrase);
		}

		// Otherwise the key is negative so add a negative sign
		else {

			// Write all information to new file
			output.println(1 + "\n-" + zeros+key + phrase);

		}

		fileW.close(); // Close the file writer

		// Display friendly message to user
		JOptionPane.showMessageDialog(null, "File Saved Successfully!", "SAVED", JOptionPane.INFORMATION_MESSAGE, save);

	};


	/**
	 * Self-testing main method
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		// Declare arrays to load phrases on
		String outputWithoutKey[] = new String [3];
		String phrase[] = new String[3];

		// Call the loadFile method
		phrase = FileAccess.fileLoader("PhrasesOne");

		// Print out the phrases 
		for (int i = 0; i < phrase.length; i++) {
			System.out.println(phrase[i]);
		}

		// Loop through each phrase to remove keys
		for (int i = 0; i < phrase.length; i++) {
			outputWithoutKey[i] = phrase[i].substring(11);
		}

		// Call save file method 
		FileAccess.saveFile(outputWithoutKey, phrase, phrase.length);
		
		// Call save file live method
		FileAccess.saveFileLive("Hello", "01");

	}	// End of main

}	// End of class
