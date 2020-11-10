package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.Game;
import entity.pieces.Location;
import entity.pieces.Pawn;
import exceptions.CaptureBypassException;
import exceptions.InvalidMoveException;
import exceptions.NotYourTurnException;

public class CheckerBoard extends JFrame implements GameListener, ActionListener {

	public static int row = 8;
	public static int col = 8;
	public static Color white = Color.WHITE;
	public static Color black = Color.BLACK;
	static ButtonPiece pieceButton;
	static boolean capture;
	static Game game;
	static remainingPiecesFrame f;
	static Tile tile;
	static Tile old;
	static ButtonPiece lastClickedPiece = null;
	static ArrayList<Tile> tiles = new ArrayList<Tile>();
	static ArrayList<ButtonPiece> buttons = new ArrayList<ButtonPiece>();
	ImageIcon icon;
	ImageIcon blackTile = new ImageIcon(new ImageIcon(CheckerBoard.class.getResource("blackTile.png")).getImage()
			.getScaledInstance(81, 81, java.awt.Image.SCALE_SMOOTH));
	ImageIcon whiteTile = new ImageIcon(new ImageIcon(CheckerBoard.class.getResource("whiteTile.png")).getImage()
			.getScaledInstance(81, 81, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redKing = new ImageIcon(new ImageIcon(CheckerBoard.class.getResource("redKing.png")).getImage()
			.getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH));
	ImageIcon blueKing = new ImageIcon(new ImageIcon(CheckerBoard.class.getResource("blueKing.png")).getImage()
			.getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH));

	static ImageIcon startGame = new ImageIcon(new ImageIcon(CheckerBoard.class.getResource("startGame.png")).getImage()
			.getScaledInstance(439, 150, java.awt.Image.SCALE_SMOOTH));
	static ImageIcon menu = new ImageIcon(new ImageIcon(CheckerBoard.class.getResource("menuBack.jpg")).getImage()
			.getScaledInstance(626, 500, java.awt.Image.SCALE_SMOOTH));
	static int blueRem = 12;
	static int redRem = 12;

	public CheckerBoard() {
		WindowDestroyer myListener = new WindowDestroyer();
		addWindowListener(myListener);
		game = new Game(this);
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		this.setSize(width / 2, height / 2);
		this.setLocationRelativeTo(null);
		this.setTitle("CheckerBoard");
		this.setSize(650, 650);
		this.getContentPane().setLayout(new GridLayout(row, col));
		for (int i = 0; i < row; i++) {
			if (i % 2 == 0)
				icon = blackTile;
			else
				icon = whiteTile;
			for (int j = 0; j < col; j++) {
				Location temp = new Location(i, j);
				tile = new Tile(temp);
				tile.setLayout(new BorderLayout());
				tile.setIcon(icon);
				tile.setBorderPainted(false);
				tile.setVisible(true);
				tile.setOpaque(true);
				tile.addActionListener(this);
				tiles.add(tile);
				this.add(tile);
				if (icon.equals(blackTile))
					icon = whiteTile;
				else {
					icon = blackTile;

				}
				for (int k = 0; k < game.getPieces().size(); k++) {
					if (game.getPieces().get(k).getLocation().equals(temp)) {
						pieceButton = new ButtonPiece(game.getPieces().get(k), temp);
						pieceButton.addActionListener(this);
						buttons.add(pieceButton);
						tile.add(pieceButton);
						tile.repaint();
						tile.revalidate();
						break;
					}
				}

			}

		}
	}

	public static void playSound(String soundName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JFrame fr = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		fr.setSize(width / 2, height / 2);
		fr.setLocationRelativeTo(null);
		fr.setLayout(new BorderLayout());
		fr.setSize(626, 417);
		fr.setTitle("Checkers");
		JButton but = new JButton();
		but.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playSound("buttonClick.wav");
				CheckerBoard c = new CheckerBoard();
				c.setVisible(true);
				f = new remainingPiecesFrame();
				f.setVisible(true);
		        f.setLocation(c.getX() + c.getWidth(), c.getY());
				fr.setVisible(false);
			}
		});
		WindowDestroyer myListener = new WindowDestroyer();
		fr.addWindowListener(myListener);
		but.setSize(439, 150);
		but.setIcon(startGame);
		but.setOpaque(false);
		but.setBorderPainted(false);
		but.setContentAreaFilled(false);
		but.setFocusPainted(false);
		fr.setContentPane(new JLabel(new ImageIcon(new ImageIcon(CheckerBoard.class.getResource("menuBack.jpg"))
				.getImage().getScaledInstance(626, 417, java.awt.Image.SCALE_SMOOTH))));
		but.setLocation(80, 140);
		fr.add(but, BorderLayout.CENTER);
		fr.setVisible(true);
	}

	public int getRem(entity.pieces.Color c) {
		return game.remainingColPieces(c);
	}

	@Override
	public void GameOver(entity.pieces.Color winner) {
		// TODO Auto-generated method stub
		playSound("win.wav");
		String z = winner == entity.pieces.Color.BLUE_COLOR ? "RED" : "BLUE";
		JOptionPane.showMessageDialog(this, z + " WINS!");
		System.exit(0);
	}

	public Tile getTileAt(Location p) {
		for (int i = 0; i < tiles.size(); i++) {
			if (tiles.get(i).getGridLocation().getRow() == p.getRow()
					&& tiles.get(i).getGridLocation().getCol() == p.getCol())
				return tiles.get(i);
		}
		return null;
	}

	public ButtonPiece getButtonPieceAt(Location p) {
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).getGridLocation().getRow() == p.getRow()
					&& buttons.get(i).getGridLocation().getCol() == p.getCol())
				return buttons.get(i);
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Location oldLoc;
		if (obj instanceof Tile && lastClickedPiece == null) {
			playSound("error.wav");
			JOptionPane.showMessageDialog(this, "Please choose a piece", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (obj instanceof ButtonPiece && lastClickedPiece != null) {
			playSound("error.wav");
			JOptionPane.showMessageDialog(this, "Please choose a Tile", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (lastClickedPiece == null && obj instanceof ButtonPiece) {
			lastClickedPiece = (ButtonPiece) obj;
			oldLoc = lastClickedPiece.getGridLocation();
			lastClickedPiece.piece.setLocation(oldLoc);
			old = getTileAt(lastClickedPiece.getGridLocation());

		} else {
			Tile newLoc = (Tile) obj;
			Location destination = newLoc.getGridLocation();

			try {

				game.Play(lastClickedPiece.piece, destination);
				if (destination.getRow() - lastClickedPiece.getGridLocation().getRow() == 2
						&& destination.getCol() - lastClickedPiece.getGridLocation().getCol() == 2) {
					ButtonPiece victim = getButtonPieceAt(new Location(lastClickedPiece.getGridLocation().getRow() + 1,
							lastClickedPiece.getGridLocation().getCol() + 1));
					Tile victimTile = getTileAt(new Location(lastClickedPiece.getGridLocation().getRow() + 1,
							lastClickedPiece.getGridLocation().getCol() + 1));
					victimTile.remove(victim);
					if (victim.piece.getColor() == entity.pieces.Color.RED_COLOR)
						redRem--;
					else
						blueRem--;
					f.update();
					buttons.remove(victim);
					capture = true;
					this.repaint();
					this.revalidate();
				}
				if (destination.getRow() - lastClickedPiece.getGridLocation().getRow() == -2
						&& destination.getCol() - lastClickedPiece.getGridLocation().getCol() == 2) {
					ButtonPiece victim = getButtonPieceAt(new Location(lastClickedPiece.getGridLocation().getRow() - 1,
							lastClickedPiece.getGridLocation().getCol() + 1));
					Tile victimTile = getTileAt(new Location(lastClickedPiece.getGridLocation().getRow() - 1,
							lastClickedPiece.getGridLocation().getCol() + 1));
					victimTile.remove(victim);
					if (victim.piece.getColor() == entity.pieces.Color.RED_COLOR)
						redRem--;
					else
						blueRem--;
					f.update();
					buttons.remove(victim);
					capture = true;
					this.repaint();
					this.revalidate();
				}
				if (destination.getRow() - lastClickedPiece.getGridLocation().getRow() == 2
						&& destination.getCol() - lastClickedPiece.getGridLocation().getCol() == -2) {
					ButtonPiece victim = getButtonPieceAt(new Location(lastClickedPiece.getGridLocation().getRow() + 1,
							lastClickedPiece.getGridLocation().getCol() - 1));
					Tile victimTile = getTileAt(new Location(lastClickedPiece.getGridLocation().getRow() + 1,
							lastClickedPiece.getGridLocation().getCol() - 1));
					victimTile.remove(victim);
					if (victim.piece.getColor() == entity.pieces.Color.RED_COLOR)
						redRem--;
					else
						blueRem--;
					f.update();
					buttons.remove(victim);
					capture = true;
					this.repaint();
					this.revalidate();
				}
				if (destination.getRow() - lastClickedPiece.getGridLocation().getRow() == -2
						&& destination.getCol() - lastClickedPiece.getGridLocation().getCol() == -2) {
					ButtonPiece victim = getButtonPieceAt(new Location(lastClickedPiece.getGridLocation().getRow() - 1,
							lastClickedPiece.getGridLocation().getCol() - 1));
					Tile victimTile = getTileAt(new Location(lastClickedPiece.getGridLocation().getRow() - 1,
							lastClickedPiece.getGridLocation().getCol() - 1));
					victimTile.remove(victim);
					if (victim.piece.getColor() == entity.pieces.Color.RED_COLOR)
						redRem--;
					else
						blueRem--;
					f.update();
					capture = true;
					buttons.remove(victim);
					this.repaint();
					this.revalidate();
				}

				if (destination.getRow() == 7 && lastClickedPiece.piece.getColor() == entity.pieces.Color.BLUE_COLOR
						&& lastClickedPiece.piece instanceof Pawn) {
					lastClickedPiece.setIcon(blueKing);
					playSound("kingUpgrade.wav");
					lastClickedPiece.piece = game.getPieces().get(game.getPieces().size() - 1);
					this.repaint();
					this.revalidate();
				} else if (destination.getRow() == 0
						&& lastClickedPiece.piece.getColor() == entity.pieces.Color.RED_COLOR
						&& lastClickedPiece.piece instanceof Pawn) {
					lastClickedPiece.setIcon(redKing);
					playSound("kingUpgrade.wav");
					lastClickedPiece.piece = game.getPieces().get(game.getPieces().size() - 1);
					this.repaint();
					this.revalidate();
				}
				old.remove(getButtonPieceAt(lastClickedPiece.getGridLocation()));
				lastClickedPiece.setGridLocation(destination);
				if (capture == true)
					playSound("pawnCapture.wav");
				else
					playSound("pawnMove.wav");
				newLoc.add(lastClickedPiece);
				lastClickedPiece = null;
				capture = false;
				oldLoc = null;

			} catch (NotYourTurnException | CaptureBypassException | InvalidMoveException e1) {
				playSound("error.wav");
				JOptionPane.showMessageDialog(this, e1.getMessage());
				lastClickedPiece = null;
			}

		}
		this.repaint();
		this.revalidate();
	}

}