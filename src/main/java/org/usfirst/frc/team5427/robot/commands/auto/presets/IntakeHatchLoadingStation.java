package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.Buffer;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeHatchLoadingStation extends CommandGroup {
    public IntakeHatchLoadingStation() {
        addParallel(new RotateWristAuto(10.1));
        addSequential(new RotateArmAuto(Config.ARM_LIMIT_BOTTOM));
    }
}