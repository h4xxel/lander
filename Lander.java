/* lander
 *  Copyright 2012 Axel Isaksson
 */

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lander {
	public static void main(String args[]) {
		Ship ship=new Ship(640/2-32/2, 10, 32, 32);
		
		JFrame frame=new JFrame("lander");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setResizable(false);
		frame.getContentPane().add((JPanel)new LanderPanel(ship, new Background(640, 480)));
		frame.pack();
		frame.setVisible(true);
		for(;;) {
			
		}
	}
}

