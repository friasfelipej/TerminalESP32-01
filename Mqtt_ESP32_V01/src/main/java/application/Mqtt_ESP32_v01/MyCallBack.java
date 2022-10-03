package application.Mqtt_ESP32_v01;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyCallBack implements MqttCallback
{


	String mqttTopicOut_001 = "esp32/output1";  //Digital from Broker to ESP32
	String mqttTopicOut_002 = "esp32/output2";  //Digital from Broker to ESP32
	String mqttTopicOut_003 = "esp32/output3";  //Digital from Broker to ESP32
	String mqttTopicOut_004 = "esp32/output4";  //PWM from Broker to ESP32
	
	boolean mqttPersistance = false;
	byte[] mqttMessageOut_001;
	byte[] mqttMessageFromBroker;
	int mqttQoS = 0;
	
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		mqttMessageFromBroker= message.getPayload();
		
		System.out.println(mqttMessageFromBroker);
		
		mqttMessageOut_001 = mqttMessageFromBroker;
		App.myClient.publish(mqttTopicOut_001,  mqttMessageOut_001, mqttQoS, mqttPersistance);
			
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}