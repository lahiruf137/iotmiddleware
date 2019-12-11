package com.example.iotmiddleware.discovery;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;



public class SimpleListener implements ServiceListener {
	public synchronized void serviceAdded(ServiceEvent event) {
		for (String host : event.getInfo().getHostAddresses()){
			NeighbourDiscovery.addHost(host);
		}		
	}
     public synchronized void serviceRemoved(ServiceEvent event) {
    	 for (String host : event.getInfo().getHostAddresses()){
    		 NeighbourDiscovery.removeHost(host);
 		}
     }
	 public synchronized void serviceResolved(ServiceEvent event) {
		 for (String host : event.getInfo().getHostAddresses()){
			 NeighbourDiscovery.addHost(host);
			}
	 }
	
}
