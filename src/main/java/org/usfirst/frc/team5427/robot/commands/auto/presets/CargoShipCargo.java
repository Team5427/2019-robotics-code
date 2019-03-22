package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoShipCargo extends CommandGroup {
    public CargoShipCargo() {
        
        addSequential(new MoveElevatorAuto(0));
        addParallel(new RotateWristAuto(31));
        addSequential(new RotateArmAuto(84));
      
    }

}