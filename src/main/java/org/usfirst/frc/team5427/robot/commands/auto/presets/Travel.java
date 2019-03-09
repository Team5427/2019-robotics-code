package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Travel extends CommandGroup {
    public Travel() {

        addParallel(new RotateWristAuto(96));
        addSequential(new RotateArmAuto(99));
        addSequential(new MoveElevatorAuto(0));
        addParallel(new RotateArmAuto(114));
        addSequential(new RotateWristAuto(79));

        
    }

}