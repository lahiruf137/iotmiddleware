package com.example.iotmiddleware;

import java.rmi.Naming;
import java.util.LinkedHashSet;
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
		try {
			for(int i=0; i<3; i++) {
				System.out.println("Host list : "+hostList);
				Thread.sleep(10000);
				RMIInterface look_up= (RMIInterface) Naming.lookup("//localhost/exampleservice");
				String response = look_up.helloTo("t1");
				System.out.println("ExecutionEngine:Output:"+response);

				
				
				
			}
		} catch (Exception e) {
			System.out.println("Exception occored - "+e.getMessage());
		}
	}
	
}
