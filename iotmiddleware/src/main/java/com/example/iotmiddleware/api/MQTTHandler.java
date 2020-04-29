package com.example.iotmiddleware.api;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTHandler {
    private int qos;
    private String clientId;
    private String broker;
    private MemoryPersistence persistence;
    private MqttClient sampleClient;
    
    public MQTTHandler(String brokerURL, int qos,String clientId, MqttCallback callback){
        this.broker=brokerURL;
        this.qos=qos;
        this.clientId=clientId;
    	this.qos=qos;
    	this.persistence = new MemoryPersistence();
    	try {
            this.sampleClient = new MqttClient(this.broker, this.clientId, this.persistence);
            this.sampleClient.setCallback(callback);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            this.sampleClient.connect(connOpts);
            System.out.println("Connected");
		} catch (MqttException me) {
            //TODO : Log errors
			
		}
    }

    public void publish(String topic, String content) {
    	try {
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(this.qos);
            this.sampleClient.publish(topic, message);
        } catch(MqttException me) {
            // TODO : Log erros
        }
    }
    
    public void disconnect() {
        try {
			this.sampleClient.disconnect();
		} catch (MqttException e) {
			// TODO : Log erors
		}
        
    }
    
    public void subscribe(String topic){
        try {
            this.sampleClient.subscribe(topic);
        } catch (MqttException e) {
            // TODO Auto-generated catch block

        }
    }

}