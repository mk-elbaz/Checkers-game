package exceptions;

import javax.swing.JOptionPane;

public class CaptureBypassException extends CheckersException{

	public CaptureBypassException() {
	
	}
	public CaptureBypassException(String errMsg) {
		super(errMsg);
		errMsg = "You can't capture now!";
	}
}
