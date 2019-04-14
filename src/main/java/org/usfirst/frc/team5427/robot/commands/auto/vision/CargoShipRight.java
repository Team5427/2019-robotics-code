package org.usfirst.frc.team5427.robot.commands.auto.vision;

import org.usfirst.frc.team5427.robot.commands.auto.motion.TurnToAngle;
import org.usfirst.frc.team5427.robot.commands.auto.presets.CargoShipCargo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoShipRight extends CommandGroup {
    public CargoShipRight() {
        addParallel(new CargoShipCargo());
        addSequential(new TurnToAngle(270));
        // addSequential(new DriveToTape());
    }
}