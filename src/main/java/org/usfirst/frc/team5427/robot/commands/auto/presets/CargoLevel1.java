package org.usfirst.frc.team5427.robot.commands.auto.presets;


import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Different
public class CargoLevel1 extends CommandGroup {
    public CargoLevel1() {
        addParallel(new RotateWristAuto(new BigDecimal("27.6")));//45
        addSequential(new RotateArmAuto(new BigDecimal("112.8"))); //89
        
    }
}