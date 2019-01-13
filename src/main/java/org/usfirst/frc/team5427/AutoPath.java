package org.usfirst.frc.team5427;

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
        //OPTIONS: Motion(set of points -> x,y,heading), TurnToAngle (angle)
                
         this.data = data;
    }

    public void parseDataIntoActions(){
        Scanner kb = new Scanner(data);

        while(kb.hasNextLine()) {
            String action = kb.next();

            if(action.equals("Motion")) {
                ArrayList<Waypoint> w = new ArrayList<>();
                String line = kb.nextLine(); 
                Scanner k = new Scanner(line);
                while(k.hasNextDouble()) {
                    w.add(new Waypoint(k.nextDouble(), k.nextDouble(), k.nextDouble()));
                }
                k.close();
                
                Waypoint[] way = new Waypoint[w.size()];
                for(int i = 0; i < w.size(); i++) {
                    way[i] = w.get(i);
                }
                autoActions.add(new MotionProfile(way));
            }
            else if(action.equals("TurnToAngle")) {
                autoActions.add(new TurnToAngle(kb.nextDouble()));
            }
            kb.nextLine();
        }
        kb.close();
    }
    public void executeAutoActions() {
        autoActions.get(0).start();

        for(int x = 1; x < autoActions.size(); x++) {
            while(autoActions.get(x-1).isRunning()) {}
            autoActions.get(x).start();
        }
    }
}
