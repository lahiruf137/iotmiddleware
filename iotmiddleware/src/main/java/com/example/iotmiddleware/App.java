package com.example.iotmiddleware;



import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;

import com.example.iotmiddleware.management.OnEventListener;

/**
* iotmiddleware
* 
*/

public class App 
{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
  public static void main( String[] args ) throws Exception
  {
	if(args.length!=1) {
		  //printHelp();
	  }
	else {
		if (args[0].equals("n")) {
			neighbourDiscovery();
		}
		else if (args[0]=="m") {
			//mqtt test
		}
		else if(args[0]=="d") {
			//
		}
		else {
			printHelp();
		}
	}
	  
    //iotsystem.setSelfAttribute("Light_1","on");
    //iotsystem.setSelfAttribute("Light_2","off");
    
    
  }
  public static void printHelp() {
	  System.out.print("See Application Usage bellow\n");
	  System.out.print("\t n - Neighbour Discovery Mode\n");
	  System.out.print("\t m - MQTT Test Mode\n");
	  System.out.print("\t d - Device Interaction Mode\n");
  }
  
  public static void neighbourDiscovery() throws Exception {
	  
	  System.out.println("\n##### Neighbour Discovery Mode #####\n");
	  
	  //Initilize middleware
	  String client_ID="Client_"+(int)(Math.random()*100);
	  System.out.print("# Starting "+client_ID+"\t");
	  //BasicConfigurator.configure();
	  IotCore iotsystem = new IotCore(new OnEventAction());
	  Thread.sleep(2000);
	  System.out.print("["+ANSI_GREEN+"DONE"+ANSI_RESET+"]"+"\n\n");
	  
	  System.out.print("# Neighbour Discovery "+"\t");
	  System.out.print("["+ANSI_GREEN+"RUNNING"+ANSI_RESET+"]"+"\n\n");
	  
	  while(true) {
		  Set<String> n=iotsystem.getNeighbours();
		  String out="";
	  	  for(String i: n){
	  	      out+=""+i+", ";
	  	  } 
	  	  System.out.print("\r"+out);
	  	Thread.sleep(2000);
	    }
	  
  }
  
  public static void printNeighbourInfo(IotCore iotsystem) throws Exception {
	  Set<String> n=iotsystem.getNeighbours();
	  System.out.println("##### Discovered Neighbour List #####");
	  for(String s: n){
	      System.out.println("Neighbour : "+s);
	      //for (String attr: iotsystem.getNeighbourAttributes(s)) {
	    //	  System.out.println(attr+" : "+iotsystem.getNeighbourAttributeValue(s,attr));
	     // }
	  } 
	  System.out.println("#####################################");
	  
  }
}

class OnEventAction implements OnEventListener{
	public void onAttributeSet(String key, String value) {
		System.out.println("Attribuet set "+key+":"+value);
	}
	public void onAttributeUnset(String key) {
		System.out.println("Attribute unset"+key);
	}
}