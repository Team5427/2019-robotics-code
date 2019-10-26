package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HatchLevel1 extends CommandGroup {
    public HatchLevel1() {

        addParallel(new RotateWristAuto(14.9));
        addSequential(new RotateArmAuto(106.9));
        
    }

}