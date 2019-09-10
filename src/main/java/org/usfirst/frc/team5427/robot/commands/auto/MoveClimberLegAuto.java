package org.usfirst.frc.team5427.robot.commands.auto;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;


public class MoveClimberLegAuto extends Command
{
    //declares and initializes any required variables
    private BigDecimal speed = new BigDecimal("0");
    private BigDecimal leg = new BigDecimal("0");
    private BigDecimal startLeg = new BigDecimal("0");;
    private BigDecimal endLegDifference = new BigDecimal("0");
    private BigDecimal previous_leg_enc = new BigDecimal("0");
    private BigDecimal goalLeg = new BigDecimal("0");
    private BigDecimal climbPot = new BigDecimal("0");


    //recieves and sets a goal leg position
    public MoveClimberLegAuto(BigDecimal goalLeg)
    {
        requires(Robot.getClimberLeg());
        this.goalLeg = goalLeg;
    }

    @Override
    protected void initialize()
    {
        //sets beginning position of leg to current encoder value
        startLeg = BigDecimal.valueOf(Robot.getClimbEnc().get());

        //decides if the leg should move up or down to reach desired position
        if(this.goalLeg.compareTo(startLeg)>0) 
        {
            //sets leg to move at a speed downwards and records the difference between the end and start position
            this.speed = BigDecimal.valueOf(Config.CLIMBER_LEG_SPEED_DOWN);
            this.leg = this.goalLeg.subtract(startLeg).abs();
        }
        else if(this.goalLeg.compareTo(startLeg)<0) 
        {
            //sets leg to move at a speed upwards and the records difference between the end and start position
            this.speed = BigDecimal.valueOf(Config.CLIMBER_LEG_SPEED_UP);
            this.leg = this.goalLeg.subtract(startLeg).abs();            
        }
        this.setInterruptible(true);

        //actually sets the leg to move based on previously decided speed
        Robot.getClimberLeg().setSpeed(this.speed);
    }

    @Override
    protected void execute()
    {
        //called repeatedly until command is finished or stopped
        Robot.getClimberLeg().setSpeed(this.speed);
    }

    @Override
    protected boolean isFinished()
    {
        //records current value of the climber leg encoder
        climbPot = BigDecimal.valueOf(Robot.getClimbEnc().get());

        //checks if the differnce between the current encoder and previous encoder value(last time the method was called) is less than 2
        if(climbPot.subtract(previous_leg_enc).abs().compareTo(BigDecimal.valueOf(2)) <=0) 
        {
            //sets the leg difference to the beggining position minus the current position
            endLegDifference = (startLeg.subtract(climbPot)).abs();
        }
        
        //sets previous location to current location for next time the method is called
        previous_leg_enc = climbPot;

        //returns true if the difference between the current leg difference and total difference is more than zero, checks if the leg moved the amount it needed to move
        return endLegDifference.compareTo(this.leg)>=0;
    }

    @Override
    protected void end()
    {
        //sets velocity of climber leg to zero
        Robot.getClimberLeg().stop();
    }
}