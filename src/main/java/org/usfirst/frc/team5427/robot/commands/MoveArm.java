package org.usfirst.frc.team5427.robot.commands;


import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class MoveArm extends AutoAction {

    public EncoderFollower follower;

    boolean backwards;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MoveArm(Waypoint[] points, boolean backwards) {
        //set up config variables for profile
        Trajectory.Config config =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT, Config.MAX_VELOCITY, Config.MAX_ACCEL, Config.MAX_JERK);

        //generate the trajectory
        Trajectory trajectory = Pathfinder.generate(points, config);
        
        
        // Create the Modifier Object (tank drive train)
        TankModifier modifier = new TankModifier(trajectory);
        modifier.modify(Config.ftm(Config.ARM_WIDTH));

        //create followers to manage input+output
        follower = new EncoderFollower(modifier.getLeftTrajectory());

        //value configuration
        follower.configureEncoder((int)Robot.rotationPotentiometerArm.get(), 360,2); //initial pot angle, degrees in circle, arm radius

        follower.configurePIDVA(Config.KP, Config.KI, Config.KD, Config.KV, Config.KA);
     
        
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