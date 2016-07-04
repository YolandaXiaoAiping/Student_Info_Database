package studentInfo;
import java.sql.*;

public class Job_Skill {
	private static final String TABLE_NAME = "job_skill";
	
	private String title, company;
	private int sid, scale;
	
	public static void print(Connection con)throws SQLException{
		PrintTable.print(con, TABLE_NAME);
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getCompany(){
		return this.company;
	}
	
	public int getSid(){
		return this.sid;
	}
	
	public int getScale(){
		return this.scale;
	}
}
