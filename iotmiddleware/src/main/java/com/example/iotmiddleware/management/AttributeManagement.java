package com.example.iotmiddleware.management;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Set;

public class AttributeManagement {
	public static transient HashMap<String, String> attributeList = new HashMap<String, String>();
	
	public synchronized static Set<String> getAttributeList() throws RemoteException {
		return attributeList.keySet();
	}
	
	public synchronized static String getAttribute(String key) throws RemoteException {
		return attributeList.get(key);
	}
	
	public synchronized static void  setAttribute(String key, String value) throws RemoteException {
		attributeList.put(key,value);
	}
	
	public synchronized static void  unsetAttribute(String key) throws RemoteException {
		attributeList.remove(key);
	}
}
