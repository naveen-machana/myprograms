package org.naveen.tictactoe;

public class Game {

	public Game() {
	}

	public static void main(String[] args) {
		Board board = new Board(1);
		Player player1 = new MachinePlayer("System", 1, board, 'X');
		Player player2 = new Player("Naveen", 2, board, 'O');
		
		player1.start();
		player2.start();
		
		try {
			player1.join();
			player2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main: Exiting");
	}
}
