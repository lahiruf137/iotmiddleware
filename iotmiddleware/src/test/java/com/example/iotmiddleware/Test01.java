package com.example.iotmiddleware;

import java.util.Set;

import junit.framework.TestCase;

public class Test01 extends TestCase {

	public void testNeighbourDiscovery() {
		IotCore iotcore=new IotCore();
		Set<String> neighbourList=iotcore.getNeighbours();
		if (neighbourList.isEmpty()) {
			fail("Unable to find neighbours");
		}
		else {
			assert(true);
		}
	}

}
