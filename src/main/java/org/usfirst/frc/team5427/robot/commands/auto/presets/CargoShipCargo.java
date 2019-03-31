package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoShipCargo extends CommandGroup {
    public CargoShipCargo() {
        
        addParallel(new RotateWristAuto(29.5));
        addSequential(new RotateArmAuto(84.5));
      
    }

}