package com.example.iotmiddleware;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.example.iotmiddleware.remoteinvocation.ServerOperation;

public class RemoteOperationServer implements Runnable{
	public void run() {
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//localhost/exampleservice", new ServerOperation());
			
			}catch (Exception e) {
				System.out.println(e.getMessage());
				

			}
	}

}
