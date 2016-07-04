package studentInfo;

public class E_Topic_Display {
	private String topic;
	private int interest_before, interest_after;
	
	public void setTopic(String topic){
		this.topic = topic;
	}
	
	public void setInterest_b(int interest_before){
		this.interest_before = interest_before;
	}
	
	public void setInterest_a(int interest_after){
		this.interest_after = interest_after;
	}
	
	public String getTopic(){
		return this.topic;
	}
	
	public int getInterestb(){
		return this.interest_before;
	}
	
	public int getInteresta(){
		return this.interest_after;
	}
}
