package com.example.iotmiddleware;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import junit.framework.TestCase;

public class Test04 extends TestCase {

	public void testNeighbourCapabilities() {
	
		try {
			IotCore instance1=new IotCore();
			instance1.setSelfAttribute("mock_attribute", "mock_value");
			ArrayList<String> attrl=instance1.getNeighbourAttributes(InetAddress.getLocalHost().getHostAddress().toString());
			if (attrl.contains("mock_attribute")) {
				assert(true);
			}
			else {
				fail("Failed");
			}
		} catch (UnknownHostException e) {
			fail("Failed");
		}
		
	}

}
