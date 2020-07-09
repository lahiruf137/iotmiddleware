package com.example.iotmiddleware;

import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

//import com.example.iotmiddleware.Test11.Callback;
import com.example.iotmiddleware.api.MQTTHandler;
import com.example.iotmiddleware.management.OnEventListener;
import com.pi4j.io.gpio.*;

/**
* iotmiddleware
* 
*/

public class App {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
  public static void main( String[] args ) throws Exception {
	BasicConfigurator.configure();
	org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);
	  
	  System.setProperty("java.net.preferIPv4Stack", "true");
	  if(args.length!=1) {
		  printHelp();
	  }
	  else {
		if (args[0].equals("n")) {
			neighbourDiscovery();
		}
		else if (args[0].equals("m")) {
			apiConnectivity();
		}
		else if(args[0].equals("d")) {
			//
			 //Initilize middleware
			 String client_ID="Client_"+(int)(Math.random()*100);
			 System.out.print("# Starting "+client_ID+"\t");
			 //BasicConfigurator.configure();
			 IotCore iotsystem = new IotCore(new OnEventAction());
			 Thread.sleep(2000);
			 System.out.print("["+ANSI_GREEN+"DONE"+ANSI_RESET+"]"+"\n\n");

			 iotsystem.setSelfAttribute("LED", "false");

			final GpioPinDigitalOutput sensorTriggerPin ;
			final GpioPinDigitalInput sensorEchoPin ;
			final  GpioController gpio = GpioFactory.getInstance();
			sensorTriggerPin =  gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00); // Trigger pin as OUTPUT
			sensorEchoPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,PinPullResistance.PULL_DOWN); // Echo pin as INPUT
			while(true){
				String value="false";
				try {
				Thread.sleep(500);
				sensorTriggerPin.high(); // Make trigger pin HIGH
				Thread.sleep((long) 0.01);// Delay for 10 microseconds
				sensorTriggerPin.low(); //Make trigger pin LOW
			
				while(sensorEchoPin.isLow()){ //Wait until the ECHO pin gets HIGH
					
				}
				long startTime= System.nanoTime(); // Store the surrent time to calculate ECHO pin HIGH time.
				while(sensorEchoPin.isHigh()){ //Wait until the ECHO pin gets LOW
					
				}
				long endTime= System.nanoTime(); // Store the echo pin HIGH end time to calculate ECHO pin HIGH time.
			
				System.out.println("Distance :"+((((endTime-startTime)/1e3)/2) / 29.1) +" cm"); //Printing out the distance in cm  
				if (((((endTime-startTime)/1e3)/2) / 29.1)<7){
					value="true";
				}
				else{
					value="false";
				}
				iotsystem.setSelfAttribute("LED",value);
				//Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				}
			}

		}
		else if (args[0].equals("c")){
			//
			 //Initilize middleware
			 String client_ID="Client_"+(int)(Math.random()*100);
			 System.out.print("# Starting "+client_ID+"\t");
			 //BasicConfigurator.configure();
			 IotCore iotsystem = new IotCore(new OnEventAction());
			 Thread.sleep(2000);
			 System.out.print("["+ANSI_GREEN+"DONE"+ANSI_RESET+"]"+"\n\n");

			final GpioController gpio = GpioFactory.getInstance();
			final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
			
			pin1.setShutdownOptions(true, PinState.LOW);
			
			 while(true){
			 Set<String> n=iotsystem.getNeighbours();
			 //System.out.println(n);
			 for(String s: n){
				 String val="false";
				 

				if(iotsystem.getNeighbourAttributes(s).contains("LED")){
					 val=iotsystem.getNeighbourAttributeValue(s, "LED");
				}
				if(val.equals("true")){
						 pin1.high();
						 System.out.println("on");

					 }
					 else{
						 pin1.low();
						 System.out.println("off");
					 }
				 
			 }
			 Thread.sleep(2000);
			} 


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
	  	  System.out.println(new java.util.Date()+" : Neighbours -> "+n);
	  	Thread.sleep(2000);
	    }
	  
  }
  
  public static void apiConnectivity() throws Exception{
	  System.out.println("\n##### API Connectivity Mode #####\n");
	  //Initilize middleware
	  String client_ID="Client_"+(int)(Math.random()*100);
	  System.out.print("# Starting "+client_ID+"\t");
	  //BasicConfigurator.configure();
	  IotCore iotsystem = new IotCore(new OnEventAction());
	  Thread.sleep(2000);
	  System.out.print("["+ANSI_GREEN+"DONE"+ANSI_RESET+"]"+"\n\n");
	  
	  System.out.println("# Adding Attributes "+"\t");
	  iotsystem.setSelfAttribute("Fan_1","off");
	  int temp=(int) (Math.floor(Math.random() * 6) + 26 );
	  iotsystem.setSelfAttribute("Temprature",String.valueOf(temp));
	  
	  MQTTHandler mqh= new MQTTHandler("tcp://test.mosquitto.org:1883", 2,client_ID, new Callback());
	  mqh.subscribe("api-mode-Temprature");
	  mqh.subscribe("api-mode-Fan");
	  
	  while (true) {
		  int new_temp=(int) (Math.floor(Math.random() * 6) + 26 );
		  iotsystem.setSelfAttribute("Temprature",String.valueOf(new_temp));
		  mqh.publish("api-mode-Temprature", String.valueOf(new_temp));
		  if(Integer.parseInt(iotsystem.getSelfAttributeValue("Temprature"))>30) {
			  iotsystem.setSelfAttribute("Fan_1","on");
		  }
		  else {
			  iotsystem.setSelfAttribute("Fan_1","off");
		  }
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
		System.out.println("# Attribuet set : "+key+"->"+value);
	}
	public void onAttributeUnset(String key) {
		System.out.println("# Attribute unset : "+key);
	}
}

class Callback implements MqttCallback{
	
	public void connectionLost(Throwable cause) {
		
	}
	public void deliveryComplete(IMqttDeliveryToken token) {
		
	}
	public void messageArrived(String topic, MqttMessage message) {
		
	}

}