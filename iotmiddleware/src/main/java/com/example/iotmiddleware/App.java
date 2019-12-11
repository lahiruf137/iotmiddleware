package com.example.iotmiddleware;

import com.example.iotmiddleware.servicediscovery.MdnsClient;
import com.example.iotmiddleware.servicediscovery.MdnsServer;

/**
 * iotmiddleware
 * 
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
        
        System.out.print("Starting Remote Operations 	\t");
        new Thread(new RemoteOperationServer()).start();
        System.out.print("[DONE] \n");

        System.out.print("Waiting for system init   	\t[....]");
        Thread.sleep(5000);
        System.out.print("\rWaiting for system init   	\t[DONE]\n");
        
        
        System.out.print("Starting Execution Engine 	\t");
        new Thread(new ExecutionEngine()).start();
        System.out.print("[DONE] \n");
        
        
        Thread.sleep(5000);
        System.out.print("Terminating System	    	\t");
        System.out.print("[DONE] \n");
        System.exit(0);
    }
   
    
}
