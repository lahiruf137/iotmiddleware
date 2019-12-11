package com.example.iotmiddleware;


/**
* iotmiddleware
* 
*/
public class App 
{
  public static void main( String[] args ) throws Exception
  {
    IotCore iotsystem = new IotCore();
    Thread.sleep(500);
    iotsystem.setSelfAttribute("Light_1","on");
    iotsystem.setSelfAttribute("Light_2","off");
    
    for(int i =0;i<10; i++) {
    Thread.sleep(2000);
    for(String s: iotsystem.getNeighbours()){
      System.out.println("Neighbour : "+s);
      for (String attr: iotsystem.getNeighbourAttributes(s)) {
    	  System.out.println(attr+" : "+iotsystem.getNeighbourAttributeValue(s,attr));
      }
    }    }
    System.exit(0);
  }
  
  
}
