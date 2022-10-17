package application.Mqtt_ESP32_v01;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyCallBack implements MqttCallback
{
	
	boolean mqttPersistance = false;
	byte[] mqttMessageOut_001;
	byte[] mqttMessageFromBroker;
	int mqttQoS = 0;
	byte[] messageByte;
	String messageString;
	int messageInt;
	float messageFloat;
	public static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
	
	static String HeartbeatStr;
	static String TemperatureStr;
	static String HumidityStr;
	
		
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		
		mqttMessageFromBroker= message.getPayload();
		messageByte=mqttMessageFromBroker;
		
		if (topic.equals(App.mqttTopicIn_001)) {
			messageString = new String(messageByte, StandardCharsets.UTF_8);
			System.out.println(App.mqttTopicIn_001 + ": " + messageString);
			HeartbeatStr=messageString;
			Ventanas.Heartbeat.setText(HeartbeatStr);	
		} else if (topic.equals(App.mqttTopicIn_002)) {
					messageString = new String(messageByte, StandardCharsets.UTF_8);
					messageFloat=Float.parseFloat(messageString);  
					System.out.println(App.mqttTopicIn_002 + "  String:" + messageString + "  Float: " + messageFloat);
					TemperatureStr=messageString;
					Ventanas.Temperature.setText(TemperatureStr);					
				} else if (topic.equals(App.mqttTopicIn_003)) {
							messageString = new String(messageByte, StandardCharsets.UTF_8);
							messageFloat=Float.parseFloat(messageString);  
							System.out.println(App.mqttTopicIn_003 + "  String:" + messageString + "  Float: " + messageFloat);
							HumidityStr=messageString;
							Ventanas.Humidity.setText(HumidityStr);	
						}	
			
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		
	}

}