package studentInfo;
import java.sql.*;

public class Department {
	private static final String TABLE_NAME = "department";
	
	private String deptcode;
	private String name;
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	
	public String getDept(){
		return this.deptcode;
	}
	public String getName(){
		return this.name;
	}
	public void setDept(String deptcode){
		this.deptcode = deptcode;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String toString () {
		return this.deptcode +"\t"+this.name;
	}

}
