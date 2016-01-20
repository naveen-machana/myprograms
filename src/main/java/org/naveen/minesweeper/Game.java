package org.naveen.minesweeper;

public class Game {

	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Board board = new Board(5, 5, 3);		
		System.out.println(board);
		board.play();
	}

}
