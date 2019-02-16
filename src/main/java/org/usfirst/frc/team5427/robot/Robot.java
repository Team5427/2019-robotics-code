/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import org.usfirst.frc.team5427.robot.commands.MoveElev;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;



public class Robot extends TimedRobot {

  public static DriveTrain driveTrain;
  public static DifferentialDrive drive;
  public static OI oi;

  public static SpeedController driveFrontLeft;
  public static SpeedController driveFrontRight;
  public static SpeedController driveRearLeft;
  public static SpeedController driveRearRight;

  public static SpeedControllerGroup driveLeft;
  public static SpeedControllerGroup driveRight;

  public static SpeedController elevator;

  public static Encoder encElevator;
  public static Encoder encElevator1;

  
  public static SpeedController arm;
  public static SpeedController wrist;

  public static AnalogPotentiometer rotationPotentiometerArm;
  public static AnalogPotentiometer rotationPotentiometerWrist;

  @Override
  public void robotInit() {
 

    //make talon motors
    driveFrontLeft = new Talon(Config.FRONT_LEFT_MOTOR);
    driveFrontRight = new Talon(Config.FRONT_RIGHT_MOTOR);
    driveRearLeft = new Talon(Config.REAR_LEFT_MOTOR);
    driveRearRight = new Talon(Config.REAR_RIGHT_MOTOR);

    
    //make speed controller groups
    driveLeft = new SpeedControllerGroup(driveFrontLeft, driveRearLeft);
    driveRight = new SpeedControllerGroup(driveFrontRight, driveRearRight);


    //make arm and wrist
    // arm = new Talon(Config.ARM_MOTOR);
    // wrist = new Talon(Config.WRIST_MOTOR);


    //initialize drive train with speed controller groups
    drive = new DifferentialDrive(driveLeft, driveRight);
  
    driveTrain = new DriveTrain(driveLeft, driveRight, drive);

    // rotationPotentiometerArm = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_ARM_PORT,Config.ROTATION_POTENTIOMETER_ARM_RANGE);
    // rotationPotentiometerWrist = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_WRIST_PORT,Config.ROTATION_POTENTIOMETER_WRIST_RANGE);

    // elevator = new Talon(Config.ELEV_MOTOR);

    encElevator = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    encElevator.setDistancePerPulse(0.1524*Math.PI/360);

    encElevator1 = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
    encElevator1.setDistancePerPulse(0.1524*Math.PI/360);
    
    Shuffleboard.getTab("SmartDashboard").add("Move Elevator 0", new MoveElev(0)).withWidget(BuiltInWidgets.kCommand);
    Shuffleboard.getTab("SmartDashboard").add("Move Elevator 1", new MoveElev(0.5)).withWidget(BuiltInWidgets.kCommand);
    Shuffleboard.getTab("SmartDashboard").add("Move Elevator 2", new MoveElev(1)).withWidget(BuiltInWidgets.kCommand);
    Shuffleboard.getTab("SmartDashboard").add("Move Elevator 3", new MoveElev(1.5)).withWidget(BuiltInWidgets.kCommand);


    oi = new OI();
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }



  @Override
  public void autonomousInit() {
    
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    

  }
  
  @Override
  public void disabledInit() {

  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void teleopInit() {
    Robot.encElevator.reset();
    Robot.encElevator1.reset();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}