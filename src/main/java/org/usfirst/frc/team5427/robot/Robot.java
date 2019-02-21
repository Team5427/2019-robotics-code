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
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot
{

    public static OI oi;

    public static SpeedController driveLeftTop;
    public static SpeedController driveLeftMiddle;
    public static SpeedController driveLeftBottom;
    public static SpeedController driveRightTop;
    public static SpeedController driveRightMiddle;
    public static SpeedController driveRightBottom;

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

    public static Solenoid solenoidOne;
    public static AnalogPotentiometer rotationPotentiometer;

    @Override
    public void robotInit()
    {
        driveLeftTop = new WPI_VictorSPX(Config.LEFT_TOP_MOTOR);
        driveLeftMiddle = new WPI_VictorSPX(Config.LEFT_MIDDLE_MOTOR);
        driveLeftBottom = new WPI_VictorSPX(Config.LEFT_BOTTOM_MOTOR);
        driveRightTop = new WPI_VictorSPX(Config.RIGHT_TOP_MOTOR);
        driveRightMiddle = new WPI_VictorSPX(Config.RIGHT_MIDDLE_MOTOR);
        driveRightBottom = new WPI_VictorSPX(Config.RIGHT_BOTTOM_MOTOR);
        
        driveLeft = new SpeedControllerGroup(driveLeftTop,driveLeftMiddle,driveLeftBottom);
        driveRight = new SpeedControllerGroup(driveRightTop,driveRightMiddle,driveRightBottom);

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

        solenoidOne = new Solenoid(Config.PCM_ID, Config.SOLENOID_ONE_CHANNEL);

        rotationPotentiometer = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT,Config.ROTATION_POTENTIOMETER_RANGE);

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
