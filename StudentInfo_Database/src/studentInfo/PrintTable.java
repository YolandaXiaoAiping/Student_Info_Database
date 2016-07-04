package studentInfo;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class PrintTable {
	public static final String PROGRAM_NAME = "PrintTable";
	public static void print (Connection conn, String tblName) throws SQLException {
		Statement stmt = null;
	    String query = "select * FROM " + tblName;
	   
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        int cols = rs.getMetaData().getColumnCount();
	        while (rs.next()) {
	        	for (int i=0; i< cols; i++)
	        		System.out.print (rs.getObject(i+1) + "\t");
	        	System.out.print("\n");	           
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	       // JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public static void main (String [] args) throws IOException, SQLException {
		if (args.length == 0){
    		System.out.println("Usage: "+PROGRAM_NAME+" <name of properties file>");
    		System.exit(1);
    	}
		Properties props = new Properties();
    	FileInputStream in = new FileInputStream(args[0]);
    	props.load(in);
    	in.close();
    	
    	java.sql.Connection conn = DBConnection.getConnection (props);
    	
    	PrintTable.print(conn, "Person");		
	}
}