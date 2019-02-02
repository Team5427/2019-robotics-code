package org.usfirst.frc.team5427.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Wrist extends Subsystem
{
    private SpeedController wristMotor;
    public double wristAngle;
    
    public Wrist(SpeedController wristMotor)
    {
        this.wristMotor = wristMotor;
    }
    
    public void moveWrist(double speed)
    {
        wristMotor.set(speed);
    }
    
    @Override
    protected void initDefaultCommand()
    {
        
    }
    
    public void setWristAngle(double angle)
    {
        this.wristAngle = angle;
    }
    
    public double getWristAngle()
    {
        return wristAngle;
    }
}