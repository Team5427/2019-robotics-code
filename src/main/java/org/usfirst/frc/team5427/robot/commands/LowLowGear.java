package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class LowLowGear extends Command
{


    public LowLowGear()
    {
        
    }

    @Override
    protected void initialize()
    {
        this.setInterruptible(true);
        DriveTrain.flipLowLowGear();
    }



    @Override
    protected boolean isFinished()
    {
        return true;
    }

    @Override
    protected void end()
    {
    }
}