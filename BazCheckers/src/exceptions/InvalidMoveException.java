package exceptions;

import javax.swing.JOptionPane;

public class InvalidMoveException extends CheckersException{

	public InvalidMoveException() {
	
	}
	public InvalidMoveException(String errMsg) {
		super(errMsg);
		
		errMsg = "Invalid move!";
	}
}
