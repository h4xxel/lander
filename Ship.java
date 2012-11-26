/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

class Ship extends Sprite implements ActionListener {
	boolean motorOn=false;
	Color flameColor=Color.YELLOW;
	Timer timer;
	
	public Ship(int w, int h) {this(0, 0, w, h);}
	public Ship(int x, int y, int w, int h) {
		super(x, y, w, h);
		timer=new Timer(1000/10, this);
		timer.setRepeats(true);
	}
	
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
		g.drawLine(x+w/2, y, x, y+h);
		g.drawLine(x+w/2, y, x+w, y+h);
		if(motorOn)
			g.setColor(flameColor);
		g.drawLine(x, y+h, x+h, y+h);
	}
}
