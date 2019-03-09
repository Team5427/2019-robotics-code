package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem
{
    //237 - 159
    
    private SpeedController armMotor;
    public double armAngle;

    public Arm(SpeedController armMotor)
    {
        this.armMotor = armMotor;
    }

    public void moveArm(double armSpeed)
    {
        if((armSpeed > 0 && Robot.armPot.get() >= 80) 
        || (armSpeed < 0 && Robot.armPot.get() <= 118))
            armMotor.set(armSpeed);
        else 
            armMotor.set(0);
    }

    public void stop(){
        armMotor.set(0);
    }

    @Override
    protected void initDefaultCommand()
    {

    }

    public void setArmAngle(double angle)
    {
        this.armAngle = angle;
    }

    public double getArmAngle()
    {
        return armAngle;
    }
}