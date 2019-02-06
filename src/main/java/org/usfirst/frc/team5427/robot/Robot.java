/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import org.usfirst.frc.team5427.lib.trajectory.Trajectory;
import org.usfirst.frc.team5427.lib.trajectory.TrajectoryGenerator;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.Intake;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
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

        // rotationPotentiometer = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT,Config.ROTATION_POTENTIOMETER_RANGE);
        TrajectoryGenerator.Config c = new TrajectoryGenerator.Config();
        c.dt = 0.02;
        c.max_vel = 10;
        c.max_acc = 3;
        c.max_jerk = 2;
        Trajectory traj = TrajectoryGenerator.generate(c, TrajectoryGenerator.AutomaticStrategy, 0, 0, 10, 5, 0);

        oi = new OI();
    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
        // SmartDashboard.putNumber("Potentiometer Angle",rotationPotentiometer.get());
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
