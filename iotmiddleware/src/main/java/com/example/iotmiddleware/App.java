package com.example.iotmiddleware;

import java.util.Scanner;

import com.example.iotmiddleware.management.OnEventListener;

/**
* iotmiddleware
* 
*/

public class App 
{
  public static void main( String[] args ) throws Exception
  {
    IotCore iotsystem = new IotCore(new OnEventAction() );
    Thread.sleep(500);
    iotsystem.setSelfAttribute("Light_1","on");
    iotsystem.setSelfAttribute("Light_2","off");
    
    while(true) {
    	System.out.println("Enter to view neighbours or Ctrl+c to exit");
    	new Scanner(System.in).nextLine();
    	printNeighbourInfo(iotsystem);
    }
    
  }
  
  public static void printNeighbourInfo(IotCore iotsystem) throws Exception {
	  for(String s: iotsystem.getNeighbours()){
	      System.out.println("Neighbour : "+s);
	      for (String attr: iotsystem.getNeighbourAttributes(s)) {
	    	  System.out.println(attr+" : "+iotsystem.getNeighbourAttributeValue(s,attr));
	      }
	  } 
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