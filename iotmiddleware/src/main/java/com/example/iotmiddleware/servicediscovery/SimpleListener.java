package com.example.iotmiddleware.servicediscovery;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

public class SimpleListener implements ServiceListener {
	public void serviceAdded(ServiceEvent event) {
		System.out.println("Service added");
	}
     public void serviceRemoved(ServiceEvent event) {
    	 System.out.println("Service removed");
     }
	 public void serviceResolved(ServiceEvent event) {
		 System.out.println("Service resolved");
	 }
	
}
