import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Amit 
 * Date: February 25th, 2023
 * Description: Class is the user interface for when the user clicks file mode in the main menu
 * Method List: public static int getKey(String phrase) -  Method to take the first 10 characters of the phrase to acquire the key 
 * 				public static boolean checkKey (int encryptKey) - Method checks if the key is in the appropriate range 
 * 				public static int putKeyInRange (int encryptKey) - Method changes the key to usable number for the encrypting algorithm 
 * 				public void actionPerformed(ActionEvent e) -  Method that listen to button clicks
 * 				
 * 				
 *
 */
public class FileModeUI extends JFrame implements ActionListener {

	// Declaring elements to be accessible throughout the class
	JTextArea outputArea = new JTextArea();
	JTextArea fileNameField = new JTextArea("Enter File Name...");
	JButton enter, reset, exit, save, menu, encrypt, decrypt, encryptImage, decryptImage;
	ImageIcon unlock = new ImageIcon("unlock.png");
	ImageIcon lock = new ImageIcon("lock.png");
	
	String [] outputTextArray;		// Holds the output to be displayed on the text area
	String outputText = "";			// Used for the output to be displayed on the text area
	String [] phrases;				// Holds the phrases read from the file loader
	String [] phrasesWithoutKeys;	// Holds the phrases without the keys
	int keys[]; 					// Hold the keys

	/**
	 * Window Constructor 
	 */
	public FileModeUI() {

		// Title of window
		super("File Mode");

		// Setting size, location and unresizeable 
		pack();		
		setSize(1000,600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(6,27,68));
		setResizable(false);
		setLayout(null);

		// Area to display the formatted findings 
		outputArea.setBounds(60,100,700,350);
		outputArea.setEditable(false);

		// Text field for the user to enter the file name
		fileNameField.setFont(new Font("Serif", Font.BOLD, 19));
		fileNameField.setForeground(Color.BLACK);
		fileNameField.setBounds(90,30,300,40);

		// Enter button for user to click after entering file name
		enter = new JButton("Enter");
		enter.setBounds(550,28,150,45);
		enter.setFont(new Font("Serif", Font.BOLD, 18));
		enter.setBackground(Color.white);
		enter.setForeground(Color.black);
		enter.addActionListener(this);

		// Reset button for user to reset text area and file name input field
		reset = new JButton("Reset");
		reset.setBounds(800,210,150,45);
		reset.setFont(new Font("Serif", Font.BOLD, 18));
		reset.setBackground(Color.white);
		reset.setForeground(Color.BLACK);
		reset.addActionListener(this);

		// Exit button if the user wishes to exit the application
		exit = new JButton("Exit");
		exit.setBounds(800,370,150,45);
		exit.setFont(new Font("Serif", Font.BOLD, 18));
		exit.setBackground(Color.white);
		exit.setForeground(Color.BLACK);
		exit.addActionListener(this);

		// Save button to save information displayed on screen to a new file
		save = new JButton("Save");
		save.setBounds(800,130,150,45);
		save.setFont(new Font("Serif", Font.BOLD, 18));
		save.setBackground(Color.white);
		save.setForeground(Color.BLACK);
		save.addActionListener(this);

		// Menu button to go back to main menu animation screen
		menu = new JButton("Menu");
		menu.setBounds(800,290,150,45);
		menu.setFont(new Font("Serif", Font.BOLD, 18));
		menu.setBackground(Color.white);
		menu.setForeground(Color.BLACK);
		menu.addActionListener(this);

		// Encrypt button to encrypt phrases
		encrypt = new JButton("Encrypt");
		encrypt.setBounds(180,480,150,45);
		encrypt.setFont(new Font("Serif", Font.BOLD, 18));
		encrypt.setBackground(Color.white);
		encrypt.setForeground(Color.BLACK);
		encrypt.addActionListener(this);

		// Decrypt button to decrypt phrases
		decrypt = new JButton("Decrypt");
		decrypt.setBounds(500,480,150,45);
		decrypt.setFont(new Font("Serif", Font.BOLD, 18));
		decrypt.setBackground(Color.white);
		decrypt.setForeground(Color.BLACK);
		decrypt.addActionListener(this);

		// Lock image
		encryptImage = new JButton(lock);
		encryptImage.setBounds(120,475,48,65);
		encryptImage.setBorderPainted(false);

		// Unlock image
		decryptImage = new JButton(unlock);
		decryptImage.setBounds(440,475,50,70);
		decryptImage.setBorderPainted(false);

		// Add all elements to the window
		add(encrypt);
		add(encryptImage);
		add(decryptImage);
		add(decrypt);
		add(menu);
		add(save);
		add(enter);
		add(exit);
		add(reset);
		add(fileNameField);
		add(outputArea);

		// Setting exit on close and visibility  
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Method to take the first 11 characters of the phrase to acquire the key 
	 */
	public static int getKey(String phrase) {

		// Encryption key is equal to first 11 characters
		int encryptionKey = Integer.parseInt(phrase.substring(0,11));
		return encryptionKey;	// return the key

	}

	/**
	 * Method checks if the key is in the appropriate range 
	 */
	public static boolean checkKey (int encryptKey) {

		// False if the key is not in range
		if (encryptKey > 2000000000 || encryptKey < -2000000000) {
			return false;
		}
		else {
			return true;	// true otherwise
		}

	}

	/**
	 * Method changes the key to usable number for the encrypting algorithm 
	 */
	public static int putKeyInRange (int encryptKey) {

		// Shifts is dependent on the number of alphabets, hence check how many times it needs to loop by doing the modulus 
		int numShifts = encryptKey % 26;
		return numShifts;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Call window constructor
		new FileModeUI();		

	}

	/**
	 * Method that listen to button clicks
	 */
	public void actionPerformed(ActionEvent e) {

		// If exit button is clicked
		if (e.getSource() == exit) {
			this.dispose();	//close window
		}

		// Menu button
		else if (e.getSource() == menu) {
			try {
				this.dispose();
				new Animation();	// Call the animation class
			} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}

		// Reset button
		else if (e.getSource() == reset) {

			fileNameField.setText("");	// reset both text fields
			outputArea.setText("");
		}

		// Save button
		else if (e.getSource() == save) {
			try {
				FileAccess.saveFile(outputTextArray, phrases, phrases.length); // Call the save file method
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		// Enter button
		else if (e.getSource() == enter) {
			
			outputText = "";	// Reset the text displayed on the text area

			try {
				
				// An array that holds the keys in the file and initialize it to the number of phrases in the file
				keys = new int[FileAccess.fileLoader(fileNameField.getText()).length];
				
				// An array that holds the phrases in the file and initialize it to the number of phrases in the file
				phrases = new String[FileAccess.fileLoader(fileNameField.getText()).length];
				phrases = FileAccess.fileLoader(fileNameField.getText());
				
				// An array that holds the output to be displayed
				outputTextArray = new String[FileAccess.fileLoader(fileNameField.getText()).length];
				
				// An array that holds phrases without the keys
				phrasesWithoutKeys = new String[FileAccess.fileLoader(fileNameField.getText()).length];
			
	
				// Loop through each row of phrases
				for (int i = 0; i < phrases.length; i++) {

					// Get the first 11 characters in the phrase and put it in the empty array with keys
					keys[i] = FileModeUI.getKey(phrases[i]);

					// Check if the key is in range
					if (checkKey(keys[i]) == false) {
						
						// Display an error message and say where the key is corrupted
						outputArea.setText("");
						JOptionPane.showMessageDialog(null, "The Key In Phrase #" + (i+1) + " Is Out Of Range!", "OPERATION CANCELLED", JOptionPane.ERROR_MESSAGE);
					}
					
					// Otherwise must be in range
					else {

						// Put the key in range of -26 to 26 to operate the algorithm
						keys[i] = FileModeUI.putKeyInRange(keys[i]);

						// Get the characters after the key to display it on the output area
						phrasesWithoutKeys[i] = phrases[i].substring(11);
						outputTextArray[i] = phrasesWithoutKeys[i] + "\n";
						outputText = outputText + outputTextArray[i];
						outputArea.setFont(new Font("serif", Font.BOLD, 20));
						
					}

				}
				
				// Display the phrases
				outputArea.setText(outputText);
				
			// Catch exceptions 
				
			// File not found	
			} catch (FileNotFoundException fileError) {
				
				JOptionPane.showMessageDialog(null, fileNameField.getText() + " Not Found!", "FILE NOT FOUND", JOptionPane.WARNING_MESSAGE);
				
			} catch (IOException e1) {
				e1.printStackTrace();
				
			// Invalid phrases count 
			} catch (NullPointerException numOfPhrasesError) {
				
				outputArea.setText("");
				JOptionPane.showMessageDialog(null, fileNameField.getText() + " Has A Corrupted Phrases Count", "CORRUPTED", JOptionPane.WARNING_MESSAGE);
			}

			

		}

		// Encrypt button
		else if (e.getSource() == encrypt) {

			// Clear the output area that contained the phrases from the file
			outputText = "";


			// Clear the output array
			for (int i = 0; i < outputTextArray.length; i++) {

				outputTextArray[i] = "";

			}

			// Row of phrases
			for (int i = 0; i < phrases.length; i++) {

				// Go through each character in the phrase
				for (int j = 0; j < phrasesWithoutKeys[i].length(); j++) {

					// If character is not a letter keep it as it is
					if(Encryption.isNotALetter(phrasesWithoutKeys[i].charAt(j)) == true) {

						outputTextArray[i] = outputTextArray[i] + phrasesWithoutKeys[i].charAt(j);

					}

					// Else call the encoding method
					else {

						outputTextArray[i] = outputTextArray[i] + Encryption.encode(phrasesWithoutKeys[i].charAt(j), keys[i]);
					}

				}

			}

			// Loop through the output array and add it to the output string
			for (int i = 0; i < outputTextArray.length; i++) {

				outputText = outputText + outputTextArray[i] + "\n";

			}

			// Display encoded
			outputArea.setFont(new Font("serif", Font.BOLD, 20));
			outputArea.setText(outputText);


		}

		// Decrypt button
		else if (e.getSource() == decrypt) {

			// Clear the output area that contained the phrases from the file
			outputText = "";


			// Clear the output array
			for (int i = 0; i < outputTextArray.length; i++) {

				outputTextArray[i] = "";

			}

			// Row of phrases
			for (int i = 0; i < phrases.length; i++) {

				// Go through each character in the phrase
				for (int j = 0; j < phrasesWithoutKeys[i].length(); j++) {

					// If character is not a letter keep it as it is
					if(Encryption.isNotALetter(phrasesWithoutKeys[i].charAt(j)) == true) {

						outputTextArray[i] = outputTextArray[i] + phrasesWithoutKeys[i].charAt(j);

					}

					// Else call the decoding method
					else {

						outputTextArray[i] = outputTextArray[i] + Encryption.decode(phrasesWithoutKeys[i].charAt(j), keys[i]);
					}

				}

			}

			// Loop through the output array and add it to the output string
			for (int i = 0; i < outputTextArray.length; i++) {

				outputText = outputText + outputTextArray[i] + "\n";

			}

			// Display decoded
			outputArea.setFont(new Font("serif", Font.BOLD, 20));
			outputArea.setText(outputText);


		}

	}	// End of action performed
	
}	// End of class

