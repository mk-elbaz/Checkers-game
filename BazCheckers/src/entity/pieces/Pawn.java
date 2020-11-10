
package entity.pieces;

import java.util.ArrayList;

import engine.Piece;
import entity.player.PieceListener;

public class Pawn extends Piece {

	public Pawn(Location location, Color color, PieceListener listener) {
		super(location, color, listener);
	}

	public Pawn(int row, int col, Color color, PieceListener listener) {
		super(row, col, color, listener);
		Location location=new Location(row, col);
		this.setLocation(location);
	}

	public boolean equals(Object obj) {
		if (obj instanceof King)
			return false;
		Pawn value = (Pawn) obj;
		return value.getLocation().getRow() == this.getLocation().getRow() && this.getLocation().getCol() == value.getLocation().getCol() && value.getColor() == this.getColor();
	}

	@Override
	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		Location current = this.getLocation();
		if (this.getColor().equals(Color.RED_COLOR)) {
			Location goUpRight = new Location(current.getRow() - 1, current.getCol() + 1);
			if (goUpRight.getRow() >= 0 && goUpRight.getCol() < 8)
				possibleMoves.add(goUpRight);
			Location goUpLeft = new Location(current.getRow() - 1, current.getCol() - 1);
			if (goUpLeft.getRow() >= 0 && goUpLeft.getCol() >= 0)
				possibleMoves.add(goUpLeft);
		} else if (this.getColor().equals(Color.BLUE_COLOR)) {
			Location goDownRight = new Location(current.getRow() + 1, current.getCol() + 1);
			if (goDownRight.getRow() < 8 && goDownRight.getCol() < 8)
				possibleMoves.add(goDownRight);
			Location goDownLeft = new Location(current.getRow() + 1, current.getCol() - 1);
			if (goDownLeft.getRow() < 8 && goDownLeft.getCol() >= 0)
				possibleMoves.add(goDownLeft);
		}
		return possibleMoves;
	}
	
	@Override
	public String toString() {
		
		return ("Pawn " + "Location: " + this.getLocation() + " Color: " + this.getColor());
	}

	
}
