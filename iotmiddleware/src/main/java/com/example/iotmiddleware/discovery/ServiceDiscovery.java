package com.example.iotmiddleware.discovery;

import java.net.InetAddress;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceListener;

public class ServiceDiscovery {
	private static JmDNS jmdns;
	private static String serviceType="_example._tcp.local.";
	private static ServiceListener serviceListener=new SimpleListener();
	
	
	public static void startDiscovery(String type,ServiceListener  listener) throws Exception{
		serviceType=type;
		serviceListener=listener;
		startDiscovery();
	}
	
	public static void startDiscovery() throws Exception{
		jmdns = JmDNS.create(InetAddress.getLocalHost());
		jmdns.addServiceListener(serviceType, serviceListener);
	}
	public static void stopDiscovery() throws Exception {
		jmdns.removeServiceListener(serviceType,serviceListener);
	}
	

}
