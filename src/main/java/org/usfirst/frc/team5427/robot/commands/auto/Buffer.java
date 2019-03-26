package org.usfirst.frc.team5427.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Waits for a given time
 * 
 * @author Akshat Jain
 */
public class Buffer extends Command {
    
    public double startTime;
	public double goalTime;
	public Buffer(double time) {
        this.goalTime = time;
	}

	/**
	 * Called once when the command is started but is not used for anything.
	 */
	@Override
    protected void initialize() {
		startTime = Timer.getFPGATimestamp();
    }
	/**
	 * Called periodically while the command is not finished. Delivers the joystick
	 * inputs into the drive train subsystem in order to drive the robot.
	 */
	@Override
	protected void execute() {
		
	}

	/**
	 * Called periodically while the command is running to check when the command is
	 * finished.
	 * 
	 * @return false
	 */
	@Override
	protected boolean isFinished() {
        return (Timer.getFPGATimestamp() - startTime) >= goalTime;
	}

	/**
	 * Called once when the command is finished. Sets the velocity of the drive
	 * train to 0 power.
	 */
	@Override
	protected void end() {

	}

	/**
	 * Called once if the command is interrupted. Calls the end method in response.
	 */
	@Override
	protected void interrupted() {
		end();
	}
}