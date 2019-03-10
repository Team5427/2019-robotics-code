package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.Buffer;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeHatchLoadingStation extends CommandGroup {
    public IntakeHatchLoadingStation() {

        addSequential(new RotateWristAuto(16));
        addSequential(new Buffer(1));
        addSequential(new MoveElevatorAuto(0));
        addParallel(new RotateWristAuto(10));
        addSequential(new RotateArmAuto(113));

        
    }

}