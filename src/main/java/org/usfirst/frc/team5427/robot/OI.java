/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5427.robot;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.*;
import org.usfirst.frc.team5427.robot.commands.auto.presets.Travel;
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
	private Joystick joy1;
	private Button solenoidActivate;
	private Button solenoidHatchActivate;


	private Button intakeOut;
	private Button intakeIn;
	private Button armDown;
	private Button climberArmDown;
	private Button armUp;
	private Button climberArmUp;
	private Button wristUp;
	private Button wristDown;
	private Button travel;
	private Button lowlowgear;
	private Button hatchShift;
	private Button climberLegUp, climberLegDown;
	private Button gearShift;
	private POVButton climberWheelUp;
	private POVButton climberWheelDown;

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
		travel = new JoystickButton(joy1, Config.BUTTON_TRAVEL);
		lowlowgear = new JoystickButton(joy1, Config.BUTTON_LOWLOWGEAR);
		climberLegDown = new JoystickButton(joy1, Config.BUTTON_CLIMBER_LEG_DOWN);
		climberLegUp = new JoystickButton(joy1, Config.BUTTON_CLIMBER_LEG_UP);
		solenoidHatchActivate = new JoystickButton(joy1,Config.BUTTON_PNEUMATIC_ACTIVATE);
		climberWheelUp = new POVButton(joy1, 0);
		climberWheelDown = new POVButton(joy1, 180);

		intakeOut.whileHeld(new MoveIntake(new BigDecimal(Config.INTAKE_SPEED_OUT)));
		intakeIn.whileHeld(new MoveIntake(new BigDecimal(Config.INTAKE_SPEED_IN)));
		climberArmDown.whenPressed(new MoveClimberArm(new BigDecimal(Config.CLIMBER_ARM_SPEED_DOWN)));
		climberArmUp.whenPressed(new MoveClimberArm(new BigDecimal(Config.CLIMBER_ARM_SPEED_UP)));
		climberLegDown.whenPressed(new MoveClimberLeg(new BigDecimal(Config.CLIMBER_LEG_SPEED_DOWN)));
		climberLegUp.whenPressed(new MoveClimberLeg(new BigDecimal(Config.CLIMBER_LEG_SPEED_UP)));
		armDown.whileHeld(new RotateArm(new BigDecimal(Config.ARM_SPEED_DOWN)));
		armUp.whileHeld(new RotateArm(new BigDecimal(Config.ARM_SPEED_UP)));
		lowlowgear.whenPressed(new LowLowGear());
		wristDown.whenPressed(new RotateWrist(new BigDecimal(Config.WRIST_SPEED_DOWN)));
		wristUp.whenPressed(new RotateWrist(new BigDecimal(Config.WRIST_SPEED_UP)));
		travel.whenPressed(new Travel());
		climberWheelUp.whenPressed(new MoveClimberWheels(new BigDecimal(Config.CLIMBER_WHEEL_SPEED_FORWARD)));
		climberWheelDown.whenPressed(new MoveClimberWheels(new BigDecimal(Config.CLIMBER_WHEEL_SPEED_BACKWARD)));

		solenoidActivate.whenPressed(new ActivateSolenoid());
		solenoidHatchActivate.whenPressed(new ActivateSolenoidHatch());
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