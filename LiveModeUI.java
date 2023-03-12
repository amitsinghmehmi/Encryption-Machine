import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * @author Aryaman, Amit
 * Date: March 3rd, 2023
 * Description: Class is the user interface for when the user clicks live mode in the main menu
 * Method List: public void actionPerformed(ActionEvent e) - Method to listen to button calls
 * 
 */

public class LiveModeUI extends JFrame implements ActionListener{

	// Declaration of variables which are accessed through different methods
	JButton enter, reset, exit, save, menu, encrypt, decrypt, encryptImage, decryptImage, input, output, key;
	ImageIcon unlock = new ImageIcon("unlock.png");
	ImageIcon lock = new ImageIcon("lock.png");
	JTextArea outputArea = new JTextArea();
	JTextArea inputArea = new JTextArea();
	JTextArea keyArea = new JTextArea();
	String outputText = "";

	/**
	 * Window Constructor
	 */
	public LiveModeUI() {

		// Title of window
		super("Live Mode");

		// Setting size, location and unresizeable 
		pack();		
		setSize(1000,600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(6,27,68));
		setResizable(false);
		setLayout(null);

		// Text area for user the user to enter a phrase
		inputArea.setBounds(90,130,300,300);
		inputArea.setFont(new Font("Serif", Font.BOLD, 19));
		inputArea.setEditable(true);

		// Text area for the output
		outputArea.setBounds(400,130,300,300);
		outputArea.setFont(new Font("Serif", Font.BOLD, 19));
		outputArea.setEditable(false);

		// Text area for the user to enter in a key
		keyArea.setBounds(330,30,370,30);
		keyArea.setFont(new Font("Serif", Font.BOLD, 19));
		keyArea.setEditable(true);

		// Reset button to reset all text areas
		reset = new JButton("Reset");
		reset.setBounds(785,210,150,45);
		reset.setFont(new Font("Serif", Font.BOLD, 18));
		reset.setBackground(Color.white);
		reset.setForeground(Color.black);
		reset.addActionListener(this);

		// Exit button if the user wishes to exit the application
		exit = new JButton("Exit");
		exit.setBounds(785,370,150,45);
		exit.setFont(new Font("Serif", Font.BOLD, 18));
		exit.setBackground(Color.white);
		exit.setForeground(Color.black);
		exit.addActionListener(this);

		// Save button to save information displayed on screen to a new file
		save = new JButton("Save");
		save.setBounds(785,130,150,45);
		save.setFont(new Font("Serif", Font.BOLD, 18));
		save.setBackground(Color.white);
		save.setForeground(Color.black);
		save.addActionListener(this);

		// Menu button to go back to main menu animation screen
		menu = new JButton("Menu");
		menu.setBounds(785,290,150,45);
		menu.setFont(new Font("Serif", Font.BOLD, 18));
		menu.setBackground(Color.white);
		menu.setForeground(Color.black);
		menu.addActionListener(this);

		// Encrypt button to encrypt phrases
		encrypt = new JButton("Encrypt");
		encrypt.setBounds(200,475,150,45);
		encrypt.setFont(new Font("Serif", Font.BOLD, 18));
		encrypt.setBackground(Color.white);
		encrypt.setForeground(Color.black);
		encrypt.addActionListener(this);

		// Decrypt button to decrypt phrases
		decrypt = new JButton("Decrypt");
		decrypt.setBounds(500,475,150,45);
		decrypt.setFont(new Font("Serif", Font.BOLD, 18));
		decrypt.setBackground(Color.white);
		decrypt.setForeground(Color.black);
		decrypt.addActionListener(this);

		// Input Label as an invisible button
		input = new JButton("Input");
		input.setBounds(165,80,150,45);
		input.setFont(new Font("Serif", Font.BOLD, 27));
		input.setBackground(new Color(6,27,68));
		input.setForeground(Color.white);
		input.setBorderPainted(false);

		// Output Label as an invisible button
		output = new JButton("Output");
		output.setBounds(475,80,150,45);
		output.setFont(new Font("Serif", Font.BOLD, 27));
		output.setBackground(new Color(6,27,68));
		output.setForeground(Color.white);
		output.addActionListener(this);
		output.setBorderPainted(false);

		// Key Label as an invisible button
		key = new JButton("Enter Key :");
		key.setBounds(150,20,145,45);
		key.setFont(new Font("Serif", Font.BOLD, 22));
		key.setBackground(new Color(6,27,68));
		key.setForeground(Color.white);
		key.setBorderPainted(false);

		// Lock image
		encryptImage = new JButton(lock);
		encryptImage.setBounds(135,460,48,65);
		encryptImage.setBorderPainted(false);

		// Unlock image
		decryptImage = new JButton(unlock);
		decryptImage.setBounds(440,460,50,70);
		decryptImage.setBorderPainted(false);

		// Add all elements to the window
		add(encrypt);
		add(decrypt);
		add(menu);
		add(save);
		add(input);
		add(output);
		add(exit);
		add(reset);
		add(key);
		add(keyArea);
		add(outputArea);
		add(inputArea);
		add(encryptImage);
		add(decryptImage);

		// Setting exit on close and visibility 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Call window constructor
		new LiveModeUI();		
	}


	/**
	 * Method to listen to button calls
	 */
	public void actionPerformed(ActionEvent e) {

		// Exit button
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


		// Save button
		else if (e.getSource() == save) {

			try {
				FileAccess.saveFileLive(outputText, keyArea.getText());	// Call the save file live method 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		// Reset button
		else if (e.getSource() == reset) {

			// Reset all text areas
			keyArea.setText("");	
			outputArea.setText("");
			inputArea.setText("");
		}

		// Encrypt button
		else if (e.getSource() == encrypt) {

			// Reset output text area to blank
			outputText = "";

			// Check if the key is in range
			if (FileModeUI.checkKey(Integer.parseInt(keyArea.getText())) == false) {

				JOptionPane.showMessageDialog(null, "The key must be between -20000000000 and +2000000000");
			}

			// Otherwise must be in range
			else {

				// Put the key in range by calling the method in FileModeUI
				int key = FileModeUI.putKeyInRange(Integer.parseInt(keyArea.getText()));

				// Go through each character in the phrase inputed 
				for (int i = 0; i < inputArea.getText().length(); i++) {

					// If character is not a letter keep it as it is
					if(Encryption.isNotALetter(inputArea.getText().charAt(i)) == true) {

						outputText = outputText + inputArea.getText().charAt(i);

					}

					// Else call the encoding method
					else {

						outputText = outputText + (Encryption.encode(inputArea.getText().charAt(i), key));
					}


				}

				// Make the output text area set to the string that stored the output
				outputArea.setText(outputText);
			}

		}

		// Decrypt button
		else if (e.getSource() == decrypt) {

			// Reset output text area to blank
			outputText = "";

			// Check if the key is in range
			if (FileModeUI.checkKey(Integer.parseInt(keyArea.getText())) == false) {

				JOptionPane.showMessageDialog(null, "The key must be between -20000000000 and +2000000000");

			}

			// Otherwise the key must be in range
			else {

				int key = FileModeUI.putKeyInRange(Integer.parseInt(keyArea.getText()));

				// Loop through each character inputed
				for (int i = 0; i < inputArea.getText().length(); i++) {

					// If character is not a letter keep it as it is
					if(Encryption.isNotALetter(inputArea.getText().charAt(i)) == true) {

						outputText = outputText + inputArea.getText().charAt(i);

					}

					// Else call the encoding method
					else {

						outputText = outputText + (Encryption.encode(inputArea.getText().charAt(i), -key));
					}


				}

				// Make the output text area set to the string that stored the output
				outputArea.setText(outputText);
			}

		}

	}	// End of action performed method


}	// End of class
