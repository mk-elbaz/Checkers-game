package entity.player;

import engine.PlayerListener;
import entity.pieces.Color;

public class Player implements PieceListener  {

	private Color color;
	private int remainingPieces;
	private PlayerListener listener;
	
	
	public Player(Color color, PlayerListener listener) {
		this.color=color;
		this.listener=listener;
		this.setRemainingPieces(12);
	}
	
	public Color getColor() {
		return this.color;
	}

	@Override
	public void onPieceCaptured() {
		// TODO Auto-generated method stub
		this.setRemainingPieces(this.getRemainingPieces() - 1);
	}

	public int getRemainingPieces() {
		return remainingPieces;
	}

	public void setRemainingPieces(int remainingPieces) {
		this.remainingPieces = remainingPieces;
	}
	
	
}   
