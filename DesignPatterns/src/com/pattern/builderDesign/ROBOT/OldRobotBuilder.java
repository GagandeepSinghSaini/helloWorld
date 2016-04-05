package com.pattern.builderDesign.ROBOT;

public class OldRobotBuilder implements RobotBuilder {

	private Robot robot;
	
	public OldRobotBuilder() {
		this.robot = new Robot();
	}
	
	@Override
	public void buildRobotHead() {
		robot.setRobotHead("Head of the robot");

	}

	@Override
	public void buildRobotArms() {
		robot.setRobotArms("Arms of the ROBOT");

	}

	@Override
	public void buildRobotLegs() {
		robot.setRobotLegs("Legs OF the Robot");

	}

	@Override
	public Robot getRobot() {
		// TODO Auto-generated method stub
		return robot;
	}

}
