package engine;

import java.util.ArrayList;

import javax.swing.JButton;

import entity.pieces.Color;
import entity.pieces.King;
import entity.pieces.Location;
import entity.player.PieceListener;

public abstract class Piece {

	private Location location;
	private Color color;
	private PieceListener listener;

	public Piece(Location location, Color color, PieceListener listener) {
		this.location = location;
		this.color = color;
		this.listener = listener;
	}

	public Piece(int row, int col, Color color, PieceListener listener) {
		Location location = new Location(row, col);
		this.location = location;
		this.listener = listener;
		this.color = color;
	}

	public void captured() {
		listener.onPieceCaptured();
	}

	public King upgrade() {
		King piece = new King(this.location, this.color, this.listener);
		return piece;
	}

	public Color getColor() {
		return this.color;
	}

	public abstract ArrayList<Location> possibleMoves();

	public boolean equals(Object obj) {
		Piece value = (Piece) obj;
		return value.getLocation().getRow() == this.getLocation().getRow()
				&& value.getLocation().getCol() == this.getLocation().getCol() && value.getColor() == this.getColor();

	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location.setRow(location.getRow());
		this.location.setCol(location.getCol());
	}
}
