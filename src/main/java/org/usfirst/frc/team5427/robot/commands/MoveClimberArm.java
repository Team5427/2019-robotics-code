package org.usfirst.frc.team5427.robot.commands;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberArm extends Command
{
    private BigDecimal speed;

    public MoveClimberArm(BigDecimal speed)
    {
        requires(Robot.getClimberArm());
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
        Robot.getClimberArm().setSpeed(speed.doubleValue());
    }

    @Override
    protected boolean isFinished()
    {
        if (speed.compareTo(new BigDecimal("0")) > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_ARM_DOWN);
        else if (speed.compareTo(new BigDecimal("0")) < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_ARM_UP);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.getClimberArm().stop();
    }
}