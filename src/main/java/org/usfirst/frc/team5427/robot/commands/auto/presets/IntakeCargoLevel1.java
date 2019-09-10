package org.usfirst.frc.team5427.robot.commands.auto.presets;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCargoLevel1 extends CommandGroup {
    public IntakeCargoLevel1() {  
        addSequential(new RotateWristAuto(new BigDecimal("15")));//Different
        addParallel(new RotateArmAuto(new BigDecimal("114")));//Different
        addSequential(new RotateWristAuto(new BigDecimal("53.5")));//Different
    }
}