package com.example.iotmiddleware;

import junit.framework.TestCase;

public class Test02 extends TestCase {

	public void testNewNeighbourDiscovery() throws Exception{
		IotCore instance1=new IotCore();
		Thread.sleep(7000);
		int count=instance1.getNeighbours().size();		
		IotCore instance2=new IotCore();
		Thread.sleep(7000);
		int newCount=instance1.getNeighbours().size();
		if (newCount>=count) {
			assert(true);
		}
		else {
			fail("Didn't discover new neighbour");
		}
	}

}
