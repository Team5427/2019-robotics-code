/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import java.util.ArrayList;

import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {

  public static DriveTrain driveTrain;
  public static DifferentialDrive drive;
  public static OI oi;

  public static SpeedController driveFrontLeft;
  public static SpeedController driveFrontRight;
  public static SpeedController driveRearLeft;
  public static SpeedController driveRearRight;

  public static SpeedControllerGroup driveLeft;
  public static SpeedControllerGroup driveRight;

  //motion profiling
  private ArrayList<Point> tenFeetForwardPoints;
  private Encoder encLeft;
  private int currentDataPointCount;
  public static final double ENCODER_DISTANCE_OFFSET = 0.9752;
  private double error_last, dt, last_time;

  //tuning
  public Point setpoint;
  public static final double KV = 0.25;
  public static final double KA = 0; //0.33
  public static final double KP = 0;
  public static final double KD = 0;

  //for velocity
  private double dtt, last_ttime, pos_last, pos;



  @Override
  public void robotInit() {
      driveFrontLeft = new Talon(Config.FRONT_LEFT_MOTOR);
      driveFrontRight = new Talon(Config.FRONT_RIGHT_MOTOR);
      driveRearLeft = new Talon(Config.REAR_LEFT_MOTOR);
      driveRearRight = new Talon(Config.REAR_RIGHT_MOTOR);

      driveLeft = new SpeedControllerGroup(driveFrontLeft, driveRearLeft);
      driveRight = new SpeedControllerGroup(driveFrontRight, driveRearRight);

      drive = new DifferentialDrive(driveLeft, driveRight);

      driveTrain = new DriveTrain(driveLeft, driveRight, drive);

      encLeft = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
      encLeft.setDistancePerPulse(ENCODER_DISTANCE_OFFSET*(6.00 * Math.PI / 360)); 

      dtt = 0;
      pos_last = 0;
      last_ttime = Timer.getFPGATimestamp();
      pos = 0; 

      oi = new OI();
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();

    SmartDashboard.putNumber("Position", encLeft.getDistance());
    dtt = Timer.getFPGATimestamp() - last_ttime;  
    last_ttime= Timer.getFPGATimestamp();
   
    pos = encLeft.getDistance();
    SmartDashboard.putNumber("Speed", ((pos-pos_last)/12)/(dtt));
    pos_last = pos;
  }

  @Override
  public void autonomousInit() {
    tenFeetForwardPoints = new ArrayList<>() {{
      add( new Point(20, 2.0E-5, 0.001972, 0.098596, 4.929804));add( new Point(20, 9.9E-5, 0.003944, 0.098596, 0.0));add( new Point(20, 2.76E-4, 0.008874, 0.24649, 7.394707));add( new Point(20, 5.92E-4, 0.015775, 0.345086, 4.929804));add( new Point(20, 0.001085, 0.024649, 0.443682, 4.929804));add( new Point(20, 0.001794, 0.035495, 0.542278, 4.929804));add( new Point(20, 0.002761, 0.048312, 0.640875, 4.929804));add( new Point(20, 0.004023, 0.063101, 0.739471, 4.929804));add( new Point(20, 0.00562, 0.079863, 0.838067, 4.929804));add( new Point(20, 0.007592, 0.098596, 0.936663, 4.929804));add( new Point(20, 0.009978, 0.119301, 1.035259, 4.929804));add( new Point(20, 0.012817, 0.141978, 1.133855, 4.929804));add( new Point(20, 0.01615, 0.166627, 1.232451, 4.929804));add( new Point(20, 0.020015, 0.193248, 1.331047, 4.929804));add( new Point(20, 0.024452, 0.221841, 1.429643, 4.929804));add( new Point(20, 0.0295, 0.252406, 1.528239, 4.929804));add( new Point(20, 0.035199, 0.284943, 1.626835, 4.929804));add( new Point(20, 0.041588, 0.319451, 1.725432, 4.929804));add( new Point(20, 0.048706, 0.355932, 1.824028, 4.929804));add( new Point(20, 0.056594, 0.394384, 1.922624, 4.929804));add( new Point(20, 0.06529, 0.434809, 2.02122, 4.929804));add( new Point(20, 0.074834, 0.477205, 2.119816, 4.929804));add( new Point(20, 0.085266, 0.521573, 2.218412, 4.929804));add( new Point(20, 0.096624, 0.567913, 2.317008, 4.929804));add( new Point(20, 0.108949, 0.616226, 2.415604, 4.929804));add( new Point(20, 0.122279, 0.66651, 2.5142, 4.929804));add( new Point(20, 0.136654, 0.718765, 2.612796, 4.929804));add( new Point(20, 0.152114, 0.772993, 2.711392, 4.929804));add( new Point(20, 0.168698, 0.829193, 2.809988, 4.929804));add( new Point(20, 0.186445, 0.887365, 2.908585, 4.929804));add( new Point(20, 0.205376, 0.946522, 2.957883, 2.464902));add( new Point(20, 0.225489, 1.00568, 2.957883, 0.0));add( new Point(20, 0.246786, 1.064838, 2.957883, -0.0));add( new Point(20, 0.269266, 1.123995, 2.957883, 0.0));add( new Point(20, 0.292929, 1.183153, 2.957883, -0.0));add( new Point(20, 0.317775, 1.242311, 2.957883, -0.0));add( new Point(20, 0.343805, 1.301468, 2.957883, 0.0));add( new Point(20, 0.371017, 1.360626, 2.957883, -0.0));add( new Point(20, 0.399413, 1.419784, 2.957883, -0.0));add( new Point(20, 0.428992, 1.478941, 2.957883, 0.0));add( new Point(20, 0.459754, 1.538099, 2.957883, 0.0));add( new Point(20, 0.491699, 1.597257, 2.957883, -0.0));add( new Point(20, 0.524827, 1.656414, 2.957883, 0.0));add( new Point(20, 0.559138, 1.715572, 2.957883, -0.0));add( new Point(20, 0.594633, 1.77473, 2.957883, -0.0));add( new Point(20, 0.631311, 1.833887, 2.957883, 0.0));add( new Point(20, 0.669172, 1.893045, 2.957883, -0.0));add( new Point(20, 0.708216, 1.952203, 2.957883, 0.0));add( new Point(20, 0.748443, 2.01136, 2.957883, 0.0));add( new Point(20, 0.789853, 2.070518, 2.957883, -0.0));add( new Point(20, 0.832447, 2.129675, 2.957883, -0.0));add( new Point(20, 0.876223, 2.188833, 2.957883, 0.0));add( new Point(20, 0.921164, 2.247005, 2.908585, -2.464902));add( new Point(20, 0.967228, 2.303205, 2.809988, -4.929804));add( new Point(20, 1.014366, 2.356932, 2.686392, -6.179804));add( new Point(20, 1.06256, 2.409688, 2.637796, -2.429804));add( new Point(20, 1.111759, 2.459972, 2.5142, -6.179804));add( new Point(20, 1.161925, 2.508284, 2.415604, -4.929804));add( new Point(20, 1.213018, 2.554625, 2.317008, -4.929804));add( new Point(20, 1.264998, 2.598993, 2.218412, -4.929804));add( new Point(20, 1.317825, 2.641389, 2.119816, -4.929804));add( new Point(20, 1.371462, 2.681814, 2.02122, -4.929804));add( new Point(20, 1.425867, 2.720266, 1.922624, -4.929804));add( new Point(20, 1.481002, 2.756747, 1.824028, -4.929804));add( new Point(20, 1.536827, 2.791255, 1.725432, -4.929804));add( new Point(20, 1.593303, 2.823792, 1.626835, -4.929804));add( new Point(20, 1.65039, 2.854357, 1.528239, -4.929804));add( new Point(20, 1.708049, 2.88295, 1.429643, -4.929804));add( new Point(20, 1.76624, 2.909571, 1.331047, -4.929804));add( new Point(20, 1.824925, 2.93422, 1.232451, -4.929804));add( new Point(20, 1.884063, 2.956897, 1.133855, -4.929804));add( new Point(20, 1.943615, 2.977602, 1.035259, -4.929804));add( new Point(20, 2.003531, 2.995835, 0.911663, -6.179804));add( new Point(20, 2.063793, 3.013096, 0.863067, -2.429804));add( new Point(20, 2.124351, 3.027886, 0.739471, -6.179804));add( new Point(20, 2.185165, 3.040703, 0.640875, -4.929804));add( new Point(20, 2.246196, 3.051549, 0.542278, -4.929804));add( new Point(20, 2.307405, 3.060423, 0.443682, -4.929804));add( new Point(20, 2.368751, 3.067324, 0.345086, -4.929804));add( new Point(20, 2.430196, 3.072254, 0.24649, -4.929804));add( new Point(20, 2.4917, 3.075212, 0.147894, -4.929804));add( new Point(20, 2.55321, 3.075478, 0.013309, -6.729268));add( new Point(20, 2.614671, 3.073053, -0.121277, -6.729268));add( new Point(20, 2.676044, 3.068655, -0.219873, -4.929804));add( new Point(20, 2.73729, 3.062286, -0.318469, -4.929804));add( new Point(20, 2.798369, 3.053944, -0.417065, -4.929804));add( new Point(20, 2.859241, 3.043631, -0.515661, -4.929804));add( new Point(20, 2.919868, 3.031346, -0.614257, -4.929804));add( new Point(20, 2.98021, 3.017089, -0.712853, -4.929804));add( new Point(20, 3.040217, 3.00036, -0.836449, -6.179804));add( new Point(20, 3.09987, 2.982659, -0.885045, -2.429804));add( new Point(20, 3.15912, 2.962486, -1.008641, -6.179804));add( new Point(20, 3.217927, 2.940342, -1.107237, -4.929804));add( new Point(20, 3.276251, 2.916225, -1.205834, -4.929804));add( new Point(20, 3.334054, 2.890136, -1.30443, -4.929804));add( new Point(20, 3.391296, 2.862076, -1.403026, -4.929804));add( new Point(20, 3.447937, 2.832043, -1.501622, -4.929804));add( new Point(20, 3.503937, 2.800039, -1.600218, -4.929804));add( new Point(20, 3.559259, 2.766063, -1.698814, -4.929804));add( new Point(20, 3.613861, 2.730115, -1.79741, -4.929804));add( new Point(20, 3.667705, 2.692194, -1.896006, -4.929804));add( new Point(20, 3.720751, 2.652302, -1.994602, -4.929804));add( new Point(20, 3.77296, 2.610438, -2.093198, -4.929804));add( new Point(20, 3.824292, 2.566603, -2.191794, -4.929804));add( new Point(20, 3.874708, 2.520795, -2.290391, -4.929804));add( new Point(20, 3.924168, 2.473015, -2.388987, -4.929804));add( new Point(20, 3.972633, 2.423263, -2.487583, -4.929804));add( new Point(20, 4.020054, 2.37104, -2.611179, -6.179804));add( new Point(20, 4.066411, 2.317844, -2.659775, -2.429804));add( new Point(20, 4.111654, 2.262177, -2.783371, -6.179804));add( new Point(20, 4.155745, 2.204537, -2.881967, -4.929804));add( new Point(20, 4.198658, 2.145646, -2.944574, -3.130341));add( new Point(20, 4.240388, 2.086488, -2.957883, -0.665439));add( new Point(20, 4.280934, 2.027331, -2.957883, 0.0));add( new Point(20, 4.320298, 1.968173, -2.957883, -0.0));add( new Point(20, 4.358478, 1.909015, -2.957883, 0.0));add( new Point(20, 4.395475, 1.849858, -2.957883, 0.0));add( new Point(20, 4.431289, 1.7907, -2.957883, -0.0));add( new Point(20, 4.46592, 1.731542, -2.957883, 0.0));add( new Point(20, 4.499368, 1.672385, -2.957883, -0.0));add( new Point(20, 4.531632, 1.613227, -2.957883, 0.0));add( new Point(20, 4.562714, 1.554069, -2.957883, -0.0));add( new Point(20, 4.592612, 1.494912, -2.957883, -0.0));add( new Point(20, 4.621327, 1.435754, -2.957883, -0.0));add( new Point(20, 4.648859, 1.376597, -2.957883, 0.0));add( new Point(20, 4.675208, 1.317439, -2.957883, 0.0));add( new Point(20, 4.700373, 1.258281, -2.957883, 0.0));add( new Point(20, 4.724356, 1.199124, -2.957883, 0.0));add( new Point(20, 4.747155, 1.139966, -2.957883, 0.0));add( new Point(20, 4.768771, 1.080808, -2.957883, -0.0));add( new Point(20, 4.789204, 1.021651, -2.957883, 0.0));add( new Point(20, 4.808454, 0.962493, -2.957883, -0.0));add( new Point(20, 4.826521, 0.903335, -2.957883, 0.0));add( new Point(20, 4.843419, 0.844897, -2.921893, 1.799464));add( new Point(20, 4.859182, 0.788165, -2.836606, 4.264366));add( new Point(20, 4.87385, 0.733405, -2.73801, 4.929804));add( new Point(20, 4.887463, 0.680617, -2.639414, 4.929804));add( new Point(20, 4.900059, 0.6298, -2.540818, 4.929804));add( new Point(20, 4.911678, 0.580956, -2.442222, 4.929804));add( new Point(20, 4.922359, 0.534084, -2.343626, 4.929804));add( new Point(20, 4.932143, 0.489183, -2.24503, 4.929804));add( new Point(20, 4.941068, 0.446254, -2.146433, 4.929804));add( new Point(20, 4.949174, 0.405298, -2.047837, 4.929804));add( new Point(20, 4.9565, 0.366313, -1.949241, 4.929804));add( new Point(20, 4.963086, 0.3293, -1.850645, 4.929804));add( new Point(20, 4.968972, 0.294259, -1.752049, 4.929804));add( new Point(20, 4.974195, 0.26119, -1.653453, 4.929804));add( new Point(20, 4.978797, 0.230093, -1.554857, 4.929804));add( new Point(20, 4.982817, 0.200967, -1.456261, 4.929804));add( new Point(20, 4.986293, 0.173814, -1.357665, 4.929804));add( new Point(20, 4.989265, 0.148633, -1.259069, 4.929804));add( new Point(20, 4.991774, 0.125423, -1.160473, 4.929804));add( new Point(20, 4.993858, 0.104186, -1.061876, 4.929804));add( new Point(20, 4.995556, 0.08492, -0.96328, 4.929804));add( new Point(20, 4.996909, 0.067626, -0.864684, 4.929804));add( new Point(20, 4.997955, 0.052305, -0.766088, 4.929804));add( new Point(20, 4.998734, 0.038955, -0.667492, 4.929804));add( new Point(20, 4.999285, 0.027577, -0.568896, 4.929804));add( new Point(20, 4.999649, 0.018171, -0.4703, 4.929804));add( new Point(20, 4.999863, 0.010737, -0.371704, 4.929804));add( new Point(20, 4.999969, 0.005275, -0.273108, 4.929804));add( new Point(20, 5.000005, 0.001784, -0.174512, 4.929804));add( new Point(20, 5.00001, 2.66E-4, -0.075916, 4.929804));add( new Point(20, 5.00001, 0.0, -0.013309, 3.130341));
      }};      

    error_last = Integer.MIN_VALUE;
    dt = 0;
    last_time = Timer.getFPGATimestamp();
    currentDataPointCount = 0;
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();

    double speed = 0;
    if(this.currentDataPointCount < tenFeetForwardPoints.size())
       setpoint = tenFeetForwardPoints.get(this.currentDataPointCount);
   
    
    //feedforward
    speed+=KV*setpoint.getVelocity();
    speed+=KA*setpoint.getAcceleration();
    
    //feedback
    dt = Timer.getFPGATimestamp()-last_time;
    last_time = Timer.getFPGATimestamp();

    double error = setpoint.getPosition() - encLeft.getDistance()/12;
    double error_deriv = (error-error_last)/dt - setpoint.getVelocity();

    if(error_last != Integer.MIN_VALUE) {
      speed+=KD*error_deriv;
      speed+=KP*error;
    }

    //set speed
    driveLeft.set(speed);
    driveRight.set(speed);
    currentDataPointCount++;
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}