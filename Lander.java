/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Lander implements ActionListener {
	Timer timer;
	LanderPanel panel;
	Background background;
	Ship ship;
	
	public Lander() {
		background=new Background(640, 480);
		panel=new LanderPanel(background);
		respawnShip();
		
		timer=new Timer(1000/60, this);
		timer.setRepeats(true);
		
		JFrame frame=new JFrame("lander");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setResizable(false);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		panel.updateInstuments();
		ship.setY(ship.getY()+1);

		panel.repaint();
		
		if(ship.getY()+ship.getH()>=background.getGroundY()||ship.getFuel()<=0)
			respawnShip();
	}
	
	public void respawnShip() {
		System.out.println("Spawn new ship");
		Random rnd=new Random(System.currentTimeMillis());
		ship=new Ship(640/2-32/2, 10, 32, 32, 75+rnd.nextInt(50));
		panel.setShip(ship);
	}
	
	public static void main(String args[]) {
		new Lander();
	}
}

