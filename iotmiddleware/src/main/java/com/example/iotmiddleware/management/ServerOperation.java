package com.example.iotmiddleware.management;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Set;

import com.example.iotmiddleware.management.RMIInterface;



public class ServerOperation extends UnicastRemoteObject implements RMIInterface {
	private static final long serialVersionUID = 1L;
	
	public static HashMap<String, String> attributeList = new HashMap<String, String>();
	
	public ServerOperation() throws RemoteException {
		super();
	}
	
	
	public String helloTo(String name) throws RemoteException {
		return "test";
	}
	
	public synchronized Set<String> getAttributeList() throws RemoteException {
		return attributeList.keySet();
	}
	
	public synchronized String getAttribute(String key) throws RemoteException {
		return attributeList.get(key);
	}
	
	public synchronized void  setAttribute(String key, String value) throws RemoteException {
		attributeList.put(key,value);
	}
	
}

