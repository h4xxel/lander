/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

class LanderPanel extends JPanel implements ActionListener {
	Background background;
	Ship ship;
	
	public LanderPanel(Ship ship, Background background) {
		setPreferredSize(new Dimension(640, 480));
		this.ship=ship;
		this.background=background;
		Timer timer=new Timer(1000/60, this);
		timer.setRepeats(true);
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		background.draw(g);
		ship.draw(g);
	}
}
