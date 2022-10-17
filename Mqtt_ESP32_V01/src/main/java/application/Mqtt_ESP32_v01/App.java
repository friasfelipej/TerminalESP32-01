package application.Mqtt_ESP32_v01;

import java.awt.EventQueue;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
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
	public static String mqttTopicIn_001 = "esp32/heartbeat";   //Digital from ESP32 to Broker
	public static String mqttTopicIn_002 = "esp32/temperature"; //Analog from ESP32 to Broker
	public static String mqttTopicIn_003 = "esp32/humidity";    //Analog from ESP32 to Broker
	
	public static String mqttTopicOut_001 = "esp32/output1";  //Digital from Broker to ESP32
	public static String mqttTopicOut_002 = "esp32/output2";  //Digital from Broker to ESP32
	public static String mqttTopicOut_003 = "esp32/output3";  //Digital from Broker to ESP32
	public static String mqttTopicOut_004 = "esp32/output4";  //PWM from Broker to ESP32
		
	public static MqttAsyncClient myClient;
	public static MqttConnectOptions connOpts;

		public static void main( String[] args ) throws MqttException
	    {
			/*
			byte bytes;
			byte[] bytesarray;
			String str;
			int ent;
			float flo;
			
			flo = 123.45F;
			str=String.valueOf(flo);
			bytesarray = str.getBytes(StandardCharsets.UTF_8);
	    		    	
			System.out.println("float: " + flo);
			System.out.println("String: " + str);
			
			str = new String(bytesarray, StandardCharsets.UTF_8);
		    flo=Float.parseFloat(str);  
			
			System.out.println("float: " + flo);
			System.out.println("String: " + str);
			*/
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Ventanas frame = new Ventanas();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});		
			
			myClient = new MqttAsyncClient(mqttBrokerAddress, mqttClientID);
			connOpts = new MqttConnectOptions();
			connOpts.setCleanSession( true );
			connOpts.setUserName( mqttUser );
			connOpts.setPassword( mqttPassword.toCharArray() );
			MyCallBack myCallBack = new MyCallBack();
			System.out.println("Listo paso 0");
			
	        myClient.setCallback(myCallBack);	         
	        IMqttToken token = myClient.connect(connOpts);
	        System.out.println("Listo paso 1");
	        
	        token.waitForCompletion();	        
	        myClient.subscribe(mqttTopicIn_001, mqttQoS);
	        myClient.subscribe(mqttTopicIn_002, mqttQoS);
	        myClient.subscribe(mqttTopicIn_003, mqttQoS);
	        
	        System.out.println("Listo paso 2");
	       
	        
	        while (true) {
	        	
	        	
	        }
	    }	
}

