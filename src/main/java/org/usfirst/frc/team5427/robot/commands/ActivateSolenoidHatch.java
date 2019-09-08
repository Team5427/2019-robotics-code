package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateSolenoidHatch extends Command
{
    private static boolean b = true;

    public ActivateSolenoidHatch() {}

    // this initializes the solenoid hatch to enable
    protected void initialize() 
    {
        Robot.getHatchShifter().set(b);
    }

    // anytime this ActivateSolenoidHatch is called, it turns on the solenoid hatch
    protected void execute() 
    {
        Robot.getHatchShifter().set(b);
    }

    // this sets b to the opposite of what it was
    protected void end() 
    {
        b = !b;
    }

    // if the button is not pressed on the joystick then end this command
    @Override
    protected boolean isFinished() 
    {
        return !Robot.oi.getJoy().getRawButtonPressed(Config.PCM_JOYSTICK_PORT);
    }

    // return the value of b
    public boolean getB()
    {
        return b;
    }
}