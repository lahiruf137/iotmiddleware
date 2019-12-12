package com.example.iotmiddleware.discovery;

import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ServiceRegistration {
	private static JmDNS jmdns;
	private static ServiceInfo serviceInfo;
	private static String serviceType="_example._tcp.local.";
	private static String serviceName="example-service";
	private static String serviceDescription="example-description";
	private static int servicePort=8080;

	public static void  RegisterService(String type,String name,String description, int port) throws Exception {
		serviceType=type;
		serviceName=name;
		serviceDescription=description;
		servicePort=port;
		RegisterService();
		
	}
	public static void RegisterService() throws Exception {
            jmdns=JmDNS.create(InetAddress.getLocalHost());
            serviceInfo = ServiceInfo.create(serviceType, serviceName, servicePort, serviceDescription);
            jmdns.registerService(serviceInfo);
	}

	public static void unregisterAllServices() throws Exception{
            jmdns.unregisterAllServices();
 	}
}
