package org.naveen.tictactoe;

public final class Cell {
	private final int x;
	private final int y;
	private final int hashCode;
	
	public Cell(int x, int y) {
		ensureValid(x, y);
		this.x = x;
		this.y = y;
		
		// fit x & y values into hashCode
		hashCode = ((x << 4) | y);
	}
	
	public int x() { return x; }
	public int y() { return y; }
	
	private void ensureValid(int x, int y) {
		if ( x < 0 || x >= 3 || y < 0 || y >= 3)
			throw new IllegalArgumentException("Invalid values provided for x or y");
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Cell)) return false;
		Cell other = (Cell)o;
		
		return hashCode == other.hashCode;
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	
	@Override
	public int hashCode() {
		return hashCode;
	}
}
