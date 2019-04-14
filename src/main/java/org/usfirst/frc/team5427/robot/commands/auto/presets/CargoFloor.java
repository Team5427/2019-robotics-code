package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoFloor extends CommandGroup {
    public CargoFloor() {
        
        addParallel(new RotateArmAuto(90));
        addSequential(new RotateWristAuto(56.7));
    }

}