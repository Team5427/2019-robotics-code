package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Different
public class HatchLevel2 extends CommandGroup {
    public HatchLevel2() {
        addParallel(new RotateWristAuto(7));
        addSequential(new RotateArmAuto(68));
    }
}