package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import java.math.BigDecimal;



public class RotateWristAuto extends Command
{
    //wrist: 9 deg/sec
    //elevator: 390 counts/sec
    //elevator: 502 counts/sec

    public BigDecimal speed = new BigDecimal("0");
    public BigDecimal angle = new BigDecimal("0");
    public BigDecimal startWrist = new BigDecimal("0");
    public BigDecimal endAngleDifference = new BigDecimal("0");
    public BigDecimal goalAngle = new BigDecimal("0");

    public boolean ignoreLimits;


    public RotateWristAuto(BigDecimal goalAngle)
    {
        requires(Robot.getWrist());

        this.goalAngle = goalAngle;
    }

    

   //for ignoring limits
   public RotateWristAuto(BigDecimal goalAngle, boolean ignoreLimits)
   {
       requires(Robot.getWrist());

       this.goalAngle = goalAngle;
       this.ignoreLimits = ignoreLimits;
   }

    @Override
    protected void initialize()
    {
        BigDecimal wristPotValue = BigDecimal.valueOf(Robot.getWristPot().get());
   
        if(this.goalAngle.compareTo(wristPotValue)<0) {
            this.speed = BigDecimal.valueOf(Config.WRIST_SPEED_UP);
            this.angle = (this.goalAngle.subtract(wristPotValue)).subtract(BigDecimal.valueOf(Config.angleOffsetUp_Wrist));
        }
        else if(this.goalAngle.compareTo(wristPotValue)>0) {
            this.speed = BigDecimal.valueOf(Config.WRIST_SPEED_DOWN);
            this.angle = (this.goalAngle.subtract(wristPotValue)).subtract(BigDecimal.valueOf(Config.angleOffsetDown_Wrist));         
        }

        this.setInterruptible(true);
        startWrist = wristPotValue;

        Robot.getWrist().moveWrist(this.speed.doubleValue());
        endAngleDifference = new BigDecimal("0");

        if(this.angle.compareTo(new BigDecimal("0"))<0) {
            this.angle = new BigDecimal("0") ;
        }
        
    }

    @Override
    protected void execute()
    {
        if(!ignoreLimits)
            Robot.getWrist().moveWrist(this.speed.doubleValue());
        else
            Robot.getWrist().moveWristNoLimits(this.speed.doubleValue());
    }
    public BigDecimal previous_wrist_pot = new BigDecimal("0");

    @Override
    protected boolean isFinished()
    {
        BigDecimal wrist_pot = BigDecimal.valueOf(Robot.getWristPot().get());

        if((wrist_pot.subtract(previous_wrist_pot)).compareTo(new BigDecimal("2"))<=0) {
            endAngleDifference = (startWrist.subtract(wrist_pot)).abs();
        }

        
        previous_wrist_pot = wrist_pot;

        return endAngleDifference.compareTo(this.angle)>=0;
    }

    @Override
    protected void end()
    {
        Robot.getWrist().stop();
    }
}