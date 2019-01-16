/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5427.robot;

import org.usfirst.frc.team5427.robot.commands.EjectCargo;
import org.usfirst.frc.team5427.robot.commands.IntakeCargo;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 *
 * This file creates the joy stick and assigns functions to its buttons.
 *
 * @author Varsha Kumar
 */
public class OI {

	public Joystick joy1;

	Button intakeIn;

	Button intakeOut;

	public OI() {
		joy1 = new Joystick(Config.JOYSTICK_PORT);
		intakeIn = new JoystickButton(joy1,Config.BUTTON_INTAKE_IN);
		intakeOut = new JoystickButton(joy1,Config.BUTTON_INTAKE_OUT);
	
		intakeIn.whenPressed(new IntakeCargo());
		intakeOut.whenPressed(new EjectCargo());
	}

	/**
	 * Accessor for the joystick we use.
	 *
	 * @return the current joystick.
	 */
	public Joystick getJoy() {
		return joy1;
	}
}