package org.usfirst.frc.team5427.robot.commands;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveIntake extends Command
{
    private BigDecimal speed;

    public MoveIntake(BigDecimal speed)
    {
        requires(Robot.getIntake());
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
        Robot.getIntake().setSpeed(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed.compareTo(new BigDecimal("0")) > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_INTAKE_OUT);
        else if (speed.compareTo(new BigDecimal("0")) < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_INTAKE_IN);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.getIntake().stop();
    }
}