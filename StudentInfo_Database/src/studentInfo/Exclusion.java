package studentInfo;
import java.sql.*;

public class Exclusion {
	private static final String TABLE_NAME = "Exclusion";
	
	private String deptcode,exDept;
	private int cnum,exCnum;
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	public String toString () {
		return this.deptcode +"\t"+this.cnum +"\t"+ this.exDept +"\t"+ this.exCnum;
	}
	
	public void setCnum(int cnum){
		this.cnum = cnum;
	}
	
	public void setDept(String deptcode){
		this.deptcode = deptcode;
	}
	
	public void setExcnum(int exCnum){
		this.exCnum = exCnum;
	}
	
	public void setExdept(String exDept){
		this.exDept = exDept;
	}
	
	public int getCnum(){
		return this.cnum;
	}
	
	public int getExcnum(){
		return this.exCnum;
	}
	
	public String getDept(){
		return this.deptcode;
	}
	
	public String getExdept(){
		return this.exDept;
	}
}
