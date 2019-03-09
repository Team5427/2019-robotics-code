/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Waypoint;

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


    public static Ultrasonic ultra;
    
    public static Encoder elevator_enc;
    public static Encoder drive_left_enc;
    public static Encoder drive_right_enc;




    public static CameraServer camServer;
    public static UsbCamera cam1;
    public static UsbCamera cam2;

    public static MoveElevatorAuto moveWristAuto;

    public static PresetPath pathWristDown;



    @Override
    public void robotInit()
    {
        driveLeftTop = new WPI_VictorSPX(Config.LEFT_TOP_MOTOR);
        driveLeftMiddle = new WPI_VictorSPX(Config.LEFT_MIDDLE_MOTOR);
        driveLeftBottom = new WPI_VictorSPX(Config.LEFT_BOTTOM_MOTOR);
        driveRightTop = new WPI_VictorSPX(Config.RIGHT_TOP_MOTOR);
        driveRightMiddle = new WPI_VictorSPX(Config.RIGHT_MIDDLE_MOTOR);
        driveRightBottom = new WPI_VictorSPX(Config.RIGHT_BOTTOM_MOTOR);
        
        driveLeft = new SpeedControllerGroup(driveLeftTop,driveLeftMiddle,driveLeftBottom);
        driveRight = new SpeedControllerGroup(driveRightTop,driveRightMiddle,driveRightBottom);

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

        solenoidOne = new Solenoid(Config.PCM_ID, Config.SOLENOID_ONE_CHANNEL);

        elevator_enc = new Encoder(Config.ELEVATOR_PORT_1, Config.ELEVATOR_PORT_2, false, EncodingType.k4X);

        wristPot = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT_WRIST, 400);
        armPot = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT_ARM, 118);


        camServer = CameraServer.getInstance();

        cam1 = camServer.startAutomaticCapture(0);
        cam1.setBrightness (35);
        cam1.setResolution(100, 100);
        cam1.setFPS(30);


        cam2 = camServer.startAutomaticCapture(1);
        cam1.setBrightness(30);
        cam2.setResolution(200, 200);
        cam2.setFPS(30);
        


        Shuffleboard.getTab("SmartDashboard").add("Hatch LV1", new HatchLevel1()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Hatch LV2", new HatchLevel2()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").add("Cargo LV1", new CargoLevel1()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Cargo LV2", new CargoLevel2()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Cargo LV3", new CargoLevel3()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").add("Intake Cargo Ground", new IntakeCargoLevel1()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Intake Hatch Loading Station", new IntakeHatchLoadingStation()).withWidget(BuiltInWidgets.kCommand);

        Shuffleboard.getTab("SmartDashboard").add("Travel", new Travel()).withWidget(BuiltInWidgets.kCommand);
        Shuffleboard.getTab("SmartDashboard").add("Cargo Ship Cargo", new CargoShipCargo()).withWidget(BuiltInWidgets.kCommand);

        oi = new OI();
    }

    @Override
    public void robotPeriodic()
    {
        Scheduler.getInstance().run();
        

        SmartDashboard.putNumber("Enc Elevator", elevator_enc.get());

        SmartDashboard.putNumber("arm pot wpi angle", armPot.get());

        SmartDashboard.putNumber("wrist pot wpi angle", wristPot.get());


    }

    @Override
    public void autonomousInit()
    {
        // moveWristAuto.start();
        // new RotateWristAuto(-Config.WRIST_SPEED_UP, Config.correctedAngle).start();
        // new RotateArmAuto(-Config.ARM_SPEED_UP, Config.correctedAngle).start();
        // new MoveElevatorAuto(Config.ELEVATOR_SPEED_DOWN, 20).start();
        pathWristDown.executeAutoActions();
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
        Scheduler.getInstance().run();
        // moveWristAuto.cancel();
        // moveWristAuto.close();
    }

    @Override
    public void testPeriodic()
    {
        Scheduler.getInstance().run();
    }
}
