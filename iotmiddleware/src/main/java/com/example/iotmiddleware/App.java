package com.example.iotmiddleware;
import javax.jmdns.ServiceEvent;

import com.example.iotmiddleware.servicediscovery.*;
/**
 * Hello world!
 * iotmiddleware
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        System.out.print( "Starting mDNS Server 		\t" );
        new Thread(new MdnsServer()).start();
        System.out.print("[DONE] \n");
        
        System.out.print("Starting Discovery Service 	\t");
        new Thread(new MdnsClient()).start();
        System.out.print("[DONE] \n");
        
        
        Thread.sleep(30000);
        System.out.println("ok");
        System.exit(0);
    }
    
    static class Listner extends SimpleListener{
    	@Override
    	public void serviceResolved(ServiceEvent event) {
    		System.out.println("Service resloved from child");
   		 }
    	
    }
    
}
