package com.example.iotmiddleware;

import java.util.ArrayList;
import java.util.List;

public class ExecutionEngine extends Thread {
	
	private static List<String> hostList=new ArrayList<String>();
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
			for(int i=0; i<10; i++) {
				System.out.println(hostList);
				Thread.sleep(10000);
			}
		} catch (Exception e) {
			System.out.println("Exception occored - "+e.getMessage());
		}
	}
	
}
