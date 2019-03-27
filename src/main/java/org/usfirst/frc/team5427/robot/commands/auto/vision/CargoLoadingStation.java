package org.usfirst.frc.team5427.robot.commands.auto.vision;

import org.usfirst.frc.team5427.robot.commands.auto.motion.TurnToAngle;
import org.usfirst.frc.team5427.robot.commands.auto.presets.CargoShipCargo;
import org.usfirst.frc.team5427.robot.commands.auto.presets.IntakeCargoLevel1;
import org.usfirst.frc.team5427.robot.commands.auto.presets.IntakeCargoLoadingStation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoLoadingStation extends CommandGroup {
    public CargoLoadingStation() {
        addParallel(new IntakeCargoLoadingStation());
        addSequential(new TurnToAngle(180));
        addSequential(new DriveToTape());
    }
}
