package org.usfirst.frc.team5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team5427.robot.AutoAction;
import org.usfirst.frc.team5427.robot.commands.TurnToAngle;
import org.usfirst.frc.team5427.robot.commands.MotionProfile;

import jaci.pathfinder.Waypoint;;

public class AutoPath {
   
    public ArrayList<AutoAction> autoActions = new ArrayList<>();
    public String data;
    public static final int portNumber = 5426;


    public AutoPath(String title) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        boolean dataNotRec = true;

        while (dataNotRec) {
            Socket socket = serverSocket.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                data = line;
                System.out.println("Client response: " + line);
                dataNotRec = false;
            }
            br.close();
            socket.close();
        }
        serverSocket.close();
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
