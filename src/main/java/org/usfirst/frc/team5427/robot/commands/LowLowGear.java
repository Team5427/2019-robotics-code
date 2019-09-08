package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class LowLowGear extends Command
{


    public LowLowGear()
    {
        
    }
    
    // sets the command to be able to be interrupted during the scheduler run and flips the lowlowgear
    @Override
    protected void initialize()
    {
        this.setInterruptible(true);
        DriveTrain.flipLowLowGear();
    }

    // used by scheduler to end the command
    @Override
    protected boolean isFinished()
    {
        return true;
    }

    // used by scheduler to make any last changes before moving on to the next command in the cycle
    @Override
    protected void end()
    {
    }
}