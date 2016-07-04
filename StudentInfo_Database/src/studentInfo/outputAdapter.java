package studentInfo;

import java.util.List;
import studentInfo.StudentAdapter;

public class outputAdapter {
	private List<CourseAdapter> courseAdapter_list;
	private StudentAdapter student_adapter;
	private List<outSkillAdapter> outSkillAdapter_list;
	
	public void setCourseAdapter(List<CourseAdapter> course_adapter){
		this.courseAdapter_list = course_adapter;
	}
	
	public void setStudentAdapter(StudentAdapter student_adapter){
		this.student_adapter = student_adapter;
	}

	
	public void setoutSkillAdapter(List<outSkillAdapter> outSkillAdapter_list){
		this.outSkillAdapter_list = outSkillAdapter_list;
	}

	public List<CourseAdapter> getCourseAdapter(){
		 return this.courseAdapter_list;
	}
	
	public StudentAdapter getStudentAdapter(){
		return this.student_adapter;
	}

	
	public List<outSkillAdapter> getoutSkillAdapter(){
		return this.outSkillAdapter_list;
	}

	
	
	
	
}
