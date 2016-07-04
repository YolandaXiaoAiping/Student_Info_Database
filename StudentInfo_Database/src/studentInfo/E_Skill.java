package studentInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class E_Skill {
	private static final String TABLE_NAME = "eskill";
	
	private int eid, sid, level_before, level_after;
	private String userid;
	
	private String updateS = "UPDATE eskill SET level_before=?, level_after=? "
			+ "WHERE eid=? AND userid=? AND sid=?";
	private static String select = "SElECT skills.name, level_before, level_after FROM eskill, skills "
			+ "WHERE skills.sid = eskill.sid AND userid=? AND eid=?";
	private static String select_sid = "SELECT sid FROM skills WHERE name=?";
	private String deleteS = "UPDATE eskill SET level_before=NULL, level_after=NULL "
			+ "WHERE eid=? AND userid=? AND sid=?";
	private static String addes = "INSERT INTO eskill VALUES(?,?,?,?,?,NULL,NULL)";
	private static String select_course = "SELECT courseed.deptcode, courseed.cnum, sid FROM courseed, course_skills "
			+ "WHERE eid=? AND courseed.deptcode = course_skills.deptcode AND courseed.cnum = course_skills.cnum";
	PreparedStatement stmt = null;
	
	public static void print(Connection con)throws SQLException{
		PrintTable.print(con, TABLE_NAME);
	}
	
	public static List <E_Skill_Display> getAllSkillRank(Connection con, String userName, int eid)throws SQLException{
		List<E_Skill_Display> e_skill = new ArrayList <E_Skill_Display>();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(select);
			stmt.setString(1, userName);
			stmt.setInt(2, eid);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				E_Skill_Display s = new E_Skill_Display();
				s.setTopic(rs.getString("name"));
				s.setLevel_b(rs.getInt("level_before"));
				s.setLevel_a(rs.getInt("level_after"));
				e_skill.add(s);
			}
		}catch (SQLException ex){
			SQLError.show(ex);
		}finally{
			if(stmt != null) stmt.close();
		}
		return e_skill;
	}
	
	public void updateSE(Connection con, String skill_name, String userid)throws SQLException{
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			stmt1 = con.prepareStatement(select_sid);
			stmt1.setString(1, skill_name);
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			stmt2 = con.prepareStatement(updateS);
			stmt2.setInt(1, this.level_before);
			stmt2.setInt(2, this.level_after);
			stmt2.setInt(3, this.eid);
			stmt2.setString(4,userid);
			stmt2.setInt(5, rs.getInt("sid"));
			stmt2.execute();
		
		} catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt1 != null) stmt2.close();
			if(stmt2 != null) stmt2.close();
		}
	}
	
	public void deleteSE(Connection con, String skill_name, String userid)throws SQLException{
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			stmt1 = con.prepareStatement(select_sid);
			stmt1.setString(1, skill_name);
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			stmt2 = con.prepareStatement(deleteS);
			stmt2.setInt(1, this.eid);
			stmt2.setString(2,userid);
			stmt2.setInt(3, rs.getInt("sid"));
			stmt2.execute();
		} catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt1 != null) stmt1.close();
			if(stmt2 != null) stmt2.close();
		}
	}
	
	public void addSE(Connection con)throws SQLException{
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			stmt1 = con.prepareStatement(select_course);
			stmt1.setInt(1, this.eid);
			ResultSet rs = stmt1.executeQuery();
			while(rs.next()){
				stmt2 = con.prepareStatement(addes);
				stmt2.setInt(1,this.eid);
				stmt2.setString(2,this.userid);
				stmt2.setInt(3,rs.getInt("sid"));
				stmt2.setString(4, rs.getString("deptcode"));
				stmt2.setInt(5, rs.getInt("cnum"));
				stmt2.execute();
			}
		}catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt1 != null) stmt1.close();
			if(stmt2 != null) stmt2.close();
		}
	}
	
	public void setSBefore(int level_before){
		this.level_before = level_before;
	}
	
	public void setSAfter(int level_after){
		this.level_after = level_after;
	}
	
	public void setEid(int eid){
		this.eid = eid;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public int getEid(){
		return this.eid;
	}
	
	public int getSid(){
		return this.sid;
	}
	
	public String getUserid(){
		return this.userid;
	}
	
}
