//Udemy Eduonix Projects in Java
//Database Project
//Using MySQL
//1-need to install MySQL server


package me.theofrancisco;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Udemy Database Demo");
		try {
			   UIManager.setLookAndFeel(
			            UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {			
			e.printStackTrace();
		}
		
		
		
		Properties properties = new Properties();
		ConnectDialog dialog = new ConnectDialog (frame,"Database Connector", properties);
		dialog.setVisible(true);
		if (dialog.isCanceled()) {
			System.exit(0);
		}
		Connector connector = new Connector(dialog.getProperties(), dialog.getPassword());
		if (!connector.openConnection()) {
			System.exit(0);
		}
		
		Database database = new Database(connector);
		frame.setSize(800,630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(database);
		frame.setVisible(true);
		

	}

}
