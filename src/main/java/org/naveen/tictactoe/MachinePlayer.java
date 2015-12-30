package org.naveen.tictactoe;

public class MachinePlayer extends Player {
	
	private final Strategy strategy;
	
	public MachinePlayer(String name, int id, Board board, char character) {
		super(name, id, board, character);
		this.strategy = getStrategy(board);
	}
	
	private Strategy getStrategy(Board board) {
		return board.startingPlayer() == this.id() ?
				(new FirstPlayerStrategy(board)) : 
					(new SecondPlayerStrategy(board));
	}	
	
	public Cell readInput() {		
		Cell cell = strategy.nextMove();
		System.out.println("System: next move is " + cell);
		return cell;
	}
}
