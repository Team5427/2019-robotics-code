package org.usfirst.frc.team5427.robot.commands;


import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;




public class MotionProfile extends AutoAction {


    public EncoderFollower followerL;
    public EncoderFollower followerR;


    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MotionProfile(Waypoint[] points){
        //set up config variables for profile
        Trajectory.Config config =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT, Config.MAX_VELOCITY, Config.MAX_ACCEL, Config.MAX_JERK);

        //generate the trajectory
        Trajectory trajectory = Pathfinder.generate(points, config);
        

        // Create the Modifier Object (tank drive train)
        TankModifier modifier = new TankModifier(trajectory);
        modifier.modify(Config.ftm(Config.WHEELBASE_WIDTH));

        //create followers to manage input+output
        followerL = new EncoderFollower(modifier.getLeftTrajectory());
        followerR = new EncoderFollower(modifier.getRightTrajectory());
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
        //reset all sensors/ control loops
        Robot.encLeft.reset(); 
        Robot.encRight.reset();
        followerL.reset(); 
        followerR.reset();
        Robot.ahrs.reset();

        //value configuration
        followerL.configureEncoder(0, 360, 0.1524); //initial encoder, ticks per revolution, wheel diameter meters
        followerR.configureEncoder(0, 360, 0.1524); //initial encoder, ticks per revolution, wheel diameter meters

        followerL.configurePIDVA(Config.KP, 0, Config.KD, Config.KV, Config.KA);
        followerR.configurePIDVA(Config.KP, 0, Config.KD, Config.KV, Config.KA);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        //calulate speed of each side of drive train based on encoder position
        double speedL = followerL.calculate(Robot.encLeft.get());
        double speedR = followerR.calculate(Robot.encRight.get());
        
        double gyro_heading = Robot.ahrs.getYaw();    // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(followerL.getHeading());  // Should also be in degrees
        
        //calculate heading curvature
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference;
        
        //apply to motors
        Robot.driveTrain.tankDrive(speedL+turn, speedR-turn);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
        //finish when both sides are done with data
        return followerL.isFinished() && followerR.isFinished();
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