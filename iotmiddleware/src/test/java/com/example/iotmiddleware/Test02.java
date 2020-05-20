package com.example.iotmiddleware;

import junit.framework.TestCase;

public class Test02 extends TestCase {

	public void testNewNeighbourDiscovery() {
		IotCore instance1=new IotCore();
		int count=instance1.getNeighbours().size();		
		IotCore instance2=new IotCore();
		int newCount=instance1.getNeighbours().size();
		if (newCount>=count) {
			assert(true);
		}
		else {
			fail("Didn't discover new neighbour");
		}
	}

}
