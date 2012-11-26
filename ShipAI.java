/* lander
 *  Copyright 2012 Axel Isaksson
 */

import java.util.Random;

class ShipAI {
	class ShipAIList {
		public int height;
		public int fuel;
		public int speed;
		public boolean run;
		public ShipAIList next=null;
		
		public ShipAIList(int height, int fuel, int speed, boolean run, ShipAIList next) {
			this.height=height;
			this.fuel=fuel;
			this.speed=speed;
			this.run=run;
			this.next=next;
		}
	}
	ShipAIList list=null;
	int cases[][][][];
	
	public ShipAI(int indecies) {
		cases=new int[indecies][indecies][indecies][2];
	}
	public boolean runEngine(int height, int fuel, int speed) {
		int a=cases[height][fuel][speed][0];
		int b=cases[height][fuel][speed][1];
		boolean run=a==b?new Random(System.currentTimeMillis()).nextBoolean():a<b;
		//TODO: do not add duplicates
		list=new ShipAIList(height, fuel, speed, run, list);
		return run;
	}
	public void learn(boolean success) {
		ShipAIList l;
		for(l=list; l!=null; l=l.next)
			cases[l.height][l.fuel][l.speed][l.run?1:0]+=success?2:-1;
		list=null;
	}
}
