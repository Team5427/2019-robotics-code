package org.usfirst.frc.team5427.robot.commands.auto.presets;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.Buffer;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeHatchLoadingStation extends CommandGroup {
    public IntakeHatchLoadingStation() {
        addParallel(new RotateWristAuto(new BigDecimal("6")));
        addSequential(new RotateArmAuto(new BigDecimal(Config.ARM_LIMIT_BOTTOM)));
    }
}