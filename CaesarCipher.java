/*
 * File: CaesarCipher.java
 * ================================================================
 * A program that implements the Caesar cipher.
 */
import acm.program.*;

public class CaesarCipher extends ConsoleProgram {
	/* The minimum and maximum legal encryption keys. */
	private static final int MIN_KEY = -25;
	private static final int MAX_KEY = +25;
	
	/**
	 * Returns whether a given Caesar cipher encryption key is
	 * valid.
	 * 
	 * @param key The key to test.
	 * @return Whether it's valid.
	 */
	private boolean isValidKey(int key) {
		return key >= MIN_KEY && key <= MAX_KEY;
	}
	
	/**
	 * Prompts the user for an encryption key, reprompting until
	 * they enter a valid key.
	 * 
	 * @param prompt The prompt to display.
	 * @return A legal Caesar cipher encryption key.
	 */
	private int readEncryptionKey(String prompt) {
		while (true) {
			/* Read a potential key from the user; if it's valid,
			 * hand it back.
			 */
			int key = readInt(prompt);
			if (isValidKey(key))
				return key;
			
			/* Otherwise report an error and try again. */
			println("That's not a valid key.");
		}
	}
	
	/**
	 * Encrypts a single character (which must be a letter) with
	 * the given encryption key.
	 * 
	 * @param toEncrypt The letter to encrypt.
	 * @param key The encryption key.
	 * @return The encrypted character.
	 */
	private char encryptSingleLetter(char toEncrypt, int key) {
		/* Find the offset from 'A'.  This is a number in the range
		 * 0 (A) to 25 (Z).
		 */
		int position = toEncrypt - 'A';
		
		/* Cycle the position forward by the proper amount.  The
		 * addition applies the shift, with the modulus being used
		 * to wrap it back around.
		 */
		position = (position + key) % 26;
		
		/* Convert the index 0 - 25 into a letter 'A' - 'Z'. */
		return (char)('A' + position);
	}
	
	/**
	 * Encrypts the given string with the given encryption key.
	 * 
	 * @param toEncrypt The string to encrypt.
	 * @param key The encryption key.
	 * @return The encrypted string.
	 */
	private String encryptString(String toEncrypt, int key) {
		/* If the key is negative (cycle backwards), convert it into
		 * an equivalent key that cycles forwards.
		 */
		if (key < 0)
			key = 26 + key;
		
		/* Build up the result one character at a time. */
		String result = "";
		
		/* For each character, encrypt it if it's a letter and
		 * preserve it otherwise.
		 */
		for (int i = 0; i < toEncrypt.length(); i++) {
			if (Character.isLetter(toEncrypt.charAt(i))) {
				result += encryptSingleLetter(toEncrypt.charAt(i), key);
			} else {
				result += toEncrypt.charAt(i);
			}
		}
		
		return result;
	}
	
	/**
	 * Counts the number of times that the given character appears
	 * in the indicated string.
	 * 
	 * @param text The string to search.
	 * @param ch The character in question.
	 * @return How many times it appears.
	 */
	private int countOccurs(String text, char ch) {
		int result = 0;
		
		/* For each character, count it if it matches the given
		 * character.
		 */
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == ch) {
				result ++;
			}
		}
		
		return result;
	}
	
	/**
	 * Given a piece of encrypted English text, guesses the most
	 * likely encryption key used to encrypt it with a Caesar
	 * cipher.
	 * 
	 * @param text The encrypted text.
	 * @return Our guess of the encryption key.
	 */
	private int guessMostLikelyKey(String text) {
		/* Track our best guess so far, which initially is 'A'. */
		char ourBestGuess = 'A';
		int numAppearancesOfBest = 0;
		
		/* For each letter, count its frequency and update our guess
		 * if this is more frequent than the last character we tried.
		 */
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			int numCopiesOfCurrent = countOccurs(text, ch);
			if (numCopiesOfCurrent > numAppearancesOfBest) {
				ourBestGuess = ch;
				numAppearancesOfBest = numCopiesOfCurrent;
			}
		}
		
		/* The encryption key would be the difference between this
		 * letter and the letter E.
		 */
		return ourBestGuess - 'E';
	}
	
	public void run() {
		/* Although we're supposedly keeping secrets, let's use a
		 * really large font!
		 */
		setFont("DejaVuSerif-BOLD-32");
		
		/* Get the text to decrypt. */
		String toEncrypt = readLine("Enter text to decrypt: ").toUpperCase();
		int key = guessMostLikelyKey(toEncrypt);
		
		/* When decrypting, we use the negative key as our key, since
		 * we want to decrypt.
		 */
		println(encryptString(toEncrypt, -key));
	}
}
