package studentInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;



public class Course_Experience {
	private static final String TABLE_NAME = "course_experience";
	
	private String userid;
	private int eid;
	private double grade;
	private int satisfaction, rank;
	
	private String alterSat = "UPDATE course_experience SET satisfaction=?, rank=? WHERE eid=? AND userid=?";
	private String deleteSat = "UPDATE course_experience SET satisfaction = NULL, rank=NULL WHERE eid = ? AND userid = ?";
	private static String SelectCourse = "SELECT courseed.eid, grade, satisfaction, courseed.deptcode, courseed.cnum, rank "
									+"FROM course_experience, courseed "
									+"WHERE userid = ? AND courseed.eid = course_experience.eid";
	
	private static String SelectSCourse = "SELECT courseed.eid, courseed.deptcode, courseed.cnum, grade"
			+" FROM course_experience, courseed "
			+"WHERE userid = ? AND courseed.eid = course_experience.eid";
	private String addCE = "INSERT INTO course_experience VALUES(?,?,?,NULL,NULL)";
	private String deleteCE = "DELETE FROM course_experience WHERE eid=? AND userid=?";
	PreparedStatement stmt = null;
	
	public static void print(Connection con)throws SQLException{
		PrintTable.print(con, TABLE_NAME);
	}
	
	// show the student course
	public static List <Course_ex> getAllCourseEx (Connection conn,String userName) throws SQLException {
		List<Course_ex> course_ex = new ArrayList <Course_ex>();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(SelectCourse);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	Course_ex s = new Course_ex();
	        	s.setEid(rs.getInt("eid"));
	        	s.setDeptcode(rs.getString("deptcode"));
	        	s.setGrade(rs.getDouble("grade"));
	        	s.setSatisfaction(rs.getInt("satisfaction"));
	        	s.setCnum(rs.getInt("cnum"));
	        	s.setRank(rs.getInt("rank"));
	        	course_ex.add(s);
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	       // JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
		return course_ex;
	}
	
	public static List <Course_ex> getAllCourseSEx (Connection conn,String userName) throws SQLException {
		List<Course_ex> course_sex = new ArrayList <Course_ex>();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(SelectSCourse);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	Course_ex s = new Course_ex();
	        	s.setEid(rs.getInt("eid"));
	        	s.setDeptcode(rs.getString("deptcode"));
	        	s.setGrade(rs.getDouble("grade"));
	        	s.setCnum(rs.getInt("cnum"));
	        	course_sex.add(s);
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	       // JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
		return course_sex;
	}
	
	public void addCourseExperience(Connection con)throws SQLException{
		try{
			stmt = con.prepareStatement(addCE);
			stmt.setInt(1, this.eid);
			stmt.setString(2, this.userid);
			stmt.setDouble(3, grade);
			stmt.execute();
		} catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public void deleteCourseExperience(Connection con)throws SQLException{
		try{
			stmt = con.prepareStatement(deleteCE);
			stmt.setInt(1, this.eid);
			stmt.setString(2, this.userid);
			stmt.execute();
		} catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public void updateSatisfaction(Connection con)throws SQLException{
		try{
			if(this.satisfaction==0){
				stmt = con.prepareStatement(deleteSat);
				stmt.setInt(1, this.eid);
				stmt.setString(2, this.userid);
				stmt.execute();
			}
			else{
				stmt = con.prepareStatement(alterSat);
				stmt.setInt(1, this.satisfaction);
				stmt.setInt(2, this.rank);
				stmt.setInt(3, this.eid);
				stmt.setString(4, this.userid);
				stmt.execute();
			}
			
		} catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public void setSat(int satisfaction){
		this.satisfaction = satisfaction;
	}
	public void setEid(int eid){
		this.eid = eid;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public void setGrade(double grade){
		this.grade = grade;
	}
	
	public void setRank(int rank){
		this.rank = rank;
	}
	
	public int getRank(){
		return this.rank;
	}
	
	public int getEid(){
		return this.eid;
	}
	
	public String getUserid(){
		return this.userid;
	}
	public int getSat(){
		return this.satisfaction;
	}
	public double getGrade(){
		return this.grade;
	}
	
}
