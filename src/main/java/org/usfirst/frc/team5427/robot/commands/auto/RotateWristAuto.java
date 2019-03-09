package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;


public class RotateWristAuto extends AutoAction
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

    public RotateWristAuto(double goalAngle)
    {
        requires(Robot.wrist);

        this.goalAngle = goalAngle;
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

        System.out.println(this.angle);
        if(this.angle < 0) {
            this.angle = 0;
        }
        
    }

    @Override
    protected void execute()
    {
        Robot.wrist.moveWrist(this.speed);
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