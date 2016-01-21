package org.naveen.minesweeper;

import org.naveen.minesweeper.Cell;

// assumptions: 1. row and columns will not exceed 128, 128 (meaning 128 rows and 128 columns grid)
// assumptions: 2. when one cell is compared to another, only x,y values are checked for equality in equals method.

public class Cell {
	public static final int SPACE = 0;
	public static final int MINE = -1;
	private final int x, y;
	private boolean isOpened;
	private int value = SPACE;
	private boolean containsMine;
	private int rchash;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;		
		rchash = (x << 7 | y);
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public boolean isOpened() {
		return isOpened;
	}

	public void setOpened(boolean isOpened) {
		this.isOpened = isOpened;
	}

	public int value() {
		return value;
	}

	public void setValue(int value) {
		if (!containsMine) {
			this.value = value;
			containsMine = value == MINE ? true : false;			
		}
	}

	public boolean containsMine() {
		return containsMine;
	}

	@Override
	public int hashCode() {
		return rchash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cell))
			return false;
		
		Cell other = (Cell) obj;
		if (rchash != other.rchash) 
			return false;
		
		return true;
	}	
	
	public String stringValue() {
		if (value == MINE) return "*";
		//else if (value == SPACE) return " ";
		else return String.valueOf(value);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(x).append(", ").append(y).append("]").append(" value: ").append(value);
		return builder.toString();
	}
	
}
