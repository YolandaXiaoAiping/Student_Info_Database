package studentInfo;
import java.sql.*;

public class Course_topic {
	private static final String TABLE_NAME = "course_topic";
	
	private String deptcode;
	private int cnum,tid;
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	public String toString () {
		return this.deptcode +"\t"+this.cnum +"\t"+ this.tid;
	}
	
	public void setCnum(int cnum){
		this.cnum = cnum;
	}
	
	public void setDept(String deptcode){
		this.deptcode = deptcode;
	}
	
	public void setTid(int tid){
		this.tid = tid;
	}
	
	public int getCnum(){
		return this.cnum;
	}
	
	public int getTid(){
		return this.tid;
	}
	
	public String getDept(){
		return this.deptcode;
	}

}
