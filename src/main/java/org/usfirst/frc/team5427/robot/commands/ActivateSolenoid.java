package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateSolenoid extends Command
{
    public ActivateSolenoid() {}

    protected void initialize() 
    {
        Robot.solenoidOne.set(true);
    }

    protected void execute() 
    {
        Robot.solenoidOne.set(true);
    }

    // protected void isInterrupted() 
    // {
    //     end();
    // }

    protected void end() 
    {
        Robot.solenoidOne.set(false);
    }

    @Override
    protected boolean isFinished() 
    {
        return !Robot.oi.getJoy().getRawButtonPressed(Config.PCM_JOYSTICK_PORT);
    }
}