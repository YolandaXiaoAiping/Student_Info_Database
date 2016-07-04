package studentInfo;

public class Course_ex {
	private int eid,satifaction,cnum,rank;
	private double grade;
	private String deptcode;
	
	public void setEid(int eid){
		this.eid = eid;
	}
	
	public void setSatisfaction(int satisfaction){
		this.satifaction = satisfaction;
	}
	
	public void setCnum(int cnum){
		this.cnum = cnum;
	}
	
	public void setGrade(double grade){
		this.grade = grade;
	}
	
	public void setDeptcode(String deptcode){
		this.deptcode = deptcode;
	}
	
	public void setRank(int rank){
		this.rank = rank;
	}
	
	public int getRank(){
		return this.rank;
	}
	
	public int getEid(){
		return this.eid;
	}
	
	public int getSatisfaction(){
		return this.satifaction;
	}
	
	public int getCnum(){
		return this.cnum;
	}
	
	public double getGrade(){
		return this.grade;
	}
	
	public String getDeptcode(){
		return this.deptcode;
	}
}
