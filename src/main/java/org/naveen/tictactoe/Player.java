package org.naveen.tictactoe;

import java.util.Scanner;

public class Player extends Thread {
	private final String name;
	private volatile Board board;
	private static final Scanner scanner = new Scanner(System.in);
	private final int id;
	private final char character;
	
	public Player(String name, int id, Board board, char character) {
		this.name = name;
		this.id = id;
		this.board = board;
		this.character = character;
	}
	
	public void run() {
		while (!board.isComplete()) {
			try {
				board.play(this);
			} catch (InvalidPositionException e) {
				System.out.println(name + ": Please retry with correct input."
						+ "\n Help: enter row and then column. "
						+ "\n Row and column should be 0, 1 or 2");
			} catch (GameCompletedException e) {
				if (e.winner() == this) {
					System.out.println(name
							+ ": Congratulations...You are the winner");
					break;
				} else if (e.winner() == null) {
					System.out.println(name + ": " + e.getMessage());
					break;
				} else {
					System.out.println(name + ": Sorry...You lose the game.");
				}
			} catch (InterruptedException e) {
				System.out.println(name + ": Time to wrap up. Good bye.");
				break;
			}
		}
		System.out.println(name + ": Exiting.");
	}
	
	public String name() {
		return name;
	}
	
	public Cell readInput() {
		int[] a = new int[2];		
		System.out.println(name + ": Enter row[0-2]: ");
		a[0] = scanner.nextInt();
		System.out.println(name + ": Enter column[0-2]: ");
		a[1] = scanner.nextInt();
		return new Cell(a[0], a[1]);
	}
	
	public int id() {
		return id;
	}
	
	public char character(){
		return character;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
}
