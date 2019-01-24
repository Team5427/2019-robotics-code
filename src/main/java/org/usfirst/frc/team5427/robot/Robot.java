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
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
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

  public static Solenoid solenoidOne;

  @Override
  public void robotInit() {
      driveFrontLeft = new Talon(Config.FRONT_LEFT_MOTOR);
      driveFrontRight = new Talon(Config.FRONT_RIGHT_MOTOR);
      driveRearLeft = new Talon(Config.REAR_LEFT_MOTOR);
      driveRearRight = new Talon(Config.REAR_RIGHT_MOTOR);

      driveLeft = new SpeedControllerGroup(driveFrontLeft, driveRearLeft);
      driveRight = new SpeedControllerGroup(driveFrontRight, driveRearRight);

      drive = new DifferentialDrive(driveLeft, driveRight);

      driveTrain = new DriveTrain(driveLeft, driveRight, drive);

      solenoidOne = new Solenoid(Config.PCM_ID, Config.SOLENOID_ONE_CHANNEL);

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
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}