package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevator1 extends Command
{
    public double speed;

    public MoveElevator1(double speed)
    {
        requires(Robot.elevator);
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
        Robot.elevator.setSpeed(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_ELEVATOR_UP);
        else if (speed < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_ELEVATOR_DOWN);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.elevator.stop();
    }
}