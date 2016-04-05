package com.pattern.builderDesign.ROBOT;

public class ClientTest {

	public static void main(String[] args) {
		RobotBuilder oldRobot = new OldRobotBuilder();
		RobotEngineer robotEngineer = new RobotEngineer(oldRobot);
		robotEngineer.makeRobot();
		Robot firstRobot = robotEngineer.getRobot();
		System.out.println("ROBOT: "+firstRobot);
		System.out.println("HEAD: "+firstRobot.getRobotHead());
		System.out.println("ARMS: "+firstRobot.getRobotLegs());
		
	}
}
