package org.usfirst.frc.team5427.robot.commands.auto.presets;

import java.math.BigDecimal;

import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.CommandGroup;

//Wrist up first, then arms down.
public class Travel extends CommandGroup {
    public Travel() {
        addSequential(new RotateWristAuto(new BigDecimal(Config.WRIST_LIMIT_TOP)));
        addSequential(new RotateArmAuto(new BigDecimal(Config.ARM_LIMIT_BOTTOM)));
        
    }

}