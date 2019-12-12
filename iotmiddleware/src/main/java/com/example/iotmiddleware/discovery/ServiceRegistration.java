package com.example.iotmiddleware.discovery;

import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceRegistration {
	
	private static JmDNS jmdns;
	private ServiceInfo serviceInfo;
	
	public ServiceRegistration(String type,String name,String description, int port) throws Exception {
		serviceInfo = ServiceInfo.create(type, name, port, description);
		jmdns=JmDNS.create(InetAddress.getLocalHost());
		jmdns.registerService(serviceInfo);
		
		
	}

	public  void  registerService(String type,String name,String description, int port) throws Exception {		
		serviceInfo = ServiceInfo.create(type, name, port, description);
        JmDNS.create(InetAddress.getLocalHost()).registerService(serviceInfo);		
	}

	public  void unregisterAllServices() throws Exception{
		jmdns.unregisterAllServices();
 	}
	
}
