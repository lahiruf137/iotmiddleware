package com.example.iotmiddleware.servicediscovery;

public class MdnsServer implements Runnable {
	public void run() {
		new ServiceRegistration();
	}

}
