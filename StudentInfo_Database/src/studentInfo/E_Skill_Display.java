package studentInfo;

public class E_Skill_Display {
	private String skill;
	private int level_before, level_after;
	
	public void setTopic(String skill){
		this.skill = skill;
	}
	
	public void setLevel_b(int level_before){
		this.level_before = level_before;
	}
	
	public void setLevel_a(int level_after){
		this.level_after = level_after;
	}
	
	public String getSkill(){
		return this.skill;
	}
	
	public int getLevelb(){
		return this.level_before;
	}
	
	public int getLevela(){
		return this.level_after;
	}
}
