package org.usfirst.frc.team5427.robot.commands.auto.vision;

import org.usfirst.frc.team5427.robot.commands.auto.motion.TurnToAngle;
import org.usfirst.frc.team5427.robot.commands.auto.presets.CargoShipCargo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoShipFront extends CommandGroup {
    public CargoShipFront() {
        addParallel(new CargoShipCargo());
        addSequential(new TurnToAngle(0));
        addSequential(new DriveToTape());
    }
}