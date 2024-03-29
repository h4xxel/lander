/* lander
 * Copyright 2012-2013 Axel Isaksson
 * 
 * Background of the scene with a random surface landscape
 */

import java.util.Random;
import java.awt.*;

public class Background {
	protected int w, h, groundY;
	Polygon ground=new Polygon();
	
	public Background(int w, int h) {
		groundY=h-20;
		resize(w, h);
		//Generate random ground, except for the landing spot which is flat
		Random rnd=new Random(System.currentTimeMillis());
		for(int x=0; x-10<w; x+=10) {
			int y=x>w/2-64&&x<w/2+64?groundY:groundY-5+rnd.nextInt(10);
			ground.addPoint(x, y);
		}
		ground.addPoint(w, h);
		ground.addPoint(0, h);
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
		
		//Draw ground
		g.fillPolygon(ground);
	}
}
