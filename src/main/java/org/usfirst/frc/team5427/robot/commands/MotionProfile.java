package org.usfirst.frc.team5427.robot.commands;


import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.Timer;
import jaci.pathfinder.*;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class MotionProfile extends AutoAction {

    public Trajectory left;
    public Trajectory right;

    public EncoderFollower followerL;
    public EncoderFollower followerR;

    public double last_errorL, last_errorR, last_time;


    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MotionProfile(Waypoint[] points){

        Trajectory.Config config =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.02, 1.75, 2, 2);

        Trajectory trajectory = Pathfinder.generate(points, config);
        // The distance between the left and right sides of the wheelbase is 0.6m
        double wheelbase_width = 0.6;

        // Create the Modifier Object
        TankModifier modifier = new TankModifier(trajectory);

        // Generate the Left and Right trajectories using the original trajectory
        // as the centre
        modifier.modify(wheelbase_width);

        left  = modifier.getLeftTrajectory();       // Get the Left Side
        right = modifier.getRightTrajectory();      // Get the Right Side

        followerL = new EncoderFollower(left);
        followerR = new EncoderFollower(right);
        System.out.println(left.length());
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
        Robot.encLeft.reset(); Robot.encRight.reset(); followerL.reset(); followerR.reset();

        followerL.configureEncoder(0, 1120, 0.1524); //initial encoder, ticks per revoltion, wheel diameter meters
        followerR.configureEncoder(0, 1120, 0.1524); //initial encoder, ticks per revoltion, wheel diameter meters

        followerL.configurePIDVA(Config.KP, 0, Config.KD, Config.KV, Config.KA);
        followerR.configurePIDVA(Config.KP, 0, Config.KD, Config.KV, Config.KA);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        double speedL = followerL.calculate(Robot.encLeft.get()), speedR = followerR.calculate(Robot.encRight.get());
        
        double heading_desiredL = followerL.getHeading(), heading_desiredR = followerR.getHeading(), heading_current = Robot.ahrs.getYaw();
            
        double errorL = heading_desiredL - heading_current; double errorR = heading_desiredR - heading_current; double time = Timer.getFPGATimestamp();
        // double error_derivL = (errorL - last_errorL)/(time - last_time);
        last_errorL = errorL; last_time = time;
        // double error_derivR = (errorR - last_errorR)/(time - last_time);
        last_errorR = errorR; last_time = time;
        
        speedL += Config.KPHeading * errorL;
                //   + Config.KDHeading * error_derivL;
        speedR += Config.KPHeading * errorR;
                //   + Config.KDHeading * error_derivR;

        

        Robot.driveTrain.tankDrive(speedL, speedR);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
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