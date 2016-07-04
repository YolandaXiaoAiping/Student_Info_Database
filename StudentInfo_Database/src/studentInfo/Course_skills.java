package studentInfo;
import java.sql.*;

public class Course_skills {
	private static final String TABLE_NAME = "course_skills";
	
	private String deptcode;
	private int cnum,sid;
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	public String toString () {
		return this.deptcode +"\t"+this.cnum +"\t"+ this.sid;
	}
	
	public void setCnum(int cnum){
		this.cnum = cnum;
	}
	
	public void setDept(String deptcode){
		this.deptcode = deptcode;
	}
	
	public void setSid(int sid){
		this.sid = sid;
	}
	
	public int getCnum(){
		return this.cnum;
	}
	
	public int getSid(){
		return this.sid;
	}
	
	public String getDept(){
		return this.deptcode;
	}

}
