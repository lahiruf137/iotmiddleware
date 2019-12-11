package com.example.iotmiddleware.management;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.example.iotmiddleware.management.RMIInterface;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {
	private static final long serialVersionUID = 1L;
	
	public ServerOperation() throws RemoteException {
		super();
	}
	
	public String helloTo(String name) throws RemoteException {
		return "test";
	}
	
	public synchronized ArrayList<String> getAttributeList() throws RemoteException {
		return new ArrayList<String>(AttributeManagement.getAttributeList());
	}
	
	public synchronized String getAttribute(String key) throws RemoteException {
		return AttributeManagement.getAttribute(key);
	}
	
	public synchronized void  setAttribute(String key, String value) throws RemoteException {
		AttributeManagement.setAttribute(key,value);
	}
	
}
