package com.example.iotmiddleware.management;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.iotmiddleware.IotCore;

public class RemoteOperationServer implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(RemoteOperationServer.class);
	public void run() {
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//"+InetAddress.getLocalHost().getHostAddress()+"/exampleservice", new ServerOperation());
			
			}catch (Exception e) {
				logger.error("unable to strart remote operations server "+e.getMessage());
				

			}
	}

}
