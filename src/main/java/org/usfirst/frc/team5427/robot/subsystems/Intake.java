/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	/**
	 * The SpeedController that controls the top motor of the intake.
	 */
	private SpeedController topFlywheel;
	
	/**
	 * The SpeedController that controls the bottom motor of the intake.
	 */
    private SpeedController bottomFlywheel;
    
	/**
	 * Assigns each SpeedController to its received motor.
	 * 
	 * @param topFlywheel
	 *            the SpeedController of the left motor of the intake.
	 * @param bottomFlywheel
	 *            the SpeedController of the right motor of the intake.
	 */
	public Intake(SpeedController topFlywheel, SpeedController bottomFlywheel) {
		this.topFlywheel = topFlywheel;
		this.bottomFlywheel = bottomFlywheel;
	}

	/**
	 * Sets the speed of each SpeedController to whatever the received speed parameter is.
	 * 
	 * @param speed
	 *            the desired power to set the intake to.
	 */
	public void setSpeed(double speed) {
        topFlywheel.set(-speed);
        bottomFlywheel.set(speed);
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
