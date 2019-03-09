package org.usfirst.frc.team5427.robot.commands.auto.presets;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.auto.MoveElevatorAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateArmAuto;
import org.usfirst.frc.team5427.robot.commands.auto.RotateWristAuto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HatchLevel1 extends CommandGroup {
    public HatchLevel1() {

        System.out.println(Robot.elevator_enc.get());
        addParallel(new RotateWristAuto(82));
        addSequential(new RotateArmAuto(108));
        
    }

}