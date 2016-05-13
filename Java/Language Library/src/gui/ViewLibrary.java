package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

/**
 * This class is a frame that is used to display all the data inside the library. The frame
 * will be displayed independent of the main menu.
 * 
 * @author Emrullah Celik (original), Trung Dang (comments, minor changes)
 * @version 3.0 December 11, 2015
 *
 */
@SuppressWarnings("serial")
public class ViewLibrary extends JFrame {

	/** A variable to hold the content pane of this frame.*/
	private JPanel contentPane;
	
	/** A table to display the data.*/
	private JTable table;
	
	/**
	 * Run this class as a separate window.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLibrary frame = new ViewLibrary();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Construct the library with all data already in the table to be displayed.
	 * 
	 * @author Emrullah Celik (original), Trung Dang (minor changes)
	 */
	public ViewLibrary() {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("View Library");
		setBackground(new Color(204, 204, 255));
		setBounds(100, 100, 605, 604);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblViewLibrary = new JLabel("View Library");
		lblViewLibrary.setForeground(Color.RED);
		lblViewLibrary.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 40));
		lblViewLibrary.setBackground(new Color(204, 204, 255));
		lblViewLibrary.setBounds(145, 11, 265, 57);
		contentPane.add(lblViewLibrary);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 161, 510, 276);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null }, },
				new String[] { "Title", "Keyword", "Description", "Text" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		JLabel label = new JLabel("Title");
		label.setForeground(new Color(128, 0, 128));
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		label.setBackground(new Color(204, 204, 255));
		label.setBounds(44, 123, 68, 27);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Keyword");
		label_1.setForeground(new Color(128, 0, 128));
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		label_1.setBackground(new Color(204, 204, 255));
		label_1.setBounds(122, 123, 100, 27);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Description");
		label_2.setForeground(new Color(128, 0, 128));
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		label_2.setBackground(new Color(204, 204, 255));
		label_2.setBounds(232, 123, 150, 27);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Text");
		label_3.setForeground(new Color(128, 0, 128));
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		label_3.setBackground(new Color(204, 204, 255));
		label_3.setBounds(392, 123, 98, 27);
		contentPane.add(label_3);
		Library file = new Library();
		LinkedList<String[]> ugh = file.listAll();
		for (int i = 0; i < ugh.size(); i++) {
			table.setValueAt(ugh.get(i)[0], i, 0);
			table.setValueAt(ugh.get(i)[1], i, 1);
			table.setValueAt(ugh.get(i)[2], i, 2);
			table.setValueAt(ugh.get(i)[3], i, 3);
		}
	}
}
