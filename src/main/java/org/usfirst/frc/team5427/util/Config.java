package org.usfirst.frc.team5427.util;

/**
 * This file will store ALL of the variables, offsets, measurements, etc. that
 * our robot will use during the year. All variables are to be static, and
 * nothing in this file should ever have to be initiated.
 *
 * @author Andrew Kennedy, Bo Corman
 */
public class Config {

	/* The name of our program as per the robot. */
	public static final String PROGRAM_NAME = "Team5427RoboCode";

	/* Stores whether the robot code is in debug mode or not. */
	public static final boolean DEBUG_MODE = true;

	/* Stores whether the robot code is sending messages to the log file. */
	public static final boolean LOGGING = true;

	/* PWM PORTS */

	/* The PWM value for the front right motor of the drive train. */
	public static final int FRONT_RIGHT_MOTOR = 3;

	/* The PWM value for the rear right motor of the drive train. */
	public static final int REAR_RIGHT_MOTOR = 6;

	/* The PWM value for the front left motor of the drive train. */
	public static final int FRONT_LEFT_MOTOR = 1;

	/* The PWM value for the rear left motor of the drive train. */
	public static final int REAR_LEFT_MOTOR = 0;

	public static final int INTAKE_TOP_MOTOR = 7;

	public static final int INTAKE_BOTTOM_MOTOR = 8;

	public static final int ROTATION_POTENTIOMETER_PORT = 0;

	public static final int ROTATION_POTENTIOMETER_RANGE = 271;

	public static final double INTAKE_SPEED_IN = .5;

	public static final double INTAKE_SPEED_OUT = -.5;

	//Buttons
	/****************************************************************/
	public static final int BUTTON_INTAKE_OUT = 1;

	public static final int BUTTON_INTAKE_IN = 2;

	public static final int BUTTON_ELBOW_DOWN = 3;

	public static final int BUTTON_ELEVATOR_DOWN = 4;
	
	public static final int BUTTON_ELBOW_UP = 5;
	
	public static final int BUTTON_ELEVATOR_UP = 6;

	public static final int BUTTON_HATCH_EJECT = 7;

	public static final int BUTTON_LEVEL_THREE_CLIMB = 8;
	
	public static final int BUTTON_WRIST_UP = 9;
	
	public static final int BUTTON_WRIST_DOWN = 10;
	/****************************************************************/

	/* The port associated with the main joystick. */
	public static final int JOYSTICK_PORT = 0;

	/* The port associated with a second joystick. */
	public static final int ALT_JOYSTICK_PORT = 0;

	/* The mode designating that we are using one joystick. */
	public static final int ONE_JOYSTICK = 0;

	/* The mode designating that we are using two joysticks. */
	public static final int TWO_JOYSTICKS = 1;

	/* Stores what mode of controller use we are currently using. */
	public static final int JOYSTICK_MODE = ONE_JOYSTICK;

	public static final double PID_STRAIGHT_P = 0;

	public static final double PID_STRAIGHT_I = 0;

	public static final double PID_STRAIGHT_D = 0;

	public static final double PID_UPDATE_PERIOD = 0;

	public static final double PID_STRAIGHT_LINEAR_INCREMENT = 0;
}
