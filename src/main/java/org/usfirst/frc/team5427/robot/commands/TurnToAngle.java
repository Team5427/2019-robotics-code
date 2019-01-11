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
        requires(Robot.pidTurn);
        this.angle = angle;
    }

    protected void initialize() {
        Robot.pidTurn.turnDegrees(angle);
    }

    protected void execute() {
        this.error = Robot.pidTurn.turnController.getError();
        this.inErrorRange = Math.abs(error) < Robot.pidTurn.turnTolerance;

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
        Robot.pidTurn.turnController.disable();
        isFinished = false;
        Robot.ahrs.reset();
        // Robot.driveTrain.turnController.close();
    }
}