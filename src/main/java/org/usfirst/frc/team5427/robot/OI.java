/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5427.robot;

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

	public Button elev_high_rocket_front;
	public Button elev_middle_rocket_front;
	public Button elev_low_rocket_front;

	public Button elev_high_rocket_side;
	public Button elev_middle_rocket_side;
	
	public Button elev_hatch_ground;
	public Button elev_ball_ground;
	public Button elev_low_hatch;
	public Button elev_ball_ship;


	

	public OI() {
		joy1 = new Joystick(Config.JOYSTICK_PORT);

		elev_high_rocket_front = new JoystickButton(joy1, Config.BUTTON_ELEV_HIGH_ROCKET_FRONT);
		elev_middle_rocket_front = new JoystickButton(joy1, Config.BUTTON_ELEV_MIDDLE_ROCKET_FRONT);
		elev_low_rocket_front = new JoystickButton(joy1, Config.BUTTON_ELEV_LOW_ROCKET_FRONT);
		
		elev_high_rocket_side = new JoystickButton(joy1, Config.BUTTON_ELEV_HIGH_ROCKET_SIDE);
		elev_middle_rocket_side = new JoystickButton(joy1, Config.BUTTON_ELEV_MIDDLE_ROCKET_SIDE);

		elev_hatch_ground = new JoystickButton(joy1, Config.BUTTON_ELEV_HATCH_GROUND);
		elev_ball_ground = new JoystickButton(joy1, Config.BUTTON_ELEV_BALL_GROUND);

		elev_low_hatch = new JoystickButton(joy1, Config.BUTTON_ELEV_HATCH_SHIP_LOADING_STATION_SIDE_LOW_ROCKET);
		elev_ball_ship = new JoystickButton(joy1, Config.BUTTON_ELEV_BALL_SHIP);


		Trajectory.Config configElev =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT, Config.MAX_VELOCITY_ELEV, Config.MAX_ACCEL_ELEV, Config.MAX_JERK_ELEV);
		
		Trajectory trajectoryElevHighRocketFront = Pathfinder.generate(new Waypoint[] {new Waypoint(0, 0, 0), new Waypoint(45,0,0)}, configElev);
	
		
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