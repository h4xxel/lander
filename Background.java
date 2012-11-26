/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.awt.*;

class Background {
	protected int w, h;
	
	public Background(int w, int h) {resize(w, h);}
	
	public void resize(int w, int h) {
		this.w=w;
		this.h=h;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.WHITE);
		g.drawLine(0, h-80, w, h-80);
	}
}
