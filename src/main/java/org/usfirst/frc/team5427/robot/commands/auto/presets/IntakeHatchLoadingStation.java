package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeHatchLoadingStation extends CommandGroup {
    public IntakeHatchLoadingStation() {

        addSequential(new MoveElevatorAuto(0));
        addParallel(new RotateWristAuto(86));
        addSequential(new RotateArmAuto(113));

        
    }

}