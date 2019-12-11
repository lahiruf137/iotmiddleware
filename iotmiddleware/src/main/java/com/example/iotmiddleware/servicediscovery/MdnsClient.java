package com.example.iotmiddleware.servicediscovery;

public class MdnsClient implements Runnable {
	public void run() {
		new ServiceDiscovery();
	}

}
