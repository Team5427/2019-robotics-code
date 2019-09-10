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

    //declares and initializes any required variables
    public BigDecimal speed = new BigDecimal("0");
    public BigDecimal angle = new BigDecimal("0");
    public BigDecimal startWrist = new BigDecimal("0");
    public BigDecimal endAngleDifference = new BigDecimal("0");
    public BigDecimal goalAngle = new BigDecimal("0");
    public BigDecimal previous_wrist_pot = new BigDecimal("0");
    public boolean ignoreLimits;

    //recieves and sets a goal angle position
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
        //sets beginning position of wrist to current potentiometer value
        startWrist = BigDecimal.valueOf(Robot.getWristPot().get());
   
        //decides if the wrist should move up or down to reach desired position
        if(this.goalAngle.compareTo(startWrist)<0) 
        {
            //sets wrist to move at a speed upwards and records the difference between the end and start position while accounting for the offset
            this.speed = BigDecimal.valueOf(Config.WRIST_SPEED_UP);
            this.angle = (this.goalAngle.subtract(startWrist)).subtract(BigDecimal.valueOf(Config.angleOffsetUp_Wrist));
        }
        else if(this.goalAngle.compareTo(startWrist)>0) 
        {
            //sets wrist to move at a speed upwards and records the difference between the end and start position while accounting for the offset
            this.speed = BigDecimal.valueOf(Config.WRIST_SPEED_DOWN);
            this.angle = (this.goalAngle.subtract(startWrist)).subtract(BigDecimal.valueOf(Config.angleOffsetDown_Wrist));         
        }

        this.setInterruptible(true);

        //actually sets the wrist to move based on previously decided speed
        Robot.getWrist().moveWrist(this.speed);

        //if the angle is less than zero, it sets it to 0
        if(this.angle.compareTo(new BigDecimal("0"))<0) {
            this.angle = new BigDecimal("0") ;
        }
        
    }

    @Override
    protected void execute()
    {
        //called repeatedly until command is finished or stopped
        if(!ignoreLimits)
            Robot.getWrist().moveWrist(this.speed);
        else
            Robot.getWrist().moveWristNoLimits(this.speed);
    }

    @Override
    protected boolean isFinished()
    {
        //records current value of the wrist potentiometer
        BigDecimal wrist_pot = BigDecimal.valueOf(Robot.getWristPot().get());

        //checks if the differnce between the current pot and previous pot value(last time the method was called) is less than 2
        if((wrist_pot.subtract(previous_wrist_pot)).compareTo(new BigDecimal("2"))<=0) 
        {
            //sets the angle difference to the beggining position minus the current position
            endAngleDifference = (startWrist.subtract(wrist_pot)).abs();
        }

        //sets previous location to current location for next time the method is called
        previous_wrist_pot = wrist_pot;

        //returns true if the difference between the current wrist difference and total difference is more than zero, checks if the wrist moved the amount it needed to move
        return endAngleDifference.compareTo(this.angle)>=0;
    }

    @Override
    protected void end()
    {
        //sets velocity of the wrist to zero
        Robot.getWrist().stop();
    }
}