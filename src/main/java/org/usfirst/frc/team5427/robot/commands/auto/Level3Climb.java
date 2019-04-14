package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Level3Climb extends Command 
{
    MoveClimberLegAuto legMovement;
    double armSpeed = 0.1; //Temporary Value
    double legDistance = 150; //Temporary Value

    public Level3Climb() 
    {
        requires(Robot.climberArm);
        legMovement = new MoveClimberLegAuto(legDistance);
    }

    @Override
    protected void initialize()
    {
        legMovement.start();
    }

    @Override
    protected void execute()
    {
        Robot.climberArm.setSpeed(armSpeed);
    }
    @Override
    protected boolean isFinished()
    {
        return legMovement.isFinished();
    }

    @Override
    protected void end()
    {
        Robot.climberArm.stop();
    }
}