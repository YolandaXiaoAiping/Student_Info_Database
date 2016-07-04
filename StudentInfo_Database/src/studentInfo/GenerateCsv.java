package studentInfo;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class GenerateCsv {
	Connection connection;
	private static String fileName = "test.csv";
	public static final String PROGRAM_NAME = "GenerateCsv";

	
	//sql query
	private String getStudent = "SELECT userid,country,birth,begin_year,gender FROM"
			+ " student";
	private String getCourse = "SELECT courseed.eid,courseed.deptcode,courseed.cnum,starttime,endtime,enroll_num,"
			+ "courseed.time FROM courseed,course_experience WHERE userid=? AND courseed.eid = course_experience.eid";
	private String getCourse_evaluate = "SELECT grade,satisfaction,rank FROM course_experience"
			+ " WHERE userid=? AND eid=?";
	private String  getInstructor = "SELECT instructor FROM instructor_ranking WHERE"
			+ " eid=?";
	private String getCourse_skill = "SELECT skills.name,level_before,level_after FROM "
			+ "eskill,skills WHERE eid=? AND userid=? AND skills.sid=eskill.sid";
	private String getCourse_topic = "SELECT topic.name,interest_before,interest_after FROM "
			+ "etopic,topic WHERE eid=? AND userid=? AND topic.tid=etopic.tid";
	private String getJob_rank = "SELECT skills.name,job_skill.scale FROM job_skill,skills,student_employment WHERE student_employment.userid=? AND skills.sid=job_skill.sid "
			+ "AND student_employment.title=job_skill.title AND student_employment.company=job_skill.company";
	

	private void generateCsvFile(List<outputAdapter> out_list){
		try{
			FileWriter writer = new FileWriter(fileName);
			
			writer.append("Course number");
			writer.append('\t');
			writer.append("Course start date");
			writer.append('\t');
			writer.append("Course end date");
			writer.append('\t');
			writer.append("Time of day");
			writer.append('\t');
			writer.append("Total number of students");
			writer.append('\t');
			writer.append("Name of the course instructor(s)");
			writer.append('\t');
			writer.append("Student user name");
			writer.append('\t');
			writer.append("Course grade");
			writer.append('\t');
			writer.append("Student age");
			writer.append('\t');
			writer.append("Student year and month of birth");
			writer.append('\t');
			writer.append("Student gender");
			writer.append('\t');
			writer.append("Student country of birth");
			writer.append('\t');
			writer.append("List of skills acquired outside academia");
			writer.append('\t');
			writer.append("Student start date at the University");
			writer.append('\t');
			writer.append("Course satisfaction");
			writer.append('\t');
			writer.append("Instructor ranking");
			writer.append('\t');
			writer.append("List of skills learned in the course");
			writer.append('\t');
			writer.append("List of topics learned in the course");
			writer.append('\n');
			
			for(int i = 0; i < out_list.size();i++){
				outputAdapter oa = out_list.get(i);
				StudentAdapter sa = oa.getStudentAdapter();
				List<outSkillAdapter> osa_list = oa.getoutSkillAdapter();
				List<CourseAdapter> csa_list = oa.getCourseAdapter();
				for(int j = 0;j<csa_list.size();j++){
					CourseAdapter ca = csa_list.get(j);
					writer.append(ca.getDeptcode());
					writer.append(Integer.toString(ca.getCnum()));
					writer.append('\t');
					writer.append(ca.getStarttime());
					writer.append('\t');
					writer.append(ca.getEndtime());
					writer.append('\t');
					writer.append(ca.getTime());
					writer.append('\t');
					//System.out.println(ca.getEnroll_num());
					writer.append(Integer.toString(ca.getEnroll_num()));
					writer.append('\t');
					//show the instructor list
					List<String> instructors = ca.getInstructor();
					for(int k = 0;k< instructors.size();k++){
						writer.append(instructors.get(k));
						if(k!=instructors.size()-1)
							writer.append("|");
					}
					writer.append('\t');
					//student info
					writer.append(sa.getUserid());
					writer.append('\t');
					//course grade
					writer.append(Double.toString(ca.getCourseEvaluateAdapter().getGrade()));
					writer.append('\t');
					writer.append(Integer.toString(calculateAge(sa.getBirth())));//that's the problem
					writer.append('\t');
					writer.append(Birth(sa.getBirth()));//problem
					writer.append('\t');
					writer.append(sa.getGender());
					writer.append('\t');
					writer.append(sa.getCountry());
					writer.append('\t');
					//get skill list
					//List<outSkillAdapter> out_skill = oa.getoutSkillAdapter();
					for(int l = 0;l<osa_list.size();l++){
						outSkillAdapter osa = osa_list.get(l);
						writer.append(osa.getSkill_name());
						writer.append("-");
						writer.append(Integer.toString(osa.getRank()));
						if(l!=osa_list.size()-1)
							writer.append("|");
					}
					writer.append('\t');
					writer.append(sa.getBegin_year());
					writer.append('\t');
					writer.append(Integer.toString(ca.getCourseEvaluateAdapter().getSatisfaction()));
					writer.append('\t');
					writer.append(Integer.toString(ca.getCourseEvaluateAdapter().getInstructor_rank()));
					writer.append('\t');
					//list of skills
					List<CourseSkiLLAdapter> course_skill = ca.getCourseSkiLLAdapter();
					for(int m = 0;m<course_skill.size();m++){
						CourseSkiLLAdapter csa = course_skill.get(m);
						writer.append(csa.getSkill_name());
						writer.append("-");
						writer.append(Integer.toString(csa.getLevel_before()));
						writer.append("-");
						writer.append(Integer.toString(csa.getLevel_after()));
						if(m!=course_skill.size()-1)
							writer.append("|");
					}
					writer.append('\t');
					//topic list
					List<CourseTopicAdapter> course_topic = ca.getCourseTopicAdapter();
					for(int n = 0;n<course_topic.size();n++){
						CourseTopicAdapter cta = course_topic.get(n);
						writer.append(cta.getTopic_name());
						writer.append("-");
						writer.append(Integer.toString(cta.getInterest_before()));
						writer.append("-");
						writer.append(Integer.toString(cta.getInterest_after()));
						if(n!=course_topic.size()-1)
							writer.append("|");
					}
					writer.append('\n');
				}
			}
			
			writer.flush();
			writer.close();
		}catch(Exception e) {
            e.printStackTrace();
      } 
	}
	//get all data
	public List<outputAdapter> getData()throws SQLException{
		
		List<outputAdapter> out_list = new ArrayList<outputAdapter>();
		
		//get the student list first
		List<StudentAdapter> sa_list = new ArrayList<StudentAdapter>();
		Statement stmt = null;
		try{
			stmt = this.connection.createStatement();
	        ResultSet rs = stmt.executeQuery(getStudent);
	        while(rs.next()){
	        	StudentAdapter sa = new StudentAdapter();
	        	sa.setUserid(rs.getString("userid"));
        		//System.out.println(rs.getString("userid"));
	        	sa.setCountry(rs.getString("country"));
	        	sa.setBirth(rs.getString("birth"));
	        	sa.setBegin_year(rs.getString("begin_year"));
	        	sa.setGender(rs.getString("gender"));
	        	sa_list.add(sa);
	        }

		}catch(SQLException e){
			SQLError.show(e);
		}finally {
	        if (stmt != null) { stmt.close(); }
	    }
		
		//get other information
		for(int i = 0; i < sa_list.size(); i++){
			outputAdapter oa = new outputAdapter();
        	StudentAdapter sa = new StudentAdapter();
        	sa = sa_list.get(i);
        	oa.setStudentAdapter(sa);
        	PreparedStatement stmt1 = null;
        	try{
        		//for each student, we get the courses they took
    	    	stmt1 = this.connection.prepareStatement(getCourse);
    	    	stmt1.setString(1, sa.getUserid());//find all courses taken by the student
    	    	ResultSet rs = stmt1.executeQuery();
    	    	List<CourseAdapter> ca_list = new ArrayList<CourseAdapter>();
    	    	while(rs.next()){
    	    		CourseAdapter ca = new CourseAdapter();
    	    		ca.setDeptcode(rs.getString("deptcode"));
            		//System.out.println(rs.getString("deptcode"));
    	    		ca.setCnum(rs.getInt("cnum"));
    	    		ca.setStarttime(rs.getString("starttime"));
    	    		ca.setEndtime(rs.getString("endtime"));
    	    		ca.setEnroll_num(rs.getInt("enroll_num"));
    	        	//System.out.println(rs.getInt("enroll_num"));
    	    		ca.setTime(rs.getString("time"));
    	    		ca.setEid(rs.getInt("eid"));
    	    		//now we need to get each course's evaluate
	    			PreparedStatement stmt2 = null;
    	    		try{
        	        	stmt2 = this.connection.prepareStatement(getCourse_evaluate);
        	        	stmt2.setString(1, sa.getUserid());
        	        	//System.out.println(sa.getUserid());
        	        	stmt2.setInt(2, ca.getEid());
        	        	//System.out.println(Integer.toString(ca.getEid()));
            	    	ResultSet rs1 = stmt2.executeQuery();
        	        	courseEvaluateAdapter cea = new courseEvaluateAdapter();
        	        	if(rs1.next()){
        	        		cea.setGrade(rs1.getDouble("grade"));
        	        		//System.out.println(Double.toString(cea.getGrade()));
        	        		cea.setSatisfaction(rs1.getInt("satisfaction"));
        	        		cea.setInstructor_rank(rs1.getInt("rank"));
        	        	}
        	        	ca.setCourseEvaluateAdapter(cea);//remember to add this back to ca!!
        	        	
    	    		}catch (SQLException e) {
    	    			SQLError.show(e);
    	    		} finally {
    	    			if (stmt2 != null) {
    	    				stmt2.close();
    	    			}
    	    		}
    	    		//then we need to get each course's instructor list
	    			PreparedStatement stmt3 = null;
	    			List<String> instructors = new ArrayList<String>();
	    			try{
        	        	stmt3 = this.connection.prepareStatement(getInstructor);
        	        	stmt3.setInt(1, ca.getEid());
            	    	ResultSet rs2 = stmt3.executeQuery();
            	    	while(rs2.next()){
            	    		instructors.add(rs2.getString("instructor"));
            	    	}
            	    	ca.setInstructor(instructors);//don't forget to put it into ca!!

	    			}catch (SQLException e) {
    	    			SQLError.show(e);
    	    		} finally {
    	    			if (stmt3 != null) {
    	    				stmt3.close();
    	    			}
    	    		}
	    			//then we need to get course_skill list 
	    			PreparedStatement stmt4 = null;
	    			List<CourseSkiLLAdapter> cea_list = new ArrayList<CourseSkiLLAdapter>();
	    			try{
        	        	stmt4= this.connection.prepareStatement(getCourse_skill);
        	        	stmt4.setInt(1, ca.getEid());
        	        	stmt4.setString(2, sa.getUserid());
        	        	ResultSet rs3 = stmt4.executeQuery();
        	        	while(rs3.next()){
        	        		CourseSkiLLAdapter cea = new CourseSkiLLAdapter();
        	        		cea.setSkill_name(rs3.getString("name"));
        	        		cea.setLevel_before(rs3.getInt("level_before"));
        	        		cea.setLevel_after(rs3.getInt("level_after"));
        	        		cea_list.add(cea);
        	        	}
        	        	ca.setCourseSkiLLAdapter(cea_list);

	    			}catch (SQLException e) {
    	    			SQLError.show(e);
    	    		} finally {
    	    			if (stmt4 != null) {
    	    				stmt4.close();
    	    			}
    	    		}
	    			//then we need to get skill_list
	    			PreparedStatement stmt5 = null;
	    			List<CourseTopicAdapter> cta_list = new ArrayList<CourseTopicAdapter>();
	    			try{
        	        	stmt5= this.connection.prepareStatement(getCourse_topic);
        	        	stmt5.setInt(1, ca.getEid());
        	        	stmt5.setString(2, sa.getUserid());
        	        	ResultSet rs4 = stmt5.executeQuery();
        	        	while(rs4.next()){
        	        		CourseTopicAdapter cta = new CourseTopicAdapter();
        	        		cta.setTopic_name(rs4.getString("name"));
        	        		cta.setInterest_before(rs4.getInt("interest_before"));
        	        		cta.setInterest_after(rs4.getInt("interest_after"));
        	        		cta_list.add(cta);
        	        	}
        	        	ca.setCourseTopicAdapter(cta_list);
	    			}catch (SQLException e) {
    	    			SQLError.show(e);
    	    		} finally {
    	    			if (stmt5 != null) {
    	    				stmt5.close();
    	    			}
    	    		} 
	    	    	ca_list.add(ca);
    	    	}
    	    	oa.setCourseAdapter(ca_list);
        	}catch (SQLException e) {
    			SQLError.show(e);
    		} finally {
    			if (stmt1 != null) {
    				stmt1.close();
    			}
    		}
        	
        	//finally we need to get the student's outskill
        	List<outSkillAdapter> osa_list = new ArrayList<outSkillAdapter>();
			PreparedStatement stmt6 = null;
			try{
	        	stmt6= this.connection.prepareStatement(getJob_rank);
	        	stmt6.setString(1, sa.getUserid());
	        	ResultSet rs5 = stmt6.executeQuery();
	        	while(rs5.next()){
	        		outSkillAdapter osa = new outSkillAdapter();
	        		osa.setSkill_name(rs5.getString("name"));
	        		osa.setRank(rs5.getInt("scale"));
	        		osa_list.add(osa);
	        	}
	        oa.setoutSkillAdapter(osa_list);
			}catch (SQLException e) {
    			SQLError.show(e);
    		} finally {
    			if (stmt6 != null) {
    				stmt6.close();
    			}
    		}
			out_list.add(oa);
		}
		return out_list;
		
	}
	
	public int calculateAge(String birth){
		String[] arr = birth.split("-");
		int age = 2016 - Integer.parseInt(arr[0]);
		return age;
	}
	
	public String Birth(String birth){
		String[] arr = birth.split("-");
		String res=arr[0]+" "+arr[1];
		return res;
	}
	
	public GenerateCsv(Connection conn)throws SQLException{
		this.connection = conn;
	}
	
	public static void main(String [] args)throws IOException, SQLException{
		//check program arguments
				if (args.length == 0){
		    		System.out.println("Usage: "+PROGRAM_NAME+" <name of properties file>");
		    		System.exit(1);
		    	}
				//read properties
				Properties props = new Properties();
		    	FileInputStream in = new FileInputStream(args[0]);
		    	props.load(in);
		    	in.close();
		    	
		    	//pre-set connection
		    	java.sql.Connection conn = DBConnection.getConnection (props);	
		    	if (conn == null)
		    		System.exit(1);
		    	GenerateCsv gc =new GenerateCsv(conn);
		    	List<outputAdapter> out = gc.getData();
		    	gc.generateCsvFile(out);
	}
	
}