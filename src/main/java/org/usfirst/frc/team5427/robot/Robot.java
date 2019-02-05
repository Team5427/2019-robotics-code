/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


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
    arm = new Talon(Config.ARM_MOTOR);
    wrist = new Talon(Config.WRIST_MOTOR);


    //initialize drive train with speed controller groups
    drive = new DifferentialDrive(driveLeft, driveRight);
  
    driveTrain = new DriveTrain(driveLeft, driveRight, drive);

    rotationPotentiometerArm = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_ARM_PORT,Config.ROTATION_POTENTIOMETER_ARM_RANGE);
    rotationPotentiometerWrist = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_WRIST_PORT,Config.ROTATION_POTENTIOMETER_WRIST_RANGE);

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
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}