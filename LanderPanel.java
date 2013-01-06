/* lander
 * Copyright 2012-2013 Axel Isaksson
 * 
 * Window panel responsible for drawing scene, instruments, statistics and sprite
 */

import java.awt.*;
import javax.swing.JPanel;

public class LanderPanel extends JPanel {
	Background background;
	Ship ship;
	Particle explosion[];
	
	Instrument instrumentFuel=new Instrument(16, 16, 128, "Fuel", 0, 125);
	Instrument instrumentSpeed=new Instrument(16, 128+32, 128, "Speed", -30, 30);
	Instrument instrumentHeight=new Instrument(16, 256+48, 128, "Height", 0, 400);
	
	int count, successCount, successRate, failFuelCount, failCrashCount;
	
	public LanderPanel(Background background, Particle explosion[]) {
		setPreferredSize(new Dimension(640, 480));
		this.background=background;
		this.explosion=explosion;
	}
	
	public void setShip(Ship ship) {this.ship=ship;}
	
	public void updateInstuments() {
		instrumentFuel.setValue(ship.getFuel());
		instrumentSpeed.setValue(-1*ship.getSpeed());
		//Height over ground level
		instrumentHeight.setValue(background.getGroundY()-ship.getY()-ship.getH());
	}
	
	public void updateStatistics(int count, int successCount, int failFuelCount) {
		this.count=count;
		this.successCount=successCount;
		this.failFuelCount=failFuelCount;
		//Calculate crash count and success rate from values above
		this.failCrashCount=count-successCount-failFuelCount;
		this.successRate=100*successCount/count;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Background
		background.draw(g);
		
		//Instruments
		instrumentFuel.draw(g);
		instrumentHeight.draw(g);
		instrumentSpeed.draw(g);
		
		//Statistics
		g.drawString("Number of tries: "+count, 400, 16);
		g.drawString("of which succeeded: "+successCount, 400, 16+16);
		g.drawString("of which crashed to ground: "+failCrashCount, 400, 16+32);
		g.drawString("of which ran out of fuel: "+failFuelCount, 400, 16+48);
		g.drawString("Success rate: "+successRate+"%", 400, 32+64);
		
		//Draw either ship or explosion when the ship is broken
		if(ship==null)
			for(Particle p:explosion)
				p.draw(g);
		else
			ship.draw(g);
	}
}
