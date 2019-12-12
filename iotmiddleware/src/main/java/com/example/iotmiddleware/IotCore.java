package com.example.iotmiddleware;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.example.iotmiddleware.discovery.NeighbourDiscovery;
import com.example.iotmiddleware.discovery.ServiceRegistration;
import com.example.iotmiddleware.management.AttributeManagement;
import com.example.iotmiddleware.management.RMIInterface;
import com.example.iotmiddleware.management.RemoteOperationServer;
import com.example.iotmiddleware.management.OnEventListener;


public class IotCore {
	private static final Logger logger = LoggerFactory.getLogger(IotCore.class);
	public IotCore(OnEventListener evntl) throws Exception {
		ServiceRegistration.registerService();
	      new Thread(new RemoteOperationServer()).start();
	      Thread.sleep(1000);
	      AttributeManagement.registerEventListner(evntl);
	      
	      Runtime.getRuntime().addShutdownHook(new Thread() {
	          public void run() {
	      		try {
					ServiceRegistration.unregisterAllServices();
					logger.debug("Unregesterd instance");
				} catch (Exception e) {
					logger.error("error occored while unregrestering");
				}
	          }
	        });
	}
	
	public IotCore() throws Exception{
	      this(new RefrenceEventLister());
		}
	
	
	public Set<String> getNeighbours() throws Exception{
		return new NeighbourDiscovery().getNeighbours();
	}
	
	public ArrayList<String> getNeighbourAttributes(String neighbour) throws Exception {
		ArrayList<String> attributeList=new ArrayList<String>();

		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/exampleservice");
		attributeList = look_up.getAttributeList();

		return attributeList;
	}
	
	public void setNeighbourAttribute(String neighbour,String attribute,String value) throws Exception {
		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/exampleservice");
		look_up.setAttribute(attribute,value);
		
	}
	
	public String getNeighbourAttributeValue(String neighbour,String attribute) throws Exception {
		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/exampleservice");
		String value = look_up.getAttribute(attribute);
		return value;
	}
	
	public ArrayList<String> getSelfAttributeList() throws Exception{
		return new ArrayList<String>(AttributeManagement.getAttributeList());
	}
	
	public void setSelfAttribute(String attribute,String value) throws Exception {
		AttributeManagement.setAttribute(attribute,value);
	}
	
	public String getSelfAttributeValue(String attribute) throws Exception {
		return AttributeManagement.getAttribute(attribute);
	}
	
	public void removeSelfAttribute(String attribute) throws Exception {
		AttributeManagement.unsetAttribute(attribute);
	}
	
	
	static class RefrenceEventLister implements OnEventListener{
		public void onAttributeSet(String key, String value) {
			logger.debug("Attribuet set "+key+":"+value);
		}
		public void onAttributeUnset(String key) {
			logger.debug("Attribute unset"+key);
		}
	}

}
