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

        if(this.goalLeg.compareTo(startLeg)>0) {
            this.speed = BigDecimal.valueOf(Config.CLIMBER_LEG_SPEED_DOWN);
            this.leg = this.goalLeg.subtract(startLeg).abs();
        }
        else if(this.goalLeg.compareTo(startLeg)<0) {
            this.speed = BigDecimal.valueOf(Config.CLIMBER_LEG_SPEED_UP);
            this.leg = this.goalLeg.subtract(startLeg).abs();            
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

        if(climbPot.subtract(previous_leg_enc).abs().compareTo(new BigDecimal("2")) <=0) {
            endLegDifference = (startLeg.subtract(climbPot)).abs();
        }
        
        previous_leg_enc = climbPot;

        return endLegDifference.compareTo(this.leg)>=0;
    }

    @Override
    protected void end()
    {
        Robot.getClimberLeg().stop();
    }
}