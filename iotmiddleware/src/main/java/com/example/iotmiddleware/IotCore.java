package com.example.iotmiddleware;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.example.iotmiddleware.discovery.NeighbourDiscovery;
import com.example.iotmiddleware.discovery.ServiceRegistration;
import com.example.iotmiddleware.management.AttributeManager;
import com.example.iotmiddleware.management.RMIInterface;
import com.example.iotmiddleware.management.RemoteOperationServer;
import com.example.iotmiddleware.management.OnEventListener;


public class IotCore {
	private final String iotcore_inst_name="exampleservice";
	private final String iotcore_serv_type="_example._tcp.local.";
	private final String iotcore_serv_name="example-service";
	private final String iotcore_serv_desc="example-service";
	private final int iotcore_serv_port=8080;
	private NeighbourDiscovery neighbourDiscovery;
	private ServiceRegistration serviceRegistration;
	private static final Logger logger = LoggerFactory.getLogger(IotCore.class);

	
	public IotCore(OnEventListener evntl) throws Exception {
		serviceRegistration=new ServiceRegistration(iotcore_serv_type,iotcore_serv_name,iotcore_serv_desc,iotcore_serv_port);
		neighbourDiscovery=new NeighbourDiscovery(iotcore_serv_type);
	    new Thread(new RemoteOperationServer(iotcore_inst_name)).start();
	      
	      AttributeManager.registerEventListner(evntl);
	      
	      Runtime.getRuntime().addShutdownHook(new Thread() {
	          public void run() {
	      		try {
	      			serviceRegistration.unregisterAllServices();
					logger.info("Unregesterd instance");
				} catch (Exception e) {
					logger.error("Error occored while unregrestering : {}",e.getMessage());
				}
	          }
	        });
	}
	
	public IotCore() throws Exception{
	      this(new RefrenceEventLister());
		}
	
	public Set<String> getNeighbours() throws Exception{
		return neighbourDiscovery.getNeighbours();
	}
	
	public ArrayList<String> getNeighbourAttributes(String neighbour) throws Exception {
		ArrayList<String> attributeList=new ArrayList<String>();

		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/"+iotcore_inst_name);
		attributeList = look_up.getAttributeList();

		return attributeList;
	}
	
	public void setNeighbourAttribute(String neighbour,String attribute,String value) throws Exception {
		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/"+iotcore_inst_name);
		look_up.setAttribute(attribute,value);
		
	}
	
	public String getNeighbourAttributeValue(String neighbour,String attribute) throws Exception {
		RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/"+iotcore_inst_name);
		String value = look_up.getAttribute(attribute);
		return value;
	}
	
	public ArrayList<String> getSelfAttributeList() throws Exception{
		return new ArrayList<String>(AttributeManager.getAttributeList());
	}
	
	public void setSelfAttribute(String attribute,String value) throws Exception {
		AttributeManager.setAttribute(attribute,value);
	}
	
	public String getSelfAttributeValue(String attribute) throws Exception {
		return AttributeManager.getAttribute(attribute);
	}
	
	public void removeSelfAttribute(String attribute) throws Exception {
		AttributeManager.unsetAttribute(attribute);
	}
		
	static class RefrenceEventLister implements OnEventListener{
		public void onAttributeSet(String key, String value) {
			logger.debug("Attribuet set : {} -> {} ",key,value);
		}
		public void onAttributeUnset(String key) {
			logger.debug("Attribute unset : {}",key);
		}
	}

}
