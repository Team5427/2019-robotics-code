package org.usfirst.frc.team5427.robot.commands.auto.presets;


import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftHatchOffLoadingStation extends CommandGroup {
    public LiftHatchOffLoadingStation() {
        addSequential(new RotateWristAuto(new BigDecimal("2"), true));
    }

} 