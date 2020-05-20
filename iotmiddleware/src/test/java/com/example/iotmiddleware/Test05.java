package com.example.iotmiddleware;

import junit.framework.TestCase;

public class Test05 extends TestCase {
	
	IotCore instance1=new IotCore();
	final String attributeName="mock_attribute";
	final String attributeValue="mock_value";
	

	public void testSelfAttribute() {
		instance1.setSelfAttribute(attributeName, attributeValue);
		if (instance1.getSelfAttributeList().contains(attributeName) && instance1.getSelfAttributeValue(attributeName)==attributeValue) {
			instance1.removeSelfAttribute(attributeName);
			assert(true);
		} 
		else {
			fail("Failed");
		}
	}


}
