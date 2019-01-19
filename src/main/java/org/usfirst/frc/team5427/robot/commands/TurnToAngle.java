package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.Robot;


public class TurnToAngle extends AutoAction
{ 
    public boolean isFinished;
    public boolean inErrorRange;
    public double error;
    public double angle;
    public int countInErrorRange;
    public int desiredCountInErrorRange = 5;

    public TurnToAngle(double angle) {
        requires(Robot.driveTrain);
        this.angle = angle;
    }

    protected void initialize() {
        Robot.driveTrain.turnDegrees(angle);
    }

    protected void execute() {
        this.error = Robot.driveTrain.turnController.getError();
        this.inErrorRange = Math.abs(error) < Robot.driveTrain.turnTolerance;

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
        Robot.driveTrain.turnController.disable();
        isFinished = false;
        Robot.ahrs.reset();
        Robot.driveTrain.turnController.close();
        
        System.out.println("ending turn");
        if(this.nextAction!=null)
            this.nextAction.start();
    }
}