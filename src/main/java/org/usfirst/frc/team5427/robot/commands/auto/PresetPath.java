package org.usfirst.frc.team5427.robot.commands.auto;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PresetPath extends CommandGroup{
   
    public ArrayList<AutoGroup> autoGroupActions = new ArrayList<>();
    public String data;


    public PresetPath(String data) { 
        this.data = data;

        String[] groups = this.data.split(Pattern.quote("|"));

        //each command
        for(int i = 0; i < groups.length; i++) {
            //determine action
            String actionGroup = groups[i];
            
            Scanner kb = new Scanner(actionGroup);

            ArrayList<AutoAction> actionsInGroup = new ArrayList<>();
            
            while(kb.hasNext()) {
                String action = kb.next();
                
                if(action.equals("Elevator")) {
                    double inches = kb.nextDouble();
                    
                    actionsInGroup.add(new MoveElevatorAuto(Math.abs(inches)));
                }
                else if(action.equals("Wrist")) {
                    double angle = kb.nextDouble();
                  

                    actionsInGroup.add(new RotateWristAuto(Math.abs(angle)));

                }
                else if(action.equals("Arm")) {
                    double angle = kb.nextDouble();


                    actionsInGroup.add(new RotateArmAuto(angle));

                }
                  
            }
            AutoGroup g = new AutoGroup(actionsInGroup);
            autoGroupActions.add(g);

            if(autoGroupActions.size() > 1) {
                autoGroupActions.get(i-1).nextAutoGroup = g;
            }

        }          
    }



    public void executeAutoActions() {
        //start first task immediately
        autoGroupActions.get(0).start();        
    }

    public void initialize() {
        autoGroupActions.get(0).start();
    }

    @Override
    protected boolean isFinished() {
        boolean b = true;
        for(int i = 0; i < autoGroupActions.size(); i++){
            if(!autoGroupActions.get(i).isCompleted()) {
                b = false;
            }
        }
        return b;
    }
}