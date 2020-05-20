package com.example.iotmiddleware;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import junit.framework.TestCase;

public class Test06 extends TestCase {

	public void testGetNeighbourAttributeValue() {
		try {
			IotCore instance1=new IotCore();
			instance1.setSelfAttribute("mock_attribute", "mock_value");
			String value=instance1.getNeighbourAttributeValue(InetAddress.getLocalHost().getHostAddress().toString(),"mock_attribute");
			assertEquals(value,"mock_value");
			
		} catch (UnknownHostException e) {
			fail("Failed");
		}	
	}
}
