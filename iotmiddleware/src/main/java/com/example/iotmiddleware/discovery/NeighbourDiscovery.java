package com.example.iotmiddleware.discovery;

import java.net.InetAddress;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class NeighbourDiscovery {
	private Set<String> hostList;
	private ServiceListener serviceListener;
	private static JmDNS jmdns;
	String iotcore_serv_type;
	boolean isUpdating=false;
	private static final Logger logger = LoggerFactory.getLogger(NeighbourDiscovery.class);
	public NeighbourDiscovery(String iotcore_serv_type) throws Exception {
		this.iotcore_serv_type=iotcore_serv_type;
		new Thread(new MdnsUpdater()).start();
	}
	
	public Set<String> getNeighbours() throws Exception{
		Set<String> r = null;
		while(true) {
			if(!isUpdating) {
				r=hostList;
				break;
			}
			else {
				Thread.sleep(500);
			}
		}
		return r;
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
	
	/*
	 * 
	 * This part is only required due to jmdns Open issue https://github.com/jmdns/jmdns/issues/18
	 * Move code inside try block to NeighbourDiscovery constructor when fixed
	 * 
	*/
	class MdnsUpdater implements Runnable{
		public void run() {
			
			while (true) {
				try {
					isUpdating=true;
					hostList=new LinkedHashSet<String>();
					serviceListener=new NeighbourListener();
					jmdns=JmDNS.create(InetAddress.getLocalHost());
					jmdns.addServiceListener(iotcore_serv_type, serviceListener);
					isUpdating=false;
					Thread.sleep(5000);
					
				} catch (Exception e) {
					logger.error(e.getMessage());
				} 
				
			}
		}
	}
	
}
