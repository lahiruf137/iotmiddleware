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

	
	public IotCore(OnEventListener evntl)  {
		try {
			serviceRegistration=new ServiceRegistration(iotcore_serv_type,iotcore_serv_name,iotcore_serv_desc,iotcore_serv_port);
			neighbourDiscovery=new NeighbourDiscovery(iotcore_serv_type);	
		} catch (Exception e) {
			logger.error("Unable to initilize child objects : {}",e.getMessage());
		}
		
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
	
	public IotCore() {
	      this(new RefrenceEventLister());
		}
	
	public Set<String> getNeighbours() {
		return neighbourDiscovery.getNeighbours();			
	}
	
	public ArrayList<String> getNeighbourAttributes(String neighbour) {
		try {
			ArrayList<String> attributeList=new ArrayList<String>();
			RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/"+iotcore_inst_name);
			attributeList = look_up.getAttributeList();
			return attributeList;
			
		} catch (Exception e) {
			logger.error("Unable to retrive neighbour attributes : {}",e.getMessage());
			return new ArrayList<>();
		}
		
	}
	
	public void setNeighbourAttribute(String neighbour,String attribute,String value) {
		try {
			RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/"+iotcore_inst_name);
			look_up.setAttribute(attribute,value);
			
		} catch (Exception e) {
			logger.error("Unable to set neighbour attribute : {}",e.getMessage());
		}
		
		
	}
	
	public String getNeighbourAttributeValue(String neighbour,String attribute) {
		try {
			RMIInterface look_up= (RMIInterface) Naming.lookup("//"+neighbour+"/"+iotcore_inst_name);
			String value = look_up.getAttribute(attribute);
			return value;	
		} catch (Exception e) {
			logger.error("Unable to retrive neighbour attribute vlue : {}",e.getMessage());
			return new String();
		}
		
	}
	
	public ArrayList<String> getSelfAttributeList() {
		try {
			return new ArrayList<String>(AttributeManager.getAttributeList());
		} catch (Exception e) {
			logger.error("Unable to retrive self attribute list : {}",e.getMessage());
			return new ArrayList<>();
		}
		
	}
	
	public void setSelfAttribute(String attribute,String value) {
		try {
			AttributeManager.setAttribute(attribute,value);
		} catch (Exception e) {
			logger.error("Unable to set self attribute : {}",e.getMessage());
		}
		
	}
	
	public String getSelfAttributeValue(String attribute) {
		try {
			return AttributeManager.getAttribute(attribute);
		} catch (Exception e) {
			logger.error("Unable to get self attribute value: {}",e.getMessage());
			return new String();
		}
		
	}
	
	public void removeSelfAttribute(String attribute) {
		try {
			AttributeManager.unsetAttribute(attribute);
		} catch (Exception e) {
			logger.error("Unable to remove self attribute : {}",e.getMessage());
		}
		
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
