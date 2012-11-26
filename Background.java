/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.awt.*;

class Background {
	protected int w, h, groundY;
	
	public Background(int w, int h) {
		groundY=h-20;
		resize(w, h);
	}
	
	public int getGroundY() {return groundY;}
	
	public void resize(int w, int h) {
		this.w=w;
		this.h=h;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.WHITE);
		g.drawLine(0, groundY, w, groundY);
	}
}
