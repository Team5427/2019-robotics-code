package org.usfirst.frc.team5427.robot.commands;


import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class MotionProfile extends AutoAction {

    public EncoderFollower followerL;
    public EncoderFollower followerR;

    boolean backwards;

    public int frequencyDivide;
    public int startingAngle;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MotionProfile(Waypoint[] points, boolean backwards) {
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

        //value configuration
        followerL.configureEncoder(-Robot.encLeft.get(), 360, 0.1524); //initial encoder, ticks per revolution, wheel diameter meters
        followerR.configureEncoder(Robot.encRight.get(), 360, 0.1524); //initial encoder, ticks per revolution, wheel diameter meters

        followerL.configurePIDVA(Config.KP, Config.KI, Config.KD, Config.KV, Config.KA);
        followerR.configurePIDVA(Config.KP, Config.KI, Config.KD, Config.KV, Config.KA);
        
        this.backwards = backwards;
        System.out.println("done motion");
        this.frequencyDivide = 1;
        startingAngle = 0;
    }

    public MotionProfile(Trajectory trajectory) {
          // Create the Modifier Object (tank drive train)
          TankModifier modifier = new TankModifier(trajectory);
          modifier.modify(Config.ftm(Config.WHEELBASE_WIDTH));
  
          //create followers to manage input+output
          followerL = new EncoderFollower(modifier.getLeftTrajectory());
          followerR = new EncoderFollower(modifier.getRightTrajectory());
          System.out.println(-Robot.encLeft.get()+"\n" + Robot.encRight.get());
          //value configuration
          followerL.configureEncoder(-Robot.encLeft.get(), 360, 0.1524); //initial encoder, ticks per revolution, wheel diameter meters
          followerR.configureEncoder(Robot.encRight.get(), 360, 0.1524); //initial encoder, ticks per revolution, wheel diameter meters
  
          followerL.configurePIDVA(Config.KP, Config.KI, Config.KD, Config.KV, Config.KA);
          followerR.configurePIDVA(Config.KP, Config.KI, Config.KD, Config.KV, Config.KA);
          
          this.backwards = false;
          this.frequencyDivide = 3;
          this.start();
          startingAngle = 90;
    }
    double x;
    double spL;
    double spR;
    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
        //reset all sensors/ control loops
        // Robot.encLeft.reset(); 
        // Robot.encRight.reset();
        
        // Robot.ahrs.reset();
        // Robot.ahrs.resetDisplacement();
        System.out.println("starting motion");
        x= 0;
        spR = 0;
        spL = 0;

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        if(x++ % frequencyDivide == 0) {
        double distance_covered = ((double)(Robot.encLeft.get()) / 360)
                * 0.1524 * Math.PI;
        double distance_covered_right = ((double)(Robot.encRight.get()) / 360)
        * 0.1524 * Math.PI;
        SmartDashboard.putNumber("distance covered", distance_covered);
        SmartDashboard.putNumber("distance covered right", distance_covered_right);


        //calulate speed of each side of drive train based on encoder position
        double speedL, speedR;
        if(!backwards) {
            speedL = followerL.calculate(-Robot.encLeft.get());
            speedR = followerR.calculate(Robot.encRight.get());
        }
        else {
            speedL = followerL.calculate(Robot.encLeft.get());
            speedR = followerR.calculate(-Robot.encRight.get());
        }
        
        
        double gyro_heading = -Robot.ahrs.getYaw();    // Assuming the gyro is giving a value in degrees
        double desired_heading = Pathfinder.r2d(followerL.getHeading());  // Should also be in degrees
        SmartDashboard.putNumber("desired heading", desired_heading);
        //calculate heading curvature
        double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
        double turn = -0.01 * angleDifference;
        
        if(!backwards) {
            //apply to motors     
            spL = (speedL + turn);
            spR = (-(speedR - turn));
        }
        else {
            spL = (-(speedL+turn));
            spR = (speedR-turn);
        }
         }
         Robot.driveLeft.set(spL);
         Robot.driveRight.set(spR);
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
        Robot.driveTrain.stop();
        if(this.nextAction!=null)
            this.nextAction.start();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}