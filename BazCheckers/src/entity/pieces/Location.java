package entity.pieces;

import engine.Piece;

public class Location {

	private int row;
	private int col;

	public Location(int row, int column) {
		this.setRow(row);
		this.setCol(column);

	}



	public boolean equals(Object obj) {
		Location value = (Location) obj;
		if (value.getRow() == getRow() && value.getCol() == getCol())
			return true;
		else
			return false;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return ("Row: " + this.getRow() 
				+ " " + "Column: " +this.getCol());
				
	}

}
