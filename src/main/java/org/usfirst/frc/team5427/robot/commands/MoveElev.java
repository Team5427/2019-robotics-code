package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.DistanceFollower;


public class MoveElev extends Command {

    public DistanceFollower follower;

    boolean backwards;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MoveElev(Trajectory trajectory, boolean backwards) {
        //create followers to manage input+output
        follower = new DistanceFollower(trajectory);
        
        follower.configurePIDVA(Config.KP_ELEV, Config.KI_ELEV, Config.KD_ELEV, Config.KV_ELEV, Config.KA_ELEV);   

        this.backwards = backwards;
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
        Robot.encElevator.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
   
         if(backwards) {
            Robot.elevator.set(-follower.calculate(-Robot.encElevator.getDistance()));
        }
        else {
            Robot.elevator.set(follower.calculate(Robot.encElevator.getDistance()));
        }
        
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //finish when both sides are done with data
        return follower.isFinished();
    }

	// Called once after isFinished returns true
	@Override
	protected void end() {
       
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}