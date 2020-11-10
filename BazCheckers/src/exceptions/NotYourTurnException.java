package exceptions;

import javax.swing.JOptionPane;

public class NotYourTurnException extends CheckersException {

	public NotYourTurnException() {
		// TODO Auto-generated constructor stub
	}

	public NotYourTurnException(String errMsg) {
		super(errMsg);
		
	}
}
