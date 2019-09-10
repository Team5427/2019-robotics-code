package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateSolenoid extends Command
{
    public static boolean b = true;

    public ActivateSolenoid() {}

    protected void initialize() 
    {
        Robot.getGearShifter().set(b);
    }

    protected void execute() 
    {}

    protected void end() 
    {
        b = !b;
    }

    @Override
    protected boolean isFinished() 
    {
        return !Robot.oi.getJoy().getRawButtonPressed(Config.PCM_JOYSTICK_PORT);
    }
}