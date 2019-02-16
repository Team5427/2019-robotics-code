package org.usfirst.frc.team5427.robot.commands;

import java.util.concurrent.CompletableFuture;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.DistanceFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class MoveElev extends Command {

    public DistanceFollower follower;
    public DistanceFollower followerR;


    public boolean backwards;
    public int runs;
    public double speedL;
    public double speedR;
    public double h;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MoveElev(Trajectory trajectory, boolean backwards) {
        //create followers to manage input+output
        follower = new DistanceFollower(trajectory);
        
        follower.configurePIDVA(Config.KP_ELEV, Config.KI_ELEV, Config.KD_ELEV, Config.KV_ELEV, Config.KA_ELEV);   

        this.backwards = backwards;
    }

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MoveElev(double h2) {
        this.h = h2;
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
        CompletableFuture.runAsync(() -> {

        Trajectory.Config configElev =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 
        Config.DT_ELEV, Config.MAX_VELOCITY, Config.MAX_ACCEL, Config.MAX_JERK);
        double dist = (-Robot.encElevator.getDistance() + Robot.encElevator1.getDistance())/2;
        System.out.println(dist);
		Trajectory trajectory = Pathfinder.generate(
            new Waypoint[] {new Waypoint(dist, 0, 0), 
                            new Waypoint(h, 0, 0)}, configElev);

        TankModifier mod = new TankModifier(trajectory);
        mod.modify(0.6);
        
        //create followers to manage input+output
        follower = new DistanceFollower(mod.getLeftTrajectory());
        followerR = new DistanceFollower(mod.getRightTrajectory());

        
        follower.configurePIDVA(Config.KP, Config.KI, Config.KD, Config.KV, Config.KA);
        followerR.configurePIDVA(Config.KP, Config.KI, Config.KD, Config.KV, Config.KA);  

        
        if(h < dist) {
            this.backwards = true;
        }
        else {
            this.backwards = false;
        }
        speedL = 0;
        speedR = 0;
        runs = 0;
        System.out.println("ended follower stuff");
    });
    }

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        if(follower!= null && followerR!=null) {
            if(runs++ % (Config.DT_ELEV/0.02) == 0) {
                SmartDashboard.putNumber("enc elevator", Robot.encElevator.getDistance());
                SmartDashboard.putNumber("enc elevator1", Robot.encElevator1.getDistance());
                speedL = follower.calculate(-Robot.encElevator.getDistance());
                speedR = followerR.calculate(Robot.encElevator1.getDistance());

                if(!backwards) {
                    speedL += 0.115;
                    speedR += 0.115;
                }
                else {
                    speedL += 0.13;
                    speedR += 0.13;
                }
            } 
            if(backwards) {
                // Robot.elevator.set(-follower.calculate(Robot.encElevator.getDistance()));
                System.out.println("backwards " + h + " speedL " +speedL + " speedR " + speedR );
                Robot.driveTrain.tankDrive(-speedL, speedR);
            }
            else {
                Robot.driveTrain.tankDrive(speedL, -speedR);
            }
        }   
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //finish when both sides are done with data
        if(follower!= null && followerR!=null)
            return follower.isFinished() && followerR.isFinished();
        return false;    
    }

	// Called once after isFinished returns true
	@Override
	protected void end() {
       this.speedL = 0;
       this.speedR = 0;
       this.follower = null;
       this.followerR = null;
       this.runs = 0;
    //    this.h = 0;
       this.backwards = false;

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}