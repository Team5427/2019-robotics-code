package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	public DifferentialDrive drive;


	public DriveTrain( DifferentialDrive drive) {
		this.drive = drive;	
	}



	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void takeJoystickInputs(Joystick joy) {
		drive.arcadeDrive(-joy.getY(), joy.getZ() * .75);
		
	}

	public void tankDrive(double leftSpeed,double rightSpeed)
	{
		drive.tankDrive(leftSpeed, rightSpeed);
	}

	public void stop() {
		drive.stopMotor();
	}
}