package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;


public class MoveArm extends Command {

    public EncoderFollower follower;

    boolean backwards;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MoveArm(Waypoint[] points, boolean backwards) {
        //set up config variables for profile
        Trajectory.Config config =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT, Config.MAX_VELOCITY_ARM, Config.MAX_ACCEL_ARM, Config.MAX_JERK_ARM);

        //generate the trajectory
        Trajectory trajectory = Pathfinder.generate(points, config);
    

        //create followers to manage input+output
        follower = new EncoderFollower(trajectory);
        

        //value configuration
        follower.configureEncoder((int)Robot.rotationPotentiometerArm.get(), 360, 360); //initial pot angle, degrees in circle, arm radius

        follower.configurePIDVA(Config.KP_ARM, Config.KI_ARM, Config.KD_ARM, Config.KV_ARM, Config.KA_ARM);
     
        

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
            Robot.arm.set(-follower.calculate((int)Robot.rotationPotentiometerArm.get()));
        }
        else {
            Robot.arm.set(follower.calculate((int)Robot.rotationPotentiometerArm.get()));
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