package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;


public class MoveClimberLegAuto extends Command
{
 

    public double speed;
    public double leg;
    public double startLeg;
    public double endLegDifference;
    public int counts;
    public double previous_leg_enc;


    public double goalLeg;

    public MoveClimberLegAuto(double goalLeg)
    {
        requires(Robot.climberLeg);

        this.goalLeg = goalLeg;
    }

    @Override
    protected void initialize()
    {
   
        if(this.goalLeg > Robot.climb_enc.get()) {
            this.speed = Config.CLIMBER_LEG_SPEED_DOWN;
            this.leg = Math.abs(this.goalLeg - Robot.climb_enc.get());
        }
        else if(this.goalLeg < Robot.climb_enc.get()) {
            this.speed = Config.CLIMBER_LEG_SPEED_UP;
            this.leg = Math.abs(this.goalLeg - Robot.climb_enc.get());            
        }

     

        this.setInterruptible(true);
        startLeg = Robot.climb_enc.get();


        endLegDifference = 0;

        Robot.climberLeg.setSpeed(this.speed);
    }

    @Override
    protected void execute()
    {
        Robot.climberLeg.setSpeed(this.speed);
    }

    @Override
    protected boolean isFinished()
    {
        double climbPot = Robot.climb_enc.get();

        if(Math.abs(climbPot - previous_leg_enc) <= 2) {
            endLegDifference = Math.abs(startLeg - climbPot);
        }
        
        previous_leg_enc = climbPot;

        return endLegDifference >= this.leg;
    }

    @Override
    protected void end()
    {
        Robot.climberLeg.stop();
    }
}