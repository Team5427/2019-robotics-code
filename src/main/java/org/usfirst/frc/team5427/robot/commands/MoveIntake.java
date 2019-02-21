package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveIntake extends Command
{
    public double speed;

    public MoveIntake(double speed)
    {
        requires(Robot.intake);
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
        Robot.intake.setSpeed(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_INTAKE_OUT);
        else if (speed < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_INTAKE_IN);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.intake.stop();
    }
}