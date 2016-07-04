package studentInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Courseed {
	private static final String TABLE_NAME = "courseed";
	
	private int eid,cnum,enroll_num;
	private String deptcode,time,starttime, endtime;
	
	private static String SelectEd = "SELECT eid, deptcode, cNum, starttime, endtime FROM courseed";
	
	public static void PrintTable(Connection con)throws SQLException{
		PrintTable.print(con,TABLE_NAME);
	}
	
	public String toString () {
		return this.deptcode +"\t"+this.cnum +"\t"+ this.starttime +"\t"+ this.endtime +"\t"+ this.enroll_num +"\t"+ this.time;
	}
	
	public static List <Courseed> getAllCourseEd (Connection conn) throws SQLException {
		List<Courseed> course_ed = new ArrayList <Courseed>();
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(SelectEd);
			ResultSet rs = stmt.executeQuery();
			//DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	        
	        while (rs.next()) {
	        	Courseed s = new Courseed();
	        	s.setEid(rs.getInt("eid"));
	        	s.setDept(rs.getString("deptcode"));
	        	s.setcNum(rs.getInt("cnum"));
        		s.setEnd(rs.getString("endtime"));
        		s.setStart(rs.getString("starttime"));
        		course_ed.add(s);
	        }
	    } catch (SQLException e ) {
	    	SQLError.show(e);
	       // JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
		return course_ed;
	}
	
	
	public String getDept(){
		return this.deptcode;
	}
	public String getTime(){
		return this.time;
	}
	public int getEid(){
		return this.eid;
	}
	public int getCnum(){
		return this.cnum;
	}
	public int getEnroll(){
		return this.enroll_num;
	}
	public String getStart(){
		return this.starttime;
	}
	public String getEnd(){
		return this.endtime;
	}
	
	public void setDept(String deptcode){
		this.deptcode = deptcode;
	}
	
	public void setEid(int eid){
		this.eid = eid;
	}
	
	public void setcNum(int cNum){
		this.cnum = cNum;
	}
	
	public void setStart(String starttime){
		this.starttime = starttime;
	}
	
	public void setEnd(String endtime){
		this.endtime = endtime;
	}
	

}
