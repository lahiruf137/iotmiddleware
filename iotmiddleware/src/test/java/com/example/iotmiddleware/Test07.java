package com.example.iotmiddleware;

import java.net.InetAddress;
import java.net.UnknownHostException;

import junit.framework.TestCase;

public class Test07 extends TestCase {
	
	public void testSetNeighbourAttributeValue() {
		try {
			IotCore instance1=new IotCore();
			instance1.setSelfAttribute("mock_attribute", "mock_value");
			
			instance1.setNeighbourAttribute(InetAddress.getLocalHost().getHostAddress().toString(),"mock_attribute","new_value");
			String value=instance1.getNeighbourAttributeValue(InetAddress.getLocalHost().getHostAddress().toString(),"mock_attribute");
			assertEquals(value,"new_value");
			
		} catch (UnknownHostException e) {
			fail("Failed");
		}	
	}

}
