package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoShipCargo extends CommandGroup {
    public CargoShipCargo() {
        
        addParallel(new RotateWristAuto(37));//Different
        addSequential(new RotateArmAuto(80));//Different
      
    }

}