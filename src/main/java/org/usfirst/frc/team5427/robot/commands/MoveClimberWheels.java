package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberWheels extends Command
{
    public double speed;

    public MoveClimberWheels(double speed)
    {
        requires(Robot.climberWheel);
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
        Robot.climberWheel.setSpeed(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_WHEEL_FORWARD);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.climberWheel.stop();
    }
}