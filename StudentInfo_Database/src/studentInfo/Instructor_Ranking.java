package studentInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Instructor_Ranking {
	private static final String TABLE_NAME = "instructor_ranking";
	
	private int eid, rank;
	private String userid, instructor;
	
	PreparedStatement stmt = null;
	/*
	private String addRank = "UPDATE instructor_ranking SET rank=? "
			+ "WHERE eid=? AND userid=? AND instructor=?";
	private String deleteRank = "UPDATE instructor_ranking SET rank = NULL "
			+ "WHERE eid=? AND userid=? AND instructor=?";
	*/
	private static String select = "SELECT instructor FROM instructor_ranking WHERE eid=?";
	
	public static void print(Connection con)throws SQLException{
		PrintTable.print(con, TABLE_NAME);
	}
	
	// show the student course
	public static List <Instructor_Ranking> getAllInsRank (Connection conn,String userName,int eid) throws SQLException {
		List<Instructor_Ranking> ins_rank = new ArrayList <Instructor_Ranking>();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(select);
			stmt.setInt(1, eid);
			//stmt.setString(2, userName);
			ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	Instructor_Ranking s = new Instructor_Ranking();
	        	s.setIns(rs.getString("instructor"));
	        	ins_rank.add(s);
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	       // JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
		return ins_rank;
	}
	
	/*
	public void updateRank(Connection con)throws SQLException{
		try{
			if(this.rank == 0){
				stmt = con.prepareStatement(deleteRank);
				stmt.setInt(1, this.eid);
				stmt.setString(2, this.userid);
				stmt.setString(3, this.instructor);
				stmt.execute();
			}
			else{
				stmt = con.prepareStatement(addRank);
				stmt.setInt(1, this.rank);
				stmt.setInt(2, this.eid);
				stmt.setString(3, this.userid);
				stmt.setString(4, this.instructor);
				stmt.execute();
			}
		} catch(SQLException e){
			SQLError.show(e);
		} finally{
			if(stmt != null)
				stmt.close();
		}
	}
	*/
	
	public void setIns(String instructor){
		this.instructor = instructor;
	}
	
	public void setEid(int eid){
		this.eid = eid;
	}
	
	
	public int getEid(){
		return this.eid;
	}
	
	public String getInstructor(){
		return this.instructor;
	}
}
