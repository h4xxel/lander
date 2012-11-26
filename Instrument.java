/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.awt.*;
import java.awt.geom.Rectangle2D;

class Instrument extends Sprite{
	String name;
	int min, max;
	double value, angle;
	
	public Instrument(int x, int y, int diameter, String name, int min, int max) {
		super(x, y, diameter, diameter);
		this.name=name;
		this.min=min;
		this.max=max;
		setValue(0);
	}
	
	public double getValue() {return value;}
	public void setValue(double value) {
		this.value=value;
		if(min<0)
			value-=min;
		angle=(value/(max-min)*180-180/2-90)*Math.PI/180;
	}
	
	public void draw(Graphics g) {
		g.drawOval(x, y, w, h);
		
		Rectangle2D r;
		int cx=x+w/2;
		int cy=y+h/2;
		
		r=g.getFont().getStringBounds(Integer.toString(min), ((Graphics2D) g).getFontRenderContext());
		g.drawString(Integer.toString(min), x+16, (int)(cy+r.getHeight()/2));
		for(int a=0; a<=180; a+=18) {
			double basex=Math.cos((a-180/2-90)*Math.PI/180);
			double basey=Math.sin((a-180/2-90)*Math.PI/180);
			g.drawLine((int)(cx+basex*w/2), (int)(cy+basey*w/2), (int)(cx+basex*(w/2-4)), (int)(cy+basey*(w/2-4)));
		}
		r=g.getFont().getStringBounds(Integer.toString(max), ((Graphics2D) g).getFontRenderContext());
		g.drawString(Integer.toString(max), (int)(x+w-16-r.getWidth()), (int)(cy+r.getHeight()/2));
		
		r=g.getFont().getStringBounds(name, ((Graphics2D) g).getFontRenderContext());
		g.drawString(name, (int)(cx-r.getWidth()/2), cy+16);
		int valueY=(int)(cy+16+r.getHeight());
		r=g.getFont().getStringBounds(Integer.toString((int)(value)), ((Graphics2D) g).getFontRenderContext());
		g.drawString(Integer.toString((int)(value)), (int)(cx-r.getWidth()/2), valueY+4);
		
		g.drawLine(x+w/2, y+h/2, (int)(x+w/2+Math.cos(angle)*(w/2-8)), (int)(y+h/2+Math.sin(angle)*(w/2-8)));
	}
}
