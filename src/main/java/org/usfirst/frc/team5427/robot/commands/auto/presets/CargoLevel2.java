package org.usfirst.frc.team5427.robot.commands.auto.presets;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Different 
public class CargoLevel2 extends CommandGroup {
    public CargoLevel2() {
       
        addParallel(new RotateWristAuto(new BigDecimal("26.8")));
        addSequential(new RotateArmAuto(new BigDecimal("70")));

    }
}