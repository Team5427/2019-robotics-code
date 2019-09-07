package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import java.math.BigDecimal;



public class RotateArmAuto extends Command
{
    //wrist: 9 deg/sec
    //elevator: 390 counts/sec
    //elevator: 502 counts/sec

    public BigDecimal speed = new BigDecimal("0");
    public BigDecimal angle = new BigDecimal("0");
    public BigDecimal startArm = new BigDecimal("0");
    public BigDecimal endAngleDifference = new BigDecimal("0");
    public BigDecimal goalAngle = new BigDecimal("0");

    public RotateArmAuto(BigDecimal goalAngle)
    {
        requires(Robot.getArm());

        this.goalAngle = goalAngle;
    }

    @Override
    protected void initialize()
    {
        BigDecimal armPotValue = BigDecimal.valueOf(Robot.getArmPot().get());
   
        if(this.goalAngle.compareTo(armPotValue)<0) {
            this.speed = BigDecimal.valueOf(Config.ARM_SPEED_UP_AUTO);
            this.angle = (this.goalAngle.subtract(armPotValue).abs()).subtract(BigDecimal.valueOf(Config.angleOffsetUp_Arm));
        }
        else if(this.goalAngle.compareTo(armPotValue)>0) {
            this.speed = BigDecimal.valueOf(Config.ARM_SPEED_DOWN_AUTO);
            this.angle = (this.goalAngle.subtract(armPotValue).abs()).subtract(BigDecimal.valueOf(Config.angleOffsetDown_Arm));           
        }

     

        this.setInterruptible(true);
        startArm = armPotValue;


        endAngleDifference = new BigDecimal("0");

        Robot.getArm().moveArm(this.speed.doubleValue());
    }

    @Override
    protected void execute()
    {
        Robot.getArm().moveArm(this.speed.doubleValue());
    }
    public BigDecimal previous_arm_pot = new BigDecimal("0");

    @Override
    protected boolean isFinished()
    {
        BigDecimal arm_pot = BigDecimal.valueOf(Robot.getArmPot().get());

        if((arm_pot.subtract(previous_arm_pot)).abs().compareTo(new BigDecimal("2"))<=0) {
            endAngleDifference = startArm.subtract(arm_pot).abs();
        }
        
        previous_arm_pot = arm_pot;

        return endAngleDifference.compareTo(this.angle)>=0;
    }

    @Override
    protected void end()
    {
        Robot.getArm().stop();
    }
}