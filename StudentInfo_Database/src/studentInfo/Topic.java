package studentInfo;
import java.sql.*;

public class Topic {
	private static final String TABLE_NAME = "topic";
	
	private int tid;
	private String name;
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	
	public String toString () {
		return this.tid +"\t"+this.name;
	}
	
	public int getTid(){
		return this.tid;
	}
	public String getName(){
		return this.name;
	}
	public void setTid(int tid){
		this.tid = tid;
	}
	public void setName(String name){
		this.name = name;
	}

}
