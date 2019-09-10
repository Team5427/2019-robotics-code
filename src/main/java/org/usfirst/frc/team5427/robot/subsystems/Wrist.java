package org.usfirst.frc.team5427.robot.subsystems;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Wrist extends Subsystem
{
    /**Wrist motor component */
    private SpeedController wristMotor;
    private BigDecimal wristAngle;

    /**Constructor for Robot Wrist*/
    public Wrist(SpeedController wristMotor){
        this.wristMotor = wristMotor;
    }
    
    /**wrist movement within wrist limits */
    public void moveWrist(BigDecimal speed){
        if((speed.doubleValue() > 0 && Robot.getWristPot().get() >= Config.WRIST_LIMIT_TOP) 
            || (speed.doubleValue() < 0 && Robot.getWristPot().get() <= Config.WRIST_LIMIT_BOTTOM))          
            wristMotor.set(speed.doubleValue());
        else 
            wristMotor.stopMotor();    
    }

    /**wrist motor movement without limits */
    public void moveWristNoLimits(BigDecimal speed){
        wristMotor.set(speed.doubleValue());
    }

    
    @Override
    protected void initDefaultCommand() {}

    /** Stops the wrist motor */
    public void stop() {
        wristMotor.stopMotor();
    }
    
    /**mutator for wrist angle */
    public void setWristAngle(double angle){
        this.wristAngle = new BigDecimal(angle);
    }
    
    /**accessor for wrist angle */
    public double getWristAngle(){
        return wristAngle.doubleValue();
    }
}