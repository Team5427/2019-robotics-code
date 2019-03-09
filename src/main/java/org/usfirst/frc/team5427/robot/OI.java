/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5427.robot;

import org.usfirst.frc.team5427.robot.commands.MoveElevator1;
import org.usfirst.frc.team5427.robot.commands.MoveIntake;
import org.usfirst.frc.team5427.robot.commands.RotateArm;
import org.usfirst.frc.team5427.robot.commands.RotateWrist;
import org.usfirst.frc.team5427.robot.commands.auto.presets.Travel;
import org.usfirst.frc.team5427.robot.commands.ActivateSolenoid;
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
public class OI
{
	public Joystick joy1;
	public Button solenoidActivate;

	Button intakeOut;
	Button intakeIn;
	Button armDown;
	Button elevatorDown;
	Button armUp;
	Button elevatorUp;
	// Button levelThreeClimb;
	Button wristUp;
	Button wristDown;
	Button travel;

	Button gearShift;

	public OI()
	{
		joy1 = new Joystick(Config.JOYSTICK_PORT);

		intakeOut = new JoystickButton(joy1, Config.BUTTON_INTAKE_OUT);
		intakeIn = new JoystickButton(joy1, Config.BUTTON_INTAKE_IN);
		armDown = new JoystickButton(joy1, Config.BUTTON_ARM_DOWN);
		elevatorDown = new JoystickButton(joy1, Config.BUTTON_ELEVATOR_DOWN);
		armUp = new JoystickButton(joy1, Config.BUTTON_ARM_UP);
		elevatorUp = new JoystickButton(joy1, Config.BUTTON_ELEVATOR_UP);
		// levelThreeClimb = new JoystickButton(joy1, Config.BUTTON_LEVEL_THREE_CLIMB);
		wristUp = new JoystickButton(joy1, Config.BUTTON_WRIST_UP);
		wristDown = new JoystickButton(joy1, Config.BUTTON_WRIST_DOWN);
		solenoidActivate = new JoystickButton(joy1, Config.PCM_JOYSTICK_PORT);
		travel = new JoystickButton(joy1, Config.BUTTON_TRAVEL);
		
		


		intakeOut.whileHeld(new MoveIntake(Config.INTAKE_SPEED_OUT));
		intakeIn.whileHeld(new MoveIntake(Config.INTAKE_SPEED_IN));
		armDown.whileHeld(new RotateArm(Config.ARM_SPEED_DOWN));
		armUp.whileHeld(new RotateArm(Config.ARM_SPEED_UP));
		// elevatorDown.whenPressed(new MoveElevator(Config.ELEVATOR_SPEED_DOWN));
		// elevatorUp.whenPressed(new MoveElevator(Config.ELEVATOR_SPEED_UP));
		wristDown.whenPressed(new RotateWrist(Config.WRIST_SPEED_DOWN));
		wristUp.whenPressed(new RotateWrist(Config.WRIST_SPEED_UP));

		elevatorDown.whileHeld(new MoveElevator1(Config.ELEVATOR_SPEED_DOWN));
		elevatorUp.whileHeld(new MoveElevator1(Config.ELEVATOR_SPEED_UP));

		travel.whenPressed(new Travel());

		solenoidActivate.whenPressed(new ActivateSolenoid());
	}

	/**
	 * Accessor for the joystick we use.
	 *
	 * @return the current joystick.
	 */
	public Joystick getJoy()
	{
		return joy1;
	}
}