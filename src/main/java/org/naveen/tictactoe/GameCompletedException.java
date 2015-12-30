package org.naveen.tictactoe;

public class GameCompletedException extends RuntimeException {
	
	private final Player winner;
	
	public GameCompletedException(Player winner) {
		super();
		this.winner = winner;
	}

	public GameCompletedException(String message, Player winner) {
		super(message);
		this.winner = winner;
	}
	
	public Player winner() {
		return winner;
	}
}
