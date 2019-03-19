package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CargoLevel1 extends CommandGroup {
    public CargoLevel1() {
        addParallel(new RotateWristAuto(29.5));
        addSequential(new RotateArmAuto(112.7));
        
    }
}