package com.example.iotmiddleware;

import com.example.iotmiddleware.servicediscovery.ServiceDiscovery;

public class MdnsClient implements Runnable {
	public void run() {
		new ServiceDiscovery();
	}

}
