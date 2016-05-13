package gui;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JInternalFrame;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;

/**
 * This class is a help panel used by MenuFrame class. 
 * 
 * @author Emrullah Celik (original), Stacie Mashnitskaya (codes for help questions and answers), Trung Dang (comments, minor changes)
 * @version 3.0 December 11, 2015
 *
 */
@SuppressWarnings("serial")
public class HelpPanel extends JPanel {

    /**
     * Construct the help panel with default look and feel and with default components
     * and data (questions and answers)
     * 
     * @author Emrullah Celik (original), Staice Mashnitskaya (codes for questions and answers), 
     * 	Trung Dang (comments, minor changes to constructor)
     */
    public HelpPanel() {
    	super();
        setBackground(new Color(204, 204, 255));
        setLayout(null);
        
        StringBuilder text = new StringBuilder();
        text.append("FAQ\n");
        text.append("\t To Search:\n");
        text.append("\t\t\t 1) Click on search button (the magnification icon) on the sidebar on the left \n");
        text.append("\t\t\t 2) Enter one of the search parameters and click  search.\n");
        text.append("\t\t\t\t NOTE:Parameters are either the title of the clause\n");
        text.append("\t\t\t\t that you need to search for or a category.\n" );
        text.append("\t\t\t\tYou can not search using a title and category at the same time.\n");
        text.append("\t\t\t 3) The search result will be shown on the search view.\n");
        text.append("\t To View the whole library :\n");
        text.append("\t\t\t 1) click on View Library button (the book icon) on the side bar on the left\n");
        text.append("\t\t\t    A new window will appear with the entire clause library will show up. \n");
        text.append("\t To create a new clause:\n");
        text.append("\t\t\t 1) Click on Create button (the plus icon) on the sidebar on the left\n");
        text.append("\t\t\t 2) Enter the title \n");
        text.append("\t\t\t 3) Enter the keyword  \n");
        text.append("\t\t\t 4) Enter the description \n");
        text.append("\t\t\t 5) Enter the text  \n");
        text.append("\t\t\t 6) Click create and the new clause will be added to the library \n");
        text.append("\t To go to the Main Menu:\n");
        text.append("\t\t\t 1) Click on Main Menu (House Icon)button on the side bar on the left  \n");
        text.append("\n\t For more help and technical assistance please contact : \n");
        text.append("\t\t\t\t\t The Crazy Code Surgeons at CodeSurgeons@u.washington.edu\n");
        
        JTextPane labelHelp = new JTextPane(); 
        labelHelp.setBackground(new Color(255, 255, 255));
        labelHelp.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        labelHelp.setText(text.toString());
        labelHelp.setBounds(20, 30, 800, 700);
        
        JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
        internalFrame.getContentPane().setBackground(new Color(232,239,233));
        
        internalFrame.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        internalFrame.setBackground(new Color(204, 204, 255));
        internalFrame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 9));
        internalFrame.setBounds(-83, -53, 848, 545);
        
        internalFrame.getContentPane().add(labelHelp);
        internalFrame.getContentPane().setLayout(null);
        internalFrame.setVisible(true);
        add(internalFrame);
    }
}
