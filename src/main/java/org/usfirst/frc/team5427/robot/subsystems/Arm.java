package org.usfirst.frc.team5427.robot.subsystems;
import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

//237 - 159

/**This class creates a Subsystem for the arm. */
public class Arm extends Subsystem{
    
     /**Speed controller for the arm motor. */
    private SpeedController armMotor;

    /** Value to store the angle of the arm.*/
    private double armAngle;

    /**
     * Constructor for the arm subsystem that initializes the arm speed controller. 
     * @param armMotor the speed controller that controls the arm motor. 
     */
    public Arm(SpeedController armMotor){
        this.armMotor = armMotor;
    }

    /**
     * Sets the speed of the arm depending on the potentiometer value. If the arm is
     * at the limits, the speed is set to 0. 
     * @param armSpeed The received speed that the arm needs to move at. 
     */
    public void moveArm(double armSpeed){
        if((armSpeed > 0 && Robot.getArmPot().get() >= Config.ARM_LIMIT_TOP) //if the arm is not at the limits
        || (armSpeed < 0 && Robot.getArmPot().get() <= Config.ARM_LIMIT_BOTTOM))
            armMotor.set(armSpeed); 
        else armMotor.stopMotor();  //if the arm is at or beyond the limits
    }



    /** Sets the motor speed of the arm to 0.  */
    public void stop(){
        armMotor.stopMotor();
    }

    /** Sets the arm's angle to given angle.  @param angle given angle for arm. */
    public void setArmAngle(double angle){
        this.armAngle = angle;
    }

    /** Returns the arm's angle.  @return the angle of the arm. */
    public double getArmAngle(){
        return armAngle;
    }

    /** Required when extending Subsystem. In this case, the arm has no default command*/
    @Override
    protected void initDefaultCommand(){}
}