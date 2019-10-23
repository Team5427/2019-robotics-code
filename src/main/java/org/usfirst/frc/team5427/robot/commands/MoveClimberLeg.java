// package org.usfirst.frc.team5427.robot.commands;

// import org.usfirst.frc.team5427.robot.Robot;
// import org.usfirst.frc.team5427.util.Config;

// import edu.wpi.first.wpilibj.command.Command;

// public class MoveClimberLeg extends Command
// {
//     public double speed;

//     public MoveClimberLeg(double speed)
//     {
//         requires(Robot.climberLeg);
//         this.speed = speed;
//     }

//     @Override
//     protected void initialize()
//     {
//         this.setInterruptible(true);
//     }

//     @Override
//     protected void execute()
//     {
//         Robot.climberLeg.setSpeed(speed);
//     }

//     @Override
//     protected boolean isFinished()
//     {
//         if (speed > 0)
//             return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_LEG_UP);
//         else if (speed < 0)
//             return !Robot.oi.getJoy().getRawButton(Config.BUTTON_CLIMBER_LEG_DOWN);
//         return false;
//     }

//     @Override
//     protected void end()
//     {
//         Robot.climberLeg.stop();
//     }
// }