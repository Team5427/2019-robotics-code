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

	/**
	 * The name of the program. 
	 */
	public static final String PROGRAM_NAME = "Team5427RoboCode";

	/**
	 * Whether or not the robot is in debug mode. 
	 */
	public static final boolean DEBUG_MODE = true;

	/**
	 * Whether or not the robot is logging data. 
	 */
	public static final boolean LOGGING = true;

	// CAN Values
	/********************************************************************************************/

	/**
	 * The CAN port for the left top motor of the drive train. 
	 */
	public static final int LEFT_TOP_MOTOR = 10;

	/**
	 * The CAN port for the left middle port of the drive train. 
	 */
	public static final int LEFT_MIDDLE_MOTOR = 10;

	/**
	 * The CAN port for the left bottom port of the drive train. 
	 */
	public static final int LEFT_BOTTOM_MOTOR = 11;

	/**
	 * The CAN port of the right top motor of the drive train. 
	 */
	public static final int RIGHT_TOP_MOTOR = 6;

	/**
	 * The CAN port of the right middle motor of the drive train. 
	 */
	public static final int RIGHT_MIDDLE_MOTOR = 7;

	/**
	 * The CAN port of the right bottom motor of the drive train. 
	 */
	public static final int RIGHT_BOTTOM_MOTOR = 8;

	/**
	 * The CAN port of the left motor of the climber arm. 
	 */
	public static final int CLIMBER_ARM_MOTOR_LEFT = 5;

	/**
	 * The CAN port of the right motor of the climber arm. 
	 */
	public static final int CLIMBER_ARM_MOTOR_RIGHT = 9;

	/**
	 * The CAN port of the motor on the climber leg. 
	 */
	public static final int CLIMBER_LEG_MOTOR = 13;

	/**
	 * The CAN port of the motor on the climber wheel. 
	 */
	public static final int CLIMBER_WHEEL_MOTOR = 12;

	/**
	 * The CAN port of the arm motor. 
	 */
	public static final int ARM_MOTOR = 6;

	/**
	 * The CAN port of the wrist motor. 
	 */
	public static final int WRIST_MOTOR = 3;

	/**
	 * The CAN port of the top motor of the intake. 
	 */
	public static final int INTAKE_TOP_MOTOR = 2;

	/**
	 * The CAN port of the bottom motor of the intake. 
	 */
	public static final int INTAKE_BOTTOM_MOTOR = 1;
	
	/********************************************************************************************/

	/**
	 * The port of the potentiometer on the arm. 
	 */
	public static final int ROTATION_POTENTIOMETER_PORT_ARM = 1;

	/**
	 * The port of the potentiometer on the wrist. 
	 */
	public static final int ROTATION_POTENTIOMETER_PORT_WRIST = 0;

	/**
	 * Port 1 of the ultrasonic sensor. 
	 */
	public static final int ULTRA_PORT1 = 8;

	/**
	 * Port 2 of the ultrasonic sensor. 
	 */
	public static final int ULTRA_PORT2 = 9;

	/**
	 * Port 1 of the left encoder on the drive train. 
	 */
	public static final int ENCODER_LEFT_PORT_1 = 4;

	/**
	 * Port 2 of the left encoder of the drive train. 
	 */
	public static final int ENCODER_LEFT_PORT_2 = 5; 

	/**
	 * Port 1 of the right encoder of the drive train. 
	 */
	public static final int ENCODER_RIGHT_PORT_1 = 6; 

	/**
	 * Port 2 of the right encoder of the drive train. 
	 */
	public static final int ENCODER_RIGHT_PORT_2 = 7;
	
	/**
	 * Port 1 of the encoder on the climber leg. 
	 */
	public static final int ENCODER_CLIMB_PORT_1 = 2; 

	/**
	 * Port 2 of the encoder on the climber leg. 
	 */
	public static final int ENCODER_CLIMB_PORT_2 = 3;

	/**
	 * The range of rotation of the potentiometers. 
	 */
	public static final int ROTATION_POTENTIOMETER_RANGE = 271;

	//Motor speeds
	//Speeds are represeted from 0-1, and are the percentage of power exerted by the motor. 
	/********************************************************************************************/

	/**
	 * The speed of the motors on the intake when collecting cargo. 
	 */
	public static final double INTAKE_SPEED_IN = -0.4;

	/**
	 * The speed of the motors on the intake when delivering cargo. 
	 */
	public static final double INTAKE_SPEED_OUT = 1;

	/**
	 * The speed of the arm motor when going up. 
	 */
	public static final double ARM_SPEED_UP = 0.5;
	
	/**
	 * The speed of the arm motor when going down. 
	 */
	public static final double ARM_SPEED_DOWN = -0.5;

	/**
	 * The speed of the arm motor when going up in autonomous. 
	 */
	public static final double ARM_SPEED_UP_AUTO = 0.85;
	
	/**
	 * The speed of the arm motor when going down in autonomous. 
	 */
	public static final double ARM_SPEED_DOWN_AUTO = -0.85;

	/**
	 * The speed of the wrist motor when going up. 
	 */
	public static final double WRIST_SPEED_UP = 0.5;
	
	/**
	 * The speed of the wrist motor when going down. 
	 */
	public static final double WRIST_SPEED_DOWN = -0.5;

	/**
	 * The speed of the climber arm when going down. 
	 */
	public static final double CLIMBER_ARM_SPEED_DOWN = 0.5;
	
	/**
	 * The speed of the climber arm when going up. 
	 */
	public static final double CLIMBER_ARM_SPEED_UP = -0.5;

	/**
	 * The speed of the climber wheel when going forward. 
	 */
	public static final double CLIMBER_WHEEL_SPEED_FORWARD = -0.5;
	
	/**
	 * The speed of the climber wheel when going backwards. 
	 */
	public static final double CLIMBER_WHEEL_SPEED_BACKWARD = 0.5;

	/**
	 * The speed of the climber leg when going down. 
	 */
	public static final double CLIMBER_LEG_SPEED_DOWN = -1;
	
	/**
	 * The speed of the climber leg when going up. 
	 */
	public static final double CLIMBER_LEG_SPEED_UP = 1;

	/**
	 * Corrected angle for the potentiometers (not in use). 
	 */
	public static final double correctedAngle = 10.0;

	/**
	 * Angle offset for the wrist when going upwards. 
	 */
	public static final double angleOffsetUp_Wrist = 3.0;

	/**
	 * Angle offset for the wrist when going down. 
	 */
	public static final double angleOffsetDown_Wrist = 0.0;

	/**
	 * Angle offset for the arm when going up. 
	 */
	public static final double angleOffsetUp_Arm = 1.0;

	/**
	 * Angle offset for the arm when going down. 
	 */
	public static final double angleOffsetDown_Arm = 3.0;

	// Buttons
	/********************************************************************************************/

	/**
	 * The button on the joystick for running the intake outwards. 
	 */
	public static final int BUTTON_INTAKE_OUT = 1;

	/**
	 * The button on the joystick for running the intake inwards. 
	 */
	public static final int BUTTON_INTAKE_IN = 2;

	/**
	 * The button on the joystick for moving the arm down. 
	 */
	public static final int BUTTON_ARM_DOWN = 3;

	/**
	 * The button on the joystick for moving the climber arm down. 
	 */
	public static final int BUTTON_CLIMBER_ARM_DOWN = 14;

	/**
	 * The button on the joystick for moving the arm up. 
	 */
	public static final int BUTTON_ARM_UP = 5;

	/**
	 * The button on the joystick for moving the climber arm up. 
	 */
	public static final int BUTTON_CLIMBER_ARM_UP = 13;

	/**
	 * The button on the joystick for moving the wrist up. 
	 */
	public static final int BUTTON_WRIST_UP = 8;

	/**
	 * The button on the joystick for moving the wrist down. 
	 */
	public static final int BUTTON_WRIST_DOWN = 10;

	/**
	 * The button on the joystick for the travel preset. 
	 */
	public static final int BUTTON_TRAVEL = 9;

	/**
	 * The button on the joystick for toggling lowlowgear. 
	 */
	public static final int BUTTON_LOWLOWGEAR = 7;

	/**
	 * The button on the joystick for moving the climber leg up. 
	 */
	public static final int BUTTON_CLIMBER_LEG_UP = 4;

	/**
	 * The button on the joystick for moving the climber leg down. 
	 */
	public static final int BUTTON_CLIMBER_LEG_DOWN = 6;
	
	/**
	 * The button on the joystick for moving the climber wheel forward. 
	 */
	public static final int BUTTON_CLIMBER_WHEEL_FORWARD = 20;

	/**
	 * The button on the joystick for the gear shifter. 
	 */
	public static final int BUTTON_PNEUMATIC_ACTIVATE = 11;

	
	// public static final int BUTTON_CLIMBER_WHEEL_BACKWARD = 11;

	//Limits
	public static final double ARM_LIMIT_TOP = 69;
	public static final double ARM_LIMIT_BOTTOM = 109.5;
	public static final double WRIST_LIMIT_TOP = 5.5;
	public static final double WRIST_LIMIT_BOTTOM = 56.4;

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
	public static final int SOLENOID_ONE_CHANNEL   = 0;
	public static final int SOLENOID_LIGHT_CHANNEL = 1;
	public static final int SOLENOID_HATCH_CHANNEL = 3;



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
