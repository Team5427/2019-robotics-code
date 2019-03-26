/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import java.util.ArrayList;

import com.kauailabs.navx.frc.AHRS;

import org.ghrobotics.lib.commands.FalconSubsystem;
import org.ghrobotics.lib.localization.TankEncoderLocalization;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.subsystems.drive.*;
import org.ghrobotics.lib.utils.SourceKt;
import org.usfirst.frc.team5427.robot.auto.MotionProfile;
import org.usfirst.frc.team5427.robot.auto.Pose2D;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.Intake;
import org.usfirst.frc.team5427.util.Config;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    public static Encoder encLeft;
    public static Encoder encRight;

    public static AHRS ahrs;

    public static MotionProfile mp;

    public static Intake intake;

    public static double robotX;
    public static double robotY;

    public static double distance;
    public static double encLeftPrev;
    public static double encRightPrev;

    public static double encLeftDist;
    public static double encRightDist;

    // public static Drivetrain dr;

    @Override
    public void robotInit() {
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

        encLeft = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        encLeft.setDistancePerPulse(0.5 * Math.PI/360);

        encRight = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
        encRight.setDistancePerPulse(0.5 * Math.PI/360);

        ahrs = new AHRS(SPI.Port.kMXP);
        ahrs.reset();

        robotX = 0;
        robotY = 0;

        ArrayList<Pose2d> p = new ArrayList<>();
        p.add(new Pose2D(0,0,0).pose);
        p.add(new Pose2D(500,0,0).pose);
        

        mp = new MotionProfile(p);

        // rotationPotentiometer = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT,Config.ROTATION_POTENTIOMETER_RANGE);

        oi = new OI();
    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
        // SmartDashboard.putNumber("Potentiometer Angle",rotationPotentiometer.get());
        encLeftDist =  -encLeft.getDistance() - encLeftPrev;
        encRightDist = encRight.getDistance() - encRightPrev;

        encLeftPrev = -encLeft.getDistance();
        encRightPrev = encRight.getDistance();

        distance = (encLeftDist + encRightDist)/2;
        robotX += Math.cos(Math.toRadians(ahrs.getYaw())) * distance;
        robotY += Math.sin(Math.toRadians(ahrs.getYaw())) * distance;
        SmartDashboard.putNumber("encLeft", encLeftDist);
        SmartDashboard.putNumber("encRight", encRightDist);

        SmartDashboard.putNumber("distance", distance);
        SmartDashboard.putNumber("ahrs", ahrs.getYaw());

        SmartDashboard.putNumber("robotX", robotX);
        SmartDashboard.putNumber("robotY", robotY);


    }

    @Override
    public void autonomousInit() {
        mp.start();
        // dr = new Drivetrain(0.65, 0.02, 0, 0, 0);
        encRight.reset();
        encLeft.reset();
        ahrs.reset();
        robotX = 0;
        robotY = 0;
        encLeftDist =  0;
        encRightDist = 0;

        encLeftPrev = 0;
        encRightPrev = 0;

        distance = 0;
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
