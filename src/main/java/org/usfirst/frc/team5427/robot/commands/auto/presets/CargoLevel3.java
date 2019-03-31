package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoLevel3 extends CommandGroup {
    public CargoLevel3() {
       
        addParallel(new RotateWristAuto(23.5));  
        addSequential(new RotateArmAuto(79));//max

    }
}