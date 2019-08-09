package org.usfirst.frc.team5427.robot.subsystems;


import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This class creates a Subsystem for the arm. 
 */
public class Arm extends Subsystem
{
    //237 - 159
    
    /**
     * Speed controller for the arm motor. 
     */
    private SpeedController armMotor;

    /**
     * Value to store the angle of the arm. 
     */
    public double armAngle;

    /**
     * Constructor for the arm subsystem that initializes the arm speed controller. 
     * @param armMotor the speed controller that controls the arm motor. 
     */
    public Arm(SpeedController armMotor)
    {
        this.armMotor = armMotor;
    }

    /**
     * Sets the speed of the arm depending on the potentiometer value. If the arm is
     * at the limits, the speed is set to 0. 
     * @param armSpeed The received speed that the arm needs to move at. 
     */
    public void moveArm(double armSpeed)
    {
        //if the arm is not at the limits
        if((armSpeed > 0 && Robot.armPot.get() >= Config.ARM_LIMIT_TOP) 
        || (armSpeed < 0 && Robot.armPot.get() <= Config.ARM_LIMIT_BOTTOM))
            armMotor.set(armSpeed);
        else //if the arm is at or beyond the limits
            armMotor.set(0);
    }

    /**
     * Sets the motor speed of the arm to 0. 
     */
    public void stop()
    {
        armMotor.set(0);
    }

    /**
     * Required when extending Subsystem. In this case, the arm has no default command
     */
    @Override
    protected void initDefaultCommand()
    {

    }

    /**
     * Sets the angle of the arm based on the received value. 
     * @param angle the received value of the arm. 
     */
    public void setArmAngle(double angle)
    {
        this.armAngle = angle;
    }

    /**
     * Gives the angle of the arm. 
     * @return the angle of the arm. 
     */
    public double getArmAngle()
    {
        return armAngle;
    }
}