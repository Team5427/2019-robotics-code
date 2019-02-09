/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5427.Networking.client.Client;
import org.usfirst.frc.team5427.robot.commands.AutoPath;
import org.usfirst.frc.team5427.robot.commands.ContinousFull;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.Intake;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Robot extends TimedRobot {
    public static DriveTrain driveTrain;
    public static DifferentialDrive drive;
    public static OI oi;
    // public static AnalogPotentiometer rotationPotentiometer;

    public static SpeedController driveFrontLeft;
    public static SpeedController driveFrontRight;
    public static SpeedController driveRearLeft;
    public static SpeedController driveRearRight;

    public static SpeedControllerGroup driveLeft;
    public static SpeedControllerGroup driveRight;

    public static SpeedController intakeTop;
    public static SpeedController intakeBottom;

    public static Intake intake;
    public static Ultrasonic ultra;

    public static AHRS ahrs;

    public static Encoder encLeft;
    public static Encoder encRight;


    public static AutoPath path;

    public static ContinousFull cont;


    public static Client client;


    @Override
    public void robotInit() {
        client = new Client();
        client.start();

        ultra = new Ultrasonic(3,2);
        ultra.setAutomaticMode(true);
    
        //initialize ahrs
        ahrs = new AHRS(SPI.Port.kMXP);
        ahrs.reset();

        //make talon motors
        driveFrontLeft = new PWMVictorSPX(Config.FRONT_LEFT_MOTOR);
        driveFrontRight = new PWMVictorSPX(Config.FRONT_RIGHT_MOTOR);
        driveRearLeft = new PWMVictorSPX(Config.REAR_LEFT_MOTOR);
        driveRearRight = new PWMVictorSPX(Config.REAR_RIGHT_MOTOR);

        
        
        //make speed controller groups
        driveLeft = new SpeedControllerGroup(driveFrontLeft, driveRearLeft);
        driveRight = new SpeedControllerGroup(driveFrontRight, driveRearRight);


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

        intakeTop = new PWMVictorSPX(Config.INTAKE_TOP_MOTOR);
        intakeBottom = new PWMVictorSPX(Config.INTAKE_BOTTOM_MOTOR);
        intake = new Intake(intakeTop,intakeBottom);

        
        path = new AutoPath("Motion 0 0 0 1 0 0");
        

        oi = new OI();
    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();  
    }



    @Override
    public void autonomousInit() {
        encLeft.reset();
        encRight.reset(); 
        ahrs.reset();  

        //execute each auto direction given in the passed data param upon path initialization
        path.executeAutoActions();
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
