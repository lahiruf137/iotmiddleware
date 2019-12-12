package com.example.iotmiddleware.discovery;

import java.net.InetAddress;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

public class NeighbourDiscovery {
	private Set<String> hostList;
	private final String serviceType="_example._tcp.local.";
	private ServiceListener serviceListener;
	
	public NeighbourDiscovery() {
		hostList=new LinkedHashSet<String>();
		serviceListener=new NeighbourListener();
		
	}
	
	public Set<String> getNeighbours() throws Exception{
		JmDNS.create(InetAddress.getLocalHost()).addServiceListener(serviceType, serviceListener);
		Thread.sleep(5000);
		return hostList;
	}
	
	
	class NeighbourListener implements ServiceListener {
		public void serviceAdded(ServiceEvent event) {
			for (String host : event.getInfo().getHostAddresses()){
				hostList.add(host);
			}		
		}
	     public synchronized void serviceRemoved(ServiceEvent event) {
	    	 for (String host : event.getInfo().getHostAddresses()){
	    		 hostList.remove(host);
	 		}
	     }
		 public synchronized void serviceResolved(ServiceEvent event) {
			 for (String host : event.getInfo().getHostAddresses()){
				 hostList.add(host);
				}
		 }
	}
	
}
