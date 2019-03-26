package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoLevel2 extends CommandGroup {
    public CargoLevel2() {
       
        addSequential(new MoveElevatorAuto(3050));
        addParallel(new RotateWristAuto(36.5));
        addSequential(new RotateArmAuto(91.5));

    }
}