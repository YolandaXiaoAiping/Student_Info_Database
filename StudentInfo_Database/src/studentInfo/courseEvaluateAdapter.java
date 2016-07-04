package studentInfo;

public class courseEvaluateAdapter {
	private int satisfaction,instructor_rank;
	private double grade;
	
	public void setGrade(double grade){
		this.grade = grade;
	}
	
	public void setSatisfaction(int satisfaction){
		this.satisfaction = satisfaction;
	}
	
	public void setInstructor_rank(int instructor_rank){
		this.instructor_rank = instructor_rank;
	}
	
	public double getGrade(){
		return this.grade;
	}
	
	public int getSatisfaction(){
		return this.satisfaction;
	}
	
	public int getInstructor_rank(){
		return this.instructor_rank;
	}

}
