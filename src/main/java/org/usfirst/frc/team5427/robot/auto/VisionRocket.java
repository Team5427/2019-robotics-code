package org.usfirst.frc.team5427.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionRocket extends CommandGroup {
    public VisionRocket() {
        addSequential(new TurnToAngle(-30));
        // addSequential(new DriveToTape());
    }
}