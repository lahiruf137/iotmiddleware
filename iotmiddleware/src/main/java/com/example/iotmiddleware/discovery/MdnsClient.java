package com.example.iotmiddleware.discovery;

public class MdnsClient implements Runnable {
	public void run() {
		new ServiceDiscovery();
	}

}
