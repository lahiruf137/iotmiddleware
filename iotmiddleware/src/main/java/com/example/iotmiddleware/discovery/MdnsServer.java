package com.example.iotmiddleware.discovery;

public class MdnsServer implements Runnable {
	public void run() {
		try {
			ServiceRegistration.registerService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Thread.currentThread().isInterrupted()) {
			try {
				ServiceRegistration.unregisterAllServices();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
