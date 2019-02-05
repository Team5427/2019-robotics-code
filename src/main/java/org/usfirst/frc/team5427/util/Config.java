package org.usfirst.frc.team5427.util;

/**
 * This file will store ALL of the variables, offsets, measurements, etc. that
 * our robot will use during the year. All variables are to be static, and
 * nothing in this file should ever have to be initiated.
 * 
 * @author Andrew Kennedy, Bo Corman
 */
public class Config {

	/**
	 * The name of our program as per the robot.
	 */
	public static final String PROGRAM_NAME = "Team5427RoboCode";

	/**
	 * Stores whether the robot code is in debug mode or not.
	 */
	public static final boolean DEBUG_MODE = true;

	/**
	 * Stores whether the robot code is sending messages to the log file.
	 */
	public static final boolean LOGGING = true;

	/******************** PWM PORTS *******************/
	/**
	 * The PWM value for the front right motor of the drive train.
	 */
	public static final int FRONT_RIGHT_MOTOR = 0;

	/**
	 * The PWM value for the rear right motor of the drive train.
	 */
	public static final int REAR_RIGHT_MOTOR = 1;

	/**
	 * The PWM value for the front left motor of the drive train.
	 */
	public static final int FRONT_LEFT_MOTOR = 2;

	/**
	 * The PWM value for the rear left motor of the drive train.
	 */
	public static final int REAR_LEFT_MOTOR = 3;
	/**
	 * The PWM value for the arm.
	 */
	public static final int ARM_MOTOR = 4;
	/**
	* The PWM value for the wrist
	*/
   public static final int WRIST_MOTOR = 5;

	/******************** CONTROLLER PORTS *******************/
	/**
	 * The port associated with the main joystick.
	 */
	public static final int JOYSTICK_PORT = 0;

	/**
	 * The port associated with a second joystick.
	 */
	public static final int ALT_JOYSTICK_PORT = 0;

	/**
	 * The mode designating that we are using one joystick.
	 */
	public static final int ONE_JOYSTICK = 0;

	/**
	 * The mode designating that we are using two joysticks.
	 */
	public static final int TWO_JOYSTICKS = 1;

	/**
	 * Stores what mode of controller use we are currently using.
	 */
	public static final int JOYSTICK_MODE = ONE_JOYSTICK;
	/*********************************************************/

	public static final double ENCODER_DISTANCE_OFFSET = 0.9752;
	
	public static final double DT = 0.02; //s
	public static final double WHEELBASE_WIDTH = 2.25; //feet


	public static final double MAX_VELOCITY = 2.75; // m/s
	public static final double MAX_ACCEL = 0.6; // 1.833 m/s/s
	public static final double MAX_JERK = 0.5; //11 m/s/s/s

	public static final double KP = 1f;
    public static final double KI = 0.0;	
	public static final double KD = 0;
	public static final double KV = 1/MAX_VELOCITY; 
	public static final double KA = 0.42; 



	public static final double MAX_VELOCITY_ARM = 20; //degrees/sec
	public static final double MAX_ACCEL_ARM = 10; //degrees/s/s
	public static final double MAX_JERK_ARM = 25; //degrees/s/s/s

	public static final double KP_ARM = 1f;
    public static final double KI_ARM = 0.0;	
	public static final double KD_ARM = 0;
	public static final double KV_ARM = 1/MAX_VELOCITY_ARM;
	public static final double KA_ARM = 0; 


	public static final double MAX_VELOCITY_WRIST = 45; //degrees/sec
	public static final double MAX_ACCEL_WRIST = 30; //degrees/s/s
	public static final double MAX_JERK_WRIST = 40; //degrees/s/s/s

	public static final double KP_WRIST = 1f;
    public static final double KI_WRIST = 0.0;	
	public static final double KD_WRIST = 0;
	public static final double KV_WRIST = 1/MAX_VELOCITY_WRIST;
	public static final double KA_WRIST = 0; 


	public static final int ROTATION_POTENTIOMETER_WRIST_PORT = 0;

	public static final int ROTATION_POTENTIOMETER_WRIST_RANGE = 271;

	public static final int ROTATION_POTENTIOMETER_ARM_PORT = 1;
	
	public static final int ROTATION_POTENTIOMETER_ARM_RANGE = 271;
	
	public static double ftm(double feet) {
		return feet/3.281;
	}

	public static double dtr(double deg) {
		return Math.toRadians(deg);
	}

}
