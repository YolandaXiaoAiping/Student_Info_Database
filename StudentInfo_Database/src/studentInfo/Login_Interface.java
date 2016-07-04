package studentInfo;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import studentInfo.DBConnection;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Login_Interface extends JFrame {
	public static final String PROGRAM_NAME = "Login_Interface";
	Connection connection;
	boolean correct = false;
	String userid;
	String setpath = "SET search_path TO a1";
	String admin;
	
	//JTable Login;
	JLabel student;
	JTextField student_userid;
	JButton button_login;
	
	public Login_Interface(Connection conn)throws SQLException{
		super("Student Evaluation System Login");
		
		this.connection = conn;
		
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	          DBConnection.closeConnection(connection);
	          System.exit(0);
	        }
	    });
		
		//Login = new JTable();
		student = new JLabel("UTORid:");
		student_userid = new JTextField(20);
		button_login = new JButton("Log In");
		
		Container contentPane = getContentPane();
        contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.CENTER;
        //c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        contentPane.add(student, c);
        
        c.fill = GridBagConstraints.CENTER;
        //c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.75;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        contentPane.add(student_userid, c);
        
        c.fill = GridBagConstraints.CENTER;
        //c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        contentPane.add(button_login, c);
        
        button_login.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		Student sid = new Student();
        		boolean rs = false;	
        		admin = student_userid.getText();
        		sid.setUserid(student_userid.getText());
        		try{
        			if(admin.equals("admin")){
        				setVisible(false);
        				JOptionPane.showMessageDialog(null, "Admin Mode!");
        				Manage_Interface mi = new Manage_Interface(connection);
        				mi.pack();
        				mi.setVisible(true);
        				dispose();
        			}
        			else{
	        			rs = sid.checkUserid(connection);
	        			if(rs){
	        				setVisible(false);
	        				JOptionPane.showMessageDialog(null, "Welcome to Student Evaluation System.");
	        				userid = student_userid.getText();
	        				User_Interface ui = new User_Interface(connection, userid);
	        				ui.setSize(400,600);;
	        				ui.setVisible(true);
	        				dispose();
	        			}
	        			else{
	        				JOptionPane.showMessageDialog(null, "Wrong UTORid! Please try again!");
	        			}
	        			clearInputs ();
        			}
        		} catch(SQLException ex){
        			SQLError.show(ex);
        		} 
        	}
        });
        
		
	}
	
	private void clearInputs () {
		student_userid.setText("");
	}
	
	public boolean check_status(){
		return this.correct;
	}
	
	public String getUserid(){
		return this.userid;
	}
	
}



class AddActionListener implements ActionListener {
	Login_Interface container;
	public AddActionListener (Login_Interface container){
		this.container = container;		
	}
	
	public void actionPerformed(ActionEvent e) {
		Student sid = new Student();
		boolean rs = false;
		sid.setUserid(container.student_userid.getText());
    	try {
    		rs = sid.checkUserid(container.connection);
    		if(rs) System.out.println("Valid user name.");
			else{
				System.out.println("Try again.");
			}
    	}
    	catch (SQLException ex){
    		SQLError.show(ex);
    	}
    }
}
