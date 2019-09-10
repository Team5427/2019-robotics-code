package org.usfirst.frc.team5427.robot.commands;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberLeg extends Command
{
    private BigDecimal speed;

    public MoveClimberLeg(BigDecimal speed)
    {
        requires(Robot.getClimberLeg());
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
        Robot.getClimberLeg().setSpeed(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed.compareTo(new BigDecimal("0")) > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_LEG_UP);
        else if (speed.compareTo(new BigDecimal("0")) < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_LEG_DOWN);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.getClimberLeg().stop();
    }
}