package com.example.iotmiddleware.discovery;

import java.net.InetAddress;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;


import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class NeighbourDiscovery {
	private ServiceListener serviceListener;
	private static JmDNS jmdns;
	private String iotcore_serv_type;
	private static final Logger logger = LoggerFactory.getLogger(NeighbourDiscovery.class);
	private static ReentrantLock lock = new ReentrantLock();
	private static LinkedHashSet<String> hostList =new LinkedHashSet<String>();

	public NeighbourDiscovery(String iotcore_serv_type) throws Exception {
		this.iotcore_serv_type=iotcore_serv_type;

		/*
	 	 * 
	 	 * This part is only required due to jmdns Open issue https://github.com/jmdns/jmdns/issues/18
	 	 * Due to this issue serviceRemoved is never called, Therfore bellow thread will update list periodically
	 	 * 
		 */
		Runnable updaterThread= ()->{
			try {
				updateNeighbourList();
				Thread.sleep(5000);
			} catch (Exception e) {
				//TODO: handle exception
			}
		};
		new Thread(updaterThread).run();
	}
	
	public Set<String> getNeighbours() throws Exception{
		try{
			NeighbourDiscovery.lock.lock();
			return NeighbourDiscovery.hostList;
		}
		finally{
			NeighbourDiscovery.lock.unlock();
		}
	}

	public void updateNeighbourList(){
		NeighbourDiscovery.lock.lock();
				try {
					NeighbourDiscovery.hostList.clear();
					serviceListener=new NeighbourListener();
					jmdns=JmDNS.create(InetAddress.getLocalHost());
					jmdns.addServiceListener(iotcore_serv_type, serviceListener);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				finally{
					NeighbourDiscovery.lock.unlock();
				}
	}
	
	class NeighbourListener implements ServiceListener {
		public void serviceAdded(ServiceEvent event) {
			for (String host : event.getInfo().getHostAddresses()){
				NeighbourDiscovery.hostList.add(host);
					logger.debug("added"+host);
			}		
		}
	     public void serviceRemoved(ServiceEvent event) {
	    	 for (String host : event.getInfo().getHostAddresses()){
				
				NeighbourDiscovery.hostList.remove(host);
					logger.debug("removed"+host);
				
	 		}
	     }
		 public void serviceResolved(ServiceEvent event) {
			 for (String host : event.getInfo().getHostAddresses()){
				NeighbourDiscovery.hostList.add(host);
					logger.debug("resolved"+host);
				
			}
		 }
	}
	
	
	
	
}
