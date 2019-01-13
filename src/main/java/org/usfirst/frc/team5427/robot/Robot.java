/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5427.AutoPath;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.PIDTurn;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {

  public static DriveTrain driveTrain;
  public static DifferentialDrive drive;
  public static OI oi;
  public static AHRS ahrs;
  public static SpeedController driveFrontLeft;
  public static SpeedController driveFrontRight;
  public static SpeedController driveRearLeft;
  public static SpeedController driveRearRight;

  public static SpeedControllerGroup driveLeft;
  public static SpeedControllerGroup driveRight;

  public static Encoder encLeft;
  public static Encoder encRight;

  public static PIDTurn pidTurn;

  public AutoPath path;

  @Override
  public void robotInit() {
      driveFrontLeft = new Talon(Config.FRONT_LEFT_MOTOR);
      driveFrontRight = new Talon(Config.FRONT_RIGHT_MOTOR);
      driveRearLeft = new Talon(Config.REAR_LEFT_MOTOR);
      driveRearRight = new Talon(Config.REAR_RIGHT_MOTOR);

      driveLeft = new SpeedControllerGroup(driveFrontLeft, driveRearLeft);
      driveRight = new SpeedControllerGroup(driveFrontRight, driveRearRight);

      drive = new DifferentialDrive(driveLeft, driveRight);
      ahrs = new AHRS(SPI.Port.kMXP);
      driveTrain = new DriveTrain(driveLeft, driveRight, drive);

      encLeft = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
      encLeft.setDistancePerPulse(Config.ENCODER_DISTANCE_OFFSET*(6.00 * Math.PI / 360)); 
      encLeft.reset();
  
      encRight = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
      encRight.setDistancePerPulse(Config.ENCODER_DISTANCE_OFFSET*(6.00 * Math.PI / 360)); 
      encRight.reset();

      pidTurn = new PIDTurn(ahrs);
      
      //OPTIONS: Motion(set of points -> x,y,heading), TurnToAngle (angle)
      path = new AutoPath("Motion "+"0 0 0 "+ "0 5 0 ");
      path.parseDataIntoActions();

      oi = new OI();
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Velocity X", ahrs.getVelocityX());
    SmartDashboard.putNumber("Velocity Y", ahrs.getVelocityY());
    
  }

  @Override
  public void autonomousInit() {
    path.executeAutoActions();
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