package com.example.iotmiddleware;

import java.util.Set;

import com.example.iotmiddleware.servicediscovery.MdnsClient;
import com.example.iotmiddleware.servicediscovery.MdnsServer;
import com.example.iotmiddleware.servicediscovery.NeighbourDiscovery;

public class IotCore {
	public IotCore() throws Exception{
        new Thread(new MdnsServer()).start();
        new Thread(new MdnsClient()).start();
        new Thread(new RemoteOperationServer()).start();
        Thread.sleep(5000);
	}
	public Set<String> getNeighbours() {
		return NeighbourDiscovery.getneighbours();
	}
	public String getNeighbourAttributes(String neighbour) {
		return "";
	}
	public void setNeighbourAttributes(String attributes) {
		
	}

}
