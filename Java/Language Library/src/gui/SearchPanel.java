package gui;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

/**
 * This is a search panel used by MenuFrame class to build its components.
 * 
 * @author Emrullah Celik (original), Trung Dang (comments and minor change to constructor), 
 * Siyuan Zhou (editing)
 * @version December 11, 2015
 *
 */
@SuppressWarnings("serial")
public class SearchPanel extends JPanel {
	
	/** A text field for user to enter a keyword.*/
	private JTextField keywordText;
	
	/** A text field for user to enter a title.*/
	private JTextField titleText;
	
	/** A table to display the search result.*/
	private JTable table;
	
	/** A library that contains access to actual data.*/	
	private Library keep;
	
	/**
	 * Construct the search panel with default look and feel.
	 * 
	 * @author Emrullah Celik (original), Trung Dang (comments and minor change to constructor)
	 * 
	 * @param theKeep The library that has access to actual data.
	 */
	public SearchPanel(Library theKeep) {
		super();
		keep = theKeep;
		setBackground(new Color(204, 204, 255));
		setLayout(null);

		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		internalFrame.setBackground(new Color(204, 204, 255));
		internalFrame.getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		internalFrame.getContentPane().setBackground(new Color(95, 158, 160));
		internalFrame.setBounds(-20, -35, 795, 541);
		add(internalFrame);
		internalFrame.getContentPane().setLayout(null);

		JButton searchKeywordButton = new JButton(" Search Keyword");
		searchKeywordButton.setBackground(new Color(204, 204, 255));
		searchKeywordButton.setForeground(new Color(0, 0, 255));
		searchKeywordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input_keyword = keywordText.getText();
				Library file = new Library();
				String[] cell = file.searchKeyword(input_keyword);
				if (cell != null) {
					table.setValueAt(cell[0], 0, 0);
					table.setValueAt(cell[1], 0, 1);
					table.setValueAt(cell[2], 0, 2);
					table.setValueAt(cell[3], 0, 3);
				} else {
					JOptionPane.showMessageDialog(null, "No record found!", "Oops", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		searchKeywordButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		searchKeywordButton.setBounds(57, 120, 265, 50);
		final java.awt.Image img3 = new ImageIcon(this.getClass().getResource("/search2.png")).getImage();
		searchKeywordButton.setIcon(new ImageIcon(img3));
		internalFrame.getContentPane().add(searchKeywordButton);

		JLabel keywordLabel = new JLabel("Enter Keyword: ");
		keywordLabel.setBackground(new Color(204, 204, 255));
		keywordLabel.setForeground(new Color(0, 0, 255));
		keywordLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 30));
		keywordLabel.setBounds(57, 25, 285, 50);
		internalFrame.getContentPane().add(keywordLabel);

		keywordText = new JTextField();
		keywordText.setForeground(new Color(0, 0, 255));
		keywordText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				keywordText.setText("");
			}
		});
		keywordText.setHorizontalAlignment(SwingConstants.LEFT);
		keywordText.setText("Please Enter Keyword");
		keywordText.setToolTipText("");
		keywordText.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		keywordText.setBounds(27, 74, 331, 35);
		internalFrame.getContentPane().add(keywordText);
		keywordText.setColumns(10);

		JLabel titleLabel = new JLabel("Enter Title: ");
		titleLabel.setBackground(new Color(204, 204, 255));
		titleLabel.setForeground(new Color(255, 0, 0));
		titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 30));
		titleLabel.setBounds(492, 25, 210, 50);
		internalFrame.getContentPane().add(titleLabel);

		titleText = new JTextField();
		titleText.setForeground(new Color(255, 0, 0));
		titleText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titleText.setText("");
			}
		});
		titleText.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		titleText.setText("Please Enter Title");
		titleText.setBounds(420, 74, 331, 35);
		internalFrame.getContentPane().add(titleText);
		titleText.setColumns(10);

		JButton searchTitleButton = new JButton(" Search Title");
		searchTitleButton.setBackground(new Color(204, 204, 255));
		searchTitleButton.setForeground(new Color(255, 0, 0));
		searchTitleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input_title = titleText.getText();

				String[] cell = keep.searchTitle(input_title);
				if (cell != null) {
					table.setValueAt(cell[0], 0, 0);
					table.setValueAt(cell[1], 0, 1);
					table.setValueAt(cell[2], 0, 2);
					table.setValueAt(cell[3], 0, 3);
				} else {
					JOptionPane.showMessageDialog(null, "No record found!", "Oops", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		searchTitleButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		searchTitleButton.setBounds(450, 120, 252, 50);
		final java.awt.Image img4 = new ImageIcon(this.getClass().getResource("/search2.png")).getImage();
		searchTitleButton.setIcon(new ImageIcon(img4));
		internalFrame.getContentPane().add(searchTitleButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 259, 693, 160);
		internalFrame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, },
						new String[] { "Title", "Keyword", "Description", "Text" }) {
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

					@SuppressWarnings({ "unchecked", "rawtypes" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);

		JLabel labelTitle = new JLabel("Title");
		labelTitle.setBackground(new Color(204, 204, 255));
		labelTitle.setForeground(new Color(128, 0, 128));
		labelTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		labelTitle.setBounds(72, 221, 46, 27);
		internalFrame.getContentPane().add(labelTitle);

		JLabel labelKeyword = new JLabel("Keyword");
		labelKeyword.setBackground(new Color(204, 204, 255));
		labelKeyword.setForeground(new Color(128, 0, 128));
		labelKeyword.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		labelKeyword.setBounds(168, 221, 83, 27);
		internalFrame.getContentPane().add(labelKeyword);

		JLabel labelDesription = new JLabel("Description");
		labelDesription.setBackground(new Color(204, 204, 255));
		labelDesription.setForeground(new Color(128, 0, 128));
		labelDesription.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		labelDesription.setBounds(299, 221, 121, 27);
		internalFrame.getContentPane().add(labelDesription);

		JLabel labelText = new JLabel("Text");
		labelText.setBackground(new Color(204, 204, 255));
		labelText.setForeground(new Color(128, 0, 128));
		labelText.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		labelText.setBounds(547, 224, 62, 20);
		internalFrame.getContentPane().add(labelText);
		internalFrame.setVisible(true);
	}
}
