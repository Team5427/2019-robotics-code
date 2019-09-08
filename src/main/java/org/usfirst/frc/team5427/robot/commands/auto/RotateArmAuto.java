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

    //declares and initializes any required variables
    public BigDecimal speed = new BigDecimal("0");
    public BigDecimal angle = new BigDecimal("0");
    public BigDecimal startArm = new BigDecimal("0");
    public BigDecimal endAngleDifference = new BigDecimal("0");
    public BigDecimal goalAngle = new BigDecimal("0");
    public BigDecimal previous_arm_pot = new BigDecimal("0");

    //recieves and sets a goal angle position
    public RotateArmAuto(BigDecimal goalAngle)
    {
        requires(Robot.getArm());

        this.goalAngle = goalAngle;
    }

    @Override
    protected void initialize()
    {
        //sets beginning position of arm to current potentiometer value
        BigDecimal startArm = BigDecimal.valueOf(Robot.getArmPot().get());
   
        //decides if the arm should move up or down to reach desired position
        if(this.goalAngle.compareTo(startArm)<0) 
        {
            //sets arm to move at a speed upwards and records the difference between the end and start position while accounting for the offset
            this.speed = BigDecimal.valueOf(Config.ARM_SPEED_UP_AUTO);
            this.angle = (this.goalAngle.subtract(startArm).abs()).subtract(BigDecimal.valueOf(Config.angleOffsetUp_Arm));
        }
        else if(this.goalAngle.compareTo(startArm)>0) 
        {
            //sets arm to move at a speed downwards and records the difference between the end and start position while accounting for the offset
            this.speed = BigDecimal.valueOf(Config.ARM_SPEED_DOWN_AUTO);
            this.angle = (this.goalAngle.subtract(startArm).abs()).subtract(BigDecimal.valueOf(Config.angleOffsetDown_Arm));           
        }

     

        this.setInterruptible(true);

        //actually sets the arm to move based on previously decided speed
        Robot.getArm().moveArm(this.speed.doubleValue());
    }

    @Override
    protected void execute()
    {
        //called repeatedly until command is finished or stopped
        Robot.getArm().moveArm(this.speed.doubleValue());
    }
    

    @Override
    protected boolean isFinished()
    {
        //records current value of the arm potentiometer
        BigDecimal arm_pot = BigDecimal.valueOf(Robot.getArmPot().get());

        //checks if the differnce between the current pot and previous pot value(last time the method was called) is less than 2
        if((arm_pot.subtract(previous_arm_pot)).abs().compareTo(new BigDecimal("2"))<=0) 
        {
            //sets the angle difference to the beggining position minus the current position
            endAngleDifference = startArm.subtract(arm_pot).abs();
        }
        
        //sets previous location to current location for next time the method is called
        previous_arm_pot = arm_pot;

        //returns true if the difference between the current arm difference and total difference is more than zero, checks if the arm moved the amount it needed to move
        return endAngleDifference.compareTo(this.angle)>=0;
    }

    @Override
    protected void end()
    {
        //sets velocity of the arm to zero
        Robot.getArm().stop();
    }
}