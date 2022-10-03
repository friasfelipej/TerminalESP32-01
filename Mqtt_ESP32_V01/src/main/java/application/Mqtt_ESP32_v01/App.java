package application.Mqtt_ESP32_v01;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class App 
{
	//********** MQTT Broker address *********************
	static String mqttBrokerAddress = "tcp://192.168.1.211:1883";
	static String mqttUser = "homebroker";
	static String mqttPassword = "kikobroker";
	static String mqttClientID = "ASUSClient"; 
	//static String mqttClientID = UUID.randomUUID().toString();
	static int mqttQoS = 0; 
	
	//**************** Topics from ESP32 to Broker ************
	static String mqttTopicIn_001 = "esp32/heartbeat";   //Digital from ESP32 to Broker
	static String mqttTopicIn_002 = "esp32/temperature"; //Analog from ESP32 to Broker
	static String mqttTopicIn_003 = "esp32/humidity";    //Analog from ESP32 to Broker
		
	public static MqttAsyncClient myClient;

		public static void main( String[] args ) throws MqttException
	    {
	       		
			myClient = new MqttAsyncClient(mqttBrokerAddress, mqttClientID);
			MyCallBack myCallBack = new MyCallBack();
			  System.out.println("Listo paso 0");
			
	        myClient.setCallback(myCallBack);
	     
	        IMqttToken token = myClient.connect();
	        token.waitForCompletion();
	        
	        System.out.println("Listo paso 1");
	        
	        myClient.subscribe(mqttTopicIn_001, mqttQoS);
	        myClient.subscribe(mqttTopicIn_002, mqttQoS);
	        myClient.subscribe(mqttTopicIn_003, mqttQoS);
	        
	        System.out.println("Listo paso 2");
	       
	    }
}
