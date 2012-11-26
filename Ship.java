/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

class Ship extends Sprite implements ActionListener {
	int fuel;
	boolean motorOn=false;
	Color flameColor=Color.YELLOW;
	Timer timer;
	
	public Ship(int x, int y, int w, int h, int fuel) {
		super(x, y, w, h);
		this.fuel=fuel;
		timer=new Timer(1000/10, this);
		timer.setRepeats(true);
	}
	
	public int getFuel() {return fuel;}
	
	public boolean getMotorState() {return motorOn;}
	public void motorOn() {
		motorOn=true;
		timer.start();
	}
	public void motorOff() {
		motorOn=false;
		timer.stop();
	}
		
	public void actionPerformed(ActionEvent e) {
		flameColor=flameColor==Color.RED?Color.YELLOW:Color.RED;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawLine(x+w/2, y, x, y+h-1);
		g.drawLine(x+w/2, y, x+w-1, y+h-1);
		if(motorOn)
			g.setColor(flameColor);
		g.drawLine(x, y+h-1, x+w-1, y+h-1);
	}
}
