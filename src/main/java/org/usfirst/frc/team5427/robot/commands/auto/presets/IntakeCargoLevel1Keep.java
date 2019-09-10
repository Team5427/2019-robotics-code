package org.usfirst.frc.team5427.robot.commands.auto.presets;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCargoLevel1Keep extends CommandGroup {
    public IntakeCargoLevel1Keep() {  
        addParallel(new RotateArmAuto(new BigDecimal("114")));
        addSequential(new RotateWristAuto(new BigDecimal("53.5")));
    }
}