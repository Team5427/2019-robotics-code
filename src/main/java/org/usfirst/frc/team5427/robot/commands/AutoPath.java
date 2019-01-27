package org.usfirst.frc.team5427.robot.commands;

import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.robot.commands.TurnToAngle;
import org.usfirst.frc.team5427.util.Config;


import jaci.pathfinder.Waypoint;

import org.usfirst.frc.team5427.robot.commands.MotionProfile;

public class AutoPath {
   
    public ArrayList<AutoAction> autoActions = new ArrayList<>();
    public String data;


    public AutoPath(String data) { 
        this.data = data;

        //parses text data into auto actions
        this.parseDataIntoActions();
    }

    public void parseDataIntoActions(){
        Scanner kb = new Scanner(data);

        //each command
        while(kb.hasNextLine()) {
            //determine action
            String action = kb.next();

            if(action.equals("Motion") || action.equals("MotionInv")) {
                //make waypoints, add it to arraylist because size of points are unknown
                ArrayList<Waypoint> w = new ArrayList<>();
                String line = kb.nextLine(); 
                Scanner k = new Scanner(line);
                while(k.hasNextDouble()) {
                    w.add(new Waypoint((k.nextDouble()), (k.nextDouble()), Config.dtr(k.nextDouble())));
                }
                k.close();
                
                //convert Waypoint arraylist to array to pass into new motion profile
                Waypoint[] way = new Waypoint[w.size()];
                for(int i = 0; i < w.size(); i++) {
                    way[i] = w.get(i);
                }
                int newIndex = autoActions.size();
                //add motion profile with points into list of tasks
                if(action.equals("MotionInv"))
                    autoActions.add(new MotionProfile(way,true));
                else
                    autoActions.add(new MotionProfile(way,false));

                if(autoActions.size()>1) {
                    autoActions.get(newIndex-1).setNextAction(autoActions.get(newIndex));
                }
            }
            else if(action.equals("TurnToAngle")) {
                int newIndex = autoActions.size();
                //add turn to angle with angle (in degrees) into list of tasks
                autoActions.add(new TurnToAngle(Double.parseDouble(kb.nextLine())));
                if(autoActions.size()>1) {
                    autoActions.get(newIndex-1).setNextAction(autoActions.get(newIndex));
                }
            }
            else if(action.equals("Buffer")) {
                int newIndex = autoActions.size();
                //add turn to angle with angle (in degrees) into list of tasks
                autoActions.add(new Buffer(Double.parseDouble(kb.nextLine())));
                if(autoActions.size()>1) {
                    autoActions.get(newIndex-1).setNextAction(autoActions.get(newIndex));
                }
            }
            else if(action.equals("VisionToTarget")) { 
                int newIndex = autoActions.size();
                //add motion profile with points into list of tasks
                autoActions.add(new VisionToTarget());
                                    
                if(autoActions.size() > 1) {
                    autoActions.get(newIndex - 1).setNextAction(autoActions.get(newIndex));
                }
                System.out.println("added new auto action vision to target");

            }
        }
        kb.close();
    }

    public void executeAutoActions() {
        //start first task immediately
        autoActions.get(0).start();        
    }
}
