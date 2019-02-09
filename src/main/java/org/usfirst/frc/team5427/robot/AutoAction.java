package org.usfirst.frc.team5427.robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoAction extends Command {
    public AutoAction nextAction;
    
    @Override
    protected boolean isFinished() {
        return false;
    }

    public void setNextAction(AutoAction a) {
        this.nextAction = a;
    }

}