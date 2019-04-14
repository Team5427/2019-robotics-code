package org.usfirst.frc.team5427.robot.commands.auto.vision;

import org.usfirst.frc.team5427.robot.commands.auto.motion.TurnToAngle;
import org.usfirst.frc.team5427.robot.commands.auto.presets.IntakeHatchLoadingStation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HatchLoadingStation extends CommandGroup {
    public HatchLoadingStation() {
        addParallel(new IntakeHatchLoadingStation());
        addSequential(new TurnToAngle(270));
        // addSequential(new DriveToTape());
    }
}