package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Wrist extends Subsystem
{
    private SpeedController wristMotor;
    public double wristAngle;

    //265 - 200
    
    
    public Wrist(SpeedController wristMotor)
    {
        this.wristMotor = wristMotor;
    }
    
    public void moveWrist(double speed)
    {

        
        if((speed > 0 && Robot.wristPot.get() >= Config.WRIST_LIMIT_TOP) || (speed < 0 && Robot.wristPot.get() <= Config.WRIST_LIMIT_BOTTOM) )          
            wristMotor.set(speed);
        else 
            wristMotor.set(0);    
    }

    public void moveWristNoLimits(double speed)
    {
        wristMotor.set(speed);
    }

    
    @Override
    protected void initDefaultCommand()
    {
        
    }

    public void stop() {
        wristMotor.set(0);
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