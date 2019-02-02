package org.usfirst.frc.team5427.util;

/**
 * This file will store ALL of the variables, offsets, measurements, etc. that
 * our robot will use during the year. All variables are to be static, and
 * nothing in this file should ever have to be initiated.
 *
 * @author Andrew Kennedy, Bo Corman
 */
public class Config
{

	public static final String PROGRAM_NAME = "Team5427RoboCode";

	public static final boolean DEBUG_MODE = true;

	public static final boolean LOGGING = true;

	// CAN Values
	/*************************************************************************************************************/
	public static final int FRONT_LEFT_MOTOR = 0;

	public static final int MIDDLE_LEFT_MOTOR = 1;

	public static final int REAR_LEFT_MOTOR = 2;

	public static final int FRONT_RIGHT_MOTOR = 3;

	public static final int MIDDLE_RIGHT_MOTOR = 4;

	public static final int REAR_RIGHT_MOTOR = 5;

	public static final int ELEVATOR_MOTOR = 6;

	public static final int ARM_MOTOR = 7;

	public static final int WRIST_MOTOR = 8;

	public static final int INTAKE_TOP_MOTOR = 9;

	public static final int INTAKE_BOTTOM_MOTOR = 10;
	/*************************************************************************************************************/

	public static final int ROTATION_POTENTIOMETER_PORT = 0;

	public static final int ROTATION_POTENTIOMETER_RANGE = 271;

	public static final double INTAKE_SPEED_IN = 0.5;

	public static final double INTAKE_SPEED_OUT = -0.5;

	public static final double ELEVATOR_SPEED_UP = 0.5;

	public static final double ELEVATOR_SPEED_DOWN = -0.5;

	public static final double ARM_SPEED_UP = 0.5;
	
	public static final double ARM_SPEED_DOWN = -0.5;

	public static final double WRIST_SPEED_UP = 0.5;
	
	public static final double WRIST_SPEED_DOWN = -0.5;

	// Buttons
	/****************************************************************/
	public static final int BUTTON_INTAKE_OUT = 1;

	public static final int BUTTON_INTAKE_IN = 2;

	public static final int BUTTON_ARM_DOWN = 3;

	public static final int BUTTON_ELEVATOR_DOWN = 4;

	public static final int BUTTON_ARM_UP = 5;

	public static final int BUTTON_ELEVATOR_UP = 6;

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
}
