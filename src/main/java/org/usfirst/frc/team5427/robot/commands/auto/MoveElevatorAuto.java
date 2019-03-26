package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;


public class MoveElevatorAuto extends Command
{
    //elevator: 390 counts/sec
    //elevator: 502 counts/sec

    public double speed;
    public double goalCount;
    public double goalCountAbsolute;

    public double startElevator;
    public double rawGoal;

    public double endEncoderCountDifference;


    public MoveElevatorAuto(double goalCountAbsolute)
    {
        requires(Robot.elevator);

        this.goalCountAbsolute = goalCountAbsolute;
    }

    @Override
    protected void initialize()
    {


        if(this.goalCountAbsolute > Robot.elevator_enc.get()) {
            this.speed = Config.ELEVATOR_SPEED_UP;
            this.goalCount = Math.abs(this.goalCountAbsolute - Robot.elevator_enc.get()) - Config.encoderOffsetUp_Elevator;
        }
        else if(this.goalCountAbsolute < Robot.elevator_enc.get()) {
            this.speed = Config.ELEVATOR_SPEED_DOWN;
            this.goalCount = Math.abs(this.goalCountAbsolute - Robot.elevator_enc.get()) - Config.encoderOffsetDown_Elevator;            
        }

        this.setInterruptible(true);
        startElevator = Robot.elevator_enc.get();

        Robot.elevator.setSpeed(this.speed);
        endEncoderCountDifference = 0;

        // if(this.goalCount < 0) {
        //     this.cancel();
        // }
    }

    @Override
    protected void execute()
    {
        Robot.elevator.setSpeed(this.speed);

    }

    @Override
    protected boolean isFinished()
    {
        double elevator_enc = Robot.elevator_enc.get();

        endEncoderCountDifference = Math.abs(startElevator - elevator_enc);
        
        if(this.goalCount < 0)
            return true;

    
        return endEncoderCountDifference >= this.goalCount;
    }

    @Override
    protected void end()
    {
        Robot.elevator.stop();
    }
}