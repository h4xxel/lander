/* lander
 * Copyright 2012 Axel Isaksson
 * 
 * Particle used for explosion
 */

import java.awt.*;

public class Particle {
	double x, y;
	double vspeed=0, hspeed=0;
	Color color;
	
	public Particle(int x, int y, double hspeed, double vspeed, Color color) {
		this.x=(double)x;
		this.y=(double)y;
		this.hspeed=hspeed;
		this.vspeed=vspeed;
		this.color=color;
	}
	
	public void move() {
		x+=hspeed;
		y+=vspeed;
		vspeed+=0.97;
	}
	
	public void draw(Graphics g) {
		//Draw a point
		g.setColor(color);
		g.drawLine((int)x, (int)y, (int)x, (int)y);
		g.setColor(Color.WHITE);
	}
}
