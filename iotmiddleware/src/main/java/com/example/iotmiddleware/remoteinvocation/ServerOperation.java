package com.example.iotmiddleware.remoteinvocation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.example.iotmiddleware.remoteinvocation.RMIInterface;



public class ServerOperation extends UnicastRemoteObject implements RMIInterface {
	private static final long serialVersionUID = 1L;
	public ServerOperation() throws RemoteException {
		super();
	}
	
	
	public String helloTo(String name) throws RemoteException {
		return "test";
	}
}

