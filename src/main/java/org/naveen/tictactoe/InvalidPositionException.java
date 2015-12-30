package org.naveen.tictactoe;

public class InvalidPositionException extends Exception {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPositionException() {
        super();
    }

    public InvalidPositionException(String s) {
        super(s);
    }

    public InvalidPositionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPositionException(Throwable cause) {
        super(cause);
    }
}
