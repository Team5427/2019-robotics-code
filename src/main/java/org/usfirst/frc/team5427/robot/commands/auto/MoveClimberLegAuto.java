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
    private BigDecimal goalLeg = new BigDecimal("0");
    private BigDecimal climbPot = new BigDecimal("0");

    public MoveClimberLegAuto(BigDecimal goalLeg)
    {
        requires(Robot.getClimberLeg());
        this.goalLeg = goalLeg;
    }

    @Override
    protected void initialize()
    {
        
        startLeg = BigDecimal.valueOf(Robot.getClimbEnc().get());

        if(this.goalLeg.doubleValue() > startLeg.doubleValue()) {
            this.speed = BigDecimal.valueOf(Config.CLIMBER_LEG_SPEED_DOWN);
            this.leg = BigDecimal.valueOf(Math.abs(this.goalLeg.doubleValue() - startLeg.doubleValue()));
        }
        else if(this.goalLeg.doubleValue() < startLeg.doubleValue()) {
            this.speed = BigDecimal.valueOf(Config.CLIMBER_LEG_SPEED_UP);
            this.leg = BigDecimal.valueOf(Math.abs(this.goalLeg.doubleValue() - startLeg.doubleValue()));            
        }
        this.setInterruptible(true);
        Robot.getClimberLeg().setSpeed(this.speed.doubleValue());
    }

    @Override
    protected void execute()
    {
        Robot.getClimberLeg().setSpeed(this.speed.doubleValue());
    }

    @Override
    protected boolean isFinished()
    {
        climbPot = BigDecimal.valueOf(Robot.getClimbEnc().get());

        if(Math.abs(climbPot.doubleValue() - previous_leg_enc.doubleValue()) <= 2) {
            endLegDifference = BigDecimal.valueOf(Math.abs(startLeg.doubleValue() - climbPot.doubleValue()));
        }
        
        previous_leg_enc = climbPot;

        return endLegDifference.doubleValue() >= this.leg.doubleValue();
    }

    @Override
    protected void end()
    {
        Robot.getClimberLeg().stop();
    }
}