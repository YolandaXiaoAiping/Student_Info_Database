package studentInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class E_Topic {
	private static final String TABLE_NAME = "etopic";
	
	private int eid, tid, interest_before, interest_after;
	
	private String userid;
	
	private String updateT = "UPDATE etopic SET interest_before=?, interest_after=? "
			+ "WHERE eid=? AND userid=? AND tid=?";
	private static String select = "SElECT topic.name, interest_before, interest_after FROM etopic, topic "
			+ "WHERE etopic.tid = topic.tid AND userid=? AND eid=?";
	private static String select_tid = "SELECT tid FROM topic WHERE name=?";
	private String deleteT = "UPDATE etopic SET interest_before=NULL, interest_after=NULL "
			+ "WHERE eid=? AND userid=? AND tid=?";
	private static String select_course = "SELECT courseed.deptcode, courseed.cnum, tid FROM courseed, course_topic "
			+ "WHERE eid=? AND courseed.deptcode = course_topic.deptcode AND courseed.cnum = course_topic.cnum";
	private static String addet = "INSERT INTO etopic VALUES(?,?,?,?,?,NULL,NULL)";
	PreparedStatement stmt = null;
	
	public static void print(Connection con)throws SQLException{
		PrintTable.print(con, TABLE_NAME);
	}
	
	public static List <E_Topic_Display> getAllTopicRank(Connection con, String userName, int eid)throws SQLException{
		List<E_Topic_Display> e_topic = new ArrayList <E_Topic_Display>();
		PreparedStatement stmt = null;
		try{
			stmt = con.prepareStatement(select);
			stmt.setString(1, userName);
			stmt.setInt(2, eid);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				E_Topic_Display s = new E_Topic_Display();
				s.setTopic(rs.getString("name"));
				s.setInterest_b(rs.getInt("interest_before"));
				s.setInterest_a(rs.getInt("interest_after"));
				e_topic.add(s);
			}
		}catch (SQLException ex){
			SQLError.show(ex);
		}finally{
			if(stmt != null) stmt.close();
		}
		return e_topic;
	}
	
	public void updateTE(Connection con, String topic_name, String userid)throws SQLException{
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			stmt1 = con.prepareStatement(select_tid);
			stmt1.setString(1, topic_name);
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			stmt2 = con.prepareStatement(updateT);
			stmt2.setInt(1, this.interest_before);
			stmt2.setInt(2, this.interest_after);
			stmt2.setInt(3, this.eid);
			stmt2.setString(4,userid);
			stmt2.setInt(5, rs.getInt("tid"));
			stmt2.execute();
		} catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt1 != null) stmt1.close();
			if(stmt2 != null) stmt2.close();
		}
	}
	
	public void deleteTE(Connection con, String topic_name, String userid)throws SQLException{
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			
			stmt1 = con.prepareStatement(select_tid);
			stmt1.setString(1, topic_name);
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			stmt2 = con.prepareStatement(deleteT);
			stmt2.setInt(1, this.eid);
			stmt2.setString(2, userid);
			stmt2.setInt(3, rs.getInt("tid"));
			stmt2.execute();
		} catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt1 != null) stmt1.close();
			if(stmt2 != null) stmt2.close();
		}
	}
	
	public void addTE(Connection con)throws SQLException{
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			stmt1 = con.prepareStatement(select_course);
			stmt1.setInt(1, this.eid);
			ResultSet rs = stmt1.executeQuery();
			while(rs.next()){
				stmt2 = con.prepareStatement(addet);
				stmt2.setInt(1,this.eid);
				stmt2.setString(2,this.userid);
				stmt2.setInt(3,rs.getInt("tid"));
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
	
	public void setIBefore(int interest_before){
		this.interest_before = interest_before;
	}
	
	public void setIAfter(int interest_after){
		this.interest_after = interest_after;
	}
	
	public void setEid(int eid){
		this.eid = eid;
	}
	
	public int getEid(){
		return this.eid;
	}
	
	public int getTid(){
		return this.tid;
	}
	
	public String getUserid(){
		return this.userid;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}
	
}
