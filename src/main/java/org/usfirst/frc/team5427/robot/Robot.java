/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.Intake;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
    public static DriveTrain driveTrain;
    public static DifferentialDrive drive;
    public static OI oi;
    // public static AnalogPotentiometer rotationPotentiometer;
    public static AHRS ahrs;

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

    @Override
    public void robotInit() {
        ultra = new Ultrasonic(3,2);
        ultra.setAutomaticMode(true);

        driveFrontLeft = new PWMVictorSPX(Config.FRONT_LEFT_MOTOR);
        driveFrontRight = new PWMVictorSPX(Config.FRONT_RIGHT_MOTOR);
        driveRearLeft = new PWMVictorSPX(Config.REAR_LEFT_MOTOR);
        driveRearRight = new PWMVictorSPX(Config.REAR_RIGHT_MOTOR);

        driveLeft = new SpeedControllerGroup(driveFrontLeft, driveRearLeft);
        driveRight = new SpeedControllerGroup(driveFrontRight, driveRearRight);

        drive = new DifferentialDrive(driveLeft, driveRight);
        driveTrain = new DriveTrain(driveLeft, driveRight, drive);

        intakeTop = new PWMVictorSPX(Config.INTAKE_TOP_MOTOR);
        intakeBottom = new PWMVictorSPX(Config.INTAKE_BOTTOM_MOTOR);
        intake = new Intake(intakeTop,intakeBottom);

        
        ahrs = new AHRS(SPI.Port.kMXP);
        // rotationPotentiometer = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT,Config.ROTATION_POTENTIOMETER_RANGE);

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
