# iotmiddleware
IoT middleware based on mDNS and Java RMI specifications

### Prerequisites for running 
* Hostnames should be configured on all hosts running middleware
* All hosts must be reachable on the desired netwaork(IPv4/IPv6)
* Requires Java runtime 1.8 or later
* TCP Ports 8080, 1099 should be open on all hosts
* Linux hosts should be running avahi-daemon 

### Note
This project uses JmDNS and Java RMI implementations.

For more specific details of these projects see,

JmDNS [http://www.jmdns.org] (http://www.jmdns.org)
Java RMI [https://docs.oracle.com/javase/tutorial/rmi/index.html] (https://docs.oracle.com/javase/tutorial/rmi/index.html )
