package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ApproachDistance extends Command
{ 
    public boolean isFinished;
    public boolean inErrorRange;
    public double error;
    public double distance;
    public int countInErrorRange;
    public int desiredCountInErrorRange = 5;

    public ApproachDistance(double distance) {
        requires(Robot.driveTrain);
        this.distance = distance;
    }

    protected void initialize() {
        Robot.driveTrain.approachInches(distance);
    }

    protected void execute() {
        SmartDashboard.putNumber("Ultrasonic Distance",Robot.ultra.getRangeInches());
        this.error = Robot.driveTrain.ultraController.getError();
        this.inErrorRange = Math.abs(error) < Robot.driveTrain.ultraTolerance;

        if(inErrorRange) {
            countInErrorRange++;
            isFinished = countInErrorRange >= desiredCountInErrorRange;
        }
        else
            countInErrorRange = 0;
    }
    @Override
	public boolean isFinished() {
        return isFinished;
        // return false;
    }

    protected void isInterrupted() {
        end();
    }

    protected void end() {
        Robot.driveTrain.ultraController.disable();
        isFinished = false;
        // Robot.driveTrain.turnController.close();
    }
}