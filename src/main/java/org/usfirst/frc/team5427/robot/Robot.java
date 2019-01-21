/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5427.robot.commands.AutoPath;
import org.usfirst.frc.team5427.robot.commands.ContinousFull;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
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

  public static AHRS ahrs;

  public static Encoder encLeft;
  public static Encoder encRight;


  public AutoPath path;

  public ContinousFull cont;


  @Override
  public void robotInit() {
    //initialize ahrs
    ahrs = new AHRS(SPI.Port.kMXP);

    //make talon motors
    driveFrontLeft = new Talon(Config.FRONT_LEFT_MOTOR);
    driveFrontRight = new Talon(Config.FRONT_RIGHT_MOTOR);
    driveRearLeft = new Talon(Config.REAR_LEFT_MOTOR);
    driveRearRight = new Talon(Config.REAR_RIGHT_MOTOR);

    
    
    //make speed controller groups
    driveLeft = new SpeedControllerGroup(driveFrontLeft, driveRearLeft);
    driveRight = new SpeedControllerGroup(driveFrontRight, driveRearRight);

    // Robot.driveRight.setInverted(true);

    //initialize drive train with speed controller groups
    drive = new DifferentialDrive(driveLeft, driveRight);
  
    driveTrain = new DriveTrain(driveLeft, driveRight, drive, ahrs);

 

    //initialize encoders, set distance per pulse, reset
    encLeft = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    encLeft.setDistancePerPulse(Config.ENCODER_DISTANCE_OFFSET*(6.00 * Math.PI / 360));
    encLeft.reset();

    encRight = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
    encRight.setDistancePerPulse(Config.ENCODER_DISTANCE_OFFSET*(6.00 * Math.PI / 360)); 
    encRight.reset(); 

    
    //blue left to cargo 1st
    // path = new AutoPath("Motion "+"0 5 0 "+"1.75 5 0"+
    //                   "\nTurnToAngle 0" + 
    //                   "\nMotion "+"1.75 5 0 " + "3 6 0 " + "4 4.75 -90"); 

    
    //blue left to rocket ship
    // path = new AutoPath("Motion "+"0 5 0 "+"1.75 5 30"+
    // "\nTurnToAngle 30" + 
    // "\nMotion "+"1.75 5 30 " + "3.25 7.75 30"); 

    path = new AutoPath("Motion 0 0 0 2 0 0");


          

    cont = new ContinousFull();
    

    oi = new OI();
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
    double distance_covered = ((double)(Robot.encLeft.get()) / 360)
    * 0.1524 * Math.PI;
    SmartDashboard.putNumber("distance cov", distance_covered);
    double distance_covered_r = ((double)(Robot.encRight.get()) / 360)
    * 0.1524 * Math.PI;
    SmartDashboard.putNumber("distance cov r", distance_covered_r);
    double gyro_heading = ahrs.getYaw();    // Assuming the gyro is giving a value in degrees    
    SmartDashboard.putNumber("Gyro", gyro_heading);
    
    // SmartDashboard.putNumber("Velocity X", Robot.ahrs.getVelocityX());
    // SmartDashboard.putNumber("Velocity Y", Robot.ahrs.getVelocityY());
    // SmartDashboard.putNumber("Accel X", Robot.ahrs.getRawAccelX());
    // SmartDashboard.putNumber("Accel Y", Robot.ahrs.getRawAccelY());
    // SmartDashboard.putNumber("Displacement X", ahrs.getDisplacementX());
    // SmartDashboard.putNumber("Displacement Y", ahrs.getDisplacementY());
    // SmartDashboard.putNumber("Encoder L", encLeft.get());
    // SmartDashboard.putNumber("Encoder R", encRight.get());
    
  }



  @Override
  public void autonomousInit() {
    //execute each auto direction given in the passed data param upon path initialization
     path.executeAutoActions();
    //  cont.start();
     encLeft.reset();
     encRight.reset(); 
     ahrs.reset();  
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