package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCargoLevel1Keep extends CommandGroup {
    public IntakeCargoLevel1Keep() {  
        addSequential(new MoveElevatorAuto(1395));
        addParallel(new RotateArmAuto(114));
        addSequential(new RotateWristAuto(53.5));
    }
}