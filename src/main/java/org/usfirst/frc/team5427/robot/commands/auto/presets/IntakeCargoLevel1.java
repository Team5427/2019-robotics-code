package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCargoLevel1 extends CommandGroup {
    public IntakeCargoLevel1() { 
        addSequential(new RotateWristAuto(96));
        addSequential(new MoveElevatorAuto(1395));
        addParallel(new RotateArmAuto(114));
        addSequential(new RotateWristAuto(126));
    }
}