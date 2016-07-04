package studentInfo;
import java.sql.*;

public class Skills {
	private static final String TABLE_NAME = "skills";
	
	private int sid;
	private String name;
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	
	public String toString () {
		return this.sid +"\t"+this.name;
	}
	
	public int getSid(){
		return this.sid;
	}
	public String getName(){
		return this.name;
	}
	public void setSid(int sid){
		this.sid = sid;
	}
	public void setName(String name){
		this.name = name;
	}
}
