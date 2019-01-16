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

	Button intakeOut;
	Button intakeIn;
	Button elbowDown;
	Button elevatorDown;
	Button elbowUp;
	Button elevatorUp;
	Button hatchEject;
	Button levelThreeClimb;
	Button wristUp;
	Button wristDown;

	public OI() {
		joy1 = new Joystick(Config.JOYSTICK_PORT);

		intakeOut = new JoystickButton(joy1,Config.BUTTON_INTAKE_OUT);
		intakeIn = new JoystickButton(joy1,Config.BUTTON_INTAKE_IN);
		elbowDown = new JoystickButton(joy1,Config.BUTTON_ELBOW_DOWN);
		elevatorDown = new JoystickButton(joy1, Config.BUTTON_ELEVATOR_DOWN);
		elbowUp = new JoystickButton(joy1,Config.BUTTON_ELBOW_UP);
		elevatorUp = new JoystickButton(joy1,Config.BUTTON_ELEVATOR_UP);
		hatchEject = new JoystickButton(joy1,Config.BUTTON_HATCH_EJECT);
		levelThreeClimb = new JoystickButton(joy1,Config.BUTTON_LEVEL_THREE_CLIMB);
		wristUp = new JoystickButton(joy1,Config.BUTTON_WRIST_UP);
		wristDown = new JoystickButton(joy1,Config.BUTTON_WRIST_DOWN);

		intakeOut.whenPressed(new EjectCargo());
		intakeIn.whenPressed(new IntakeCargo());
		// elbowDown.whenPressed(new ElbowDown());
		// elevatorDown.whenPressed(new ElevatorDown());
		// elbowUp.whenPressed(new ElbowUp());
		// elevatorUp.whenPressed(new ElevatorUp());
		// hatchEject.whenPressed(new EjectHatch());
		// levelThreeClimb.whenPressed(new LevelThreeClimb());
		// wristUp.whenPressed(new WristUp());
		// wristDown.whenPressed(new WristDown());
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