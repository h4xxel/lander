/* lander
 * Copyright 2012-2013 Axel Isaksson
 * 
 * Lander test case, tests expected behaviour of simulator ship when crashing or running out of fuel
 */

class TestShipAI extends ShipAI {
	public TestShipAI(int indecies, Ship ship, Background background, AIAdapter adapter) {
		super(indecies, ship, background, adapter);
	}
	
	public void learn(boolean success) {
		super.learn(success);
		if(success) {
			if(ship.getSpeed()>10) {
				System.out.println("Test failed: landing succeded but speed was too high");
				TestLander.testSuccessful=false;
			} else if(ship.getFuel()<=0&&background.getGroundY()-ship.getY()-ship.getH()>0) {
				System.out.println("Test failed: landing succeded but ran out of fuel above ground level "+(background.getGroundY()-ship.getY()-ship.getH()));
				TestLander.testSuccessful=false;
			}
		} else {
			if(ship.getSpeed()<=10&&background.getGroundY()-ship.getY()-ship.getH()<=0) {
				System.out.println("Test failed: landing failed but speed was in range and rocket was on ground level");
				TestLander.testSuccessful=false;
			}
		}
	}
}

public class TestLander extends Lander {
	static boolean testSuccessful=true;
	public TestLander(int iterations) {
		super(0);
		timer.stop();
		this.ai=new TestShipAI(8, ship, background, new ShipAdapter());
		while(count<iterations)
			actionPerformed(null);
	}
	
	public static void main(String args[]) {
		int iterations;
		
		//Parse command line arguments
		try {
			if(args[0].equals("-h")||args[0].equals("--help")) {
				System.out.println("lander test case - tests expected behaviour of simulator ship when crashing or running out of fuel");
				System.out.println("Axel Isaksson 2012-2013");
				System.out.println("Usage:");
				System.out.println("	lander		- test lander with 10000");
				System.out.println("	lander	[n]	- test lander with n iterations");
				return;
			}
			iterations=new Integer(args[0].replace("-", ""));
		} catch(Throwable e) {
			//Invalid or no arguments specified, default to 10000 iterations
			iterations=10000;
		}
		
		new TestLander(iterations);
		System.out.println("All tests completed");
		System.exit(TestLander.testSuccessful?0:1);
	}
}
