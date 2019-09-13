package com.example.remoteinvocation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {
	private static final long serialVersionUID = 1L;
	public ServerOperation() throws RemoteException {
		super();
	}

	public String helloTo(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}

