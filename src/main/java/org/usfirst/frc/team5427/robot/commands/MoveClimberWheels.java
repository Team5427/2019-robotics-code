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
        if(Robot.oi.getJoy().getPOV(0) >= 315 || Robot.oi.getJoy().getPOV(0) <= 45)
        {
            Robot.climberWheel.setSpeed(Config.CLIMBER_WHEEL_SPEED_FORWARD);
        }
        else if(Robot.oi.getJoy().getPOV(0) >= 135 && Robot.oi.getJoy().getPOV(0) <= 225)
        {
            Robot.climberWheel.setSpeed(Config.CLIMBER_WHEEL_SPEED_BACKWARD);
        }
    }

    @Override
    protected boolean isFinished()
    {
        if (speed != 0)
            return Robot.oi.getJoy().getPOV(0) == -1;
        return false;
    }

    @Override
    protected void end()
    {
        Robot.climberWheel.stop();
    }
}