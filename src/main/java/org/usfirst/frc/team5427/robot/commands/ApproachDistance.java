package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ApproachDistance extends Command implements PIDOutput
{ 
    public boolean isFinished;
    public boolean inErrorRange;
    public double error;
    public double distance;
    public int countInErrorRange;
    public int desiredCountInErrorRange = 5;

	public PIDController ultraController;
	public Ultrasonic ultra;
	public double ultraTolerance = 0.5f;
	public double p = 0.1;
	public double i = 0;
    public double d = 0;
    
    public ApproachDistance(double distance) {
        this.distance = distance;

        ultra = Robot.ultra;
		ultraController = new PIDController(p,i,d,ultra,this);
		ultraController.setOutputRange(-1f,1f);
		ultraController.setAbsoluteTolerance(ultraTolerance);
    }

    protected void initialize() {
        approachInches(distance);
    }

    public void approachInches(double distance)
	{
		ultraController.reset();
		ultraController.setPID(p, i, d);
		ultraController.setSetpoint(distance);
		ultraController.enable();
	}

    protected void execute() {
        SmartDashboard.putNumber("Ultrasonic Distance",this.ultra.getRangeInches());
        this.error = this.ultraController.getError();
        this.inErrorRange = Math.abs(error) < this.ultraTolerance;

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
        this.ultraController.disable();
        isFinished = false;
        // Robot.driveTrain.turnController.close();
    }

    @Override
    public void pidWrite(double output) {
		Robot.drive.arcadeDrive(-output,0);
    }
}