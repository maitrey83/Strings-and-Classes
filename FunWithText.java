/*
 * File: FunWithText.java
 * ================================================================
 * A program where we have fun with text!
 */
import acm.program.*;

public class FunWithText extends ConsoleProgram {
	/* Constant controlling the delay time in printCharByChar. */
	private static final int PAUSE_TIME = 75;
	
	/**
	 * Prints the given string one character at a time, pausing between
	 * each character.
	 *
	 * @param text The text to display.
	 */
	private void printCharByChar(String text) {
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			print(ch);
			pause(PAUSE_TIME);
		}
		
		/* Output a newline at the end. */
		print('\n');
	}
	
	/**
	 * Given a piece of text, returns whether we consider
	 * that text to be a word, under the assumption that a
	 * word is a nonempty string made purely of letters.
	 *
	 * @param text The text to test.
	 * @return Whether the text is a word.
	 */
	private boolean isWord(String text) {
		// Words can't be empty.
		if (text.length() == 0) {
			return false;
		}
		
		// Words must be made purely of letters
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			
			if (!Character.isLetter(ch)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Prompts the user for a word, continuously reprompting until
	 * the user enters a word.
	 *
	 * @param prompt The prompt to display.
	 * @return The entered word.
	 */
	private String readWord(String prompt) {
		while (true) {
			String input = readLine(prompt);
			if (isWord(input)) {
				return input;
			}
			println("Please enter a word.");
		}
	}
	
	/**
	 * Returns the reverse of the input string.
	 *
	 * @param input The string whose reverse should be computed.
	 * @return The reverse of that string.
	 */
	private String reverseString(String input) {
		String result = "";
		
		for (int i = 0; i < input.length(); i++) {
			result = input.charAt(i) + result;
		}
		
		return result;
	}
	
	/**
	 * Given a string of text, returns whether the string is a palindrome.
	 *
	 * This method is incomplete.  We'll finish it next time.
	 *
	 * @param text The text to test.
	 * @return Whether that text is a palindrome.
	 */
	private boolean isPalindrome(String text) {
		String reversed = reverseString(text);
		
		/* Note the use of .equals() here instead of ==. */
		return reversed.equals(text);
	}
	
	public void run() {
		/* We're working with text!  We should be big and bold! */
		setFont("DejaVuSerif-BOLD-24");
		
		String text = readLine("Enter some text: ");
		println("Is that a palindrome? " + isPalindrome(text));
	}
}
