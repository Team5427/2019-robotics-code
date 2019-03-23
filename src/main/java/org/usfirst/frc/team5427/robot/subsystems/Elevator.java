/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem
{

    // 50 - 4050
    public SpeedController elevatorMotor;
    public int elevatorPosition;

    public Elevator(SpeedController elevatorMotor)
    {
        this.elevatorMotor = elevatorMotor;
    }

    public void setSpeed(double speed)
    {
        if( (speed < 0  && Robot.elevator_enc.get() > 0 )  || (speed > 0 && Robot.elevator_enc.get() < 3990)) {
            elevatorMotor.set(speed);
        }
        else {
            elevatorMotor.set(0);
        }
    }

    public void stop()
    {
        Robot.elevator.setSpeed(0);
    }

    public int getElevatorPosition()
    {
        return elevatorPosition;
    }

    public void setElevatorPosition(int elevatorPosition)
    {
        this.elevatorPosition = elevatorPosition;
    }

    @Override
    public void initDefaultCommand()
    {
    }

}
