package org.usfirst.frc.team5427.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PIDTurn extends Subsystem implements PIDOutput {

    public PIDController turnController;
	public AHRS ahrs;
	public double turnTolerance = 1.0f;
	
	
	public static final double Ku = 0.12;
	public static final double Tu = 0.62830187;
	public double p = 0.097;
	public double i = 0.0;
    public double d = 0.18;

    public PIDTurn(AHRS ahrs){
        this.ahrs = ahrs;

        turnController = new PIDController(p,i,d, ahrs,this);
		turnController.setInputRange(-180.0f,180.0f);
		turnController.setContinuous();
		turnController.setOutputRange(-0.5f,0.5f);
		turnController.setAbsoluteTolerance(turnTolerance);
    }

    public void turnDegrees(double angle)
	{
		ahrs.reset();
		turnController.reset();
		turnController.setPID(p,i,d);
		turnController.setSetpoint(angle);
		turnController.enable();
	}
    
    @Override
	public void pidWrite(double output) {
		Robot.driveTrain.tankDrive(output,output);
	}

    @Override
    protected void initDefaultCommand() {

    }

}