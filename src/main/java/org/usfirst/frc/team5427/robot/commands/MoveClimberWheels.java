package org.usfirst.frc.team5427.robot.commands;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberWheels extends Command
{
    private BigDecimal speed;

    public MoveClimberWheels(BigDecimal speed)
    {
        requires(Robot.getClimberWheel());
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
        if(Robot.oi.getJoy().getPOV(0) >= 315 || Robot.oi.getJoy().getPOV(0) <= 45)
        {
            Robot.getClimberWheel().setSpeed(new BigDecimal(Config.CLIMBER_WHEEL_SPEED_FORWARD));
        }
        else if(Robot.oi.getJoy().getPOV(0) >= 135 && Robot.oi.getJoy().getPOV(0) <= 225)
        {
            Robot.getClimberWheel().setSpeed(new BigDecimal(Config.CLIMBER_WHEEL_SPEED_BACKWARD));
        }
    }

    @Override
    protected boolean isFinished()
    {
        if (speed.compareTo(new BigDecimal("0")) > 0)
            return Robot.oi.getJoy().getPOV(0) == -1;
        return false;
    }

    @Override
    protected void end()
    {
        Robot.getClimberWheel().stop();
    }
}