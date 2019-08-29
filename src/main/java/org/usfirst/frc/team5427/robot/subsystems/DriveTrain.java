package org.usfirst.frc.team5427.robot.subsystems;



import org.usfirst.frc.team5427.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	/**A toggle for lowlow gear and low gear */
	public static boolean lowlowgear = false;

	//Drive Train and SpeedController Group components
	public DifferentialDrive drive;
	public SpeedControllerGroup driveLeft;
	public SpeedControllerGroup driveRight;

	/**Constructor for the DriveTrain [Takes in SCG left, SCG right, DifferentialDrive driveTrain] */
	public DriveTrain(SpeedControllerGroup drive_Left, SpeedControllerGroup drive_Right, DifferentialDrive drive) {
		this.drive = drive;
		this.driveLeft = drive_Left;
		this.driveRight = drive_Right;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}


	/**Sets up arcadeDrive joystick mapping given boolean lowlowgear */
	public void takeJoystickInputs(Joystick joy) {
		if(!lowlowgear)
			drive.arcadeDrive(joy.getY(), -joy.getZ() * Config.Z_ROT_DAMPENING);
		else 
			drive.arcadeDrive(joy.getY() * Config.HIGH_GEAR_SENSITIVITY, -joy.getZ() * Config.Z_ROT_DAMPENING * Config.HIGH_GEAR_SENSITIVITY);
	}

	/**Toggle for low low gear */
	public static void flipLowLowGear() {
		lowlowgear = !lowlowgear;
	}

	/** Sets DriveTrain Speed */
	public void tankDrive(double leftSpeed,double rightSpeed)
	{
		driveRight.set(rightSpeed);
		driveLeft.set(leftSpeed);
	}

	/** Stop Subsystem*/
	public void stop() {
		drive.stopMotor();
	}
}