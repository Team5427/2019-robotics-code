package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Level2Climb extends Command 
{
    MoveClimberLegAuto legMovement;
    double armSpeed = 0.2; //Temporary Value
    double legDistance = 50; //Temporary Value

    public Level2Climb() 
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