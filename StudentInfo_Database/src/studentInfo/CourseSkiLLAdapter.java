package studentInfo;

public class CourseSkiLLAdapter {
	 private String skill_name;
	 private int level_before,level_after;
	 
	 public void setSkill_name(String skill_name){
		 this.skill_name = skill_name;
	 }
	 
	 public void setLevel_before(int level_before){
		 this.level_before = level_before;
	 }
	 
	 public void setLevel_after(int level_after){
		 this.level_after = level_after;
	 }
	 
	 public String getSkill_name(){
		 return this.skill_name;
	 }
	 
	 public int getLevel_before(){
		 return this.level_before;
	 }
	 
	 public int getLevel_after(){
		 return this.level_after;
	 }
}
