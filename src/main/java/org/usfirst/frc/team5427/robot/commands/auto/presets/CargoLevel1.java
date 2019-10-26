package org.usfirst.frc.team5427.robot.commands.auto.presets;


import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Different
public class CargoLevel1 extends CommandGroup {
    public CargoLevel1() {
        addParallel(new RotateWristAuto(27.6));//45 27.6
        addSequential(new RotateArmAuto(112.8)); //89
        
    }
}