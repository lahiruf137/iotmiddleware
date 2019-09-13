package com.example.iotmiddleware.servicediscovery;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import com.example.iotmiddleware.ExecutionEngine;

public class SimpleListener implements ServiceListener {
	public synchronized void serviceAdded(ServiceEvent event) {
		System.out.println("Service added");
		ExecutionEngine.addHost("a");
	}
     public synchronized void serviceRemoved(ServiceEvent event) {
    	 System.out.println("Service removed");
    	 ExecutionEngine.addHost("a");
     }
	 public synchronized void serviceResolved(ServiceEvent event) {
		 System.out.println("Service resolved");
		 ExecutionEngine.addHost("a");
	 }
	
}
