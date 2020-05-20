package com.example.iotmiddleware;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.example.iotmiddleware.api.*;

import junit.framework.TestCase;

public class Test10 extends TestCase {
	
	class Callback implements MqttCallback{
		
		public void connectionLost(Throwable cause) {
			
		}
		public void deliveryComplete(IMqttDeliveryToken token) {
			assert(true);
		}
		public void messageArrived(String topic, MqttMessage message) {
			
		}
		
		
	}
	public void testPublish() {
		MQTTHandler mqh= new MQTTHandler("tcp://test.mosquitto.org:1883", 2,"mock_instance_001", new Callback());
		mqh.publish("test001", "dummy_data");
		
	}

}
