/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.awt.*;
import javax.swing.JPanel;

class LanderPanel extends JPanel {
	Background background;
	Ship ship;
	Instrument instrumentFuel=new Instrument(16, 16, 128, "Fuel", 0, 125);
	Instrument instrumentSpeed=new Instrument(16, 128+32, 128, "Speed", -30, 30);
	Instrument instrumentHeight=new Instrument(16, 256+48, 128, "Height", 0, 200);
	int count, successCount, successRate;
	
	public LanderPanel(Background background) {
		setPreferredSize(new Dimension(640, 480));
		this.background=background;
	}
	
	public void setShip(Ship ship) {this.ship=ship;}
	
	public void updateInstuments() {
		instrumentFuel.setValue(ship.getFuel());
		instrumentSpeed.setValue(ship.getSpeed());
		instrumentHeight.setValue(background.getGroundY()-ship.getY()-ship.getH());
	}
	
	public void updateStatistics(int count, int successCount) {
		this.count=count;
		this.successCount=successCount;
		this.successRate=100*successCount/count;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		background.draw(g);
		instrumentFuel.draw(g);
		instrumentHeight.draw(g);
		instrumentSpeed.draw(g);
		
		g.drawString("Number of tries: "+count, 480, 16);
		g.drawString("of which succeeded: "+successCount, 480, 16+16);
		g.drawString("Success rate: "+successRate+"%", 480, 16+32);
		
		
		ship.draw(g);
	}
}
