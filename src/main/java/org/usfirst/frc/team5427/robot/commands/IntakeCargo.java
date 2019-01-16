package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCargo extends Command {
    public IntakeCargo() {
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
        this.setInterruptible(true);
    }

    @Override
    protected void execute() {
        Robot.intake.setSpeed(Config.INTAKE_SPEED_IN);
    }

    @Override
    protected boolean isFinished() {
        return !Robot.oi.getJoy().getRawButton(Config.BUTTON_INTAKE_IN);
    }

    @Override
    protected void end() {
        Robot.intake.stop();
    }
}