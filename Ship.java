/* lander
 * Copyright 2012 Axel Isaksson
 * 
 * Ship sprite with basic engine logic
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Ship extends Sprite implements ActionListener {
	int fuel;
	double speed;
	boolean motorOn=false;
	Color flameColor=Color.YELLOW;
	Timer timer;
	
	public Ship(int x, int y, int w, int h, int fuel, int speed) {
		super(x, y, w, h);
		this.fuel=fuel;
		this.speed=speed;
		//Timer for changing the flame colour
		timer=new Timer(1000/10, this);
		timer.setRepeats(true);
	}
	
	public int getFuel() {return fuel;}
	public int getSpeed() {return (int)speed;}
	public void setSpeed(int speed) {this.speed=(double)speed;}
	
	//Motor control
	public boolean isMotorOn() {return motorOn;}
	public void motorOn() {
		//Running motor drains fuel and accellerates ship
		motorOn=true;
		int kick=Math.min(2, fuel);
		fuel-=kick;
		speed-=(double)kick;
		if(!timer.isRunning())
			timer.start();
	}
	public void motorOff() {
		motorOn=false;
		timer.stop();
	}
		
	public void actionPerformed(ActionEvent e) {
		//Change flame colour
		flameColor=flameColor==Color.RED?Color.YELLOW:Color.RED;
	}
	
	public void move() {
		//Do gravity
		y+=(int)speed;
		speed+=0.97;
	}
	
	public void draw(Graphics g) {
		//Draw ship
		g.setColor(Color.WHITE);
		g.drawLine(x+w/2, y, x, y+h-1);
		g.drawLine(x+w/2, y, x+w, y+h-1);
		g.drawLine(x, y+h-1, x+w, y+h-1);
		//Draw flame
		if(motorOn) {
			g.setColor(flameColor);
			g.drawLine(x, y+h, x+w/2, y+h+h/2);
			g.drawLine(x+w, y+h, x+w/2, y+h+h/2);
		}
	}
}
