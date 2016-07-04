package studentInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Main {
	public static final String PROGRAM_NAME = "Main Function";
	//private static boolean login = false;
	public static void main(String [] args) throws IOException, SQLException {
		if (args.length == 0){
    		System.out.println("Usage: "+PROGRAM_NAME+" <name of properties file>");
    		System.exit(1);
    	}
		
		String setpath = "SET search_path TO a1";
		
		Properties props = new Properties();
    	FileInputStream in = new FileInputStream(args[0]);
    	props.load(in);
    	in.close();
    	java.sql.Connection conn = DBConnection.getConnection (props);	
		
		if(args[0].equals("properties_postgre.txt")){
			Statement stmt = null;
	    	stmt = conn.createStatement();
	    	stmt.execute(setpath);
		}
		else if(args[0].equals("properties_sqlite.txt")){
			conn.createStatement().execute("PRAGMA foreign_keys = ON");
		}
		
		
		//read properties
		
    	
    	//pre-set connection
    	
    	/*
    	String setpath = "SET search_path TO a1";
    	Statement stmt = null;
    	stmt = conn.createStatement();
    	stmt.execute(setpath);
    	*/
    	
    	if (conn == null)
    		System.exit(1);
    	
    	Login_Interface app = new Login_Interface(conn);
    	app.setSize(300, 500);
        app.setVisible(true);
        
	}
		
}