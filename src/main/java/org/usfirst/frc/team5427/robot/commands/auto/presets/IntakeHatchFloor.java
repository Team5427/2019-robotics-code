package org.usfirst.frc.team5427.robot.commands.auto.presets;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Need to check
public class IntakeHatchFloor extends CommandGroup {
    public IntakeHatchFloor() {

        addParallel(new RotateWristAuto(new BigDecimal("44")));
        addSequential(new RotateArmAuto(new BigDecimal("111")));

        
    }

}