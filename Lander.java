/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.lang.Throwable;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Lander implements ActionListener {
	Timer timer;
	LanderPanel panel;
	Background background;
	Ship ship;
	ShipAI ai=new ShipAI(8);
	
	int count=0, successCount=0, failFuelCount=0;
	
	public Lander(int iterations) {
		background=new Background(640, 480);
		panel=new LanderPanel(background);
		respawnShip();
		
		for(; count<iterations;)
			actionPerformed(null);
		
		timer=new Timer(1000/8, this);
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
	
	public int calcHeightIndex(int h) {
		if(h>100) return 7;
		if(h>50) return 6;
		if(h>25) return 5;
		return h/5;
	}
	
	public int calcSpeedIndex(int s) {
		if(s<-30) return 0;
		if(s<-20) return 1;
		if(s<-10) return 2;
		if(s<-1) return 3;
		if(s<1) return 4;
		if(s<10) return 5;
		if(s<20) return 6;
		return 7;
	}
	
	public int calcFuelIndex(int f) {
		return f>70?7:f/10;
	}
	
	public void actionPerformed(ActionEvent e) {
		int shipY=background.getGroundY()-ship.getY()-ship.getH();
		panel.updateInstuments();
		if(ai.runEngine(calcHeightIndex(shipY), calcFuelIndex(ship.getFuel()), calcSpeedIndex(ship.getSpeed())))
			ship.motorOn();
		else
			ship.motorOff();
		ship.move();

		panel.repaint();
		
		if(ship.getY()+ship.getH()>=background.getGroundY()||ship.getFuel()<=0) {
			boolean success;
			if(ship.getFuel()<=0&&ship.getY()+ship.getH()<background.getGroundY()) {
				failFuelCount++;
				success=false;
			}else if(ship.getSpeed()>10)
				success=false;
			else
				success=true;
			ai.learn(success);
			count++;
			if(success)
				successCount++;
			panel.updateStatistics(count, successCount, failFuelCount);
			respawnShip();
		}
	}
	
	public void respawnShip() {
		Random rnd=new Random(System.currentTimeMillis());
		ship=new Ship(640/2-32/2, background.getGroundY()-200+rnd.nextInt(100), 32, 32, 75+rnd.nextInt(50), rnd.nextInt(10)-5);
		panel.setShip(ship);
	}
	
	public static void main(String args[]) {
		int iterations;
		try {
			if(args[0].equals("-h")||args[0].equals("--help")) {
				System.out.println("lander - moon landing simulator with AI");
				System.out.println("Axel Isaksson 2012");
				System.out.println("Usage:");
				System.out.println("lander		- run lander");
				System.out.println("lander	[n]	- run lander with n initial iterations");
				return;
			}
			iterations=new Integer(args[0].replace("-", ""));
		} catch(Throwable e) {
			iterations=0;
		}
		
		new Lander(iterations);
	}
}

