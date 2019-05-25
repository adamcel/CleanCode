package galgenmaennchen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Galgenmaennchen {

	// main method of game
	public static void main(String[] args) {

		System.out.println("Welcome to the game 'Galgenmaennchen'!");

		System.out.println("Please let the game host enter a word first:");

		// Scanner for the user input
		Scanner scan = new Scanner(System.in);
		String word = scan.nextLine().toLowerCase();

		System.out.println("Please enter each round a letter of your choice. Try to guess the word!\n(If you type in more than a letter then the first letter will be used.)\n");

		// Every round the user gets the output, what parts of the word he already guessed and how many mistrials are left until he looses.
		System.out.println("Letter\t\tOutput\t\tLeft mistrials");

		boolean correctWord = false;
		int leftMistrials = 10;
		char letter;
		List<String> letters = new ArrayList<>();
		String guessedWord = "";

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ' ') {
				guessedWord += word.charAt(i);
			} else {
				guessedWord += "-";							// For every letter a minus is added to the string.
			}
		}

		while (!correctWord) {
			letter = scan.next().toLowerCase().charAt(0);
			String newGuessedWord = "";

			// Starting on -1 because else this part will never be entered.
			for (int i = -1; i < letters.size(); i++) {
				if (i != -1 && letters.get(i).equals(letter + "")) {
					System.out.println("You already entered this letter!");
					break;
				}
				
				// When the letter is entered the first time, then do this part.
				if (i == letters.size() - 1) {
					letters.add(letter + "");
					// Checking if the letter was correct and change the guessed word accordingly.
					for (int j = 0; j < word.length(); j++) {
						if (letter == word.charAt(j)) {
							newGuessedWord += letter;
						} else {
							newGuessedWord += guessedWord.charAt(j);
						}
					}
					
					// Calculating the left mistrials
					if (guessedWord.equals(newGuessedWord)) {
						leftMistrials--;
						if (leftMistrials == 0) {
							System.out.println("\t\t" + guessedWord + "\t\t" + leftMistrials);
							System.out.println("I'm sorry but you couldn't guess the right word. The host wins!");
							break;
						}
					}

					guessedWord = newGuessedWord;

					System.out.println("\t\t" + guessedWord + "\t\t" + leftMistrials);

					// Guessed the word?
					if (word.equals(guessedWord)) {
						correctWord = true;
						System.out.println("Congrats! You guessed the right word!");
					}
					break;
				}
			}

		}

		scan.close();

	}

}