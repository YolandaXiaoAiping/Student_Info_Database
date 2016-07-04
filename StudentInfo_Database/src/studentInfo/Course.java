package studentInfo;
import java.sql.*;

public class Course {
	private static final String TABLE_NAME = "course";
	
	private String deptcode;
	private int cnum;
	private String name;
	private String area;
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	
	public String getDept(){
		return this.deptcode;
	}
	
	public int getCnum(){
		return this.cnum;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getArea(){
		return this.area;
	}

	public void setDept(String deptcode){
		this.deptcode = deptcode;
	}
	
	public void setCnum(int cnum){
		this.cnum = cnum;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setArea(String area){
		this.area = area;
	}
}
