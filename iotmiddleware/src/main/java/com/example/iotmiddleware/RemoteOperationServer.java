package com.example.iotmiddleware;

import java.rmi.Naming;

import com.example.remoteinvocation.ServerOperation;

public class RemoteOperationServer implements Runnable{
	public void run() {
		try {
			Naming.rebind("//localhost/exampleservice", new ServerOperation());
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();

			}
	}

}
