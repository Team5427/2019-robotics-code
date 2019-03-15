package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCargoLoadingStation extends CommandGroup {
    public IntakeCargoLoadingStation() {
        
        addParallel(new RotateArmAuto(82));
        addSequential(new RotateWristAuto(30.5));
        addSequential(new MoveElevatorAuto(0));
    }

}