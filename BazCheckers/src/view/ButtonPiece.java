package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import engine.Piece;
import entity.pieces.Color;
import entity.pieces.Location;

public class ButtonPiece extends JButton {

	Piece piece;
	Tile tile;
	Location location;
	ImageIcon redPawn = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redPawn.png")).getImage().getScaledInstance(55,
			55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bluePawn = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("bluePawn.png")).getImage()
			.getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH));

	public ButtonPiece(Piece piece, Location location) {
		if (piece.getColor().equals(Color.BLUE_COLOR)) {
			this.setIcon(bluePawn);
		} else {
			this.setIcon(redPawn);
		}
		this.setOpaque(false);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.piece = piece;
		this.location = location;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Location getGridLocation() {
		return this.location;
	}

	public void setGridLocation(Location r) {
		this.location = r;
	}

}
