/**
 * 
 */

/**
 * @author Edith, Amit
 * Date: February 27th, 2023
 * Description: This class is used to encrypt/decrypt characters 
 * Method List: public static boolean isNotALetter (char character) - Method checks if a character is a letter or not
 * 				public static char encode (char letter, int key) -  Method returns a character encoded depending on the given key
 * 				public static char decode (char letter, int key) - Method returns a character decoded depending on the given key
 * 				
 */

public class Encryption {


	/**
	 * Method checks if a character is a letter or not
	 */
	public static boolean isNotALetter (char character) {

		// If the character is between the letter ranges in ASCII code return false
		if (character >= 97 && character <= 122 || character >= 65 && character <= 90) {
			return false;
		}
		else {
			return true;
		}

	}

	/**
	 * Method returns a character encoded depending on the given key
	 */
	public static char encode (char letter, int key) {


		// If the character is a lowercase letter
		if (Character.toLowerCase(letter) == letter) {

			// If it goes past z
			if ((letter + key) > 122) {
				letter = (char) (96 + (key - (122 - letter)));
				return letter;

			}

			// Goes backwards from a 
			else if ((letter + key) < 97) {
				letter = (char) (122 + (key + (letter - 96)));
				return letter;
			}

			// Otherwise it must be in range 
			else {
				return (char) (letter + key);
			}


		}

		// Otherwise it must be a uppercase letter
		else {

			// If it goes past Z
			if ((letter + key) > 90) {

				letter = (char) (64 + (key - (90 - letter)));
				return letter;

			}

			// Goes backwards from A
			else if ((letter + key) < 65) {
				letter = (char) (90 + (key + (letter - 64)));
				return letter;
			}

			// Otherwise it must be in range
			else {
				return (char) (letter + key);
			}
		}

	}


	/**
	 * Method returns a character decoded depending on the given key
	 */
	public static char decode (char letter, int key) {

		// Return decoded character by called encode method by giving a negative key to reverse
		return encode(letter, -key);

	}

	/**
	 * @param args
	 * Self-testing method
	 */
	public static void main(String[] args) {

		// Declare and initialize the keys
		int [] keys = new int[3];
		keys[0] = 3;
		keys[1] = 25;
		keys[2] = 6;

		// Declare and initialize the phrases
		String [] phrases = new String[3];
		phrases[0] = "The cook worked 12 hours in the darkened kitchen!";
		phrases[1] = "Did Fred look well? That's it!";
		phrases[2] = "Ckrr, grr 2567 yvoky roqkj znk ktixevzotm vxumxgs";

		// Make the output blank to store the encoded phrases after
		String output = "";

		// Loop through the number of rows
		for (int i = 0; i < phrases.length; i++) {

			// Loop through each letter in the phrase
			for (int j = 0; j < phrases[i].length(); j++) {

				// If it is not a letter keep it as it is
				if(Encryption.isNotALetter(phrases[i].charAt(j)) == true) {

					output = output + phrases[i].charAt(j);

				}
				// Otherwise it must be a letter, call the encode method
				else {

					output = output + Encryption.encode(phrases[i].charAt(j), keys[i]);
				}

			}

			// New line for next row
			output = output + "\n";

		}

		// Print the result
		System.out.println(output);

		// Make the output empty to test decoding method
		output = "";

		// Loop through the number of rows
		for (int i = 0; i < phrases.length; i++) {

			// Loop through each letter in the phrase
			for (int j = 0; j < phrases[i].length(); j++) {

				// If it is not a letter keep it as it is
				if(Encryption.isNotALetter(phrases[i].charAt(j)) == true) {

					output = output + phrases[i].charAt(j);

				}
				// Otherwise it must be a letter, call the decode method
				else {

					output = output + Encryption.decode(phrases[i].charAt(j), keys[i]);
				}

			}

			// New line for next row
			output = output + "\n";

		}

		// Print the result
		System.out.println(output);


	} // End of main

}	// End of encryption class
