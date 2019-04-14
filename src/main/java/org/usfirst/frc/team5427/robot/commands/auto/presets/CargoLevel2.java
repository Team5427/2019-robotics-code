package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Different 
public class CargoLevel2 extends CommandGroup {
    public CargoLevel2() {
       
        addParallel(new RotateWristAuto(26.8));
        addSequential(new RotateArmAuto(75));

    }
}