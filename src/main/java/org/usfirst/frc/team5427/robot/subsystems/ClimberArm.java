/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberArm extends Subsystem {
    
    
	public ClimberArm() {
	}

	/**
	 * Sets the speed of each SpeedController to whatever the received speed parameter is.
	 * 
	 * @param speed
	 *            the desired power to set the intake to.
	 */
	public void setSpeed(double speed) {
		Robot.climberArmMotor.set(speed);
		Robot.climberArmMotor1.set(-speed);
	}

	/**
	 * Unused method but required by extending SubSystem class
	 */
	@Override
	public void initDefaultCommand() {
	}

	/**
	 * Stops the motors of the intake.
	 */
	public void stop() {
		setSpeed(0);
	}

}
