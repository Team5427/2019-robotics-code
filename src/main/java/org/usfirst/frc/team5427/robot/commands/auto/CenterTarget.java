package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.FalconPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterTarget extends Command{

	FalconPID pid;
	double power;
	public static final double KU = 1.0;

	public CenterTarget(double power) {
		this.power = power;
	
		pid = new FalconPID(KU, 0, 0, 1f);
		pid.setReference(-Math.toDegrees(Robot.client.getLastRecievedGoal().getHorizontalAngle()));
		SmartDashboard.putNumber("Setpoint", -Math.toDegrees(Robot.client.getLastRecievedGoal().getHorizontalAngle()));
	}
	
	protected void initialize() {
		Robot.ahrs.zeroYaw();
	}
	
	protected void execute() {
		if (Robot.client.isTargetVisible()) {
			// double calcAnglePID = pid.calcPID(Math.toDegrees(Robot.client.getLastRecievedGoal().getHorizontalAngle()));
			double calcAnglePID = pid.calcPID(Robot.ahrs.getYaw());
			SmartDashboard.putNumber("Current Angle", Robot.ahrs.getYaw());
		
			// System.out.println(Robot.client.getLastRecievedGoal().getHorizontalAngle());
			SmartDashboard.putNumber("power", calcAnglePID);
			Robot.driveTrain.tankDrive(power + calcAnglePID, -power + calcAnglePID);
		} else {
			Robot.driveTrain.stop();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return  pid.isDone();
	}
	
	@Override
	protected void end(){
		Robot.driveTrain.stop();
	}
}