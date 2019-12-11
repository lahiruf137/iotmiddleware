package com.example.iotmiddleware.management;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RemoteOperationServer implements Runnable{
	public void run() {
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//"+InetAddress.getLocalHost().getHostAddress()+"/exampleservice", new ServerOperation());
			
			}catch (Exception e) {
				System.out.println(e.getMessage());
				

			}
	}

}
