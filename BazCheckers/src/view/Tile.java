package view;

import java.awt.Point;

import javax.swing.JButton;

import entity.pieces.Location;

public class Tile extends JButton {

	Location location;

	public Tile(Location location) {
		this.location = location;
	}

	public Tile(Point location2) {
		this.location = new Location(location2.x, location2.y);
	}

	public Location getGridLocation() {
		return this.location;
	}

}
