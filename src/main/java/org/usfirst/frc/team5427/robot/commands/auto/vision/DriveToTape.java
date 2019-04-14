// package org.usfirst.frc.team5427.robot.commands.auto.vision;

// import java.util.ArrayList;

// import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;
// import org.usfirst.frc.team5427.robot.Robot;
// import org.usfirst.frc.team5427.robot.commands.auto.motion.MotionProfile;
// import org.usfirst.frc.team5427.robot.commands.auto.motion.Pose2D;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.networktables.NetworkTable;

// public class DriveToTape extends Command {

//     public MotionProfile motion;
//     public double stopBefore;

//     public DriveToTape(double stopBefore) {
//         this.stopBefore = stopBefore;
//     }
//     public DriveToTape() {
//         this.stopBefore = 10.0;
//     }

//     @Override
//     protected void initialize() {
//         ArrayList<Pose2d> poses = new ArrayList<Pose2d>();

//         NetworkTable net = NetworkTable.getTable("ChickenVision");

//         double x = net.getValue("tapeYaw").getDouble();

//         double ultra = Robot.ultra.getRangeInches()/12.0;

//         poses.add(new Pose2D(0,0,Robot.ahrs.getYaw()).pose);
//         poses.add(new Pose2D(ultra - stopBefore/12.0, (Math.tan(Math.toRadians(x)) *ultra)/12, Robot.ahrs.getYaw()).pose);
//         motion = new MotionProfile(poses);
//     }


//     @Override
//     protected boolean isFinished() {
//         if(motion != null) {
//             return motion.isCompleted();
//         }
//         else {
//             return false;
//         }
//     }

// } 