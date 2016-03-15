package com.pattern.builderDesign.ROBOT;

public interface RobotBuilder {
	public void buildRobotHead();
	public void buildRobotArms();
	public void buildRobotLegs();
	public Robot getRobot();
}
