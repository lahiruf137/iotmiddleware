package com.example.iotmiddleware;

import java.rmi.Naming;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import com.example.iotmiddleware.remoteinvocation.RMIInterface;

public class ExecutionEngine extends Thread {
	
	private static Set<String> hostList=new LinkedHashSet<String>();
	
	public ExecutionEngine() throws Exception {
		
	}
	
	public static void addHost(String host) {
		hostList.add(host);
	}
	public static void removeHost(String host) {
		hostList.remove(host);
	}
	public void run() {
		for(int i=0; i<50; i++) {
			try {
					System.out.println("Host list : "+hostList);
					Thread.sleep(1000);
					
					String host=getLoadBalancedHost();
					RMIInterface look_up= (RMIInterface) Naming.lookup("//"+host+"/exampleservice");
					String response = look_up.helloTo("t1");
					System.out.println("ExecutionEngine:Output:"+response+":host"+host);	
				
			} catch (Exception e) {
				System.out.println("Exception occored - "+e.getMessage());
			}
		}
	}
	public String getLoadBalancedHost() {
		String host=null;
		int element = new Random().nextInt(hostList.size());
		int i=0;
		for (String h:hostList) {
			if(i==element)
				host=h;
			i++;
		}
		return host;
	}
	
}
