package studentInfo;

public class CourseTopicAdapter {
	private String topic_name;
	 private int interest_before,interest_after;
	 
	 public void setTopic_name(String topic_name){
		 this.topic_name = topic_name;
	 }
	 
	 public void setInterest_before(int interest_before){
		 this.interest_before = interest_before;
	 }
	 
	 public void setInterest_after(int interest_after){
		 this.interest_after = interest_after;
	 }
	 
	 public String getTopic_name(){
		 return this.topic_name;
	 }
	 
	 public int getInterest_before(){
		 return this.interest_before;
	 }
	 
	 public int getInterest_after(){
		 return this.interest_after;
	 }
}
