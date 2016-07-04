package studentInfo;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import studentInfo.DBConnection;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Manage_Interface extends JFrame{
	public static final String PROGRAM_NAME = "Manage_Interface";
	Connection connection;
	
	String userName;
	int eid;
	
	JTable cedition; //table to show all course edition
	
	JTable students; //all students in the database

	JTable enrollment; //enrollment info according to different students
	
	JLabel UserId;
	JTextField tUserId;
	
	JLabel Edition;
	JTextField tEdition;
	
	JLabel Grade;
	JTextField tGrade;
	
	JButton Add;
	JButton Delete;
	
	JLabel note;
	
	JButton finish;//set a go back button
	JButton exit;
	
	Object[] edition_column = {"Eid","Deptcode","Course Num","Start Time","End Time"};
	Object[] student_column = {"Userid", "Name", "Gender"};
	Object[] enroll_column = {"Eid","Deptcode","Course Num","Grade"};
	
	public Manage_Interface(Connection conn)throws SQLException{
		
		super("Management of Database");
		
		this.connection = conn;
		
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	          DBConnection.closeConnection(conn);
	          System.exit(0);
	        }
	    }); 
		
		//*************************Initialization****************************//
		cedition = new JTable();
		refreshCedition();
		
		students = new JTable();
		refreshStudents();
		enrollment = new JTable();
		
		/*
		UserId = new JLabel("User ID:");
		tUserId = new JTextField(10);
		*/
		
		Edition = new JLabel("New Edition:");
		tEdition = new JTextField(10);
		
		Grade = new JLabel("Grade:");
		tGrade = new JTextField(10);
		
		Add = new JButton("Add");
		Add.setEnabled(false);
		Delete = new JButton("Delete");
		Delete.setEnabled(false);
		
		note = new JLabel("Exit when you finish.");
		
		finish = new JButton("Finish");
        exit = new JButton("Exit");
        
      //***************************Set up the UI*************************************//
		Container contentPane = getContentPane();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//edition table
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 5.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		contentPane.add(new JScrollPane(cedition), c);
		
		//student table
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		contentPane.add(new JScrollPane(students), c);
		
		//mouse click event for students
		students.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 1){
					Add.setEnabled(true);
					//Delete.setEnabled(true);
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					userName = (String)target.getValueAt(row, 0);
					refreshEnroll();
				}
			}
		});
		
		//enrollment table
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		contentPane.add(new JScrollPane(enrollment), c);
		
		enrollment.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 1){
					//Add.setEnabled(true);
					Delete.setEnabled(true);
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					eid = (Integer)target.getValueAt(row, 0);
					refreshEnroll();
				}
			}
		});
		
		/*
		c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        contentPane.add(UserId, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        contentPane.add(tUserId, c);
        */
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        contentPane.add(Edition, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        contentPane.add(tEdition, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        contentPane.add(Grade, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        contentPane.add(tGrade, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        contentPane.add(Add, c);
        
        Add.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		if(tEdition.getText().equals("") || tGrade.getText().equals("")){
    				JOptionPane.showMessageDialog(null, "Input edition and grade.");
    			}
        		else{
	        		Course_Experience ce = new Course_Experience();
	        		ce.setEid(Integer.parseInt(tEdition.getText()));
	        		ce.setUserid(userName);
	        		ce.setGrade(Double.parseDouble(tGrade.getText()));
	        		E_Skill es = new E_Skill();
	        		es.setUserid(userName);
	        		es.setEid(Integer.parseInt(tEdition.getText()));
	        		E_Topic et = new E_Topic();
	        		et.setUserid(userName);
	        		et.setEid(Integer.parseInt(tEdition.getText()));
	        		try{
	        			ce.addCourseExperience(connection);
	        			es.addSE(connection);
	        			et.addTE(connection);
	        			tEdition.setText("");
	        			tGrade.setText("");
	        			refreshEnroll();
	        		}catch (SQLException ex){
	        			SQLError.show(ex);
	        		}
        		}
        		
        	}
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        contentPane.add(Delete, c);
        
        Delete.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		Course_Experience ce = new Course_Experience();
        		ce.setEid(eid);
        		ce.setUserid(userName);
        		try{
        			ce.deleteCourseExperience(connection);
        			tEdition.setText("");
        			tGrade.setText("");
        			refreshEnroll();
        		}catch (SQLException ex){
        			SQLError.show(ex);
        		}
        	}
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        contentPane.add(note, c);
        
        c.fill = GridBagConstraints.CENTER;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        contentPane.add(finish, c);
        
        finish.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		try{
	        		Login_Interface li= new Login_Interface(conn);
	        		li.setSize(300, 500);
	        		li.setVisible(true);
	        		dispose();
        		} catch(SQLException ex){
        			SQLError.show(ex);
        		}
        	}
        });
        
        c.fill = GridBagConstraints.CENTER;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 8;
        c.gridwidth = 1;
        contentPane.add(exit, c);
        
        exit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.exit(1);
        	}
        });
				
		
	}
	
	private void refreshCedition() {
		try {    		
    		DefaultTableModel model = new DefaultTableModel(new Object[0][0], edition_column);
    		java.util.List<Courseed> getAllCourseEd = Courseed.getAllCourseEd(connection);
            for (Courseed s : getAllCourseEd) {
                 Object[] o = new Object[5];
                 o[0] = s.getEid();
                 o[1] = s.getDept();
                 o[2] = s.getCnum();
                 o[3] = s.getStart();
                 o[4] = s.getEnd();
                 model.addRow(o);
            }
            cedition.setModel(model);
            cedition.repaint();
    	}
    	catch (SQLException ex){
    		SQLError.show(ex);
    	}
	}
	
	private void refreshEnroll () {
		try {    		
    		DefaultTableModel model = new DefaultTableModel(new Object[0][0], enroll_column);
    		java.util.List<Course_ex> getAllCourseSEx = Course_Experience.getAllCourseSEx(connection,userName);
            for (Course_ex s : getAllCourseSEx) {
                 Object[] o = new Object[4];
                 o[0] = s.getEid();
                 o[1] = s.getDeptcode();
                 o[2] = s.getCnum();
                 o[3] = s.getGrade();
                 model.addRow(o);
            }
            enrollment.setModel(model);
            enrollment.repaint();
    	}
    	catch (SQLException ex){
    		SQLError.show(ex);
    	}
	}
	
	private void refreshStudents() {
		try {    		
    		DefaultTableModel model = new DefaultTableModel(new Object[0][0], student_column);
    		java.util.List<Student> getAllStudent = Student.getAllStudent(connection);
            for (Student s : getAllStudent) {
                 Object[] o = new Object[3];
                 o[0] = s.getUserid();
                 o[1] = s.getName();
                 o[2] = s.getGender();
                 model.addRow(o);
            }
            students.setModel(model);
            students.repaint();
    	}
    	catch (SQLException ex){
    		SQLError.show(ex);
    	}
	}
}
