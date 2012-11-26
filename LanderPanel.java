/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.awt.*;
import javax.swing.JPanel;

class LanderPanel extends JPanel {
	Background background;
	Ship ship;
	Instrument instrumentFuel=new Instrument(16, 16, 128, "Fuel", 0, 125);
	Instrument instrumentSpeed=new Instrument(16, 128+32, 128, "Speed", -10, 10);
	Instrument instrumentHeight=new Instrument(16, 256+48, 128, "Height", 0, 400);
	
	public LanderPanel(Background background) {
		setPreferredSize(new Dimension(640, 480));
		this.background=background;
	}
	
	public void setShip(Ship ship) {this.ship=ship;}
	
	public void updateInstuments() {
		instrumentFuel.setValue(ship.getFuel());
		instrumentHeight.setValue(background.getGroundY()-ship.getY()-ship.getH());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		background.draw(g);
		instrumentFuel.draw(g);
		instrumentHeight.draw(g);
		instrumentSpeed.draw(g);
		ship.draw(g);
	}
}
