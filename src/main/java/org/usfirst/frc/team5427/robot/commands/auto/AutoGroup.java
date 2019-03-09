package org.usfirst.frc.team5427.robot.commands.auto;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;

public class AutoGroup extends Command {
    public ArrayList<AutoAction> actions;
    public AutoGroup nextAutoGroup;

    public AutoGroup(ArrayList<AutoAction> actions) {
        this.actions = actions;
    }

    public void initialize() {
        for(int i = 0; i < actions.size(); i++) {
            actions.get(i).start();
        }
    }

    @Override
    protected boolean isFinished() {
        boolean b = true;

        for(int i = 0; i < actions.size(); i++) {
            if(!actions.get(i).isCompleted()) {
                b = false;
            }
        }

        return b;
    }

    public void end() {

        if(nextAutoGroup != null) {
            nextAutoGroup.start();
        }
    }


}