/* lander
 * Copyright 2012 Axel Isaksson
 * 
 * Ship AI, decides on motor control and learns from its decisions
 */

import java.util.Random;

public class ShipAI {
	//Linked list containing the decisions of the current run
	class AILearnList {
		public int height;
		public int fuel;
		public int speed;
		public boolean run;
		public AILearnList next=null;
		
		public AILearnList(int height, int fuel, int speed, boolean run, AILearnList next) {
			this.height=height;
			this.fuel=fuel;
			this.speed=speed;
			this.run=run;
			this.next=next;
		}
	}
	AILearnList list=null;
	int cases[][][][];
	Ship ship;
	Background background;
	AIAdapter adapter;
	
	public ShipAI(int indecies, Ship ship, Background background, AIAdapter adapter) {
		//Array containing all the possible cases and two counters for run/don't run engine
		cases=new int[indecies][indecies][indecies][2];
		
		this.ship=ship;
		this.background=background;
		this.adapter=adapter;
	}
	
	public void setShip(Ship ship) {this.ship=ship;}
	
	public boolean runEngine() {
		//Indecies calculated by adapter interface
		int hi=adapter.calcHeightIndex(ship, background);
		int fi=adapter.calcFuelIndex(ship);
		int si=adapter.calcSpeedIndex(ship);
		//Figure out if we should run the engine or not based on the current ship status ('height', 'fuel', 'speed')
		int a=cases[hi][fi][si][0]; //Do not run
		int b=cases[hi][fi][si][1]; //Run
		boolean run=a==b?new Random(System.currentTimeMillis()).nextBoolean():a<b;
		
		//The algorithm seems to work just as well, if not even better if we add duplicates to decision list
		/*for(ShipAIList l=list; l!=null; l=l.next)
			if(l.height==height&&l.fuel==fuel&&l.speed==speed)
				return run;*/
		
		//Prepend the made decision to the learn list
		list=new AILearnList(hi, fi, si, run, list);
		
		return run;
	}
	
	public void learn(boolean success) {
		//Update the 'cases' array from the decision list of the previous run and clear the list
		for(AILearnList l=list; l!=null; l=l.next)
			cases[l.height][l.fuel][l.speed][l.run?1:0]+=success?2:-1;
		list=null;
	}
}
