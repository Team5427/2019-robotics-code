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
	public static final int LEFT_TOP_MOTOR = 9;

	public static final int LEFT_MIDDLE_MOTOR = 10;

	public static final int LEFT_BOTTOM_MOTOR = 11;

	public static final int RIGHT_TOP_MOTOR = 6;



	public static final int RIGHT_MIDDLE_MOTOR = 7;

	public static final int RIGHT_BOTTOM_MOTOR = 8;

	public static final int ELEVATOR_MOTOR = 5;

	public static final int ARM_MOTOR = 4;

	public static final int WRIST_MOTOR = 3;

	public static final int INTAKE_TOP_MOTOR = 2;

	public static final int INTAKE_BOTTOM_MOTOR = 1;
	/*************************************************************************************************************/

	public static final int ROTATION_POTENTIOMETER_PORT_ARM = 1;
	public static final int ROTATION_POTENTIOMETER_PORT_WRIST = 0;

	public static final int ULTRA_PORT1 = 8;
	public static final int ULTRA_PORT2 = 9;

	public static final int ENCODER_LEFT_PORT_1 = 4;
	public static final int ENCODER_LEFT_PORT_2 = 5; 

	public static final int ENCODER_RIGHT_PORT_1 = 6; 
	public static final int ENCODER_RIGHT_PORT_2 = 7;
	
	public static final int ELEVATOR_PORT_1 = 2; 
	public static final int ELEVATOR_PORT_2 = 3;

	public static final int ROTATION_POTENTIOMETER_RANGE = 271;

	public static final double INTAKE_SPEED_IN = -0.4;

	public static final double INTAKE_SPEED_OUT = 1;

	public static final double ELEVATOR_SPEED_UP = 1;

	public static final double ELEVATOR_SPEED_DOWN = -0.75;

	public static final double ARM_SPEED_UP = 0.5;
	
	public static final double ARM_SPEED_DOWN = -0.5;

	public static final double ARM_SPEED_UP_AUTO = 0.85;
	
	public static final double ARM_SPEED_DOWN_AUTO = -0.85;

	public static final double WRIST_SPEED_UP = 0.5;
	
	public static final double WRIST_SPEED_DOWN = -0.5;

	public static final double correctedAngle = 10.0;

	public static final double angleOffsetUp_Wrist = 3.0;
	public static final double angleOffsetDown_Wrist = 0.0;

	public static final double encoderOffsetUp_Elevator = 90;
	public static final double encoderOffsetDown_Elevator = 32;

	
	public static final double angleOffsetUp_Arm = 1.0;
	public static final double angleOffsetDown_Arm = 3.0;



	// Buttons
	/****************************************************************/
	public static final int BUTTON_INTAKE_OUT = 1;

	public static final int BUTTON_INTAKE_IN = 2;

	public static final int BUTTON_ARM_DOWN = 3;

	public static final int BUTTON_ELEVATOR_DOWN = 4;

	public static final int BUTTON_ARM_UP = 5;

	public static final int BUTTON_ELEVATOR_UP = 6;

	public static final int BUTTON_WRIST_UP = 8;

	public static final int BUTTON_WRIST_DOWN = 10;

	public static final int BUTTON_TRAVEL = 9;

	public static final int BUTTON_LOWLOWGEAR = 7;


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

	/********************** PNEUMATICS ************************/

	/**
	 * PCP ID value
	 */
	public static final int PCM_ID = 0;

	/**
	 * Port value for PCP
	 */
	public static final int PCM_JOYSTICK_PORT = 12;

	/**
	 * Channel values for Solenoids
	 */
	public static final int SOLENOID_ONE_CHANNEL = 0;

	public static final double DT_ARM_WRIST = 0.02; //s

	public static final double MAX_VELOCITY_WRIST = 9; //degrees/sec
	public static final double MAX_ACCEL_WRIST = 5; //degrees/s/s
	public static final double MAX_JERK_WRIST = 4; //degrees/s/s/s

	public static final double KP_WRIST = 0.0;
    public static final double KI_WRIST = 0.0;	
	public static final double KD_WRIST = 0;
	public static final double KV_WRIST = 1/MAX_VELOCITY_WRIST;
	public static final double KA_WRIST = 0.06;
	public static final double KLIN_WRIST = 0.1; 
	public static final double HORIZONTAL_WRIST = 255; 


}
