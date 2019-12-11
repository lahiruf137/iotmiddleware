package com.example.iotmiddleware.discovery;

import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceRegistration {
	private JmDNS jmdns;
	private ServiceInfo serviceInfo;
	private String serviceType;
	private String serviceName;
	private String serviceDescription;
	private int servicePort;

	public ServiceRegistration(String serviceType,String serviceName,String serviceDescription, int servicePort) {
		
		this.serviceType=serviceType;
		this.serviceName=serviceName;
		this.serviceDescription=serviceDescription;
		this.servicePort=servicePort;
		
		try {
			System.out.println("Creating for "+InetAddress.getLocalHost());
            this.jmdns=JmDNS.create(InetAddress.getLocalHost());
            this.serviceInfo = ServiceInfo.create(this.serviceType, this.serviceName, this.servicePort, this.serviceDescription);
            this.jmdns.registerService(serviceInfo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		
	}
	public ServiceRegistration() {
		this("_example._tcp.local.","example-service","example-description",8080);
	}
	public void unregisterAllServices() {
		
		try {
            this.jmdns.unregisterAllServices();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
