package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.DistanceFollower;


public class MoveElev extends Command {

    public DistanceFollower follower;

    public boolean backwards;
    public int runs;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MoveElev(Trajectory trajectory, boolean backwards) {
        //create followers to manage input+output
        follower = new DistanceFollower(trajectory);
        
        follower.configurePIDVA(Config.KP_ELEV, Config.KI_ELEV, Config.KD_ELEV, Config.KV_ELEV, Config.KA_ELEV);   

        this.backwards = backwards;
    }

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MoveElev(double h2) {
        Trajectory.Config configElev =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT, Config.MAX_VELOCITY_ELEV, Config.MAX_ACCEL_ELEV, Config.MAX_JERK_ELEV);
		
		Trajectory trajectory = Pathfinder.generate(
			new Waypoint[] {new Waypoint(Robot.encElevator.getDistance(), 0, 0), new Waypoint(h2,0,0)}, configElev);
		
        //create followers to manage input+output
        follower = new DistanceFollower(trajectory);
        
        follower.configurePIDVA(Config.KP_ELEV, Config.KI_ELEV, Config.KD_ELEV, Config.KV_ELEV, Config.KA_ELEV);   

        if(h2 < Robot.encElevator.getDistance()) {
            this.backwards = true;
        }
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
        
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        if(runs++ % (Config.DT/0.02) == 0) {
            if(backwards) {
                Robot.elevator.set(-follower.calculate(Robot.encElevator.getDistance()));
            }
            else {
                Robot.elevator.set(follower.calculate(Robot.encElevator.getDistance()));
            }
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