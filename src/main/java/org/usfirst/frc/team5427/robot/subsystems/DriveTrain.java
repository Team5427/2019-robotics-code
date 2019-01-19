package org.usfirst.frc.team5427.robot.subsystems;


import org.usfirst.frc.team5427.robot.Robot;

// import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5427.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem implements PIDOutput {

	public DifferentialDrive drive;
	public SpeedControllerGroup driveLeft;
	public SpeedControllerGroup driveRight;
	public PIDController ultraController;
	public Ultrasonic ultra;
	public double ultraTolerance = 0.5f;
	public double p = 0.1;
	public double i = 0;
	public double d = 0;

	public DriveTrain(SpeedControllerGroup drive_Left, SpeedControllerGroup drive_Right, DifferentialDrive drive) {
		this.drive = drive;
		this.driveLeft = drive_Left;
		this.driveRight = drive_Right;
		ultra = Robot.ultra;
		ultraController = new PIDController(p,i,d,ultra,this);
		ultraController.setOutputRange(-1f,1f);
		ultraController.setAbsoluteTolerance(ultraTolerance);
	}

	public void approachInches(double distance)
	{
		ultraController.reset();
		ultraController.setPID(p, i, d);
		ultraController.setSetpoint(distance);
		ultraController.enable();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void takeJoystickInputs(Joystick joy) {
		drive.arcadeDrive(-joy.getY(), joy.getZ() * .75);
	}

	public void tankDrive(double rightSpeed,double leftSpeed)
	{
		driveRight.set(rightSpeed);
		driveLeft.set(leftSpeed);
	}

	public void stop() {
		drive.stopMotor();
	}

	@Override
	public void pidWrite(double output) {
		drive.arcadeDrive(-output,0);
	}
}