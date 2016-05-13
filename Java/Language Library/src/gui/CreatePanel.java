package gui;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

/**
 * This class is the create panel. This class is used in MenuFrame class as a
 * component.
 * 
 * @author Viktoriya Celik, Trung Dang (comments, minor changes to constructor), 
 * Stacie Mashnitskaya (editing)
 * @version 3.0 December 11, 2015
 *
 */
@SuppressWarnings("serial")
public class CreatePanel extends JPanel {

	/** The library that contains access to data. */
	private Library keep;

	/** A text field for user to enter title. */
	private JTextField titleText;

	/** A text field for user to enter keyword. */
	private JTextField keywordText;

	/** A text field for user to enter description. */
	private JTextField descriptionText;

	/** A text field for user to enter text. */
	private JTextField textText;

	/**
	 * Construct this panel with default look and feel and with necessary components to allow
	 * user to enter new entry to data.
	 * 
	 * @author Viktoriya Celik, Trung Dang (minor change to constructor)
	 * @param theKeep The library that contains access to data
	 */
	public CreatePanel(Library theKeep) {
		super();
		keep = theKeep;
		setBackground(new Color(204, 204, 255));
		setLayout(null);

		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		internalFrame.setBackground(new Color(255, 255, 255));
		internalFrame.getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		internalFrame.setBounds(-33, -35, 832, 558);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);

		JLabel infoLabel = new JLabel("Please Enter Information Below :");
		infoLabel.setForeground(new Color(255, 0, 0));
		infoLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 35));
		infoLabel.setBackground(new Color(204, 204, 255));
		infoLabel.setBounds(93, 43, 573, 40);
		internalFrame.getContentPane().add(infoLabel);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setForeground(new Color(128, 0, 128));
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		titleLabel.setBackground(new Color(204, 204, 255));
		titleLabel.setBounds(72, 115, 68, 27);
		internalFrame.getContentPane().add(titleLabel);

		JLabel keywordLabel = new JLabel("Keyword");
		keywordLabel.setForeground(new Color(128, 0, 128));
		keywordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		keywordLabel.setBackground(new Color(204, 204, 255));
		keywordLabel.setBounds(72, 167, 115, 27);
		internalFrame.getContentPane().add(keywordLabel);

		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setForeground(new Color(128, 0, 128));
		descriptionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		descriptionLabel.setBackground(new Color(204, 204, 255));
		descriptionLabel.setBounds(72, 219, 150, 27);
		internalFrame.getContentPane().add(descriptionLabel);

		JLabel textLabel = new JLabel("Text");
		textLabel.setForeground(new Color(128, 0, 128));
		textLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		textLabel.setBackground(new Color(204, 204, 255));
		textLabel.setBounds(72, 278, 98, 27);
		internalFrame.getContentPane().add(textLabel);

		JButton createButton = new JButton(" Create New Clause");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (keep.add(titleText.getText(), keywordText.getText(), descriptionText.getText(), textText.getText())) {
					JOptionPane.showMessageDialog(null, "Your Clause Added to Library!", "Title",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Your Clause was not added to the Library!" + "\nPlease use a unique Keyword and/or Title",
							"Title", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		createButton.setForeground(Color.BLUE);
		createButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		createButton.setBackground(new Color(204, 204, 255));
		createButton.setBounds(492, 430, 283, 47);
		final java.awt.Image img3 = new ImageIcon(this.getClass().getResource("/create2.png")).getImage();
		createButton.setIcon(new ImageIcon(img3));
		internalFrame.getContentPane().add(createButton);

		titleText = new JTextField();
		titleText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				titleText.setText("");
			}
		});
		titleText.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		titleText.setText("Please enter title");
		titleText.setBounds(244, 115, 450, 27);
		internalFrame.getContentPane().add(titleText);
		titleText.setColumns(10);

		keywordText = new JTextField();
		keywordText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				keywordText.setText("");
			}
		});
		keywordText.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		keywordText.setText("Please enter keyword");
		keywordText.setColumns(10);
		keywordText.setBounds(244, 167, 450, 27);
		internalFrame.getContentPane().add(keywordText);

		descriptionText = new JTextField();
		descriptionText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				descriptionText.setText("");
			}
		});
		descriptionText.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		descriptionText.setText("Please enter description");
		descriptionText.setColumns(10);
		descriptionText.setBounds(244, 219, 450, 27);
		internalFrame.getContentPane().add(descriptionText);

		textText = new JTextField();
		textText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textText.setText("");
			}
		});
		textText.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		textText.setText("Please enter text");
		textText.setColumns(10);
		textText.setBounds(244, 278, 450, 27);
		internalFrame.getContentPane().add(textText);
		internalFrame.setVisible(true);

	}
}
