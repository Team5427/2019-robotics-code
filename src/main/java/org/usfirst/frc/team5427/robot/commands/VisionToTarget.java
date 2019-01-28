package org.usfirst.frc.team5427.robot.commands;

import java.util.concurrent.CompletableFuture;

import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

public class VisionToTarget extends AutoAction {

    public MotionProfile mp;

    public VisionToTarget() {
        System.out.println("created a new vision to target");

    }

    @Override
    protected void initialize() {
        super.initialize();
        while(Robot.client.lastRecievedGoal == null) {}

        System.out.println("starting vision to target");

        double distance = Robot.client.lastRecievedGoal.getDistance();
        distance -= (47-distance)*0.1;
        distance = Math.abs(Config.ftm(distance/12));
        double angle = Robot.client.lastRecievedGoal.getHorizontalAngle();
        double y_co = distance*Math.sin(angle) - Config.ftm(16.0/12.0);
        double x_co = distance*Math.cos(angle);
        System.out.println(distance+" DATA FROM VISION " + angle);
        //over to the right
        if(angle < 0 ) {
            y_co = -y_co;
        }
        

        Waypoint[] way = {

            new Waypoint(0, 1, Config.dtr(Robot.ahrs.getYaw())),
            new Waypoint(x_co, 1-y_co, 0)
        };
        System.out.println("**********" + (x_co) + "   AND   " + (y_co) + "***************");
        
        CompletableFuture.runAsync(() -> {
            Trajectory.Config config =  new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_FAST, 
            Config.DT*3, Config.MAX_VELOCITY, Config.MAX_ACCEL, Config.MAX_JERK);

            //generate the trajectory
            Trajectory trajectory = Pathfinder.generate(way, config);

            mp = new MotionProfile(trajectory);
            mp.nextAction = this.nextAction;
        });
     
    }
    @Override
    protected void execute() {
        super.execute();
    }
    @Override
    protected boolean isFinished() {
        if(mp == null) return false;
        return mp.isFinished();
    }
    @Override
    protected void end() {
        
    }
}