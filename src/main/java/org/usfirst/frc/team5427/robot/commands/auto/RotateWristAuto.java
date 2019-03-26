package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;


public class RotateWristAuto extends Command
{
    //wrist: 9 deg/sec
    //elevator: 390 counts/sec
    //elevator: 502 counts/sec

    public double speed;
    public double angle;
    public double startWrist;
    public double endAngleDifference;
    public int counts;

    public double goalAngle;

    public boolean ignoreLimits;


    public RotateWristAuto(double goalAngle)
    {
        requires(Robot.wrist);

        this.goalAngle = goalAngle;
    }

    

   //for ignoring limits
   public RotateWristAuto(double goalAngle, boolean ignoreLimits)
   {
       requires(Robot.wrist);

       this.goalAngle = goalAngle;
       this.ignoreLimits = ignoreLimits;
   }

    @Override
    protected void initialize()
    {
   
        if(this.goalAngle < Robot.wristPot.get()) {
            this.speed = Config.WRIST_SPEED_UP;
            this.angle = Math.abs(this.goalAngle - Robot.wristPot.get()) - Config.angleOffsetUp_Wrist;
        }
        else if(this.goalAngle > Robot.wristPot.get()) {
            this.speed = Config.WRIST_SPEED_DOWN;
            this.angle = Math.abs(this.goalAngle - Robot.wristPot.get()) - Config.angleOffsetDown_Wrist;            
        }

        this.setInterruptible(true);
        startWrist = Robot.wristPot.get();

        Robot.wrist.moveWrist(this.speed);
        endAngleDifference = 0;

        if(this.angle < 0) {
            this.angle = 0;
        }
        
    }

    @Override
    protected void execute()
    {
        if(!ignoreLimits)
            Robot.wrist.moveWrist(this.speed);
        else
            Robot.wrist.moveWristNoLimits(this.speed);
    }
    public double previous_wrist_pot;

    @Override
    protected boolean isFinished()
    {
        double wrist_pot = Robot.wristPot.get();

        if(Math.abs(wrist_pot - previous_wrist_pot) <= 2) {
            endAngleDifference = Math.abs(startWrist - wrist_pot);
        }

        
        previous_wrist_pot = wrist_pot;

        return endAngleDifference >= this.angle;
    }

    @Override
    protected void end()
    {
        Robot.wrist.stop();
    }
}