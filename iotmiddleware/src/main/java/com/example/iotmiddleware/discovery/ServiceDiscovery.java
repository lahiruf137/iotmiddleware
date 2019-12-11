package com.example.iotmiddleware.discovery;

import java.net.InetAddress;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceListener;

public class ServiceDiscovery {
	private JmDNS jmdns;
	private String serviceType;
	private ServiceListener serviceListener;
	public ServiceDiscovery(String serviceType,ServiceListener  serviceListener) {
		this.serviceType=serviceType;
		this.serviceListener=serviceListener;
		try {
			jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(this.serviceType, this.serviceListener);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ServiceDiscovery() {
		this("_example._tcp.local.",new SimpleListener());
	}
	public void stopDiscovery() {
		jmdns.removeServiceListener(this.serviceType,this.serviceListener);
	}
	

}
