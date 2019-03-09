package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class RotateArm extends Command
{
    public double speed;

    public RotateArm(double speed)
    {
        requires(Robot.arm);
        this.speed = speed;
    }

    @Override
    protected void initialize()
    {
        this.setInterruptible(true);
    }

    @Override
    protected void execute()
    {
        Robot.arm.moveArm(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_ARM_UP);
        else if (speed < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_ARM_DOWN);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.arm.stop();
    }
}