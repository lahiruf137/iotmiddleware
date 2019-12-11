package com.example.iotmiddleware;

import java.rmi.Naming;
import java.util.HashSet;
import java.util.Set;

import com.example.iotmiddleware.discovery.MdnsClient;
import com.example.iotmiddleware.discovery.MdnsServer;
import com.example.iotmiddleware.discovery.NeighbourDiscovery;
import com.example.iotmiddleware.management.RMIInterface;
import com.example.iotmiddleware.management.RemoteOperationServer;
import com.example.iotmiddleware.management.ServerOperation;

public class IotCore {
	public IotCore() throws Exception{
        new Thread(new MdnsServer()).start();
        new Thread(new MdnsClient()).start();
        new Thread(new RemoteOperationServer()).start();
        Thread.sleep(5000);
	}
	
	public Set<String> getNeighbours() {
		return NeighbourDiscovery.getneighbours();
	}
	
	public Set<String> getNeighbourAttributes(String neighbour) {
		System.out.println("here !");
		Set<String> attributeList=new HashSet<String>();
		try{
		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/exampleservice");
		attributeList = look_up.getAttributeList();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return attributeList;
	}
	
	public void setNeighbourAttribute(String neighbour,String attribute,String value) throws Exception {
		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/exampleservice");
		look_up.setAttribute(attribute,value);
		
	}
	
	public String getNeighbourAttribute(String neighbour,String attribute) throws Exception {
		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/exampleservice");
		String value = look_up.getAttribute(attribute);
		return value;
	}
	
	public void setSelfAttribute(String attribute,String value) {
		ServerOperation.attributeList.put(attribute,value);
	}

	public void removeSelfAttribute(String attribute) {
		ServerOperation.attributeList.remove(attribute);
	}

}
