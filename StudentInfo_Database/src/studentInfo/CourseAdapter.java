package studentInfo;

import java.util.List;


public class CourseAdapter {
	private String deptcode,starttime,endtime,time;
	private int cnum,enroll_num,eid;
	private courseEvaluateAdapter evaluate_adapter;
	private List<CourseSkiLLAdapter> courseSkillAdapter_list;
	private List<CourseTopicAdapter> courseTopicAdapter_list;
	private List<String> instructors;

	
	public String getDeptcode(){
		return this.deptcode;
	}
	
	public String getStarttime(){
		return this.starttime;
	}
	
	public String getEndtime(){
		return this.endtime;
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
	
	public int getEnroll_num(){
		return this.enroll_num;
	}
	
	public courseEvaluateAdapter getCourseEvaluateAdapter(){
		return this.evaluate_adapter;
	}
	
	public List<CourseSkiLLAdapter> getCourseSkiLLAdapter(){
		return this.courseSkillAdapter_list;
	}
	
	public List<CourseTopicAdapter> getCourseTopicAdapter(){
		return this.courseTopicAdapter_list;
	}
	
	public List<String> getInstructor(){
		return this.instructors;
	}
	
	public void setDeptcode(String deptcode){
		this.deptcode = deptcode;
	}
	
	public void setStarttime(String starttime){
		this.starttime = starttime;
	}
	
	public void setEndtime(String endtime){
		this.endtime = endtime;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public void setCnum(int cnum){
		this.cnum = cnum;
	}
	
	public void setEnroll_num(int Enroll_num){
		this.enroll_num = Enroll_num;
	}
	
	public void setCourseEvaluateAdapter(courseEvaluateAdapter evaluate_adapter){
		this.evaluate_adapter = evaluate_adapter;
	}
	
	public void setCourseSkiLLAdapter(List<CourseSkiLLAdapter> courseSkillAdapter_list){
		this.courseSkillAdapter_list = courseSkillAdapter_list;
	}
	

	public void setCourseTopicAdapter(List<CourseTopicAdapter> courseTopicAdapter_list){
		this.courseTopicAdapter_list = courseTopicAdapter_list;
	}
	
	
	public void setInstructor(List<String> instructors){
		this.instructors = instructors;
	}
	
	public void setEid(int eid){
		this.eid = eid;
	}
	
	
}
