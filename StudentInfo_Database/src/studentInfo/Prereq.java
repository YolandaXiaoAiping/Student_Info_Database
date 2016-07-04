package studentInfo;
import java.sql.*;

public class Prereq {
	private static final String TABLE_NAME = "prereq";
	
	private int cnum, preCnum;
	private String deptcode, preDept;
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	public String toString () {
		return this.deptcode +"\t"+this.cnum +"\t"+ this.preDept +"\t"+ this.preCnum;
	}
	
	public void setCnum(int cnum){
		this.cnum = cnum;
	}
	
	public void setDept(String deptcode){
		this.deptcode = deptcode;
	}
	
	public void setPrecnum(int preCnum){
		this.preCnum = preCnum;
	}
	
	public void setPredept(String preDept){
		this.preDept = preDept;
	}
	
	public int getCnum(){
		return this.cnum;
	}
	
	public int getPrecnum(){
		return this.preCnum;
	}
	
	public String getDept(){
		return this.deptcode;
	}
	
	public String getPredept(){
		return this.preDept;
	}
	

}
