package org.usfirst.frc.team5427.robot;

public class Point{
    private int deltaTime;
    private double position, velocity, acceleration, jerk;
    
    public Point(int deltaTime, double position, double velocity, double acceleration, double jerk) {
        this.deltaTime = deltaTime;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.jerk = jerk;
    }

    public int getDeltaTime() {
        return deltaTime;
    }

    public double getPosition() {
        return position;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getJerk() {
        return jerk;
    }

}