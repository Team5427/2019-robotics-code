package org.usfirst.frc.team5427.robot.auto;

import java.util.ArrayList;

import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DriveToTape extends Command {

    public static MotionProfile motion;

    public DriveToTape() {

    }
    
    @Override
    protected void initialize() {
        ArrayList<Pose2d> poses = new ArrayList<Pose2d>();

        NetworkTable net = NetworkTable.getTable("ChickenVision");

        double x = net.getValue("tapeYaw").getDouble();

        double ultra = Robot.ultra.getRangeInches()/12;

        poses.add(new Pose2D(0,0,Robot.ahrs.getYaw()).pose);
        poses.add(new Pose2D(ultra, Math.tan(Math.toRadians(x)) *ultra, Robot.ahrs.getYaw()).pose);
        motion = new MotionProfile(poses);
    }

    



    @Override
    protected boolean isFinished() {
        return false;
    }

}