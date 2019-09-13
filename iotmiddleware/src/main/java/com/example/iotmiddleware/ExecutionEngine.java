package com.example.iotmiddleware;

import java.util.LinkedHashSet;
import java.util.Set;

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
				
				
				
				
			}
		} catch (Exception e) {
			System.out.println("Exception occored - "+e.getMessage());
		}
	}
	
}
