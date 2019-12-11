package com.example.iotmiddleware.discovery;

public class MdnsServer implements Runnable {
	public void run() {
		new ServiceRegistration();
	}

}
