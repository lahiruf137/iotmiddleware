package com.example.iotmiddleware;



import com.example.iotmiddleware.management.OnEventListener;

/**
* iotmiddleware
* 
*/

public class App 
{
  public static void main( String[] args ) throws Exception
  {
	  System.out.println("starting");
	IotCore iotsystem = new IotCore(new OnEventAction() );
	System.out.println("started");
    //Thread.sleep(2000);
    iotsystem.setSelfAttribute("Light_1","on");
    iotsystem.setSelfAttribute("Light_2","off");
    
    while(true) {
    	Thread.sleep(500);
    	printNeighbourInfo(iotsystem);
    }
    
  }
  
  public static void printNeighbourInfo(IotCore iotsystem) throws Exception {
	  System.out.println("##### Discovered Neighbour List #####");
	  for(String s: iotsystem.getNeighbours()){
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