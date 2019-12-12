package com.example.iotmiddleware;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.example.iotmiddleware.discovery.NeighbourDiscovery;
import com.example.iotmiddleware.discovery.NewNeighbourDiscovery;
import com.example.iotmiddleware.discovery.ServiceRegistration;
import com.example.iotmiddleware.management.AttributeManagement;
import com.example.iotmiddleware.management.RMIInterface;
import com.example.iotmiddleware.management.RemoteOperationServer;
import com.example.iotmiddleware.management.OnEventListener;


public class IotCore {
	private final String iotcore_inst_name="exampleservice";
	private final String iotcore_serv_type="_example._tcp.local.";
	private final String iotcore_serv_name="example-service";
	private final String iotcore_serv_desc="example-service";
	private final int iotcore_serv_port=8080;
	private NewNeighbourDiscovery nnd;
	ServiceRegistration sr;
	private static final Logger logger = LoggerFactory.getLogger(IotCore.class);

	
	public IotCore(OnEventListener evntl) throws Exception {
		sr=new ServiceRegistration(iotcore_serv_type,iotcore_serv_name,iotcore_serv_desc,iotcore_serv_port);
		nnd=new NewNeighbourDiscovery(iotcore_serv_type);
	    new Thread(new RemoteOperationServer(iotcore_inst_name)).start();
	      
	      AttributeManagement.registerEventListner(evntl);
	      
	      Runtime.getRuntime().addShutdownHook(new Thread() {
	          public void run() {
	      		try {
					sr.unregisterAllServices();
					Thread.sleep(2000);
					logger.info("Unregesterd instance");
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
		//return new NeighbourDiscovery().getNeighbours(iotcore_serv_type);
		return nnd.getNeighbours();
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
