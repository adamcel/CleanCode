package galgenmaennchen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Galgenmaennchen {
	
	private Scanner scan = new Scanner(System.in);
	private boolean correctWord = false;
	private int leftMistrials = 10;
	private char letter;
	private List<String> letters = new ArrayList<>();
	private String solution;
	private String guessedWord;
	
	public void startGame() {
		solution = readSolution();
		guessedWord = garbleWord();
		explainGame();
		
		while (!correctWord && leftMistrials > 0) {
			letter = scan.next().toLowerCase().charAt(0);
			String newGuessedWord = "";

			if (checkIfNewLetter()) {
				letters.add(letter + "");
				// Checking if the letter was correct and change the guessed word accordingly.
				for (int j = 0; j < solution.length(); j++) {
					if (letter == solution.charAt(j)) {
						newGuessedWord += letter;
					} else {
						newGuessedWord += guessedWord.charAt(j);
					}
				}

				// Calculating the left mistrials
				if (guessedWord.equals(newGuessedWord)) {
					leftMistrials--;
				}
				
				if (leftMistrials == 0) {
					System.out.println("\t\t" + guessedWord + "\t\t" + leftMistrials);
					System.out.println("I'm sorry but you couldn't guess the right word. The host wins!");
				} else {
					guessedWord = newGuessedWord;
					System.out.println("\t\t" + guessedWord + "\t\t" + leftMistrials);

					// Guessed the word?
					if (solution.equals(guessedWord)) {
						correctWord = true;
						System.out.println("Congrats! You guessed the right word!");
					}
				}

			}
			
		}
		scan.close();
	}
	
	private String readSolution() {
		System.out.println("Welcome to the game 'Galgenmaennchen'!");
		System.out.println("Please let the game host enter a word first:");
		String word = scan.nextLine().toLowerCase();
		
		return word;
	}
	
	private void explainGame() {
		System.out.println("Please enter each round a letter of your choice. Try to guess the word!");
		System.out.println("(If you type in more than a letter then the first letter will be used.)\n");
		System.out.println("Letter\t\tOutput\t\tLeft mistrials");
	}
	
	private String garbleWord() {
		String guessedWord = "";
		
		for (int i = 0; i < solution.length(); i++) {
			if (solution.charAt(i) == ' ') {
				guessedWord += solution.charAt(i);
			} else {
				guessedWord += "-";
			}
		}
		
		return guessedWord;
	}
	
	private boolean checkIfNewLetter() {
		for (int i = 0; i < letters.size(); i++) {
			if (letters.get(i).equals(letter + "")) {
				System.out.println("You already entered this letter!");
				return false;
			}
		}
		return true;
	}

}