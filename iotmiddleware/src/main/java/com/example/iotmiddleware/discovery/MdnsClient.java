package com.example.iotmiddleware.discovery;

public class MdnsClient implements Runnable {
	public void run() {
		try {
			ServiceDiscovery.startDiscovery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Thread.currentThread().isInterrupted()) {
			try {
				ServiceDiscovery.stopDiscovery();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
