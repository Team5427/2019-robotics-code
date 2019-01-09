package org.usfirst.frc.team5427.robot.subsystems;


import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5427.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem implements PIDOutput{

	public DifferentialDrive drive;

	public SpeedControllerGroup driveLeft;

	public SpeedControllerGroup driveRight;

	public PIDController turnController;

	public AHRS ahrs;

	public double turnTolerance = 2.0f;

	public double p = 0;
	public double i = 0;
	public double d = 0;

	public DriveTrain(SpeedControllerGroup drive_Left, SpeedControllerGroup drive_Right, DifferentialDrive drive) {

		this.drive = drive;
		this.driveLeft = drive_Left;
		this.driveRight = drive_Right;

		turnController = new PIDController(p,i,d,ahrs,this);
		turnController.setInputRange(-180.0f,180.0f);
		turnController.setContinuous();
		turnController.setOutputRange(-0.5f,0.5f);
		turnController.setAbsoluteTolerance(turnTolerance);
	}

	public void turnDegrees(double angle)
	{
		ahrs.reset();
		turnController.reset();
		turnController.setPID(p,i,d);
		turnController.setSetpoint(angle);
		turnController.enable();
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
		tankDrive(output,-output);
	}
}