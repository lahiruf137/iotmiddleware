package com.example.iotmiddleware.management;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteOperationServer implements Runnable{
	private String iotcore_inst_name;
	public RemoteOperationServer(Object inst_name) {
		this.iotcore_inst_name=(String) inst_name;
		
	}
	private static final Logger logger = LoggerFactory.getLogger(RemoteOperationServer.class);
	public void run() {
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//"+InetAddress.getLocalHost().getHostAddress()+"/"+this.iotcore_inst_name, new ServerOperation());
			
			}catch (Exception e) {
				logger.error("unable to strart remote operations server "+e.getMessage());
				

			}
	}

}
