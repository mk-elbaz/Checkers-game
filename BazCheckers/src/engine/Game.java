package engine;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import entity.pieces.Color;
import entity.pieces.Location;
import entity.pieces.Pawn;
import entity.player.Player;
import exceptions.CaptureBypassException;
import exceptions.InvalidMoveException;
import exceptions.NotYourTurnException;
import view.GameListener;

public class Game implements PlayerListener {

	private Player currentPlayer;
	private Player opponentPlayer;
	private Board board;
	private GameListener listener;
	private final int DIMNESION = 600;

	public Game(GameListener listener) {
		this.listener = listener;
		currentPlayer = new Player(Color.RED_COLOR, this);
		opponentPlayer = new Player(Color.BLUE_COLOR, this);
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		pieces.add(new Pawn(5, 1, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(5, 3, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(5, 5, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(5, 7, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(6, 0, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(6, 2, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(6, 4, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(6, 6, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(7, 1, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(7, 3, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(7, 5, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(7, 7, currentPlayer.getColor(), currentPlayer));
		pieces.add(new Pawn(0, 0, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(0, 2, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(0, 4, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(0, 6, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(1, 1, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(1, 3, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(1, 5, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(1, 7, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(2, 0, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(2, 2, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(2, 4, opponentPlayer.getColor(), opponentPlayer));
		pieces.add(new Pawn(2, 6, opponentPlayer.getColor(), opponentPlayer));
		board = new Board(pieces, DIMNESION);
	}

	public ArrayList<Piece> getPieces() {

		return board.getPieces();
	}

	public void Play(Piece piece, Location destination)
			throws NotYourTurnException, CaptureBypassException, InvalidMoveException {
		Location oldLocation = new Location(piece.getLocation().getRow(), piece.getLocation().getCol());
		if (currentPlayer.getColor() != piece.getColor()) {
			throw new NotYourTurnException("Not Your Turn!");
		} else if (board.captureCapable(currentPlayer.getColor()).size() > 0
				&& board.captureCapable(currentPlayer.getColor()).indexOf(piece) == -1) {
			throw new CaptureBypassException("You must capture now!");
		} else if (board.possiblePlays(piece).indexOf(destination) == -1) {
			throw new InvalidMoveException("Invalid Move!");
		}
		board.movePiece(piece, destination);
		if (destination.getRow() - oldLocation.getRow() == 2 || destination.getRow() - oldLocation.getRow() == -2) {
			if (board.captureCapable(currentPlayer.getColor()).indexOf(piece) == -1) {
				endTurn();
			}
		} else
			endTurn();
	}

	private void endTurn() {
		if (!board.canMakeMove(opponentPlayer.getColor()))
			onLose(opponentPlayer);
		Player temp = currentPlayer;
		currentPlayer = opponentPlayer;
		opponentPlayer = temp;
		if (remainingColPieces(currentPlayer.getColor()) == 0)
			onLose(currentPlayer);
		else if (remainingColPieces(opponentPlayer.getColor()) == 0)
			onLose(opponentPlayer);
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public Player getOpponnentPlayer() {
		return this.opponentPlayer;
	}

	public int getDimension() {
		return this.DIMNESION;
	}

	public int remainingColPieces(Color color) {
		int num = 0;
		for (int i = 0; i < getPieces().size(); i++) {
			if (getPieces().get(i).getColor() == color)
				num++;
		}
		return num;
	}

	@Override
	public void onLose(Player player) {
		listener.GameOver(player.getColor());
	}

}