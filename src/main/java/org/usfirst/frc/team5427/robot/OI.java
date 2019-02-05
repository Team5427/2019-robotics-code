/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5427.robot;

import org.usfirst.frc.team5427.robot.commands.MoveArm;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

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

	public Button armUp;
	public Button armDown;
	public Button wristUp;
	public Button wristDown;

	public OI() {
		joy1 = new Joystick(Config.JOYSTICK_PORT);

		//arm 45 degrees
		Trajectory.Config configArm =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT, Config.MAX_VELOCITY_ARM, Config.MAX_ACCEL_ARM, Config.MAX_JERK_ARM);
        Trajectory trajectoryArm45 = Pathfinder.generate(new Waypoint[] {new Waypoint(0, 0, 0), new Waypoint(45,0,0)}, configArm);
	
		//wrist 45 degrees
		Trajectory.Config configWrist =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT, Config.MAX_VELOCITY_WRIST, Config.MAX_ACCEL_WRIST, Config.MAX_JERK_WRIST);
        Trajectory trajectoryWrist45 = Pathfinder.generate(new Waypoint[] {new Waypoint(0, 0, 0), new Waypoint(45,0,0)}, configWrist);
		
		armUp = new JoystickButton(joy1, Config.BUTTON_ARM_UP);
		armDown = new JoystickButton(joy1, Config.BUTTON_ARM_DOWN);
		wristUp = new JoystickButton(joy1, Config.BUTTON_WRIST_UP);
		wristDown = new JoystickButton(joy1, Config.BUTTON_WRIST_DOWN);

		armUp.whenPressed(new MoveArm(trajectoryArm45, false));
		armDown.whenPressed(new MoveArm(trajectoryArm45, true));
		wristUp.whenPressed(new MoveArm(trajectoryWrist45, false));
		wristDown.whenPressed(new MoveArm(trajectoryWrist45, true));	
	
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