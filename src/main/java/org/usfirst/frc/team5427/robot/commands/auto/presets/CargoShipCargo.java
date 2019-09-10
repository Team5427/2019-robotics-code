package org.usfirst.frc.team5427.robot.commands.auto.presets;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoShipCargo extends CommandGroup {
    public CargoShipCargo() {
        
        addParallel(new RotateWristAuto(new BigDecimal("37")));//Different
        addSequential(new RotateArmAuto(new BigDecimal("80")));//Different
      
    }

}