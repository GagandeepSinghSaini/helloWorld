package com.pattern.builderDesign.ROBOT;

public class Robot implements RobotPlan{
	private String robotHead;
	private String robotArms;
	private String robotLegs;
	@Override
	public void setRobotHead(String head) {
		this.robotHead = head;
	}
	@Override
	public void setRobotArms(String arms) {
		this.robotArms = arms;				
		
	}
	@Override
	public void setRobotLegs(String legs) {
		this.robotLegs = legs;
		
	}
	public String getRobotHead() {
		return robotHead;
	}
	public String getRobotArms() {
		return robotArms;
	}
	public String getRobotLegs() {
		return robotLegs;
	}
	

}
