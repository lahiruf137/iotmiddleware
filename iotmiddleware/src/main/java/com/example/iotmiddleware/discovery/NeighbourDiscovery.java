package com.example.iotmiddleware.discovery;

import java.util.LinkedHashSet;
import java.util.Set;

public class NeighbourDiscovery {
	private static Set<String> hostList=new LinkedHashSet<String>();
	
	public static void addHost(String host) {
		hostList.add(host);
	}
	public static void removeHost(String host) {
		hostList.remove(host);
	}
	public static Set<String> getneighbours(){
		return hostList;
	}
}
