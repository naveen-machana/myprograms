package org.naveen.tictactoe;

import static org.naveen.tictactoe.Constants.SPACE;
import static org.naveen.tictactoe.Constants.isACorner;
import static org.naveen.tictactoe.Constants.isAnEdge;
import static org.naveen.tictactoe.Constants.isCenter;

import java.util.List;
import java.util.Random;

public class FirstPlayerStrategy implements Strategy {
	private final Board board;
	private Strategy strategy;
	
	public FirstPlayerStrategy(Board board) {
		this.board = board;
	}

	@Override
	public Cell nextMove() {
		
		// if it is the firstMove
		if (board.isEmpty()) {
			return firstTimeMove();
		}
		
		List<Cell> previousMoves = board.previousMoves();
		
		if (strategy != null) {
			return strategy.nextMove();
		}
		
		if (board.numberOfEntries() == 2 && 
			isAnEdge(previousMoves.get(1)) &&
			isCenter(previousMoves.get(0))) {
			strategy = new FMCSME();
			return strategy.nextMove();			
		}
		
		if (board.numberOfEntries() == 2 &&
			isACorner(previousMoves.get(1)) &&
			isCenter(previousMoves.get(0))){
			strategy = new FMCOFMCORNER();
			return strategy.nextMove();
		}	
		
		return null;
	}
	
	private Cell firstTimeMove() {
		Random r = new Random();
		//int m = r.nextInt(2);
		int m = 0;
		// pick center position
		if (m == 0){
			return new Cell(1, 1);
		}
		else {
			// pic corner
			return Constants.getCorner(r.nextInt(4));
		}
	}
	
	private MissingMatchableCell findMissingCell(Cell firstMove) {
		char character = board.a(firstMove.x(), firstMove.y());
		
		// check all rows
		int c = 0;
		MissingMatchableCell emptyCell = new MissingMatchableCell(null, false);
		MissingMatchableCell foundCell = emptyCell;
		
		for (int i = 0; i < 3; i++) {
			c = 0;
			for (int j = 0; j < 3; j++) {
				if (board.a(i, j) == character) c++;
				else if (board.a(i, j) == SPACE) {
					foundCell = new MissingMatchableCell(new Cell(i, j), true);
				}
				else {
					foundCell = emptyCell;
					break;
				}
			}
			
			if (foundCell != emptyCell && c == 2){
				return foundCell;
			}
		}
		
		// check all columns
		for (int i = 0; i < 3; i++) {
			c = 0;
			for (int j = 0; j < 3; j++) {
				if (board.a(j, i) == character) c++;
				else if (board.a(j, i) == SPACE) {
					foundCell = new MissingMatchableCell(new Cell(j, i), true);
				}
				else {
					foundCell = emptyCell;
					break;
				}
			}
			
			if (foundCell != emptyCell && c == 2){
				return foundCell;
			}
		}
		
		c = 0;
		// check in LR diagonal
		for (int i = 0; i < 3; i++) {
			if (board.a(i, i) == character) c++;
			else if (board.a(i, i) == SPACE) {
				foundCell = new MissingMatchableCell(new Cell(i, i), true);
			}
			else {
				foundCell = emptyCell;
				break;
			}
		}
		
		if (foundCell != emptyCell && c == 2) {
			return foundCell;
		}
		
		c = 0;
		// check in RL diagonal
		for (int i = 0, j = 2; i < 3; i++, j--) {
			if (board.a(i, j) == character) c++;
			else if (board.a(i, j) == SPACE) {
				foundCell = new MissingMatchableCell(new Cell(i, j), true);
			}
			else {
				foundCell = emptyCell;
				break;
			}
		}
		if (foundCell != emptyCell && c == 2) {
			return foundCell;
		}
		return emptyCell;
		// check 2 diagonals
	}
	
	private static class MissingMatchableCell{
		private final Cell missingCell;
		private final boolean canBeMatched;
		
		public MissingMatchableCell(Cell missingCell, boolean canBeMatched) {
			this.missingCell = missingCell;
			this.canBeMatched = canBeMatched;
		}
	}
	
	// first move is center and second move is an edge
	private class FMCSME implements Strategy{
		private Cell getFarthestCorner(Cell secondMove) {
			Cell res = null;		
			if (Constants.TOP_EDGE.equals(secondMove)) res = Constants.BOTTOM_LEFT;
			else if (Constants.LEFT_EDGE.equals(secondMove)) res = Constants.BOTTOM_RIGHT;
			else if (Constants.BOTTOM_EDGE.equals(secondMove)) res = Constants.TOP_LEFT;
			else if (Constants.RIGHT_EDGE.equals(secondMove)) res = Constants.TOP_RIGHT;
			return res;
		}

		@Override
		public Cell nextMove() {
			List<Cell> previousMoves = board.previousMoves();
			Cell myFirstMove = previousMoves.get(0);
			Cell opponentFirstMove = previousMoves.get(1);
			
			if (board.numberOfEntries() == 2)
				return getFarthestCorner(previousMoves.get(1));
			
			else {
				// check first for winning position
				MissingMatchableCell winningMove = findMissingCell(myFirstMove);
				if (winningMove.canBeMatched == true) return winningMove.missingCell;
				else {
					MissingMatchableCell blockingMove = findMissingCell(opponentFirstMove);
					return blockingMove.missingCell;
				}
			}
		}
	}
	
	// first move is center opponent first move is corner
	private class FMCOFMCORNER implements Strategy {
		
		private Cell formAnXXODiagonal(Cell secondMove) {
			if (Constants.TOP_LEFT.equals(secondMove)) return Constants.BOTTOM_RIGHT;
			if (Constants.TOP_RIGHT.equals(secondMove)) return Constants.BOTTOM_LEFT;
			if (Constants.BOTTOM_LEFT.equals(secondMove)) return Constants.TOP_RIGHT;
			if (Constants.BOTTOM_RIGHT.equals(secondMove)) return Constants.TOP_LEFT;
			return null;
		}
		
		@Override
		public Cell nextMove() {
			List<Cell> previousMoves = board.previousMoves();
			Cell myFirstMove = previousMoves.get(0);
			Cell opponentFirstMove = previousMoves.get(1);
			
			if (board.numberOfEntries() == 2)
				return formAnXXODiagonal(opponentFirstMove);
			
			Cell opponentSM = previousMoves.get(3);
			Cell mySM = previousMoves.get(2);

			if (board.numberOfEntries() == 4) {
				MissingMatchableCell missingCell = findMissingCell(opponentSM);
				if (missingCell.canBeMatched) {
					return missingCell.missingCell;
				}
				else if (Constants.isAnEdge(opponentSM)){
					return getEmptyRCCorner(mySM);
				}				
			}
			
			else {
				MissingMatchableCell winningMove = findMissingCell(myFirstMove);
				if (winningMove.canBeMatched) {
					return winningMove.missingCell;
				}
				else {
					MissingMatchableCell blockingMove = findMissingCell(opponentFirstMove);
					if (blockingMove.canBeMatched){
						return blockingMove.missingCell;
					}
				}
			}
			
			return pickARandomPosition();
		}
		
		private Cell getEmptyRCCorner(Cell corner) {
			List<Cell> adjCorners = Constants.getAdjacentCorners(corner);
			for (Cell o : adjCorners) {
				if (o.x() == corner.x() && board.isEmptySpace(new Cell(o.x(), 1))){
					return o;
				}
				
				else if (o.y() == corner.y() && board.isEmptySpace(new Cell(1, o.y()))){
					return o;
				}
			}
			return null;
		}
		
		private Cell pickARandomPosition() {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					Cell r = new Cell(i, j);
					if (board.isEmptySpace(r)) {
						return r;
					}
				}
			}
			return null;
		}
		
	}

	
}
