import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Amit 
 * Date: February 25th, 2023
 * Description: Class is a main menu for the program, displays an animation with a live mode and file mode options
 * Method List: public void paint(Graphics g) - paints words to the screen for animation
 * 				public void actionPerformed(ActionEvent e) - listens to when a button is clicked
 * 
 * 	
 */

public class Animation extends JFrame implements ActionListener {

	// Position for animations
	int x = 1000, delay = 10;

	// background 
	ImageIcon backGnd;

	// Making live mode and file mode button
	JButton liveModeBtn = new JButton("Live Mode");
	JButton fileModeBtn = new JButton("File Mode");

	// Intro music
	File file = new File("hacker.wav");
	AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
	Clip clip = AudioSystem.getClip();

	/**
	 * Window Constructor
	 */
	public Animation() throws IOException, UnsupportedAudioFileException, LineUnavailableException {

		// Window Title
		super(" The Encryption Machine");

		// Setting size, location and unresizable 
		pack();
		setSize(1000,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		// Live Mode button location, font and adding action listener
		liveModeBtn.setBounds(70, 280, 200, 60);
		liveModeBtn.setFont(new Font("Serif", Font.BOLD, 24));
		liveModeBtn.addActionListener(this);
		liveModeBtn.setBackground(Color.white);
		liveModeBtn.setForeground(Color.BLACK);

		// File Mode button location, font and adding action listener
		fileModeBtn.setBounds(350, 280, 200, 60);
		fileModeBtn.setFont(new Font("Serif", Font.BOLD, 24));
		fileModeBtn.addActionListener(this);
		fileModeBtn.setBackground(Color.white);
		fileModeBtn.setForeground(Color.BLACK);

		// Open audio file
		clip.open(audioStream);
		clip.start();


		// Adding components to the frame
		add(fileModeBtn);
		add(liveModeBtn);

		// Initialize background image
		backGnd = new ImageIcon("background.jpg");

		// Setting exit on close and visibility  
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Paint Method
	 */
	public void paint(Graphics g) {

		// Moving "The" 
		for (int i = 0 ; i < 1000 ; i++) {

			backGnd.paintIcon(this, g, 0, 0);
			x = x - 12;
			g.setFont(new Font("monoscaped", 3, 48));
			g.setColor(Color.white);
			g.drawString("The",x, 200);

			try {
				Thread.sleep(delay);
			}
			catch(Exception error) {
			}

			if (x < 20) {
				x = 1000;
				break;		// stop after 20
			}
		}

		// Moving "Encryption"
		for (int i = 0 ; i < 1000 ; i++) {

			backGnd.paintIcon(this, g, 0, 0);
			g.setFont(new Font("monoscaped", 3, 48));
			g.drawString("The",20, 200);
			x = x - 12;
			g.setFont(new Font("monoscaped", 3, 48));
			g.drawString("Encryption", x, 200);

			try {
				Thread.sleep(delay);
			}
			catch(Exception error) {
			}

			if (x < 115) {
				x = 0;
				break;		// stop after 115
			}
		}

		// Moving "Machine"
		for (int i = 0 ; i < 1000 ; i++) {

			backGnd.paintIcon(this, g, 0, 0);
			g.setFont(new Font("monoscaped", 3, 48));
			g.drawString("The",20, 200);
			x = x + 12;
			g.setFont(new Font("monoscaped", 3, 48));
			g.drawString("Encryption", 130, 200);
			g.setFont(new Font("monoscaped", 3, 48));
			g.drawString("Machine", x, 200);
			g.drawString("______________________", 20,225);

			try {
				Thread.sleep(delay);
			}
			catch(Exception error) {
			}

			if (x > 400) {
				x = 0;
				break;		// Stop after 625
			}
		}

	}

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

		// Call window constructor
		new Animation();
	}

	/*
	 * Method to listen to button clicks
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// If Live Mode button is clicked
		if (e.getSource() == liveModeBtn) {
			this.dispose();	// Close window and stop music then call the livemodeui class 
			clip.stop();
			new LiveModeUI();

		}

		// If File Mode button is clicked
		if (e.getSource() == fileModeBtn) {
			this.dispose();	// Close window and stop music then call the filemodeui class 
			clip.stop();
			new FileModeUI();

		}

	} // End of action performed 

}	// End of class
