package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HatchLevel3 extends CommandGroup {
    public HatchLevel3() {
        addSequential(new MoveElevatorAuto(4000));
        addParallel(new RotateWristAuto(10));
        addSequential(new RotateArmAuto(80));
    }
}