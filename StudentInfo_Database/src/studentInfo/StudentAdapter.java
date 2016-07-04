package studentInfo;

public class StudentAdapter {
	private String userid,country,birth,begin_year,gender;
	
	public String getUserid(){
		return this.userid;
	}
	
	public String getCountry(){
		return this.country;
	}
	
	public String getBirth(){
		return this.birth;
	}
	
	public String getBegin_year(){
		return this.begin_year;
	}
	
	public String getGender(){
		return this.gender;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public void setBirth(String birth){
		this.birth = birth;
	}
	
	public void setBegin_year(String begin_year){
		this.begin_year = begin_year;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
}
