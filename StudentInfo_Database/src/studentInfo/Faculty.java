package studentInfo;
import java.sql.*;

public class Faculty {
	private static final String TABLE_NAME = "faculty";
	
	private String name, type;
	private int start_year;
	
	public static void print(Connection connect)throws SQLException{
		PrintTable.print(connect, TABLE_NAME);
	}
	
	//get all info from table faculty
	public String getName(){
		return this.name;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getStart_year(){
		return this.start_year;
	}
}
