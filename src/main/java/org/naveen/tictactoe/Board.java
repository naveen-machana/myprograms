package org.naveen.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Board {
	private final Object lock;
	private volatile int currentPlayer;
	private final BoardCompleteValidator validator;
	private final AtomicReference<Winner> winner = new AtomicReference<>(); 
	private final PrintUtil printer = new PrintUtil();
	private volatile int entriesSet = 0;
	private final int startingPlayer;
	private final List<Cell> previousMoves = Collections.synchronizedList(new ArrayList<Cell>());
	
	private static final char SPACE = ' ';
	private static final Winner NO_WINNER_YET = new Winner(false, null, false);
	
	private final char[][] a = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
	
	public Board(int player) {
		this.currentPlayer = player;
		this.startingPlayer = player;
		lock = new Object();
		winner.set(NO_WINNER_YET);
		validator = new BoardCompleteValidator();
	}
	
	public void play(Player player) throws InterruptedException, InvalidPositionException {
		synchronized(lock) {
			while (!isComplete() && currentPlayer != player.id()) {
					lock.wait();
			}				
			
			if (isComplete()) {
				throw new GameCompletedException("Game is already completed", winner.get().player);
			}
			
			Cell cell = readInput(player);
			
			// check if the given position is not already taken
			boolean isValid = ensureValidPosition(cell);

			// set character in given position
			a[cell.x()][cell.y()] = player.character();
			entriesSet++;
			previousMoves.add(cell);

			// check if this move completes the game
			validator.setBoard(this);
			boolean isCompleted = validator.checkIfGameIsComplete(player.character(), cell);

			printer.print();
			terminateIfComplete(player, isCompleted);

			// set next player
			setNextPlayer(player);

			// notify the next player
			lock.notifyAll();			
		}		
	}
	
	private void setNextPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer.id() == 1 ? 2 : 1;	
	}
	
	private void terminateIfComplete(Player currentPlayer, boolean isCompleted) {		
		if (isCompleted) {
			setWinner(new Winner(true, currentPlayer, false));		
			setNextPlayer(currentPlayer);
			lock.notifyAll();
			throw new GameCompletedException("Game is completed.", currentPlayer);
		}
		else if (entriesSet == 9){
			setWinner(new Winner(true, null, true));
			setNextPlayer(currentPlayer);
			lock.notifyAll();
			throw new GameCompletedException("Game is completed in a tie", null);
		}
	}
	
	private boolean ensureValidPosition(Cell cell) throws InvalidPositionException{
		if (a[cell.x()][cell.y()] != SPACE) {
			throw new InvalidPositionException("Position is already taken. Retry");
		}
		return true;
	}
	
	public boolean isComplete() {
		return winner.get().isComplete;
	}
	
	public int startingPlayer() {
		return startingPlayer;
	}
	
	private void setWinner(Winner currentWinner) {		
		winner.set(currentWinner);
	}
	
	private static class Winner {
		private final boolean isComplete;
		private final Player player;
		private final boolean isTie;
		
		Winner(boolean isComplete, Player player, boolean isTie) {
			this.isComplete = isComplete;
			this.player = player;
			this.isTie = isTie;
		}
	}
	
	private class PrintUtil {
		void print() {
			System.out.println("----------------");
			System.out.println(String.format("[ %s ][ %s ][ %s ]", a[0][0], a[0][1], a[0][2]));
			System.out.println("----------------");
			System.out.println(String.format("[ %s ][ %s ][ %s ]", a[1][0], a[1][1], a[1][2]));
			System.out.println("----------------");
			System.out.println(String.format("[ %s ][ %s ][ %s ]", a[2][0], a[2][1], a[2][2]));
			System.out.println("----------------");
		}
	}
	
	public Cell readInput(Player player) {
		return player.readInput();
	}
	
	public char[][] a(){
		return a;
	}
	
	public boolean isEmpty() {
		return entriesSet == 0;
	}
	
	public List<Cell> previousMoves() {
		return previousMoves;
	}
	
	public int numberOfEntries() {
		return entriesSet;
	}
	
	public boolean isEmptySpace(Cell c) {
		return a[c.x()][c.y()] == ' ';
	}
	
	public char a(int x, int y) {
		return a[x][y];
	}
}
