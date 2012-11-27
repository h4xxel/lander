/* lander
 * Copyright 2012 Axel Isaksson
 * 
 * Main class setting up simulation
 */

import java.lang.Throwable;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class ShipAdapter implements AIAdapter {
	//AIAdapter interface methods
	//This data comes from suggested table from the exercise specifications
	public int calcHeightIndex(Ship ship, Background background) {
		int h=background.getGroundY()-ship.getY()-ship.getH();
		if(h>100) return 7;
		if(h>50) return 6;
		if(h>25) return 5;
		return h/5;
	}
	
	public int calcSpeedIndex(Ship ship) {
		int s=ship.getSpeed();
		if(s<-30) return 0;
		if(s<-20) return 1;
		if(s<-10) return 2;
		if(s<-1) return 3;
		if(s<1) return 4;
		if(s<10) return 5;
		if(s<20) return 6;
		return 7;
	}
	
	public int calcFuelIndex(Ship ship) {
		int f=ship.getFuel();
		return f>70?7:f/10;
	}
}

public class Lander implements ActionListener {
	Timer timer;
	LanderPanel panel;
	Background background;
	Ship ship;
	ShipAI ai;
	
	int count=0, successCount=0, failFuelCount=0;
	
	public Lander(int iterations) {
		background=new Background(640, 480);
		panel=new LanderPanel(background);
		ai=new ShipAI(8, ship, background, new ShipAdapter());
		respawnShip();
		
		//Run initial iterations if specified
		for(; count<iterations;)
			actionPerformed(null);
		
		//Main loop timer at 8 frames
		timer=new Timer(1000/8, this);
		timer.setRepeats(true);
		
		//Set up window
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
		//Main loop, updates panel view, runs engine
		panel.updateInstuments();
		
		if(ai.runEngine())
			ship.motorOn();
		else
			ship.motorOff();
		ship.move();

		panel.repaint();
		
		//Check if simulation should end
		if(ship.getY()+ship.getH()>=background.getGroundY()||ship.getFuel()<=0) {
			boolean success;
			//Check if out of fuel or crashing with high speed
			if(ship.getFuel()<=0&&ship.getY()+ship.getH()<background.getGroundY()) {
				failFuelCount++;
				success=false;
			} else if(ship.getSpeed()>10)
				success=false;
			else
				success=true;
			
			//Make AI learn from this run
			ai.learn(success);
			//Update statistics
			count++;
			if(success)
				successCount++;
			panel.updateStatistics(count, successCount, failFuelCount);
			
			//Restart simulation
			respawnShip();
		}
	}
	
	public void respawnShip() {
		//Restart simulation with a new ship
		Random rnd=new Random(System.currentTimeMillis());
		ship=new Ship(640/2-32/2, background.getGroundY()-64-100-rnd.nextInt(successCount>count/2?300:100), 32, 64, 75+rnd.nextInt(50), rnd.nextInt(10)-5);
		panel.setShip(ship);
		ai.setShip(ship);
	}
	
	public static void main(String args[]) {
		int iterations;
		
		//Parse command line arguments
		try {
			if(args[0].equals("-h")||args[0].equals("--help")) {
				System.out.println("lander - moon landing simulator with AI");
				System.out.println("Axel Isaksson 2012");
				System.out.println("Usage:");
				System.out.println("	lander		- run lander");
				System.out.println("	lander	[n]	- run lander with n initial iterations");
				return;
			}
			iterations=new Integer(args[0].replace("-", ""));
		} catch(Throwable e) {
			iterations=0;
		}
		
		new Lander(iterations);
	}
}

