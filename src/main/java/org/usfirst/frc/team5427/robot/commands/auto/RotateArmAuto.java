package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;


public class RotateArmAuto extends Command
{
    //wrist: 9 deg/sec
    //elevator: 390 counts/sec
    //elevator: 502 counts/sec

    public double speed;
    public double angle;
    public double startArm;
    public double endAngleDifference;
    public int counts;

    public double goalAngle;

    public RotateArmAuto(double goalAngle)
    {
        requires(Robot.arm);

        this.goalAngle = goalAngle;
    }

    @Override
    protected void initialize()
    {
   
        if(this.goalAngle < Robot.armPot.get()) {
            this.speed = Config.ARM_SPEED_UP_AUTO;
            this.angle = Math.abs(this.goalAngle - Robot.armPot.get()) - Config.angleOffsetUp_Arm;
        }
        else if(this.goalAngle > Robot.armPot.get()) {
            this.speed = Config.ARM_SPEED_DOWN_AUTO;
            this.angle = Math.abs(this.goalAngle - Robot.armPot.get()) - Config.angleOffsetDown_Arm;            
        }

     

        this.setInterruptible(true);
        startArm = Robot.armPot.get();


        endAngleDifference = 0;

        Robot.arm.moveArm(this.speed);
    }

    @Override
    protected void execute()
    {
        Robot.arm.moveArm(this.speed);
    }
    public double previous_arm_pot;

    @Override
    protected boolean isFinished()
    {
        double arm_pot = Robot.armPot.get();

        if(Math.abs(arm_pot - previous_arm_pot) <= 2) {
            endAngleDifference = Math.abs(startArm - arm_pot);
        }
        
        previous_arm_pot = arm_pot;

        return endAngleDifference >= this.angle;
    }

    @Override
    protected void end()
    {
        Robot.arm.stop();
    }
}