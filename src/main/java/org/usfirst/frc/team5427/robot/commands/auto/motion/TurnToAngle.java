package org.usfirst.frc.team5427.robot.commands.auto.motion;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {
    double goalAngle;
    double speed;

    public TurnToAngle(double goalAngle) {
        this.goalAngle = goalAngle;
    }

    @Override
    protected void initialize() {
        double angle = Robot.ahrs.getAngle() % 360;
        
        if(goalAngle > angle) {
            if(goalAngle - angle > 360 - goalAngle + angle) {
                speed = 0.25;//clockwise
            }
            else {
                speed = -0.25; //anticlockwise
            }
        }
        else {
            if(angle - goalAngle > 360 - angle + goalAngle) {
                speed = 0.25; 
            }
            else {
                speed = -0.25;
            }
        }

    }

    @Override
    protected void execute() {
        Robot.driveTrain.tankDrive(speed, speed);
    }


    @Override
    protected boolean isFinished() {
        double angle = Robot.ahrs.getAngle() % 360;

        if(goalAngle == 0 && angle >= 358)
            return true;

        return Math.abs(angle - goalAngle) <= 2;
    }
} 