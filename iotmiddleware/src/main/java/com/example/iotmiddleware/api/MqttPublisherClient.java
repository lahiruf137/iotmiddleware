package com.example.iotmiddleware.api;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPublisherClient {
	

    private int qos;
    private String broker;
    private String clientId;
    MemoryPersistence persistence;
    
    MqttClient sampleClient;
    
    public MqttPublisherClient(String broker,int qos,String clientId) {
    	this.broker=broker;
    	this.qos=qos;
    	this.clientId=clientId;
    	this.persistence = new MemoryPersistence();
    	try {
			this.sampleClient = new MqttClient(this.broker, this.clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            this.sampleClient.connect(connOpts);
            System.out.println("Connected");
		} catch (MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
			
		}
    }
    public  MqttPublisherClient() {
		this("tcp://iot.eclipse.org:1883",2,"JavaSample");
	}
    
    public void publish(String topic, String content) {
    	try {
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(this.qos);
            this.sampleClient.publish(topic, message);
            System.out.println("Message published");
        } catch(MqttException me) {
            System.out.println("msg "+me.getMessage());
        }
    }
    public void disconnect() {
        try {
			this.sampleClient.disconnect();
			System.out.println("Disconnected");
		} catch (MqttException e) {
			e.printStackTrace();
		}
        
    }

    
}
