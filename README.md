# iotmiddleware
IoT middleware based on mDNS and Java RMI specifications

### Prerequisites for running 
* Hostnames should be configured on all hosts running middleware
* All hosts must be reachable on the desired netwaork (IPv4/IPv6) and multicast traffic should be allowed
* Requires Java runtime 1.8 or later
* TCP Ports 8080, 1099 should be open on all hosts
* Linux hosts should be running avahi-daemon 

### Example implementation

You may initilize middleware and add its own resources as follows

```java
    import com.example.iotmiddleware;
    ...
    IotCore iotsystem = new IotCore();
    iotsystem.setSelfAttribute("Light_1","on");

```

Later you may interact with neighbour devices as,

```java
    import com.example.iotmiddleware;
    ...
    // Note that IotCore() constructor is only called once
    // IotCore iotsystem = new IotCore();
     for(String s: iotsystem.getNeighbours()){
      System.out.println("Neighbour : "+n);
      for (String attribute: iotsystem.getNeighbourAttributes(n)) {
    	  System.out.println(attribute+" : "+iotsystem.getNeighbourAttributeValue(n,attribute));
      }
    }
    ...
    iotsystem.setNeighbourAttributes(negihbour,"light_1","off");

```

Implement callback methods to get notified when other neghibours update self  attributes

```java
    import com.example.iotmiddleware;
    import com.example.iotmiddleware.management.OnEventListener;
    ...
    class OnEventAction implements OnEventListener{
	    public void onAttributeSet(String key, String value) {
		    System.out.println("Attribuet set "+key+":"+value);
	    }
	    public void onAttributeUnset(String key) {
		    System.out.println("Attribute unset"+key);
	    }
    }
    ...
    IotCore iotsystem = new IotCore(new OnEventAction());

```

### Note
This project uses JmDNS and Java RMI implementations.  
For more specific details of these projects see,  
JmDNS [http://www.jmdns.org](http://www.jmdns.org)  
Java RMI [https://docs.oracle.com/javase/tutorial/rmi/index.html](https://docs.oracle.com/javase/tutorial/rmi/index.html )
