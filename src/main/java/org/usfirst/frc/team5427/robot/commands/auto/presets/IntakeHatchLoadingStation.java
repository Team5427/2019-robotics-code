package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.Buffer;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeHatchLoadingStation extends CommandGroup {
    public IntakeHatchLoadingStation() {

        addSequential(new RotateWristAuto(20));
        addSequential(new Buffer(0.5));
        addParallel(new RotateWristAuto(11));
        addSequential(new RotateArmAuto(113));

        
    }

}