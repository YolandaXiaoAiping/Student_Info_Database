package studentInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
	private static final String TABLE_NAME = "student";
	
	private String userid, country, name, birth, begin_year, gender;
	private String check = "SELECT name FROM student WHERE userid=?";
	private static String SelectS = "SELECT userid, name, gender FROM student";
	
	PreparedStatement stmt = null;
	
	public static void print(Connection connect)throws SQLException{
		PrintTable.print(connect, TABLE_NAME);
	}
	
	public boolean checkUserid(Connection conn)throws SQLException{
		ResultSet rs = null;
		boolean res = false;
		try{
			stmt = conn.prepareStatement(check);
			stmt.setString(1, this.userid);
			rs = stmt.executeQuery();
			if(!rs.next()) res = false;
			else res = true;
		} catch (SQLException e){
			SQLError.show(e);
		} finally{
			stmt.close();
		}
		return res;
	}
	
	public static List <Student> getAllStudent (Connection conn) throws SQLException {
		List<Student> student_list = new ArrayList <Student>();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(SelectS);
			ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	Student s = new Student();
	        	s.setUserid(rs.getString("userid"));
	        	s.setName(rs.getString("name"));
	        	s.setGender(rs.getString("gender"));
	        	student_list.add(s);
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	       // JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
		return student_list;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getUserid(){
		return this.userid;
	}
	
	public String getCountry(){
		return this.country;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getBirth(){
		return this.birth;
	}
	
	public String getBegin_year(){
		return this.begin_year;
	}
	
	public String getGender(){
		return this.gender;
	}
	
}
