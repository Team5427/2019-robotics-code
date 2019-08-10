package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveClimberArm extends Command
{
    public double speed;

    public MoveClimberArm(double speed)
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
        Robot.getClimberArm().setSpeed(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_ARM_DOWN);
        else if (speed < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_ARM_UP);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.getClimberArm().stop();
    }
}