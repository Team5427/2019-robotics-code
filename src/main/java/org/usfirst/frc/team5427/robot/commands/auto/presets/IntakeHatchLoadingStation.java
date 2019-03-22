package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.Buffer;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeHatchLoadingStation extends CommandGroup {
    public IntakeHatchLoadingStation() {

        addSequential(new RotateWristAuto(17));
        addSequential(new Buffer(0.5));
        addSequential(new MoveElevatorAuto(0));
        addParallel(new RotateWristAuto(9));
        addSequential(new RotateArmAuto(113));

        
    }

}