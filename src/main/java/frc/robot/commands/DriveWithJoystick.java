package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Utilizes the joystick inputs in order to drive the robot.
 * 
 * @author Varsha Kumar
 */
public class DriveWithJoystick extends Command {

	/**
	 * DriveWithJoystick requires the drive train subsystem.
	 */
	public DriveWithJoystick() {
		requires(Robot.driveTrain);
	}

	/**
	 * Called once when the command is started but is not used for anything.
	 */
	@Override
	protected void initialize() {}

	/**
	 * Called periodically while the command is not finished. Delivers the joystick
	 * inputs into the drive train subsystem in order to drive the robot.
	 */
	@Override
	protected void execute() {
		Robot.driveTrain.takeJoystickInputs(Robot.oi.getJoy());
	}

	/**
	 * Called periodically while the command is running to check when the command is
	 * finished.
	 * 
	 * @return false
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once when the command is finished. Sets the velocity of the drive
	 * train to 0 power.
	 */
	@Override
	protected void end() {
		Robot.driveTrain.stop();
	}

	/**
	 * Called once if the command is interrupted. Calls the end method in response.
	 */
	@Override
	protected void interrupted() {
		end();
	}
}
