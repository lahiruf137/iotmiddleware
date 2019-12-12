package com.example.iotmiddleware.discovery;

import java.net.InetAddress;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.iotmiddleware.IotCore;


public class NewNeighbourDiscovery {
	private Set<String> hostList;
	private ServiceListener serviceListener;
	private static final Logger logger = LoggerFactory.getLogger(NewNeighbourDiscovery.class);
	public NewNeighbourDiscovery(String iotcore_serv_type) throws Exception {
		hostList=new LinkedHashSet<String>();
		serviceListener=new NeighbourListener();
		JmDNS.create(InetAddress.getLocalHost()).addServiceListener(iotcore_serv_type, serviceListener);
	}
	
	public Set<String> getNeighbours() throws Exception{
		return hostList;
	}
	
	
	class NeighbourListener implements ServiceListener {
		public void serviceAdded(ServiceEvent event) {
			for (String host : event.getInfo().getHostAddresses()){
				hostList.add(host);
				logger.info("added"+host);
			}		
		}
	     public void serviceRemoved(ServiceEvent event) {
	    	 for (String host : event.getInfo().getHostAddresses()){
	    		 hostList.remove(host);
	    		 logger.info("removed"+host);
	 		}
	     }
		 public void serviceResolved(ServiceEvent event) {
			 for (String host : event.getInfo().getHostAddresses()){
				 hostList.add(host);
				 logger.info("resolved"+host);
				}
		 }
	}
	
}
