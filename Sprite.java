/* lander
 *  Copyright 2012 Axel Isaksson
 */
 
import java.awt.*;

abstract class Sprite {
	protected int x, y, w, h;
	
	public Sprite(int w, int h) {
		moveTo(0, 0);
		resize(w, h);
	}
	public Sprite(int x, int y, int w, int h) {
		moveTo(x, y);
		resize(w, h);
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getW() {return w;}
	public int getH() {return h;}
	public void setX(int x) {this.x=x;}
	public void setY(int y) {this.y=y;}
	public void setW(int x) {this.w=w;}
	public void setH(int y) {this.h=h;}
	
	public void moveTo(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public void resize(int w, int h) {
		this.w=w;
		this.h=h;
	}
	
	public abstract void draw(Graphics g);
}
