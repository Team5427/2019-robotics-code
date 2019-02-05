package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;


public class MoveWrist extends Command {

    public EncoderFollower follower;

    boolean backwards;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MoveWrist(Waypoint[] points, boolean backwards) {
        //set up config variables for profile
        Trajectory.Config config =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT_ARM_WRIST, Config.MAX_VELOCITY_WRIST, Config.MAX_ACCEL_WRIST, Config.MAX_JERK_WRIST);

        //generate the trajectory
        Trajectory trajectory = Pathfinder.generate(points, config);

        //create followers to manage input+output
        follower = new EncoderFollower(trajectory);

        //value configuration
        follower.configureEncoder((int)Robot.rotationPotentiometerWrist.get(), 360, 360); //initial pot angle, degrees in circle, arm radius

        follower.configurePIDVA(Config.KP_WRIST, Config.KI_WRIST, Config.KD_WRIST, Config.KV_WRIST, Config.KA_WRIST);
     
        
        this.backwards = backwards;
    }

     //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
     public MoveWrist(Trajectory trajectory, boolean backwards) {
        //create followers to manage input+output
        follower = new EncoderFollower(trajectory);

        //value configuration
        follower.configureEncoder((int)Robot.rotationPotentiometerWrist.get(), 360, 360); //initial pot angle, degrees in circle, arm radius

        follower.configurePIDVA(Config.KP_WRIST, Config.KI_WRIST, Config.KD_WRIST, Config.KV_WRIST, Config.KA_WRIST);
     
        
        this.backwards = backwards;
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
        

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
   
         if(backwards) {
            Robot.wrist.set(-follower.calculate((int)Robot.rotationPotentiometerWrist.get()));
        }
        else {
            Robot.wrist.set(follower.calculate((int)Robot.rotationPotentiometerWrist.get()));
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