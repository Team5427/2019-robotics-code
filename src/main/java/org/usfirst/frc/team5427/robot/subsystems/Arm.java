package org.usfirst.frc.team5427.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem
{
    private SpeedController armMotor;
    public double armAngle;

    public Arm(SpeedController armMotor)
    {
        this.armMotor = armMotor;
    }

    public void moveArm(double armSpeed)
    {
        armMotor.set(armSpeed);
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