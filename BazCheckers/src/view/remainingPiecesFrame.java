package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class remainingPiecesFrame extends JFrame {

	CheckerBoard c;
	ImageIcon rednum;
	ImageIcon bluenum;
	JButton rightRed = new JButton();
	JButton leftBlue = new JButton();
	ImageIcon redone = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redOne.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redtwo = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redTwo.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redthree = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redThree.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redfour = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redFour.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redfive = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redFive.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redsix = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redSix.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redseven = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redSeven.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redeight = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redEight.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon rednine = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redNine.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redten = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redTen.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redeleven = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redEleven.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon redtwelve = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("redTwelve.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon blueone = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueOne.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bluetwo = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueTwo.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bluethree = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueThree.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bluefour = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueFour.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bluefive = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueFive.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bluesix = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueSix.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon blueseven = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueSeven.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon blueeight = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueEight.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bluenine = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueNine.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon blueten = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueTen.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon blueeleven = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueEleven.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));
	ImageIcon bluetwelve = new ImageIcon(new ImageIcon(ButtonPiece.class.getResource("blueTwelve.png")).getImage()
			.getScaledInstance(136, 170, java.awt.Image.SCALE_SMOOTH));

	public remainingPiecesFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		this.setSize(width / 2, height / 2);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout());
		this.setResizable(false);
		this.setSize(170, 170);
		this.setTitle("Pieces Counter");
		rightRed.setIcon(redtwelve);
		leftBlue.setIcon(bluetwelve);
		this.add(rightRed, BorderLayout.EAST);
		this.add(leftBlue, BorderLayout.WEST);
		this.setBackground(java.awt.Color.GREEN);
		
	}

	public void update() {
		switch (c.blueRem) {
		case 1:
			bluenum = blueone;
			break;
		case 2:
			bluenum = bluetwo;
			break;
		case 3:
			bluenum = bluethree;
			break;
		case 4:
			bluenum = bluefour;
			break;
		case 5:
			bluenum = bluefive;
			break;
		case 6:
			bluenum = bluesix;
			break;
		case 7:
			bluenum = blueseven;
			break;
		case 8:
			bluenum = blueeight;
			break;
		case 9:
			bluenum = bluenine;
			break;
		case 10:
			bluenum = blueten;
			break;
		case 11:
			bluenum = blueeleven;
			break;
		case 12:
			bluenum = bluetwelve;
			break;
		default:
			break;
		}
		leftBlue.setIcon(bluenum);
		switch (c.redRem) {
		case 1:
			rednum = redone;
			break;
		case 2:
			rednum = redtwo;
			break;
		case 3:
			rednum = redthree;
			break;
		case 4:
			rednum = redfour;
			break;
		case 5:
			rednum = redfive;
			break;
		case 6:
			rednum = redsix;
			break;
		case 7:
			rednum = redseven;
			break;
		case 8:
			rednum = redeight;
			break;
		case 9:
			rednum = rednine;
			break;
		case 10:
			rednum = redten;
			break;
		case 11:
			rednum = redeleven;
			break;
		case 12:
			rednum = redtwelve;
			break;
		default:
			break;
		}
		rightRed.setIcon(rednum);
	}

}
