// package org.usfirst.frc.team5427.robot.commands.auto;

// import org.usfirst.frc.team5427.robot.Robot;

// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// /**
//  * Drives robot at given speed for 2 seconds
//  * 
//  * @author Akshat Jain
//  */
// public class ContinuousFull extends Command {
//     public double startTime;

//     public String distance;
//     public String percentage;
//     public double sp;

// 	/**
// 	 * ContinuousFull requires the drive train subsystem.
// 	 */
// 	public ContinuousFull() {
// 		requires(Robot.driveTrain);
// 	}

// 	/**
// 	 * Called once when the command is started but is not used for anything.
// 	 */
// 	@Override
//     protected void initialize() {
// 		Robot.encLeft.reset();
// 		Robot.encRight.reset();
//         startTime = Timer.getFPGATimestamp();
//         sp = 1;
//         distance = "";
// 		percentage = "";
// 		Robot.driveLeft.set(-sp);
// 		Robot.driveRight.set(sp);
//     }
// 	double distance_covered, distance_covered_r;
// 	/**
// 	 * Called periodically while the command is not finished. Drives at set speed;
// 	 */
// 	@Override
// 	protected void execute() {
// 		Robot.driveLeft.set(-sp);
// 		Robot.driveRight.set(sp);

//         SmartDashboard.putNumber("Position X", (Robot.encLeft.get()/360)*Math.PI*0.5);
        
//         SmartDashboard.putNumber("Accel X", Robot.ahrs.getRawAccelX());
//         SmartDashboard.putNumber("Accel Y", Robot.ahrs.getRawAccelY());

// 		distance_covered = ((double)(-Robot.encLeft.get()) / 360)
// 		* 0.1524 * Math.PI;
// 		distance_covered_r = ((double)(Robot.encRight.get()) / 360)
// 		* 0.1524 * Math.PI;
// 		SmartDashboard.putNumber("distance cov", distance_covered);
// 		SmartDashboard.putNumber("distance cov right", distance_covered_r);
// 	}

// 	/**
// 	 * Called periodically while the command is running to check when the command is
// 	 * finished.
// 	 * 
// 	 * @return false
// 	 */
// 	@Override
// 	protected boolean isFinished() {
// 		return Timer.getFPGATimestamp() - startTime >= 2.0;
// 	}

// 	/**
//      * 
// 	 * Called once when the command is finished. Sets the velocity of the drive
// 	 * train to 0 power.
// 	 */
// 	@Override
// 	protected void end() {
//         SmartDashboard.putNumber("Average Distance Covered", (distance_covered+distance_covered_r) / 2);

// 		Robot.driveTrain.stop();
// 	}

// 	/**
// 	 * Called once if the command is interrupted. Calls the end method in response.
// 	 */
// 	@Override
// 	protected void interrupted() {
// 		end();
// 	}
// }