package com.example.iotmiddleware;

import java.util.HashMap;

import com.example.iotmiddleware.api.HTTPHandler;

import junit.framework.TestCase;

public class Test09 extends TestCase {

	public void testHttpPost() {
		HashMap<String,String> data = new  HashMap<String,String>();
		HashMap<String,String> options = new  HashMap<String,String>();
		data.put("mock_key", "mock_value");
		
		HTTPHandler httpHandler=new HTTPHandler("http://httpbin.org/post");
		String result = httpHandler.httpPost(data, options);
        assertEquals(result,"HTTP/1.1 200 OK");
		
	}

}
