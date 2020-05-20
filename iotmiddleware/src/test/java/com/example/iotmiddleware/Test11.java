package com.example.iotmiddleware;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.example.iotmiddleware.Test10.Callback;
import com.example.iotmiddleware.api.MQTTHandler;

import junit.framework.TestCase;

public class Test11 extends TestCase {
	
class Callback implements MqttCallback{
		
		public void connectionLost(Throwable cause) {
			
		}
		public void deliveryComplete(IMqttDeliveryToken token) {
			
		}
		public void messageArrived(String topic, MqttMessage message) {
			assertEquals(topic,"test001");
		}
	
	}
	public void testSubscribe() {
		MQTTHandler mqh= new MQTTHandler("tcp://test.mosquitto.org:1883", 2,"mock_instance_001", new Callback());
		mqh.subscribe("test001");
		mqh.publish("test001", "dummy_data");
	}

}
