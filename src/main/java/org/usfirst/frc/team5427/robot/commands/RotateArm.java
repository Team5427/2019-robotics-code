package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class rotates the arm during teleoperated control. 
 */
public class RotateArm extends Command
{
    /**
     * The speed of the arm. 
     */
    public double speed;

    /**
     * Constructor for the command to rotate the arm in teleop. Stores the received speed value. 
     * @param speed the speed that the arm should be at. 
     */
    public RotateArm(double speed)
    {
        requires(Robot.getArm());
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
        Robot.getArm().moveArm(speed);
    }

    @Override
    protected boolean isFinished()
    {
        if (speed > 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_ARM_UP);
        else if (speed < 0)
            return !Robot.oi.getJoy().getRawButton(Config.BUTTON_ARM_DOWN);
        return false;
    }

    @Override
    protected void end()
    {
        Robot.getArm().stop();
    }
}