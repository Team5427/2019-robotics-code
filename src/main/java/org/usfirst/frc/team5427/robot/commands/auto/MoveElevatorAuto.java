package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;


public class MoveElevatorAuto extends AutoAction
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

        System.out.println("goalCountAbsolute:\t" + goalCountAbsolute);

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

        if(this.goalCount < 0) {
            System.out.println("cancelling");
            this.cancel();
        }
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
        
        System.out.println("ELEVATOR\t\t: " + goalCount + "\t" + startElevator + "\t" + endEncoderCountDifference + " \t" + elevator_enc);

    
        return endEncoderCountDifference >= this.goalCount;
    }

    @Override
    protected void end()
    {
        Robot.elevator.stop();
    }
}