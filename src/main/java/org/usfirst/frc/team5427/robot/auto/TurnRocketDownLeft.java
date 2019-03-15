package org.usfirst.frc.team5427.robot.auto;


import java.util.ArrayList;

import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnRocketDownLeft extends Command {
    public static MotionProfile motion;

    public TurnRocketDownLeft() {

    }

    @Override
    protected void initialize() {
        
        double angle = Robot.ahrs.getYaw();
        double goalAngle = -30;
        ArrayList<Pose2d> mp = new ArrayList<Pose2d>();

        mp.add(new Pose2D(0,0,angle).pose);
        mp.add(new Pose2D(0,0,goalAngle).pose);

        motion = new MotionProfile(mp);

        motion.start();


    }

    @Override
    protected boolean isFinished() {
        if(motion!=null)
            return motion.isFinished(); 
        else 
            return false;
    }

}