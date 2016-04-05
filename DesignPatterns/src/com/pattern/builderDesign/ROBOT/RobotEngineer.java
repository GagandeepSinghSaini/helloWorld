package com.pattern.builderDesign.ROBOT;

public class RobotEngineer {

	private RobotBuilder robotBuilder;
	
	public RobotEngineer(RobotBuilder rob) {
		this.robotBuilder = rob;
	}
	
	public Robot getRobot() {
		return robotBuilder.getRobot();
	}
	
	public void makeRobot() {
		robotBuilder.buildRobotArms();
		robotBuilder.buildRobotHead();
		robotBuilder.buildRobotLegs();
	}
}

