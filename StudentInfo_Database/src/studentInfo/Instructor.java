package studentInfo;
import java.sql.*;

public class Instructor {
	private static final String TABLE_NAME = "instructor";
	
	private String name, gender, type, area;
	private int age;
	
	public static void print (Connection connect)throws SQLException{
		PrintTable.print(connect, TABLE_NAME);
	}
	
	/*
	private boolean validate(){
		if(this.name == null)
			return false;
		return true;
	}
	*/
	
	//get all the info from table instructor
	public String getName(){
		return this.name;
	}
	
	public String getGender(){
		return this.gender;
	}
	
	public int getAge(){
		return this.age;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getArea(){
		return this.area;
	}
}
