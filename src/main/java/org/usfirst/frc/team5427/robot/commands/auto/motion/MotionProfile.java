package org.usfirst.frc.team5427.robot.commands.auto.motion;


import java.util.ArrayList;
import java.util.List;

import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class MotionProfile extends Command {

	public List<Pose2d> points;
	public FalconTrajectoryFollower follower;
	public double startTime;

	public ArrayList<Double> xPoints, yPoints;

	public ArrayList<TrajectoryPoint> path;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MotionProfile(List<Pose2d> points) {
		System.out.println("constructing motion profile");
	   	this.points = points;
	  	TrajectoryGen g = new TrajectoryGen();

		xPoints = new ArrayList<>();
		
		yPoints = new ArrayList<>();

	   	//points, max centripetal, start vel, end vel, max vel, linear accel, reversed
	   	path = g.generateTrajectory(points, 1.2, 0, 0, 11, 3, true);
	

		System.out.println(path);

		System.out.println(path.size() * 0.02);
		
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
		System.out.println(" init motion profile");

		Robot.robotX = 0;
		Robot.robotY = 0;

		startTime = Timer.getFPGATimestamp();
		//0.0121
		//0.097 .18
		
		follower = new FalconTrajectoryFollower(path, 3, true, 0.097, 0.18);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double dt = Timer.getFPGATimestamp() - startTime;

		follower.calculate(Robot.robotX, Robot.robotY, Robot.ahrs.getYaw(), dt);

		Robot.driveTrain.tankDrive(-follower.getLeftVelocity()/11 - 0.12, follower.getRightVelocity()/11 + 0.12);

		SmartDashboard.putNumber("left vel", -follower.getLeftVelocity()/11 - 0.12);
		SmartDashboard.putNumber("right vel", follower.getRightVelocity()/11 + 0.12);


		startTime = Timer.getFPGATimestamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
		if(follower!=null)
			return follower.isFinished();
		else
			return false;
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