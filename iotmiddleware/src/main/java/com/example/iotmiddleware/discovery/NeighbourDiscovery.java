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
			while(true){
			try {
				//NeighbourDiscovery.lock.lock();
				updateNeighbourList();
				//NeighbourDiscovery.lock.unlock();
				Thread.sleep(1000);
				
			} catch (Exception e) {
				logger.error("Exception occurred while updating Neighbours  : {}",e.getMessage());
			}
			
		}
		};
		new Thread(updaterThread).start();
	}
	
	public Set<String> getNeighbours(){
		try{
			NeighbourDiscovery.lock.lock();
			return NeighbourDiscovery.hostList;
		}
		catch(Exception e){
			logger.error("Unable to return Neighbour list  : {}",e.getMessage());
			return new LinkedHashSet<String>();
		}
		finally{
			NeighbourDiscovery.lock.unlock();
		}
	}

	public void updateNeighbourList(){
		//NeighbourDiscovery.lock.lock();
				try {
					NeighbourDiscovery.lock.lock();
					NeighbourDiscovery.hostList.clear();
					serviceListener=new NeighbourListener();
					NeighbourDiscovery.lock.unlock();
					//String address= InetAddress.getLocalHost().getHostAddress();
					jmdns=JmDNS.create(InetAddress.getLocalHost());
					jmdns.addServiceListener(iotcore_serv_type, serviceListener);
					Thread.sleep(5000);
				} catch (Exception e) {
					logger.error("Unable to update Neighbour list : {}",e.getMessage());
				}
				finally{
					//NeighbourDiscovery.lock.unlock();
				}
	}
	
	class NeighbourListener implements ServiceListener {
		public void serviceAdded(ServiceEvent event) {
			for (String host : event.getInfo().getHostAddresses()){
				NeighbourDiscovery.lock.lock();
				NeighbourDiscovery.hostList.add(host);
					logger.debug("Neighbour host added : {}",host);
					NeighbourDiscovery.lock.unlock();
			}		
		}
	     public void serviceRemoved(ServiceEvent event) {
	    	 for (String host : event.getInfo().getHostAddresses()){
	    		 NeighbourDiscovery.lock.lock();
				NeighbourDiscovery.hostList.remove(host);
					logger.debug("Neighbour host removed : {}",host);
					NeighbourDiscovery.lock.unlock();
				
	 		}
	     }
		 public void serviceResolved(ServiceEvent event) {
			 for (String host : event.getInfo().getHostAddresses()){
				 NeighbourDiscovery.lock.lock();
				NeighbourDiscovery.hostList.add(host);
					logger.debug("Neighbour host sevice resolved : {} ",host);
					NeighbourDiscovery.lock.unlock();
				
			}
		 }
	}
	
	
	
	
}
