package com.example.iotmiddleware.management;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIInterface extends Remote {
	
	public String helloTo(String name) throws RemoteException;
	
	public ArrayList<String> getAttributeList() throws RemoteException;
	
	public String getAttribute(String key) throws RemoteException;
	
	public void setAttribute(String key, String value) throws RemoteException;
	
}
