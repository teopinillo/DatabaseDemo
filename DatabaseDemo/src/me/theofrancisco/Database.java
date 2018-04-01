package me.theofrancisco;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;

public class Database extends JPanel {
	static JTextArea txtSql = new JTextArea();
	static Connector dbConnector;
	
	
	JLabel prompt = new JLabel ("Please enter your sql statemment below");
	JButton btnExecute = new JButton ("Execute");
	JButton btnReset = new JButton ("Reset");
	static JTable tblTable = new JTable();
	static DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
	
	JScrollPane scpPane;
	JScrollPane scpTable;
	
	
	public Database (Connector connector) {
		dbConnector = connector;
		add(prompt);
		scpPane = new JScrollPane(txtSql);		
		scpPane.setPreferredSize ( new Dimension (750,100));
		add (scpPane);	
		btnExecute.addActionListener(
			new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent event) {
					execute();
					
				}
				
			}
		);
		
		btnReset.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				model.setColumnCount(0);
				model.setRowCount(0);
			}
		});
		add (btnExecute);
		add (btnReset);
		
		scpTable = new JScrollPane(tblTable);
		scpTable.setPreferredSize(new Dimension (750,400));				
		add (scpTable);
	}
	
	private static void execute() {
		model.setColumnCount(0);
		model.setRowCount(0);
		String s = txtSql.getText();
		try {
			s=s.toLowerCase();
			if (s.length()>6 && s.substring(0, 6).equals("select")) {
			ResultSet rs = dbConnector.executeQuery(s);
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i=1; i<=rsmd.getColumnCount(); i++) {
				model.addColumn(rsmd.getColumnName(i));
				
			}
			while (rs.next()) {
				String[] data = new String [rsmd.getColumnCount()];
				for (int i=1; i<=rsmd.getColumnCount(); i++) {
					data[i-1]=rs.getString(i);			
				}
				model.addRow(data);
			}
			}else {
				dbConnector.executeUpdate(s);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
