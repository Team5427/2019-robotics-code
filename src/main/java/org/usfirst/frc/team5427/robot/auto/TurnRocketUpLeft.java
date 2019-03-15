package org.usfirst.frc.team5427.robot.auto;


import org.usfirst.frc.team5427.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnRocketUpLeft extends Command {


    public TurnRocketUpLeft() {

    }

    @Override
    protected void initialize() {
        double angle = Robot.ahrs.getYaw();
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}