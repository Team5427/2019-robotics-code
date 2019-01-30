package org.usfirst.frc.team5427.robot.commands;


import java.util.List;

import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2dWithCurvature;
import org.ghrobotics.lib.mathematics.twodim.trajectory.types.TimedEntry;
import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;


public class MotionProfile extends Command {
    int encoder_offset, encoder_tick_count;
    double wheel_circumference;

    double kp, ki, kd, kv, ka;

    double last_error, heading;

    int segment;

    public List<TimedEntry<Pose2dWithCurvature>> points;

    //generate path, eventually open custom-made GUI (with built in commands such as drop a gear? dont know...)
    public MotionProfile(List<TimedEntry<Pose2dWithCurvature>> points) {
       this.points = points;
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

        
       
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //finish when both sides are done with data
        return segment >= points.size();
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
    
     

     /**
     * Calculate the desired output for the motors, based on the amount of ticks the encoder has gone through.
     * This does not account for heading of the robot. To account for heading, add some extra terms in your control
     * loop for realignment based on gyroscope input and the desired heading given by this object.
     * @return             The desired output for your motor controller
     */
    public double calculate() {
        
        if (segment < points.size()) {
            TimedEntry<Pose2dWithCurvature> seg = points.get(segment);
            double calculated_value =       
                    (kv * seg.getVelocity().getValue() + ka * seg.getAcceleration().getValue());    // V and A Terms
           
            segment++;

            return calculated_value;
        } else return 0;
    }

}