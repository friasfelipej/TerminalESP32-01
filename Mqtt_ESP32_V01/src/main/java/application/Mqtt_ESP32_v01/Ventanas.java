package application.Mqtt_ESP32_v01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Ventanas extends JFrame implements ActionListener{

	private JPanel PanelPrincipal;
	private JButton btnOutput02_off;
	private JButton btnOutput02_on;
	private JButton btnOutput03_off;
	private JButton btnOutput03_on;
	private JSlider Slider_OutPut04;
	
	static JTextField Heartbeat;
	static JTextField Temperature;
	static JTextField Humidity;
	
	int mqttQoS = 0;
	boolean mqttPersistance = false;
	private JTextField Slider_value;
	
	

	
	public Ventanas() {
		
		System.out.println("Ventanas y running....");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 475);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(null);
		
		/* labels */
		
		JLabel lblHeartbeat = new JLabel("Heartbeat");
		lblHeartbeat.setBounds(110, 178, 102, 30);
		PanelPrincipal.add(lblHeartbeat);
		
		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setBounds(110, 206, 102, 30);
		PanelPrincipal.add(lblTemperature);
		
		JLabel lblHumidity = new JLabel("Humidity");
		lblHumidity.setBounds(110, 234, 102, 30);
		PanelPrincipal.add(lblHumidity);
		
		JLabel lblOutput2 = new JLabel("Output #2");
		lblOutput2.setBounds(192, 60, 135, 15);
		PanelPrincipal.add(lblOutput2);
		
		JLabel lblOutput3 = new JLabel("Output #3");
		lblOutput3.setBounds(192, 114, 135, 15);
		PanelPrincipal.add(lblOutput3);
		
		btnOutput02_off = new JButton("OFF");
		btnOutput02_off.setBounds(69, 77, 143, 25);
		btnOutput02_off.addActionListener(this);
		PanelPrincipal.add(btnOutput02_off);
		
		btnOutput02_on = new JButton("ON");
		btnOutput02_on.setBounds(240, 77, 143, 25);
		btnOutput02_on.addActionListener(this);
		PanelPrincipal.add(btnOutput02_on);
		
		btnOutput03_off = new JButton("OFF");
		btnOutput03_off.setBounds(69, 129, 143, 25);
		btnOutput03_off.addActionListener(this);
		PanelPrincipal.add(btnOutput03_off);
		
		btnOutput03_on = new JButton("ON");
		btnOutput03_on.setBounds(240, 129, 143, 25);
		btnOutput03_on.addActionListener(this);
		PanelPrincipal.add(btnOutput03_on);
		
		Heartbeat = new JTextField();
		Heartbeat.setText("-----------");
		Heartbeat.setColumns(10);
		Heartbeat.setBounds(214, 179, 114, 30);
		PanelPrincipal.add(Heartbeat);
		
		Temperature = new JTextField();
		Temperature.setText("-----------");
		Temperature.setBounds(214, 207, 114, 30);
		PanelPrincipal.add(Temperature);
		Temperature.setColumns(10);
		
		Humidity = new JTextField();
		Humidity.setText("-----------");
		Humidity.setColumns(10);
		Humidity.setBounds(214, 235, 114, 30);
		PanelPrincipal.add(Humidity);
		
		/*  Slider */
		
		Slider_OutPut04 = new JSlider();
		Slider_OutPut04.setValue(0);
		Slider_OutPut04.setSnapToTicks(true);
		Slider_OutPut04.setMinorTickSpacing(1);
		Slider_OutPut04.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Slider_value.setText(String.valueOf(Slider_OutPut04.getValue()));
			}
		});
		Slider_OutPut04.setPaintTicks(true);
		Slider_OutPut04.setPaintLabels(true);
		Slider_OutPut04.setMajorTickSpacing(10);
		Slider_OutPut04.setBounds(62, 296, 321, 42);
		PanelPrincipal.add(Slider_OutPut04);
		
		Slider_value = new JTextField();
		Slider_value.setHorizontalAlignment(SwingConstants.CENTER);
		Slider_value.setFont(new Font("Dialog", Font.PLAIN, 20));
		Slider_value.setBounds(132, 350, 161, 51);
		Slider_value.setText(String.valueOf(Slider_OutPut04.getValue()));
		PanelPrincipal.add(Slider_value);
		Slider_value.setColumns(10);
				
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String TopicOut;
		byte[] MessageOut;
		String MessageOutStr;		
		
		Object ButtonEvent = e.getSource();
		TopicOut=".";
		MessageOutStr=".";
		
		if (ButtonEvent==btnOutput02_off) {
			TopicOut=App.mqttTopicOut_002;
			MessageOutStr="off";
		} else if (ButtonEvent==btnOutput02_on) {
			TopicOut=App.mqttTopicOut_002;
			MessageOutStr="on";
		} else if (ButtonEvent==btnOutput03_off) {
			TopicOut=App.mqttTopicOut_003;
			MessageOutStr="off";
		} else if (ButtonEvent==btnOutput03_on) {
			TopicOut=App.mqttTopicOut_003;
			MessageOutStr="on";
		} 	
				
		System.out.println(TopicOut + "   " + MessageOutStr);
		
		
		MessageOut = MessageOutStr.getBytes(StandardCharsets.UTF_8);
		try {
			App.myClient.publish(TopicOut, MessageOut, mqttQoS,mqttPersistance);
		} catch (MqttPersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MqttException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
	}
}
