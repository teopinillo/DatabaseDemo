package me.theofrancisco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ConnectDialog extends JDialog implements ActionListener {
	
	private boolean isCanceled = true;
	
	JLabel lblHost = new JLabel ("Host name");
	JTextField txtHost = new JTextField();
	JLabel lblPort = new JLabel ("Port");
	JTextField txtPort = new JTextField();
	JLabel lblDatabase = new JLabel ("Database");
	JTextField txtDatabase = new JTextField();
	JLabel lblUser= new JLabel ("User Name");
	JTextField txtUser = new JTextField();
	JLabel lblPassword = new JLabel ("Password");
	JPasswordField txtPassword = new JPasswordField();
	
	JButton btnOk = new JButton ("Ok");
	JButton btnCancel = new JButton ("Cancel");
	
	Properties properties;
	
	
	public ConnectDialog (JFrame frameOwner, String strTitle, Properties properties) {
		super (frameOwner, strTitle, true);
		setSize (300,200);
		setLocation (250,200);
		this.properties = properties;
		btnOk.setPreferredSize(new Dimension(75,25));
		btnOk.addActionListener(this);
		
		btnCancel.setPreferredSize(new Dimension(75,25));
		btnCancel.addActionListener(this);
		
		JPanel panelCPanel = new JPanel();
		JPanel panelCPanel2 = new JPanel();
		panelCPanel.setLayout ( new GridLayout (5,2));
		
		panelCPanel.add(lblHost);
		panelCPanel.add(txtHost);
		
		panelCPanel.add(lblPort);
		panelCPanel.add(txtPort);
		panelCPanel.add(lblDatabase);
		panelCPanel.add(txtDatabase);
		panelCPanel.add(lblUser);
		panelCPanel.add(txtUser);
		panelCPanel.add(lblPassword);
		panelCPanel.add(txtPassword);
		
		//default values
		txtHost.setText("den1.mysql3.gear.host");
		txtPort.setText("3306");
		txtUser.setText("employees1");
		txtDatabase.setText("employees1");
		txtPassword.setText("Jo6U~gzh104!");
		
		
		panelCPanel2.add(btnOk);
		panelCPanel2.add(btnCancel);
		
		add (panelCPanel,BorderLayout.NORTH);
		add (panelCPanel2, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==btnOk) {
			isCanceled = false;
		}
		
		this.dispose();
		
	}

	public boolean isCanceled() {		
		return isCanceled;
	}

	public Properties getProperties() {	
		properties.setProperty("host", txtHost.getText());
		properties.setProperty("port", txtPort.getText());
		properties.setProperty("database", txtDatabase.getText());
		properties.setProperty("user", txtUser.getText());	
		return properties;
	}

	public String getPassword() {
		return new String (txtPassword.getPassword());		
	}
	
}
