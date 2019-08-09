/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

//EXTERNAL LIBRARIES 
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5427.robot.commands.auto.MoveClimberLegAuto;
import org.usfirst.frc.team5427.robot.commands.auto.presets.CargoFloor;
import org.usfirst.frc.team5427.robot.commands.auto.presets.CargoLevel1;
import org.usfirst.frc.team5427.robot.commands.auto.presets.CargoLevel2;
import org.usfirst.frc.team5427.robot.commands.auto.presets.CargoLevel3;
import org.usfirst.frc.team5427.robot.commands.auto.presets.CargoShipCargo;
import org.usfirst.frc.team5427.robot.commands.auto.presets.HatchLevel1;
import org.usfirst.frc.team5427.robot.commands.auto.presets.HatchLevel2;
import org.usfirst.frc.team5427.robot.commands.auto.presets.HatchLevel3;
import org.usfirst.frc.team5427.robot.commands.auto.presets.IntakeCargoLevel1;
import org.usfirst.frc.team5427.robot.commands.auto.presets.IntakeCargoLevel1Keep;
import org.usfirst.frc.team5427.robot.commands.auto.presets.IntakeCargoLoadingStation;
import org.usfirst.frc.team5427.robot.commands.auto.presets.IntakeHatchFloor;
import org.usfirst.frc.team5427.robot.commands.auto.presets.IntakeHatchLoadingStation;
import org.usfirst.frc.team5427.robot.commands.auto.presets.Travel;
import org.usfirst.frc.team5427.robot.subsystems.Arm;
import org.usfirst.frc.team5427.robot.subsystems.ClimberArm;
import org.usfirst.frc.team5427.robot.subsystems.ClimberLeg;
import org.usfirst.frc.team5427.robot.subsystems.ClimberWheel;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.Intake;
import org.usfirst.frc.team5427.robot.subsystems.Wrist;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    /**
     * The operator interface, creates buttons and assigns their functions. 
     */
    public static OI oi;

    /**
     * Speed controller for the left top motor of the drive train.
     */
    public static SpeedController driveLeftTop;

    /**
     * Speed controller for the left middle motor of the drive train. 
     */
    public static SpeedController driveLeftMiddle;

    /**
     * Speed controller for the left bottom motor of the drive train.
     */
    public static SpeedController driveLeftBottom;

    /**
     * Speed controller for the right top motor of the drive train.
     */
    public static SpeedController driveRightTop;

    /**
     * Speed controller for the right middle motor of the drive train.
     */
    public static SpeedController driveRightMiddle;

    /**
     * Speed controller for the right bottom motor of the drive train.
     */
    public static SpeedController driveRightBottom;

    /**
     * Group of the left motors of the drive train. 
     */
    public static SpeedControllerGroup driveLeft;

    /**
     * Group of the right motors of the drive train.
     */
    public static SpeedControllerGroup driveRight;

    /**
     * The drive train subsystem.
     */
    public static DriveTrain driveTrain;

    /**
     * Differential drive base for the robot, which allows for control of the left and 
     * right side of the drive train in control groups. 
     */
    public static DifferentialDrive drive;

    /**
     * Speed controller for the arm motor. 
     */
    public static SpeedController armMotor;

    /**
     * Speed controller for the climber arm motor -- not in use. 
     */
    public static SpeedController climberArmMotor;

    /**
     * Speed controller for the climber arm motor -- not in use. 
     */
    public static SpeedController climberArmMotor1;

    /**
     * Speed controller for the climber wheel motor -- not in use. 
     */
    public static SpeedController climberWheelMotor;

    /**
     * Speed controller for the climber leg motor -- not in use. 
     */
    public static SpeedController climberLegMotor;

    /**
     * Speed controller for the wrist motor. 
     */
    public static SpeedController wristMotor;

    /**
     * Speed controller for the top intake motor. 
     */
    public static SpeedController intakeTopMotor;

    /**
     * Speed controller for the bottom intake motor. 
     */
    public static SpeedController intakeBottomMotor;

    /**
     * Subsystem for the arm. 
     */
    public static Arm arm;

    /**
     * Subsystem for the climber arm -- not in use. 
     */
    public static ClimberArm climberArm;

    /**
     * Subsystem for the climber leg -- not in use. 
     */
    public static ClimberLeg climberLeg;

    /**
     * Subsystem for the climber wheel -- not in use. 
     */
    public static ClimberWheel climberWheel;

    /**
     * Subsystem for the wrist. 
     */
    public static Wrist wrist;

    /**
     * Subsystem for the intake. 
     */
    public static Intake intake;

    /**
     * Solenoid for the gear shifter. 
     */
    public static Solenoid solenoidGearShifter;

    /**
     * Solenoid for the hatch shifter. 
     */
    public static Solenoid solenoidHatchShifter;

    /**
     * Solenoid for the light. 
     */
    public static Solenoid solenoidLight;   

    /**
     * Potentiometer on the wrist. 
     */
    public static AnalogPotentiometer wristPot;

    /**
     * Potentiometer on the arm. 
     */
    public static AnalogPotentiometer armPot;

    /**
     * The NavX sensor used to read angle (yaw) values. 
     */
    public static AHRS ahrs;

    /**
     * Ultrasonic sensor. 
     */
    public static Ultrasonic ultra;
    
    /**
     * Encoder on the climber.
     */
    public static Encoder climb_enc;

    /**
     * Encoder on the left side of the drive train. 
     */
    public static Encoder encLeft;

    /**
     * Encoder on the right side of the drive train. 
     */
    public static Encoder encRight;

    /**
     * Camera server that allows the transfer of camera data to NetworkTables, 
     * which sends data back and forth from the robot to the driver station. 
     */
    public static CameraServer camServer;

    /**
     * USB camera one. 
     */
    public static UsbCamera cam1;

    /**
     * USB camera two -- not in use. 
     */
    public static UsbCamera cam2;

    /**
     * the x coordinate of the robot -- not in use. 
     */
    public static double robotX = 0;

    /**
     * the y coordinate of the robot -- not in use.
     */
    public static double robotY = 0;

    /**
     * The previous value stored in the left encoder -- not in use. 
     */
    public static double encLeftPrev = 0;

    /**
     * The previous value stored in the right encoder -- not in use. 
     */
    public static double encRightPrev = 0;
    
    /**
     * The distance travelled by the left encoder -- not in use. 
     */
    public static double encLeftDist = 0;

    /**
     * The distance travelled by the right encoder -- not in use. 
     */
    public static double encRightDist = 0;


    /**
     * This class in called at the beginning of the robot program, once
     * there is comms with the robot. All motors, subsystems, 
     * sensors, preset command buttons, etc, used throughout the program 
     * should be initialized here. 
     * 
     * @see edu.wpi.first.wpilibj.IterativeRobotBase#robotInit()
     */
    @Override
    public void robotInit()
    {
        //drive train speed controllers
        driveLeftTop = new WPI_VictorSPX(Config.LEFT_TOP_MOTOR);
        // driveLeftMiddle = new WPI_VictorSPX(Config.LEFT_MIDDLE_MOTOR);
        driveLeftBottom = new WPI_VictorSPX(Config.LEFT_BOTTOM_MOTOR);
        // driveRightTop = new WPI_VictorSPX(Config.RIGHT_TOP_MOTOR);
        driveRightMiddle = new WPI_VictorSPX(Config.RIGHT_MIDDLE_MOTOR);
        driveRightBottom = new WPI_VictorSPX(Config.RIGHT_BOTTOM_MOTOR);
        
        //speed controller groups for the drive train. 
        driveLeft = new SpeedControllerGroup(driveLeftTop,driveLeftBottom);
        driveRight = new SpeedControllerGroup(driveRightMiddle,driveRightBottom);

        //drive train subsystem
        drive = new DifferentialDrive(driveLeft, driveRight);
        driveTrain = new DriveTrain(driveLeft, driveRight, drive);

        //climber arm
        climberArmMotor = new WPI_VictorSPX(Config.CLIMBER_ARM_MOTOR_LEFT);
        climberArmMotor1 = new WPI_VictorSPX(Config.CLIMBER_ARM_MOTOR_RIGHT);
        climberArm = new ClimberArm();

        //clumber wheel
        climberWheelMotor = new WPI_VictorSPX(Config.CLIMBER_WHEEL_MOTOR);
        climberWheel = new ClimberWheel();

        //climber leg
        climberLegMotor = new WPI_VictorSPX(Config.CLIMBER_LEG_MOTOR);
        climberLeg = new ClimberLeg();

        //arm 
        armMotor = new WPI_VictorSPX(Config.ARM_MOTOR);
        arm = new Arm(armMotor);

        //wrist
        wristMotor = new WPI_VictorSPX(Config.WRIST_MOTOR);
        wrist = new Wrist(wristMotor);

        //intake
        intakeTopMotor = new WPI_VictorSPX(Config.INTAKE_TOP_MOTOR);
        intakeBottomMotor = new WPI_VictorSPX(Config.INTAKE_BOTTOM_MOTOR);
        intake = new Intake(intakeTopMotor, intakeBottomMotor);

        // ahrs = new AHRS(SPI.Port.kMXP);

        //ultrasonic sensor
        ultra = new Ultrasonic(Config.ULTRA_PORT2, Config.ULTRA_PORT1);
        ultra.setAutomaticMode(true);
      
        //solenoids
        solenoidGearShifter = new Solenoid(Config.PCM_ID, Config.SOLENOID_ONE_CHANNEL);
        solenoidLight = new Solenoid(Config.PCM_ID, Config.SOLENOID_LIGHT_CHANNEL);
        solenoidHatchShifter = new Solenoid(Config.PCM_ID, Config.SOLENOID_HATCH_CHANNEL);
        solenoidLight.set(true);

        //sensors
        climb_enc = new Encoder(Config.ENCODER_CLIMB_PORT_1, Config.ENCODER_CLIMB_PORT_2, false, EncodingType.k4X);

        wristPot = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT_WRIST, 121);
        armPot = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT_ARM, 118);

        encRight = new Encoder(Config.ENCODER_RIGHT_PORT_1, Config.ENCODER_RIGHT_PORT_2);
        encRight.setDistancePerPulse(Math.PI * 0.1524 * 360); // cicrumference divided by 360 (feet)
        encLeft = new Encoder(Config.ENCODER_LEFT_PORT_1, Config.ENCODER_LEFT_PORT_2);
        encLeft.setDistancePerPulse(Math.PI * 0.1524 * 360); // cicrumference divided by 360 (feet)

        //cameras
        camServer = CameraServer.getInstance();

        cam1 = camServer.startAutomaticCapture(0);
        cam1.setBrightness (35);
        cam1.setFPS(30);
        cam1.setResolution(100, 100);

        cam2 = camServer.startAutomaticCapture(1);
        cam2.setBrightness(40);
        cam2.setFPS(30);
        cam2.setResolution(100, 100);
        
        // ArrayList<Pose2d> p = new ArrayList<>();
        // mp = new MotionProfile(p);

        //presets for the Shuffleboard
        Shuffleboard.getTab("SmartDashboard").add("Hatch LV1", new HatchLevel1()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Hatch LV2", new HatchLevel2()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").add("Cargo LV1", new CargoLevel1()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Cargo LV2", new CargoLevel2()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").add("Intake Cargo", new IntakeCargoLoadingStation()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Intake Hatch", new IntakeHatchLoadingStation()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").add("Travel", new Travel()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Cargo Ship Cargo", new CargoShipCargo()).withWidget(BuiltInWidgets.kCommand);
        
        // Shuffleboard.getTab("SmartDashboard").add("Climber Leg Level 2", new MoveClimberLegAuto(100)).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Climber Leg Level 3", new MoveClimberLegAuto(200)).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").add("Cargo Floor", new CargoFloor()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Hatch Floor", new IntakeHatchFloor()).withWidget(BuiltInWidgets.kCommand);

        // ahrs.reset();

        //operator interface
        oi = new OI();
    }

    /**
     * Code called periodically during the robot program should go here. 
     * 
     * @see edu.wpi.first.wpilibj.IterativeRobotBase#robotPeriodic()
     */
    @Override
    public void robotPeriodic()
    {
        //runs commands and makes sure that commands with conflicting requirements do not run at once. 
        Scheduler.getInstance().run();

        //calculates the distance each encoder has travelled since the last iteration.
        double encLeftDist =  -encLeft.getDistance() - encLeftPrev;
        double encRightDist = encRight.getDistance() - encRightPrev;

        //resets the previous values. 
        encLeftPrev = -encLeft.getDistance();
        encRightPrev = encRight.getDistance();

        //averages the distance values to get a read of the robot movement, when in a straight line. 
        double distance = (encLeftDist + encRightDist)/2;

        //based on the distance (the hypotenuse), calculates the robotX and robotY values. 
        robotX += Math.cos(Math.toRadians(ahrs.getYaw())) * distance;
        robotY += Math.sin(Math.toRadians(ahrs.getYaw())) * distance;

        //accesses the NetworkTable for vision processing
        NetworkTableInstance netInstance = NetworkTableInstance.getDefault();
        NetworkTable netTable = netInstance.getTable("ChickenVision");
        if(netTable!=null)
        {
            //accesses the value to indicate that the tape is detected. 
            boolean tapeDetected = netTable.getEntry("tapeDetected").getBoolean(false);

            //
            double yDist = 0;
            if(tapeDetected) 
            {
                //accesses the tape yaw value (the angle the tape is viewed from). 
                double tapeYaw = netTable.getEntry("tapeYaw").getDouble(-1);
                
                //distance on the "x-axis" to the tape, as read by the ultrasonic sensor. 
                double xDist = ultra.getRangeInches() + 15.5;

                //calculates the distance on the y-axis to the tape (sideways movement). 
                yDist = Math.tan(Math.toRadians(tapeYaw)) * xDist;

                yDist += 9.7; //offset in inches
            }

            //displays the sideways distance to the tape, if the tape was detected. 
            SmartDashboard.putString("Tape Aim", tapeDetected ? yDist+"" : "No Tape Detected");

        }

        //displays information about the robot periodically to the Smartdashboard
        SmartDashboard.putNumber("climb encoder", climb_enc.get());
        SmartDashboard.putNumber("arm pot wpi angle", armPot.get());
        SmartDashboard.putNumber("wrist pot wpi angle", wristPot.get());

        SmartDashboard.putNumber("ahrs yaw", ahrs.getYaw());
        SmartDashboard.putNumber("ahrs velocity", ahrs.getVelocityX());
        SmartDashboard.putNumber("ahrs accel", ahrs.getRawAccelX());

        SmartDashboard.putNumber("robotX", robotX);
        SmartDashboard.putNumber("robotY", robotY);

        SmartDashboard.putNumber("Ultrasonic", ultra.getRangeInches());
        SmartDashboard.putBoolean("LowLowGear", DriveTrain.lowlowgear);
        SmartDashboard.putBoolean("Distance Hatch", ultra.getRangeInches() >= 11 && ultra.getRangeInches() <= 13);
        SmartDashboard.putBoolean("Distance Cargo Loading", ultra.getRangeInches() >= 19 && ultra.getRangeInches() <= 21);
        SmartDashboard.putBoolean("Distance Ship Shoot", ultra.getRangeInches() >= 23 && ultra.getRangeInches() <= 25);
    }

    /**
     * Called once at the beginning of the autonomous period. Code to initialize autonomous
     * commands should go here. 
     * 
     * @see edu.wpi.first.wpilibj.IterativeRobotBase#autonomousInit()
     */
    @Override
    public void autonomousInit()
    {

    }

    /**
     * Called periodically during the autonomous period. Code to update variables and display
     * sensor values during autonomous should go here. 
     * 
     * @see edu.wpi.first.wpilibj.IterativeRobotBase#autonomousPeriodic()
     */
    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
    }

    /**
     * Called periodically during the teleoperated period. Code to update variables and 
     * display values during the teleop period should go here. 
     * 
     * @see edu.wpi.first.wpilibj.IterativeRobotBase#teleopPeriodic()
     */
    @Override
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
    }

    /**
     * Called once at the start of the teleoperated period. Code to initalize variables
     * during for the teleoperated period should go here. 
     * 
     * @see edu.wpi.first.wpilibj.IterativeRobotBase#teleopInit()
     */
    @Override
    public void teleopInit()
    {

    }

    /**
     * Called periocially while the robot is in test mode. 
     * 
     * @see edu.wpi.first.wpilibj.IterativeRobotBase#testPeriodic()
     */
    @Override
    public void testPeriodic()
    {
        Scheduler.getInstance().run();
    }
}
