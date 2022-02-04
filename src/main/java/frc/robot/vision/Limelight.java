package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    private boolean hasValidTarget = false;
    private double driveCommand = 0.0;
    private double steerCommand = 0.0;

    public Limelight() {
        this.hasValidTarget = false;
    }

    public void updateTracking(Boolean ball) {

          // These numbers must be tuned for your Robot!  Be careful!
          final double STEER_K = 0.03;                    // how hard to turn toward the target
          final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
          final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
          //final double DESIRED_TARGET_AREA = 23.0;        // Area of the target when the robot reaches the wall
          final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast
  
      // for horizontal alignment limelight pipeline 1 is the ball, pipeline two is the hatch 
          NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
          double tv =  NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
          double tx =NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
          //double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); unused
          double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
          
          if (!ball) {
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(2);
            tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
            tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
            //ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); unused
            ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);  
          }
      

          if ( tv < 1.0)
          {
            hasValidTarget = false;
            driveCommand = 0.0;
            steerCommand = 0.0;
            return;
          }
  
          hasValidTarget = true;
  
          // Start with proportional steering
          double steer_cmd = tx * STEER_K;
          steerCommand = steer_cmd;
  
          // try to drive forward until the target area reaches our desired area
      // This should be replaced by LIDAR call
          double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;
  
          // don't let the robot drive too fast into the goal
          if (drive_cmd > MAX_DRIVE)
          {
            drive_cmd = MAX_DRIVE;
          }
          driveCommand = drive_cmd;
    }



    public boolean hasValidTarget() {
      return hasValidTarget;
    }

    public double getSteer() {
      return steerCommand;
    }

    public double getThrottle() {
      return driveCommand;
    }

}