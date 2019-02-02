/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.usfirst.frc.team5427.robot.subsystems.Arm;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.Elevator;
import org.usfirst.frc.team5427.robot.subsystems.Intake;
import org.usfirst.frc.team5427.robot.subsystems.Wrist;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot
{

    public static OI oi;

    public static SpeedController driveLeftFront;
    public static SpeedController driveLeftMiddle;
    public static SpeedController driveLeftRear;
    public static SpeedController driveRightFront;
    public static SpeedController driveRightMiddle;
    public static SpeedController driveRightRear;

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

    @Override
    public void robotInit()
    {
        driveLeftFront = new WPI_VictorSPX(Config.FRONT_LEFT_MOTOR);
        driveLeftMiddle = new WPI_VictorSPX(Config.MIDDLE_LEFT_MOTOR);
        driveLeftRear = new WPI_VictorSPX(Config.REAR_LEFT_MOTOR);
        driveRightFront = new WPI_VictorSPX(Config.FRONT_RIGHT_MOTOR);
        driveRightMiddle = new WPI_VictorSPX(Config.MIDDLE_RIGHT_MOTOR);
        driveRightRear = new WPI_VictorSPX(Config.REAR_RIGHT_MOTOR);

        driveLeft = new SpeedControllerGroup(driveLeftFront,driveLeftMiddle,driveLeftRear);
        driveRight = new SpeedControllerGroup(driveRightFront,driveRightMiddle,driveRightRear);

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

        // rotationPotentiometer = new
        // AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT,Config.ROTATION_POTENTIOMETER_RANGE);

        oi = new OI();
    }

    @Override
    public void robotPeriodic()
    {
        Scheduler.getInstance().run();
        // SmartDashboard.putNumber("Potentiometer Angle",rotationPotentiometer.get());
    }

    @Override
    public void autonomousInit()
    {

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
    public void testPeriodic()
    {
        Scheduler.getInstance().run();
    }
}
