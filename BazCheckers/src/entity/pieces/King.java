package entity.pieces;

import java.util.ArrayList;

import engine.Piece;
import entity.player.PieceListener;

public class King extends Piece {

	public King(Location location, Color color, PieceListener listener) {
		super(location, color, listener);
	}

	public King(int row, int col, Color color, PieceListener listener) {
		super(row, col, color, listener);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Pawn)
			return false;
		King value = (King) obj;
		return value.getLocation() == this.getLocation() && value.getColor() == this.getColor();

	}

	@Override
	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		Location current = this.getLocation();
		Location goDownRight = new Location(current.getRow() + 1, current.getCol() + 1);
		if (goDownRight.getRow() < 8 && goDownRight.getCol() < 8)
			possibleMoves.add(goDownRight);
		Location goDownLeft = new Location(current.getRow() + 1, current.getCol() - 1);
		if (goDownLeft.getRow() < 8 && goDownLeft.getCol() >= 0)
			possibleMoves.add(goDownLeft);
		Location goUpRight = new Location(current.getRow() - 1, current.getCol() + 1);
		if (goUpRight.getRow() >= 0 && goUpRight.getCol() < 8)
			possibleMoves.add(goUpRight);
		Location goUpLeft = new Location(current.getRow() - 1, current.getCol() - 1);
		if (goUpLeft.getRow() >= 0 && goUpLeft.getCol() >= 0)
			possibleMoves.add(goUpLeft);
		return possibleMoves;
	}

	@Override
	public String toString() {
		
		return ("King " + "Location: " + this.getLocation() + " Color: " + this.getColor());
	}
	
}
