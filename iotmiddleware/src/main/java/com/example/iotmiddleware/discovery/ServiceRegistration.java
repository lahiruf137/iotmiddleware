package com.example.iotmiddleware.discovery;

import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceRegistration {

	public static void  registerService(String type,String name,String description, int port) throws Exception {		
		ServiceInfo serviceInfo = ServiceInfo.create(type, name, port, description);
        JmDNS.create(InetAddress.getLocalHost()).registerService(serviceInfo);		
	}

	public static void unregisterAllServices() throws Exception{
		JmDNS.create(InetAddress.getLocalHost()).unregisterAllServices();
 	}
}
