package studentInfo;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class User_Interface extends JFrame{
	public static final String PROGRAM_NAME = "User_Interface";
	
	String userName;
	
	int eid;//record the selected eid
	
	String instructor_name; //record the selected instructor.
	
	String topic_name; // record the selected topic;
	String skill_name;
	
	Connection conn;
	/*
	boolean select = false;
	boolean select_instructor = false; //select state for instructor
	boolean select_t = false; //select state for topic_experience
	boolean select_s = false;
	*/
	
	JTable student_course;//show the courses and grade and satisfaction the student has taken
	
	JLabel course_satisfaction;
	
	JTextField satisfaction;
	
	JButton satisfaction_edit,satisfaction_delete;
	
	JTable instructor_ranking;// show the instructors and ranking
	
	JLabel instructorinfo;
	JLabel instructor_rank;
	
	JTextField rank;
	
	JTable skill_experience;//show the feedback of skills
	
	JLabel skill_info;
	
	JLabel experience_before;
	
	JTextField before;
	
	JLabel experience_after;
	
	JTextField after;
	
	JButton skill_edit,skill_delete;
	
	JTable topic_experience;//show the feedback of topics
	
	JLabel topic_info;
	
	JLabel topic_before;
	
	JTextField tbefore;
	
	JLabel topic_after;
	
	JTextField tafter;
	
	JButton button_edit;//change the value
	JButton button_delete;//delete the feedback
	
	JButton finish;//set a go back button
	JButton exit;
	
	Object[] student_course_column = {"Eid","Deptcocde","Course Num","Course Grade","Satisfaction","Instructor Rank"};
	Object[] instructor_ranking_column = {"Instructor Name","Rank"};
	Object[] topic_experience_column = {"Topic Name","Before","After"};
	Object[] skill_experience_column = {"Skill Name","Before","After"};
	
	public User_Interface(Connection conn,String username)throws SQLException{
		
		super("Student Information Database");
		
		this.conn = conn;
		this.userName = username;
		
		// Close connections exit the application when the user
	    // closes the window
	    addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	          DBConnection.closeConnection(conn);
	          System.exit(0);
	        }
	    }); 
	    
	    //*************************Initialization****************************/
	    student_course = new JTable(); // Displays the table
        refreshTable ();
        course_satisfaction = new JLabel("  Course Satisfaction :");
        satisfaction = new JTextField(10);
        satisfaction_edit = new JButton("Edit");
        satisfaction_edit.setEnabled(false);
        satisfaction_delete = new JButton("Delete");
        satisfaction_delete.setEnabled(false);
        
        instructor_ranking = new JTable();//Display rank table
        instructorinfo = new JLabel("Instructor Information:");
        instructor_rank = new JLabel("  Instructor Rank: ");
        rank = new JTextField(10);
        
        skill_experience = new JTable();//Display the experience table
        skill_info = new JLabel("Skill Development:");
        experience_before = new JLabel("  Before: ");
        before = new JTextField(10);
        experience_after = new JLabel("  After: ");
        after = new JTextField(10);
        skill_edit = new JButton("Edit");
        skill_edit.setEnabled(false);
        skill_delete = new JButton("Delte");
        skill_delete.setEnabled(false);
        
        topic_experience = new JTable();//Display the topic table
        topic_info = new JLabel("Topic Interest:");
        topic_before = new JLabel("  before: " );
        tbefore = new JTextField(10);
        topic_after = new JLabel("  after: ");
        tafter = new JTextField(10);
        
        button_edit = new JButton("Edit");
        button_edit.setEnabled(false);
        button_delete = new JButton("Delete");
        button_delete.setEnabled(false);
        
        finish = new JButton("Finish");
        exit = new JButton("Exit");
        
        
        //***************************Set up the UI*************************************//
		Container contentPane = getContentPane();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//student_course table
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 7.0;
		c.weighty = 7.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		contentPane.add(new JScrollPane(student_course), c);
		
		//Add actionListener on table
		student_course.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 1){
					//select = true;//enable the text fields and buttons
					satisfaction_edit.setEnabled(true);
					satisfaction_delete.setEnabled(true);
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					//int column = target.getSelectedColumn();
					eid = (int)target.getValueAt(row, 0);
					//show the relevant values in text boxes
					//show satisfaction
					satisfaction.setText(Integer.toString((Integer)target.getValueAt(row, 4)));
					rank.setText(Integer.toString((Integer)target.getValueAt(row, 5)));
					//show instructor_rank table
					refreshIns();
					//show skill table
					refreshSkill();
					refreshTopic();
					
				}
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        contentPane.add(course_satisfaction, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.25;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        contentPane.add(satisfaction, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		contentPane.add(instructor_rank, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 2;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		contentPane.add(rank, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        contentPane.add(satisfaction_edit, c);
        
        //satisfaction edit button
        satisfaction_edit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		//you have to select a specific row before editing the value
        		/*
        		if(select)
        			satisfaction_edit.setEnabled(true);
        		else
        			satisfaction_edit.setEnabled(false);
        		*/
        		//set satisfaction value
        		Course_Experience ce = new Course_Experience();
        		ce.setSat(Integer.parseInt(satisfaction.getText()));
        		ce.setRank(Integer.parseInt(rank.getText()));
        		ce.setEid(eid);
        		ce.setUserid(userName);
        		try{
        			ce.updateSatisfaction(conn);
        			satisfaction.setText("");
        			rank.setText("");
        			refreshTable();
        		}catch(SQLException ex){
            		SQLError.show(ex);
        		}
        	}
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        contentPane.add(satisfaction_delete, c);
        
        satisfaction_delete.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		//you have to select a specific row before editing the value
        		//set satisfaction value
        		Course_Experience ce = new Course_Experience();
        		ce.setSat(0);
        		ce.setRank(0);
        		ce.setEid(eid);
        		ce.setUserid(userName);
        		try{
        			ce.updateSatisfaction(conn);
        			satisfaction.setText("");
        			rank.setText("");
        			refreshTable();
        		}catch(SQLException ex){
            		SQLError.show(ex);
        		}
        	}
        });
        
        //instructor ranking table
        c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 1.0;
		c.weighty = 4.0;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 2;
		contentPane.add(instructorinfo, c);
        
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 4.0;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 2;
		contentPane.add(new JScrollPane(instructor_ranking), c);
		
		/*
		//mouse click event for instructor
		instructor_ranking.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 1){
					rank_edit.setEnabled(true);
					rank_delete.setEnabled(true);
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					instructor_name = (String)target.getValueAt(row, 0);
					//show rank in text field
					rank.setText(Integer.toString((Integer)target.getValueAt(row, 1)));
				}
			}
		});
		*/
		
		
		/*
		c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        contentPane.add(rank_edit, c);
        
        //edit button for instructor ranking
        rank_edit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		Instructor_Ranking ir = new Instructor_Ranking();
        		ir.setRank(Integer.parseInt(rank.getText()));
        		ir.setIns(instructor_name);
        		ir.setEid(eid);
        		ir.setUserid(userName);
        		try{
        			ir.updateRank(conn);
        			rank.setText("");
        			refreshIns();
        		} catch (SQLException ex){
        			SQLError.show(ex);
        		}
        	}
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 9;
        c.gridwidth = 1;
        contentPane.add(rank_delete, c);
        
        rank_delete.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		Instructor_Ranking ir = new Instructor_Ranking();
        		ir.setRank(0);
        		ir.setEid(eid);
        		ir.setIns(instructor_name);
        		ir.setUserid(userName);
        		try{
        			ir.updateRank(conn);
        			rank.setText("");
        			refreshIns();
        		} catch (SQLException ex){
        			SQLError.show(ex);
        		}
        	}
        });
        */
		
		//skill table
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 1.0;
		c.weighty = 4.0;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 2;
		contentPane.add(skill_info, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 2.0;
		c.weighty = 7.0;
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 2;
		contentPane.add(new JScrollPane(skill_experience),c);
		
		skill_experience.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 1){
					skill_edit.setEnabled(true);
					skill_delete.setEnabled(true);
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					skill_name = (String)target.getValueAt(row, 0);
					//eid = (Integer)target.getValueAt(row, 0);
					before.setText(Integer.toString((Integer)target.getValueAt(row, 1)));
					after.setText(Integer.toString((Integer)target.getValueAt(row, 2)));
					refreshSkill();
				}
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 2;
		contentPane.add(experience_before, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 12;
		c.gridwidth = 2;
		contentPane.add(before, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 2;
		contentPane.add(experience_after, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 13;
		c.gridwidth = 2;
		contentPane.add(after, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 14;
        c.gridwidth = 1;
        contentPane.add(skill_edit, c);
        
        skill_edit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		E_Skill es = new E_Skill();
        		es.setSBefore(Integer.parseInt(before.getText()));
        		es.setSAfter(Integer.parseInt(after.getText()));
        		es.setEid(eid);
        		//es.setUserid(userName);
            	try {
            		es.updateSE(conn, skill_name, userName);
            		before.setText("");
            		after.setText("");
            		refreshSkill();
            	}catch(SQLException ex){
            		SQLError.show(ex);
            	}
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 14;
        c.gridwidth = 1;
        contentPane.add(skill_delete, c);
        
        skill_delete.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		E_Skill es = new E_Skill();
        		es.setEid(eid);
        		//es.setUserid(userName);
            	try {
            		es.deleteSE(conn, skill_name, userName);
            		before.setText("");
            		after.setText("");
            		refreshSkill();
            	}catch(SQLException ex){
            		SQLError.show(ex);
            	}
            }
        });
		
		//topic table
        c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 1.0;
		c.weighty = 4.0;
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 2;
		contentPane.add(topic_info, c);
        
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 4.0;
		c.gridx = 0;
		c.gridy = 16;
		c.gridwidth = 2;
		contentPane.add(new JScrollPane(topic_experience), c);
		
		//mouse click event for topic table
		topic_experience.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 1){
					button_edit.setEnabled(true);
					button_delete.setEnabled(true);
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					topic_name = (String)target.getValueAt(row, 0);
					//eid = (Integer)target.getValueAt(row, 0);
					tbefore.setText(Integer.toString((Integer)target.getValueAt(row, 1)));
					tafter.setText(Integer.toString((Integer)target.getValueAt(row, 2)));
					refreshTopic();
				}
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 17;
		c.gridwidth = 2;
		contentPane.add(topic_before, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 17;
		c.gridwidth = 2;
		contentPane.add(tbefore, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 18;
		c.gridwidth = 2;
		contentPane.add(topic_after, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 18;
		c.gridwidth = 2;
		contentPane.add(tafter, c);
		
		//edit button
		c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 19;
        c.gridwidth = 1;
        contentPane.add(button_edit, c);
        
        button_edit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		E_Topic et = new E_Topic();
        		et.setIBefore(Integer.parseInt(tbefore.getText()));
        		et.setIAfter(Integer.parseInt(tafter.getText()));
        		et.setEid(eid);
            	try {
            		et.updateTE(conn, topic_name, userName);
            		tbefore.setText("");
            		tafter.setText("");
            		refreshTopic();
            	}catch(SQLException ex){
            		SQLError.show(ex);
            	}
            }
        });

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 19;
        c.gridwidth = 1;
        contentPane.add(button_delete, c);
        
        button_delete.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		E_Topic et = new E_Topic();
        		et.setEid(eid);
            	try {
            		et.deleteTE(conn, topic_name, userName);
            		tbefore.setText("");
            		tafter.setText("");
            		refreshTopic();
            	}catch(SQLException ex){
            		SQLError.show(ex);
            	}
            }
        });
        
        c.fill = GridBagConstraints.CENTER;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 20;
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
        c.gridy = 20;
        c.gridwidth = 1;
        contentPane.add(exit, c);
        
        exit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.exit(1);
        	}
        });
        
		
	}
	
	//show the student course table
	private void refreshTable () {
		try {    		
    		DefaultTableModel model = new DefaultTableModel(new Object[0][0], student_course_column);
    		java.util.List<Course_ex> getAllCourseEx = Course_Experience.getAllCourseEx(conn,userName);
            for (Course_ex s : getAllCourseEx) {
                 Object[] o = new Object[6];
                 o[0] = s.getEid();
                 o[1] = s.getDeptcode();
                 o[2] = s.getCnum();
                 o[3] = s.getGrade();
                 o[4] = s.getSatisfaction();
                 o[5] = s.getRank();
                 model.addRow(o);
            }
            student_course.setModel(model);
            student_course.repaint();
    	}
    	catch (SQLException ex){
    		SQLError.show(ex);
    	}
	}
	
	//show the instructor ranking table
	private void refreshIns(){
		try{
			DefaultTableModel model = new DefaultTableModel(new Object[0][0],instructor_ranking_column);
			java.util.List<Instructor_Ranking> getAllInsRank = Instructor_Ranking.getAllInsRank(conn,userName,eid);
			for(Instructor_Ranking is : getAllInsRank){
				Object[] o = new Object[1];
				o[0] = is.getInstructor();
				model.addRow(o);
			}
			instructor_ranking.setModel(model);
			instructor_ranking.repaint();
		}catch(SQLException ex){
    		SQLError.show(ex);
		}
	}
	
	//show skill table
	private void refreshSkill(){
		try{
			DefaultTableModel model = new DefaultTableModel(new Object[0][0], skill_experience_column);
			java.util.List<E_Skill_Display> getAllSkillRank = E_Skill.getAllSkillRank(conn, userName, eid);
			for(E_Skill_Display is : getAllSkillRank){
				Object[] o = new Object[3];
				o[0] = is.getSkill();
				o[1] = is.getLevelb();
				o[2] = is.getLevela();
				model.addRow(o);
			}
			skill_experience.setModel(model);
			skill_experience.repaint();
		}catch(SQLException ex){
			SQLError.show(ex);
		}
	}
	
	//show topic table
	private void refreshTopic(){
		try{
			DefaultTableModel model = new DefaultTableModel(new Object[0][0], topic_experience_column);
			java.util.List<E_Topic_Display> getAllTopicRank = E_Topic.getAllTopicRank(conn, userName, eid);
			for(E_Topic_Display is : getAllTopicRank){
				Object[] o = new Object[3];
				o[0] = is.getTopic();
				o[1] = is.getInterestb();
				o[2] = is.getInteresta();
				model.addRow(o);
			}
			topic_experience.setModel(model);
			topic_experience.repaint();
		}catch(SQLException ex){
			SQLError.show(ex);
		}
	}

}
