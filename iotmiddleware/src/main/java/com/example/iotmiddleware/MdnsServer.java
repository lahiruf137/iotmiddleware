package com.example.iotmiddleware;

import com.example.iotmiddleware.servicediscovery.ServiceRegistration;

public class MdnsServer implements Runnable {
	public void run() {
		new ServiceRegistration();
	}

}
