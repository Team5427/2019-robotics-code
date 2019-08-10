package org.usfirst.frc.team5427.robot.commands.auto;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;


public class MoveClimberLegAuto extends Command
{
 
    private BigDecimal speed = new BigDecimal("0");
    private BigDecimal leg = new BigDecimal("0");
    private BigDecimal startLeg = new BigDecimal("0");;
    private BigDecimal endLegDifference = new BigDecimal("0");
    private BigDecimal previous_leg_enc = new BigDecimal("0");


    public double goalLeg;

    public MoveClimberLegAuto(double goalLeg)
    {
        requires(Robot.getClimberLeg());

        this.goalLeg = goalLeg;
    }

    @Override
    protected void initialize()
    {
        
        startLeg = BigDecimal.valueOf(Robot.getClimbEnc().get());

        if(this.goalLeg > Robot.getClimbEnc().get()) {
            this.speed = BigDecimal.valueOf(Config.CLIMBER_LEG_SPEED_DOWN);
            this.leg = BigDecimal.valueOf(Math.abs(this.goalLeg - startLeg.doubleValue()));
        }
        else if(this.goalLeg < Robot.getClimbEnc().get()) {
            this.speed = BigDecimal.valueOf(Config.CLIMBER_LEG_SPEED_UP);
            this.leg = BigDecimal.valueOf(Math.abs(this.goalLeg - startLeg.doubleValue()));            
        }
        
        this.setInterruptible(true);
        Robot.getClimberLeg().setSpeed(this.speed.doubleValue());
    }

    @Override
    protected void execute()
    {
        Robot.getClimberLeg().setSpeed(this.speed);
    }

    @Override
    protected boolean isFinished()
    {
        double climbPot = Robot.getClimbEnc().get();

        if(Math.abs(climbPot - previous_leg_enc) <= 2) {
            endLegDifference = Math.abs(startLeg - climbPot);
        }
        
        previous_leg_enc = climbPot;

        return endLegDifference >= this.leg;
    }

    @Override
    protected void end()
    {
        Robot.getClimberLeg().stop();
    }
}