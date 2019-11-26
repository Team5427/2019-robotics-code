/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5427.robot;

import org.usfirst.frc.team5427.robot.commands.*;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

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
	public Button solenoidHatchActivate;


	Button intakeOut;
	Button intakeIn;
	Button armDown;
	Button climberArmDown;
	Button armUp;
	Button climberArmUp;
	Button wristUp;
	Button wristDown;
	// Button travel;
	Button lowlowgear;
	Button hatchShift;

	Button climberLegUp, climberLegDown;

	Button solenoidClimb;

	// Button climberWheelForward, climberWheelBackward;

	Button gearShift;

	POVButton climberForward, climberBackward;

	public OI()
	{
		joy1 = new Joystick(Config.JOYSTICK_PORT);

		intakeOut = new JoystickButton(joy1, Config.BUTTON_INTAKE_OUT);
		intakeIn = new JoystickButton(joy1, Config.BUTTON_INTAKE_IN);
		armDown = new JoystickButton(joy1, Config.BUTTON_ARM_DOWN);
		climberArmDown = new JoystickButton(joy1, Config.BUTTON_CLIMBER_ARM_DOWN);
		armUp = new JoystickButton(joy1, Config.BUTTON_ARM_UP);
		climberArmUp = new JoystickButton(joy1, Config.BUTTON_CLIMBER_ARM_UP);
		wristUp = new JoystickButton(joy1, Config.BUTTON_WRIST_UP);
		wristDown = new JoystickButton(joy1, Config.BUTTON_WRIST_DOWN);
		solenoidActivate = new JoystickButton(joy1, Config.PCM_JOYSTICK_PORT);
		// travel = new JoystickButton(joy1, Config.BUTTON_TRAVEL);
		lowlowgear = new JoystickButton(joy1, Config.BUTTON_LOWLOWGEAR);
		// climberLegDown = new JoystickButton(joy1, Config.BUTTON_CLIMBER_LEG_DOWN);
		// climberLegUp = new JoystickButton(joy1, Config.BUTTON_CLIMBER_LEG_UP);
		solenoidHatchActivate = new JoystickButton(joy1,Config.BUTTON_PNEUMATIC_ACTIVATE);
		solenoidClimb = new JoystickButton(joy1, Config.SOLENOID_CLIMB_BUTTON);

		climberForward = new POVButton(joy1, 0);
		climberBackward = new POVButton(joy1, 180);
		// climberWheelForward = new JoystickButton(joy1, Config.BUTTON_CLIMBER_WHEEL_FORWARD);
		// climberWheelBackward = new JoystickButton(joy1, Config.BUTTON_CLIMBER_WHEEL_BACKWARD);

		intakeOut.whileHeld(new MoveIntake(Config.INTAKE_SPEED_OUT));
		intakeIn.whileHeld(new MoveIntake(Config.INTAKE_SPEED_IN));
		climberArmDown.whenPressed(new MoveClimberArm(Config.CLIMBER_ARM_SPEED_DOWN));
		climberArmUp.whenPressed(new MoveClimberArm(Config.CLIMBER_ARM_SPEED_UP));
		// climberLegDown.whenPressed(new MoveClimberLeg(Config.CLIMBER_LEG_SPEED_DOWN));
		// climberLegUp.whenPressed(new MoveClimberLeg(Config.CLIMBER_LEG_SPEED_UP));
		armDown.whileHeld(new RotateArm(Config.ARM_SPEED_DOWN));
		armUp.whileHeld(new RotateArm(Config.ARM_SPEED_UP));
		lowlowgear.whenPressed(new LowLowGear());
		wristDown.whenPressed(new RotateWrist(Config.WRIST_SPEED_DOWN));
		wristUp.whenPressed(new RotateWrist(Config.WRIST_SPEED_UP));

		climberForward.whenPressed(new MoveClimberWheels(Config.CLIMBER_WHEEL_SPEED_FORWARD));
		climberBackward.whenPressed(new MoveClimberWheels(Config.CLIMBER_WHEEL_SPEED_BACKWARD));
		// climberWheelBackward.whileHeld(new MoveClimberWheels(Config.CLIMBER_WHEEL_SPEED_BACKWARD));
		// climberWheelForward.whileHeld(new MoveClimberWheels(Config.CLIMBER_WHEEL_SPEED_FORWARD));
		// travel.whenPressed(new Travel());

		solenoidActivate.whenPressed(new ActivateSolenoid());
		solenoidHatchActivate.whenPressed(new ActivateSolenoidHatch());
		solenoidClimb.whenPressed(new ActivateSolenoidClimb());

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