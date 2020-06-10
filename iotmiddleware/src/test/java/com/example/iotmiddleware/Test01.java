package com.example.iotmiddleware;

import java.util.Set;

import junit.framework.TestCase;

public class Test01 extends TestCase {

	public void testNeighbourDiscovery() throws Exception{
		IotCore iotcore=new IotCore();
		Thread.sleep(7000);
		Set<String> neighbourList=iotcore.getNeighbours();
		if (neighbourList.isEmpty()) {
			fail("Unable to find neighbours");
		}
		else {
			assert(true);
		}
	}

}
