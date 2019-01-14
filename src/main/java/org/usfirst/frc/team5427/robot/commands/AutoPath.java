package org.usfirst.frc.team5427.robot.commands;

import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.commands.TurnToAngle;

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

            if(action.equals("Motion")) {
                //make waypoints, add it to arraylist because size of points are unknown
                ArrayList<Waypoint> w = new ArrayList<>();
                String line = kb.nextLine(); 
                Scanner k = new Scanner(line);
                while(k.hasNextDouble()) {
                    w.add(new Waypoint((k.nextDouble()), (k.nextDouble()), k.nextDouble()));
                }
                k.close();
                
                //convert Waypoint arraylist to array to pass into new motion profile
                Waypoint[] way = new Waypoint[w.size()];
                for(int i = 0; i < w.size(); i++) {
                    way[i] = w.get(i);
                }

                //add motion profile with points into list of tasks
                autoActions.add(new MotionProfile(way));
            }
            else if(action.equals("TurnToAngle")) {
                //add turn to angle with angle (in degrees) into list of tasks
                autoActions.add(new TurnToAngle(kb.nextDouble()));
            }
            //continue adding commands if more
            if(kb.hasNextLine())
                kb.nextLine();
        }
        kb.close();
    }

    public void executeAutoActions() {
        //start first task immediately
        autoActions.get(0).start();

        //for each next task, wait to start until previous task stops running
        for(int x = 1; x < autoActions.size(); x++) {
            while(autoActions.get(x-1).isRunning()) {}
            autoActions.get(x).start();
        }
    }
}
