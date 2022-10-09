package application.Mqtt_ESP32_v01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Ventanas extends JFrame implements ActionListener{

	private JPanel PanelPrincipal;
	
	private JButton btnOutput01;
	private JButton btnOutput02;
	private JButton btnOutput03;

	static JTextField Heartbeat;
	static JTextField Temperature;
	static JTextField Humidity;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
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
	}
	*/

	/**
	 * Create the frame.
	 */
	public Ventanas() {
		
		System.out.println("Ventanas y running....");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(null);
					
		btnOutput01 = new JButton("Button Out-01");
		btnOutput01.setBounds(38, 30, 143, 25);
		btnOutput01.addActionListener(this);
		PanelPrincipal.add(btnOutput01);
		
		btnOutput02 = new JButton("Button Out-02");
		btnOutput02.setBounds(38, 67, 143, 25);
		btnOutput02.addActionListener(this);
		PanelPrincipal.add(btnOutput02);
		
		btnOutput03 = new JButton("Button Out-03");
		btnOutput03.setBounds(41, 104, 143, 25);
		btnOutput03.addActionListener(this);
		PanelPrincipal.add(btnOutput03);
		
		Heartbeat = new JTextField();
		Heartbeat.setText("-----------");
		Heartbeat.setColumns(10);
		Heartbeat.setBounds(214, 139, 114, 30);
		PanelPrincipal.add(Heartbeat);
		
		Temperature = new JTextField();
		Temperature.setText("-----------");
		Temperature.setBounds(214, 181, 114, 30);
		PanelPrincipal.add(Temperature);
		Temperature.setColumns(10);
		
		Humidity = new JTextField();
		Humidity.setText("-----------");
		Humidity.setColumns(10);
		Humidity.setBounds(214, 223, 114, 30);
		PanelPrincipal.add(Humidity);
				
		JLabel lblHeartbeat = new JLabel("Heartbeat");
		lblHeartbeat.setBounds(110, 138, 102, 30);
		PanelPrincipal.add(lblHeartbeat);
		
		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setBounds(110, 180, 102, 30);
		PanelPrincipal.add(lblTemperature);
		
		JLabel lblHumidity = new JLabel("Humidity");
		lblHumidity.setBounds(110, 222, 102, 30);
		PanelPrincipal.add(lblHumidity);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnOutput01) {
			//Temperature.setText("Opcion 1");
			} else if (e.getSource()==btnOutput02 ) {
			//Temperature.setText("Opcion 2");
		} else if (e.getSource()==btnOutput03) {
			//Temperature.setText("Opcion 3");
		}
	}
}
