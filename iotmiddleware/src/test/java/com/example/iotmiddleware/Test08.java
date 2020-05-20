package com.example.iotmiddleware;
import com.example.iotmiddleware.api.*;

import java.util.HashMap;


import junit.framework.TestCase;

public class Test08 extends TestCase {
	
	public void testHttpGet() { 
		
		HashMap<String,String> data = new  HashMap<String,String>();
		HashMap<String,String> options = new  HashMap<String,String>();
		data.put("mock_key", "mock_value");
		
		HTTPHandler httpHandler=new HTTPHandler("http://httpbin.org/get");
		String result = httpHandler.httpGet(data, options);
        assertEquals(result,"HTTP/1.1 200 OK");
		
		
	}

}
