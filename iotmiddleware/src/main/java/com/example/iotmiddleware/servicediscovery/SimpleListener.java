package com.example.iotmiddleware.servicediscovery;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import com.example.iotmiddleware.ExecutionEngine;

public class SimpleListener implements ServiceListener {
	public synchronized void serviceAdded(ServiceEvent event) {
		for (String host : event.getInfo().getHostAddresses()){
			ExecutionEngine.addHost(host);
		}		
	}
     public synchronized void serviceRemoved(ServiceEvent event) {
    	 for (String host : event.getInfo().getHostAddresses()){
 			ExecutionEngine.removeHost(host);
 		}
     }
	 public synchronized void serviceResolved(ServiceEvent event) {
		 for (String host : event.getInfo().getHostAddresses()){
				ExecutionEngine.addHost(host);
			}
	 }
	
}
