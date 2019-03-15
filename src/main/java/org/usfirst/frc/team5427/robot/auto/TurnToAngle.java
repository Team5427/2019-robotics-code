package org.usfirst.frc.team5427.robot.auto;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {
    double goalAngle;
    public TurnToAngle(double goalAngle) {
        this.goalAngle = goalAngle;
    }

    @Override
    protected void initialize() {
        double angle = Robot.ahrs.getYaw();
    }

    @Override
    protected void execute() {
        double angle = Robot.ahrs.getYaw();

            Robot.driveTrain.tankDrive(0.25, 0.25);
        
    }


    @Override
    protected boolean isFinished() {
        double angle = Robot.ahrs.getYaw();

        return Math.abs(angle - goalAngle) <= 2;
    }
}