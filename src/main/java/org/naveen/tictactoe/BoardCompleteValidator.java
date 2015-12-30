package org.naveen.tictactoe;

import static org.naveen.tictactoe.Constants.BOTTOM_LEFT;
import static org.naveen.tictactoe.Constants.BOTTOM_RIGHT;
import static org.naveen.tictactoe.Constants.TOP_LEFT;
import static org.naveen.tictactoe.Constants.TOP_RIGHT;
import static org.naveen.tictactoe.Constants.CENTER;;
public class BoardCompleteValidator {
	
	private volatile Board board;
	
	public BoardCompleteValidator() {
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	private char[][] a() { return board.a();}
	
	boolean checkIfGameIsComplete(char charOfPlayer, Cell cell) {
		boolean result = isRowMatch(charOfPlayer, cell) ||
						 isColumnMatch(charOfPlayer, cell) ||
						 l2rDiagonalMatch(charOfPlayer, cell) ||
						 r2lDiagonalMatch(charOfPlayer, cell);
		return result;
	}
	
	private boolean isRowMatch(char charOfPlayer, Cell cell) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			if (a()[cell.x()][i] != charOfPlayer) {
				break;
			}
			else count++;
		}
		
		return count == 3;
	}
	
	private boolean isColumnMatch(char charOfPlayer, Cell cell) {
		int count = 0;
		
		for (int i = 0; i < 3; i++) {
			if (a()[i][cell.y()] != charOfPlayer) {
				break;
			}
			else count++;
		}
		
		return count == 3;
	}
	
	private boolean l2rDiagonalMatch(char charOfPlayer, Cell cell) {
		int count = 0;
		if (cell.equals(TOP_LEFT) || cell.equals(BOTTOM_RIGHT) || cell.equals(CENTER)) {
			for (int i = 0; i < 3; i++) {
				if (a()[i][i] != charOfPlayer) {
					break;
				}
				else count++;
			}
		}
		
		return count == 3;
	}
	
	private boolean r2lDiagonalMatch(char charOfPlayer, Cell cell) {
		int count = 0;
		if (cell.equals(TOP_RIGHT) || cell.equals(BOTTOM_LEFT) || cell.equals(CENTER)) {
			for (int i = 0, j = 2; i < 3; i++, j--) {
				if (a()[i][j] != charOfPlayer) {
					break;
				}
				else count++;
			}
		}
		
		return count == 3;
	}
}
