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
	private String answer;
	
	public void startGame() {
		readSolution();
		garbleWord();
		explainGame();
		
		while (!correctWord && leftMistrials > 0) {
			letter = scan.next().toLowerCase().charAt(0);
			printGameStatus();
		}
		scan.close();
	}
	
	private void readSolution() {
		System.out.println("Welcome to the game 'Galgenmaennchen'!");
		System.out.println("Please let the game host enter a word first:");
		solution = scan.nextLine().toLowerCase();
	}
	
	private void garbleWord() {
		StringBuilder result = new StringBuilder(solution.length());
		for (int i = 0; i < solution.length(); i++) {
			if (solution.charAt(i) == ' ') {
				result.append(solution.charAt(i));
			} else {
				result.append("-");
			}
		}
		answer = result.toString();
	}
	
	private void explainGame() {
		System.out.println("Please enter each round a letter of your choice. Try to guess the word!");
		System.out.println("(If you type in more than a letter then the first letter will be used.)\n");
		System.out.println("Letter\t\tOutput\t\tLeft mistrials");
	}
	
	private void printGameStatus() {
		if (checkIfNewLetter()) {
			letters.add(letter + "");
			String newAnswer = updateAnswer();
			calculateLeftMistrials(newAnswer);
			
			answer = newAnswer;
			System.out.println("\t\t" + answer + "\t\t" + leftMistrials);
			
			endGame();
		}
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
	
	private String updateAnswer() {
		StringBuilder result = new StringBuilder(solution.length());
		for (int j = 0; j < solution.length(); j++) {
			if (letter == solution.charAt(j)) {
				result.append(letter);
			} else {
				result.append(answer.charAt(j));
			}
		}
		return result.toString();
	}
	
	private void calculateLeftMistrials(String newGuessedWord) {
		if (answer.equals(newGuessedWord)) {
			leftMistrials--;
		}
	}
	
	private void endGame() {
		if (leftMistrials == 0) {
			System.out.println("I'm sorry but you couldn't guess the right word. The host wins!");
		} else {
			checkForCorrectAnswer();
		}
	}
	
	private void checkForCorrectAnswer() {
		if (solution.equals(answer)) {
			correctWord = true;
			System.out.println("Congrats! You guessed the right word!");
		}
	}
}