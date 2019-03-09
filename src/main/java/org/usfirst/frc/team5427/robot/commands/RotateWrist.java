package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class RotateWrist extends Command
{
    public double speed;

    public RotateWrist(double speed)
    {
        requires(Robot.wrist);
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
        Robot.wrist.moveWrist(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_WRIST_UP);
        else if (speed < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_WRIST_DOWN);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.wrist.stop();
    }
}