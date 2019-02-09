package org.usfirst.frc.team5427.robot.commands.auto;

import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnUntilTargetDetected extends Command {
	
	double power;
	
	public TurnUntilTargetDetected(double power) {
		this.power = power;
		//requires(Robot.camera);
		requires(Robot.driveTrain);
	}
	
	protected void initialize() {
		Robot.driveTrain.stop();
	}
	
	protected void execute() {
		Robot.driveTrain.tankDrive(power, power);
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.client.isTargetVisible();
	}
	
	@Override
	protected void end(){
		Robot.driveTrain.stop();
	}
}