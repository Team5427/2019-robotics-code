package org.usfirst.frc.team5427.robot.auto;


import java.util.ArrayList;
import java.util.List;

import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;



public class MotionProfile extends Command {

	public List<Pose2d> points;
	public FalconTrajectoryFollower follower;
	public double startTime;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MotionProfile(List<Pose2d> points) {
	   	this.points = points;
	  	TrajectoryGen g = new TrajectoryGen();

	   	//points, max centripetal, start vel, end vel, max vel, linear accel, reversed
	   	ArrayList<TrajectoryPoint> a = g.generateTrajectory(points, 0.6, 0.0, 0.0, 2.75, 0.6, false);
		follower = new FalconTrajectoryFollower(a, 2, true, 0.01, 0.002);
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double dt = Timer.getFPGATimestamp() - startTime;
		follower.calculate(Robot.robotX, Robot.robotY, Robot.ahrs.getYaw(), dt);
		Robot.drive.tankDrive(follower.getLeftVelocity(), follower.getRightVelocity());
		startTime = Timer.getFPGATimestamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
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