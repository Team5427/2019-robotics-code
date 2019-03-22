/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.PresetPath;
import org.usfirst.frc.team5427.robot.commands.auto.presets.*;

import org.usfirst.frc.team5427.robot.subsystems.Arm;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.Elevator;
import org.usfirst.frc.team5427.robot.subsystems.Intake;
import org.usfirst.frc.team5427.robot.subsystems.Wrist;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot
{

    public static OI oi;

    public static SpeedController driveLeftTop;
    public static SpeedController driveLeftMiddle;
    public static SpeedController driveLeftBottom;
    public static SpeedController driveRightTop;
    public static SpeedController driveRightMiddle;
    public static SpeedController driveRightBottom;

    public static SpeedControllerGroup driveLeft;
    public static SpeedControllerGroup driveRight;

    public static DriveTrain driveTrain;
    public static DifferentialDrive drive;

    public static SpeedController elevatorMotor;
    public static SpeedController armMotor;
    public static SpeedController wristMotor;
    public static SpeedController intakeTopMotor;
    public static SpeedController intakeBottomMotor;

    public static Elevator elevator;
    public static Arm arm;
    public static Wrist wrist;
    public static Intake intake;

    public static Solenoid solenoidOne;

    public static AnalogPotentiometer wristPot;
    public static AnalogPotentiometer armPot;

    public static AHRS ahrs;


    public static Ultrasonic ultra;
    
    public static Encoder elevator_enc;

    public static Encoder encLeft;
    public static Encoder encRight;




    public static CameraServer camServer;
    public static UsbCamera cam1;
    public static UsbCamera cam2;

    public static MoveElevatorAuto moveWristAuto;

    public static PresetPath pathWristDown;

    public static double robotX;
    public static double robotY;

    public static double distance;
    public static double encLeftPrev;
    public static double encRightPrev;
    
    public static double encLeftDist;
    public static double encRightDist;



    @Override
    public void robotInit()
    {
        driveLeftTop = new WPI_VictorSPX(Config.LEFT_TOP_MOTOR);
        // driveLeftMiddle = new WPI_VictorSPX(Config.LEFT_MIDDLE_MOTOR);
        driveLeftBottom = new WPI_VictorSPX(Config.LEFT_BOTTOM_MOTOR);
        // driveRightTop = new WPI_VictorSPX(Config.RIGHT_TOP_MOTOR);
        driveRightMiddle = new WPI_VictorSPX(Config.RIGHT_MIDDLE_MOTOR);
        driveRightBottom = new WPI_VictorSPX(Config.RIGHT_BOTTOM_MOTOR);
        
        driveLeft = new SpeedControllerGroup(driveLeftTop ,driveLeftBottom);
        driveRight = new SpeedControllerGroup(driveRightMiddle,driveRightBottom);

        drive = new DifferentialDrive(driveLeft, driveRight);
        driveTrain = new DriveTrain(driveLeft, driveRight, drive);

        elevatorMotor = new WPI_VictorSPX(Config.ELEVATOR_MOTOR);
        elevator = new Elevator(elevatorMotor);

        armMotor = new WPI_VictorSPX(Config.ARM_MOTOR);
        arm = new Arm(armMotor);

        wristMotor = new WPI_VictorSPX(Config.WRIST_MOTOR);
        wrist = new Wrist(wristMotor);

        intakeTopMotor = new WPI_VictorSPX(Config.INTAKE_TOP_MOTOR);
        intakeBottomMotor = new WPI_VictorSPX(Config.INTAKE_BOTTOM_MOTOR);
        intake = new Intake(intakeTopMotor, intakeBottomMotor);

        ahrs = new AHRS(SPI.Port.kMXP);

        ultra = new Ultrasonic(Config.ULTRA_PORT2, Config.ULTRA_PORT1);
        ultra.setAutomaticMode(true);
      

        solenoidOne = new Solenoid(Config.PCM_ID, Config.SOLENOID_ONE_CHANNEL);

        elevator_enc = new Encoder(Config.ELEVATOR_PORT_1, Config.ELEVATOR_PORT_2, false, EncodingType.k4X);

        wristPot = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT_WRIST, 121);
        armPot = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT_ARM, 118);

        encRight = new Encoder(Config.ENCODER_RIGHT_PORT_1, Config.ENCODER_RIGHT_PORT_2);
        encLeft = new Encoder(Config.ENCODER_LEFT_PORT_1, Config.ENCODER_LEFT_PORT_2);

        camServer = CameraServer.getInstance();

        cam1 = camServer.startAutomaticCapture(0);
        cam1.setBrightness (50);
        cam1.setResolution(200, 200);

        cam2 = camServer.startAutomaticCapture(1);
        cam2.setBrightness(50);
        cam2.setResolution(150, 100);
        
          

        


        // Shuffleboard.getTab("SmartDashboard").add("Hatch LV1", new HatchLevel1()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Ship Hatch LV1", new HatchLevel1()).withWidget(BuiltInWidgets.kCommand);
        
        // Shuffleboard.getTab("SmartDashboard").add("Hatch LV2", new HatchLevel2()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Hatch LV3", new HatchLevel3()).withWidget(BuiltInWidgets.kCommand);

        // Shuffleboard.getTab("SmartDashboard").add("Cargo LV1", new CargoLevel1()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Cargo LV2", new CargoLevel2()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Cargo LV3", new CargoLevel3()).withWidget(BuiltInWidgets.kCommand);

        // Shuffleboard.getTab("SmartDashboard").add("Cargo", new IntakeCargoLevel1()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Cargo Keep", new IntakeCargoLevel1Keep()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Intake Cargo", new IntakeCargoLoadingStation()).withWidget(BuiltInWidgets.kCommand);
        
        // Shuffleboard.getTab("SmartDashboard").add("Intake Hatch", new IntakeHatchLoadingStation()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Hatch", new IntakeHatchFloor()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Hatch Off", new LiftHatchOffLoadingStation()).withWidget(BuiltInWidgets.kCommand);

        // Shuffleboard.getTab("SmartDashboard").add("Travel", new Travel()).withWidget(BuiltInWidgets.kCommand);
        // Shuffleboard.getTab("SmartDashboard").add("Cargo Ship Cargo", new CargoShipCargo()).withWidget(BuiltInWidgets.kCommand);
        

        
                  Shuffleboard.getTab("SmartDashboard").getLayout("Rocket", "Grid Layout").add("Hatch LV1", new HatchLevel1()).withWidget(BuiltInWidgets.kCommand);        
        Shuffleboard.getTab("SmartDashboard").getLayout("Rocket", "Grid Layout").add("Hatch LV2", new HatchLevel2()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").getLayout("Rocket", "Grid Layout").add("Hatch LV3", new HatchLevel3()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").getLayout("Rocket", "Grid Layout").add("Cargo LV1", new CargoLevel1()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").getLayout("Rocket", "Grid Layout").add("Cargo LV2", new CargoLevel2()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").getLayout("Rocket", "Grid Layout").add("Cargo LV3", new CargoLevel3()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").getLayout("Ground", "Grid Layout").add("Cargo", new IntakeCargoLevel1()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").getLayout("Ground", "Grid Layout").add("Cargo Keep", new IntakeCargoLevel1Keep()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").getLayout("Ground", "Grid Layout").add("Hatch", new IntakeHatchFloor()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").getLayout("Loading Station", "Grid Layout").add("Intake Cargo", new IntakeCargoLoadingStation()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").getLayout("Loading Station", "Grid Layout").add("Intake Hatch", new IntakeHatchLoadingStation()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").getLayout("Loading Station", "Grid Layout").add("Hatch Off", new LiftHatchOffLoadingStation()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").getLayout("Ship", "Grid Layout").add("Cargo Ship Cargo", new CargoShipCargo()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").getLayout("Ship", "Grid Layout").add("Ship Hatch LV1", new HatchLevel1()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").add("Travel", new Travel()).withWidget(BuiltInWidgets.kCommand);

        
        ahrs.reset();

        oi = new OI();
    }

    @Override
    public void robotPeriodic()
    {
        Scheduler.getInstance().run();

        encLeftDist =  -encLeft.getDistance() - encLeftPrev;
        encRightDist = encRight.getDistance() - encRightPrev;

        encLeftPrev = -encLeft.getDistance();
        encRightPrev = encRight.getDistance();

        distance = (encLeftDist + encRightDist)/2;
        robotX += Math.cos(Math.toRadians(ahrs.getYaw())) * distance;
        robotY += Math.sin(Math.toRadians(ahrs.getYaw())) * distance;
        

        SmartDashboard.putNumber("Enc Elevator", elevator_enc.get());

        SmartDashboard.putNumber("arm pot wpi angle", armPot.get());

        SmartDashboard.putNumber("wrist pot wpi angle", wristPot.get());

        SmartDashboard.putNumber("ahrs yaw", ahrs.getYaw());
        
        SmartDashboard.putNumber("ahrs velocity", ahrs.getVelocityX());
        SmartDashboard.putNumber("ahrs accel", ahrs.getRawAccelX());

        SmartDashboard.putNumber("robotX", robotX);
        SmartDashboard.putNumber("robotY", robotY);


        SmartDashboard.putNumber("Ultrasonic", ultra.getRangeInches());
        SmartDashboard.putBoolean("LowLowGear", DriveTrain.lowlowgear);
        SmartDashboard.putBoolean("Distance Hatch", ultra.getRangeInches() >= 9 && ultra.getRangeInches() <= 11);
        SmartDashboard.putBoolean("Distance Cargo Loading", ultra.getRangeInches() >= 19 && ultra.getRangeInches() <= 21);
        SmartDashboard.putBoolean("Distance Ship Shoot", ultra.getRangeInches() >= 23 && ultra.getRangeInches() <= 25);



    }

    @Override
    public void autonomousInit()
    {
        // new ContinuousFull().start();
    }

    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit()
    {

    }

    @Override
    public void testPeriodic()
    {
        Scheduler.getInstance().run();
    }
}
