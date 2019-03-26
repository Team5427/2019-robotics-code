package org.usfirst.frc.team5427.robot.subsystems;



import org.usfirst.frc.team5427.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	public static boolean lowlowgear = false;

	public DifferentialDrive drive;
	public SpeedControllerGroup driveLeft;
	public SpeedControllerGroup driveRight;

	// public SpeedController testMotor;
	public DriveTrain(SpeedControllerGroup drive_Left, SpeedControllerGroup drive_Right, DifferentialDrive drive) {
		this.drive = drive;
		this.driveLeft = drive_Left;
		this.driveRight = drive_Right;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void takeJoystickInputs(Joystick joy) {
		
		if(!lowlowgear)
			if(joy.getY() == 0)
				drive.curvatureDrive(0, -joy.getZ() * 0.75, true);
			else
				drive.arcadeDrive(joy.getY(), -joy.getZ() * .75, false);
		else 
			if(joy.getY() == 0)
				drive.curvatureDrive(0, -joy.getZ() * 0.70 * 0.75, true);
			else
				drive.arcadeDrive(joy.getY() * 0.70, -joy.getZ() * 0.70 * .75, false);
	}

	public static void flipLowLowGear() {
		lowlowgear = !lowlowgear;
	}

	public void tankDrive(double leftSpeed,double rightSpeed)
	{
		driveRight.set(rightSpeed);
		driveLeft.set(leftSpeed);
	}

	public void stop() {
		drive.stopMotor();
	}
}