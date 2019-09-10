package org.usfirst.frc.team5427.robot.commands;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class RotateWrist extends Command
{
    private BigDecimal speed;

    public RotateWrist(BigDecimal speed)
    {
        requires(Robot.getWrist());
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
        Robot.getWrist().moveWrist(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed.compareTo(new BigDecimal("0")) > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_WRIST_UP);
        else if (speed.compareTo(new BigDecimal("0")) < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_WRIST_DOWN);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.getWrist().stop();
    }
}