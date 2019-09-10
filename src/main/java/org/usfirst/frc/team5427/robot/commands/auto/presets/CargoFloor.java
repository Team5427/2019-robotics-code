package org.usfirst.frc.team5427.robot.commands.auto.presets;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoFloor extends CommandGroup {
    public CargoFloor() {
        
        addParallel(new RotateArmAuto(new BigDecimal("90")));
        addSequential(new RotateWristAuto(new BigDecimal("56.7")));
    }

}