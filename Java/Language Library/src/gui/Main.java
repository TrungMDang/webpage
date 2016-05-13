package gui;

import java.awt.Color;
import java.awt.EventQueue;

/**
 * Launch the Language Library application.
 * 
 * @author Emrullah Celik, Trung Dang (comments)
 * @version 11/26/15
 *
 */
public class Main {
	
 	/**
	 * Launch the application.
	 * 
	 * @author Emrullah Celik (original), Trung Dang (comments)
	 * 
	 * @param theArgs The list of arguments for this method
	 * @throws Throwable 	if any exception occurs
	 */
	public static void main(final String[] theArgs) throws Throwable {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final MenuFrame gui = new MenuFrame();
					gui.setBackground(new Color(204, 204, 255));
					gui.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
} //end of class Main
