package engine;

import java.util.ArrayList;

import entity.pieces.Color;
import entity.pieces.King;
import entity.pieces.Location;
import entity.pieces.Pawn;

public class Board {

	private int dimension;
	private ArrayList<Piece> pieces;

	public Board(ArrayList<Piece> pieces, int dimension) {
		this.dimension = dimension;
		this.pieces = pieces;
	}

	public Piece getPieceAt(Location location) {
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getLocation().equals(location))
				return pieces.get(i);
		}
		return null;
	}

	private Color getOpponentColor(Piece piece) {
		return piece.getColor() == Color.RED_COLOR ? Color.BLUE_COLOR : Color.RED_COLOR;
	}

	public ArrayList<Location> possiblePlays(Piece piece) {
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		Color opponentColor = getOpponentColor(piece);
		Location current = piece.getLocation();
		if (piece instanceof King) {
			Location goDownRight = new Location(current.getRow() + 1, current.getCol() + 1);
			Location goDownRightCapture = new Location(current.getRow() + 2, current.getCol() + 2);
			if (goDownRightCapture.getRow() < 8 && goDownRightCapture.getCol() < 8 && getPieceAt(goDownRight) != null
					&& getPieceAt(goDownRight).getColor() == opponentColor && getPieceAt(goDownRightCapture) == null)
				possibleMoves.add(goDownRightCapture);
			Location goDownLeft = new Location(current.getRow() + 1, current.getCol() - 1);
			Location goDownLeftCapture = new Location(current.getRow() + 2, current.getCol() - 2);
			if (goDownLeftCapture.getRow() < 8 && goDownLeftCapture.getCol() >= 0 && getPieceAt(goDownLeft) != null
					&& getPieceAt(goDownLeft).getColor() == opponentColor && getPieceAt(goDownLeftCapture) == null)
				possibleMoves.add(goDownLeftCapture);
			Location goUpRight = new Location(current.getRow() - 1, current.getCol() + 1);
			Location goUpRightCapture = new Location(current.getRow() - 2, current.getCol() + 2);
			if (goUpRightCapture.getRow() >= 0 && goUpRightCapture.getCol() < 8 && getPieceAt(goUpRight) != null
					&& getPieceAt(goUpRight).getColor() == opponentColor && getPieceAt(goUpRightCapture) == null)
				possibleMoves.add(goUpRightCapture);
			Location goUpLeft = new Location(current.getRow() - 1, current.getCol() - 1);
			Location goUpLeftCapture = new Location(current.getRow() - 2, current.getCol() - 2);
			if (goUpLeftCapture.getRow() >= 0 && goUpLeftCapture.getCol() >= 0 && getPieceAt(goUpLeft) != null
					&& getPieceAt(goUpLeft).getColor() == opponentColor && getPieceAt(goUpLeftCapture) == null)
				possibleMoves.add(goUpLeftCapture);

		} else if (piece instanceof Pawn) {
			if (piece.getColor().equals(Color.BLUE_COLOR)) {
				Location goDownRight = new Location(current.getRow() + 1, current.getCol() + 1);
				Location goDownRightCapture = new Location(current.getRow() + 2, current.getCol() + 2);
				if (goDownRightCapture.getRow() < 8 && goDownRightCapture.getCol() < 8
						&& getPieceAt(goDownRight) != null && getPieceAt(goDownRight).getColor() == opponentColor
						&& getPieceAt(goDownRightCapture) == null)
					possibleMoves.add(goDownRightCapture);
				Location goDownLeft = new Location(current.getRow() + 1, current.getCol() - 1);
				Location goDownLeftCapture = new Location(current.getRow() + 2, current.getCol() - 2);
				if (goDownLeftCapture.getRow() < 8 && goDownLeftCapture.getCol() >= 0 && getPieceAt(goDownLeft) != null
						&& getPieceAt(goDownLeft).getColor() == opponentColor && getPieceAt(goDownLeftCapture) == null)
					possibleMoves.add(goDownLeftCapture);

			} else if (piece.getColor().equals(Color.RED_COLOR)) {
				Location goUpRight = new Location(current.getRow() - 1, current.getCol() + 1);
				Location goUpRightCapture = new Location(current.getRow() - 2, current.getCol() + 2);
				if (goUpRightCapture.getRow() >= 0 && goUpRightCapture.getCol() < 8 && getPieceAt(goUpRight) != null
						&& getPieceAt(goUpRight).getColor() == opponentColor && getPieceAt(goUpRightCapture) == null)
					possibleMoves.add(goUpRightCapture);
				Location goUpLeft = new Location(current.getRow() - 1, current.getCol() - 1);
				Location goUpLeftCapture = new Location(current.getRow() - 2, current.getCol() - 2);
				if (goUpLeftCapture.getRow() >= 0 && goUpLeftCapture.getCol() >= 0 && getPieceAt(goUpLeft) != null
						&& getPieceAt(goUpLeft).getColor() == opponentColor && getPieceAt(goUpLeftCapture) == null)
					possibleMoves.add(goUpLeftCapture);

			}
		}

		if (possibleMoves.isEmpty()) {
			for (int i = 0; i < piece.possibleMoves().size(); i++) {
				if (getPieceAt(piece.possibleMoves().get(i)) == null)
					possibleMoves.add(piece.possibleMoves().get(i));
			}
		}
		return possibleMoves;

		// remove locations that r not empty f new arraylist

	}

	public boolean movePiece(Piece piece, Location destination) {
		if (destination.getRow() == 0 && piece.getColor() == Color.RED_COLOR) {
			pieces.remove(piece);
			piece = piece.upgrade();
			pieces.add(piece);
		}
		if (destination.getRow() == 7 && piece.getColor() == Color.BLUE_COLOR) {
			pieces.remove(piece);
			piece = piece.upgrade();
			pieces.add(piece);

		}
		if (destination.getRow() - piece.getLocation().getRow() == 2
				&& destination.getCol() - piece.getLocation().getCol() == 2) {
			getPieceAt(new Location(piece.getLocation().getRow() + 1, piece.getLocation().getCol() + 1)).captured();
			pieces.remove(getPieceAt(new Location(piece.getLocation().getRow() + 1, piece.getLocation().getCol() + 1)));
			piece.setLocation(destination);
			return false;
		}
		if (destination.getRow() - piece.getLocation().getRow() == 2
				&& destination.getCol() - piece.getLocation().getCol() == -2) {
			getPieceAt(new Location(piece.getLocation().getRow() + 1, piece.getLocation().getCol() - 1)).captured();
			pieces.remove(getPieceAt(new Location(piece.getLocation().getRow() + 1, piece.getLocation().getCol() - 1)));
			piece.setLocation(destination);

			return false;
		}
		if (destination.getRow() - piece.getLocation().getRow() == -2
				&& destination.getCol() - piece.getLocation().getCol() == 2) {
			getPieceAt(new Location(piece.getLocation().getRow() - 1, piece.getLocation().getCol() + 1)).captured();
			pieces.remove(getPieceAt(new Location(piece.getLocation().getRow() - 1, piece.getLocation().getCol() + 1)));
			piece.setLocation(destination);

			return false;
		}
		if (destination.getRow() - piece.getLocation().getRow() == -2
				&& destination.getCol() - piece.getLocation().getCol() == -2) {
			getPieceAt(new Location(piece.getLocation().getRow() - 1, piece.getLocation().getCol() - 1)).captured();
			pieces.remove(getPieceAt(new Location(piece.getLocation().getRow() - 1, piece.getLocation().getCol() - 1)));
			piece.setLocation(destination);

			return false;
		}
		piece.setLocation(destination);
		return true;

	}

	public ArrayList<Piece> captureCapable(Color color) {
		ArrayList<Piece> captureCapable = new ArrayList<Piece>();
		for (int i = 0; i < pieces.size(); i++) {
			Color opponentColor = getOpponentColor(pieces.get(i));
			Location current = pieces.get(i).getLocation();
			if (pieces.get(i).getColor() == color) {
				if (pieces.get(i) instanceof King) {
					Location goDownRight = new Location(current.getRow() + 1, current.getCol() + 1);
					Location goDownRightCapture = new Location(current.getRow() + 2, current.getCol() + 2);
					if (goDownRightCapture.getRow() < 8 && goDownRightCapture.getCol() < 8
							&& getPieceAt(goDownRight) != null && getPieceAt(goDownRight).getColor() == opponentColor
							&& getPieceAt(goDownRightCapture) == null)
						captureCapable.add(pieces.get(i));
					Location goDownLeft = new Location(current.getRow() + 1, current.getCol() - 1);
					Location goDownLeftCapture = new Location(current.getRow() + 2, current.getCol() - 2);
					if (goDownLeftCapture.getRow() < 8 && goDownLeftCapture.getCol() >= 0
							&& getPieceAt(goDownLeft) != null && getPieceAt(goDownLeft).getColor() == opponentColor
							&& getPieceAt(goDownLeftCapture) == null)
						captureCapable.add(pieces.get(i));
					Location goUpRight = new Location(current.getRow() - 1, current.getCol() + 1);
					Location goUpRightCapture = new Location(current.getRow() - 2, current.getCol() + 2);
					if (goUpRightCapture.getRow() >= 0 && goUpRightCapture.getCol() < 8 && getPieceAt(goUpRight) != null
							&& getPieceAt(goUpRight).getColor() == opponentColor
							&& getPieceAt(goUpRightCapture) == null)
						captureCapable.add(pieces.get(i));
					Location goUpLeft = new Location(current.getRow() - 1, current.getCol() - 1);
					Location goUpLeftCapture = new Location(current.getRow() - 2, current.getCol() - 2);
					if (goUpLeftCapture.getRow() >= 0 && goUpLeftCapture.getCol() >= 0 && getPieceAt(goUpLeft) != null
							&& getPieceAt(goUpLeft).getColor() == opponentColor && getPieceAt(goUpLeftCapture) == null)
						captureCapable.add(pieces.get(i));
				} else if (pieces.get(i) instanceof Pawn) {
					if (pieces.get(i).getColor().equals(Color.BLUE_COLOR)) {
						Location goDownRight = new Location(current.getRow() + 1, current.getCol() + 1);
						Location goDownRightCapture = new Location(current.getRow() + 2, current.getCol() + 2);
						if (goDownRightCapture.getRow() < 8 && goDownRightCapture.getCol() < 8
								&& getPieceAt(goDownRight) != null
								&& getPieceAt(goDownRight).getColor() == opponentColor
								&& getPieceAt(goDownRightCapture) == null)
							captureCapable.add(pieces.get(i));
						Location goDownLeft = new Location(current.getRow() + 1, current.getCol() - 1);
						Location goDownLeftCapture = new Location(current.getRow() + 2, current.getCol() - 2);
						if (goDownLeftCapture.getRow() < 8 && goDownLeftCapture.getCol() >= 0
								&& getPieceAt(goDownLeft) != null && getPieceAt(goDownLeft).getColor() == opponentColor
								&& getPieceAt(goDownLeftCapture) == null)
							captureCapable.add(pieces.get(i));
					} else if (pieces.get(i).getColor().equals(Color.RED_COLOR)) {
						Location goUpRight = new Location(current.getRow() - 1, current.getCol() + 1);
						Location goUpRightCapture = new Location(current.getRow() - 2, current.getCol() + 2);
						if (goUpRightCapture.getRow() >= 0 && goUpRightCapture.getCol() < 8
								&& getPieceAt(goUpRight) != null && getPieceAt(goUpRight).getColor() == opponentColor
								&& getPieceAt(goUpRightCapture) == null)
							captureCapable.add(pieces.get(i));
						Location goUpLeft = new Location(current.getRow() - 1, current.getCol() - 1);
						Location goUpLeftCapture = new Location(current.getRow() - 2, current.getCol() - 2);
						if (goUpLeftCapture.getRow() >= 0 && goUpLeftCapture.getCol() >= 0
								&& getPieceAt(goUpLeft) != null && getPieceAt(goUpLeft).getColor() == opponentColor
								&& getPieceAt(goUpLeftCapture) == null)
							captureCapable.add(pieces.get(i));
					}

				}
			}
		}

		return captureCapable;
	}

	public boolean canMakeMove(Color color) {
		for (int i = 0; i < pieces.size(); i++) {
			if (pieces.get(i).getColor() == color)
				if (possiblePlays(pieces.get(i)).size() > 0)
					return true;
		}
		return false;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

}
