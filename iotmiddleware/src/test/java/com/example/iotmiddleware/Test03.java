package com.example.iotmiddleware;

import junit.framework.TestCase;

public class Test03 extends TestCase {

	public void testNeighbourDisconnect() throws Exception {
		IotCore instance1=new IotCore();
		IotCore instance2=new IotCore();
		Thread.sleep(7000);
		int count=instance1.getNeighbours().size();
		instance2=null;
		int newCount=instance1.getNeighbours().size();
		if (newCount<=count) {
			assert(true);
		}
		else {
			fail("Didn't rmove neighbour");
		}
	}

}
