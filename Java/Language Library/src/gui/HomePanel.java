package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * This class is a home panel used by MenuFrame class to build its components.
 * 
 * @author Viktoriya Celik (original), Trung Dang (comments, minor change to constructor)
 * @version 3.0 December 11, 2015
 *
 */
@SuppressWarnings("serial")
public class HomePanel extends JPanel {

	/**
	 * Construct this panel with default look and feel.
	 * 
	 * @author Viktoriya Celik (original), Trung Dang (comments, minor change to constructor)
	 */
	public HomePanel() {
		super();
		setBackground(new Color(204, 204, 255));
		setLayout(null);

		final JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		internalFrame.setBackground(new Color(204, 204, 255));
		internalFrame.getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		internalFrame.getContentPane().setBackground(new Color(204, 204, 255));
		internalFrame.setBounds(-34, -26, 801, 514);
		add(internalFrame);
		final java.awt.Image img1 = new ImageIcon(this.getClass().getResource("/ccs.jpg")).getImage();
		internalFrame.getContentPane().setLayout(null);

		final JLabel CCSLogo = new JLabel("");
		CCSLogo.setBackground(new Color(204, 204, 204));
		CCSLogo.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		CCSLogo.setBounds(220, 219, 323, 179);
		CCSLogo.setIcon(new ImageIcon(img1));
		internalFrame.getContentPane().add(CCSLogo);
		
		final JLabel Welcome = new JLabel("WELCOME TO");
		Welcome.setBackground(new Color(204, 204, 204));
		Welcome.setBounds(49, 47, 422, 82);
		Welcome.setForeground(new Color(0, 204, 0));
		Welcome.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 50));
		internalFrame.getContentPane().add(Welcome);
		
		final JLabel CSSLanguageLibrary = new JLabel("CCS Language Library");
		CSSLanguageLibrary.setBackground(new Color(204, 204, 204));
		CSSLanguageLibrary.setBounds(141, 140, 589, 82);
		CSSLanguageLibrary.setForeground(new Color(51, 153, 255));
		CSSLanguageLibrary.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 50));
		internalFrame.getContentPane().add(CSSLanguageLibrary);

		JLabel namesLabel = new JLabel("Emrullah Celik, Viktoriya Celik, Trung Dang, Stacie Mashnitskaya, Siyuan Zhou");
		namesLabel.setForeground(new Color(204, 0, 51));
		namesLabel.setBackground(new Color(204, 204, 204));
		namesLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		namesLabel.setBounds(45, 396, 714, 63);
		internalFrame.getContentPane().add(namesLabel);
		internalFrame.setVisible(true);
	}
}
