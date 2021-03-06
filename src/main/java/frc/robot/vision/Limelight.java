package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;

public class Limelight {

    private boolean hasValidTarget = false;
    private double throttleValue = 0.0;
    private double turnValue = 0.0;
    private double verticalTargetAngle = 0.0;
    private double horizontalTargetAngle = 0.0;

    static final double LIMELIGHT_HEIGHT_CM = 85.1; // h1
    static final double TARGET_HEIGHT_CM = 261.6; // h2
    static final double LIMELIGHT_MOUNTING_ANGLE = 35.5; // a1
     // These numbers must be tuned for your Robot!  Be careful!
    static final double STEER_K = 0.03;                    // how hard to turn toward the target
    static final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
    static final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
     //final double DESIRED_TARGET_AREA = 23.0;        // Area of the target when the robot reaches the wall
    static final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast
    
    private static NetworkTableInstance table = null;

	/**
	 * Light modes for Limelight.
	 * 
	 * @author Dan Waxman
	 */
	public static enum LightMode {
		eOn, eOff, eBlink
	}

    public void updateTracking() {
        
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0); // change the setNumber to the appropriate target
        final double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        final double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        // final double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
        final double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);

        if (tv < 1.0) {
            hasValidTarget = false;
            throttleValue = 0.0;
            turnValue = 0.0;
            // verticalTargetAngle = 0.0;   don't set the angle of the target to zero????
            return;
        }

        hasValidTarget = true;

        // Start with proportional steering
        final double steer_cmd = tx * STEER_K;
        turnValue = steer_cmd;
        verticalTargetAngle = ty;
        horizontalTargetAngle = tx;

    }

    public double calculateDistance() {
		updateTracking();
        double distance = 0.0;
		// System.out.println(this.getVerticalTargetAngle());
        double differenceDistance = TARGET_HEIGHT_CM - LIMELIGHT_HEIGHT_CM;
		double shooterAngle = LIMELIGHT_MOUNTING_ANGLE + this.getVerticalTargetAngle();
		// System.out.println("Shooter Angle="+shooterAngle);
        double denom = Math.tan(Math.toRadians(shooterAngle));
        distance = differenceDistance / denom;
        //  System.out.println(denom);
		//  System.out.println(differenceDistance);
        return distance;
    }

	public double getDifferenceDistanceCM() { return TARGET_HEIGHT_CM - LIMELIGHT_HEIGHT_CM; }

    public double getVerticalTargetAngle() { return this.verticalTargetAngle; }

    public double getHorizontalTargetAngle() { return this.horizontalTargetAngle; }

    public boolean hasValidTarget() { return hasValidTarget; }

    public double getThrottleValue() { return throttleValue; }
    
    public double getTurnValue() { return turnValue; }

    public boolean isLockedOn() {
		// System.out.println("Locked ON Angle="+getHorizontalTargetAngle());
        return (Math.abs(getHorizontalTargetAngle()) < Constants.LIMELIGHT_TOLERANCE*30);
    }

    /**
	 * Sets LED mode of Limelight.
	 * 
	 * @param mode
	 *            Light mode for Limelight.
	 */
	public static void setLedMode(LightMode mode) {
		getValue("ledMode").setNumber(mode.ordinal());
	}

    /**
	 * Camera modes for Limelight.
	 * 
	 * @author Dan Waxman
	 */
	public static enum CameraMode {
		eVision, eDriver
	}

	/**
	 * Gets whether a target is detected by the Limelight.
	 * 
	 * @return true if a target is detected, false otherwise.
	 */
	public static boolean isTarget() {
		return getValue("tv").getDouble(0) == 1;
	}

	/**
	 * Horizontal offset from crosshair to target (-27 degrees to 27 degrees).
	 * 
	 * @return tx as reported by the Limelight.
	 */
	public static double getTx() {
		return getValue("tx").getDouble(0.00);
	}

	/**
	 * Vertical offset from crosshair to target (-20.5 degrees to 20.5 degrees).
	 * 
	 * @return ty as reported by the Limelight.
	 */
	public static double getTy() {
		return getValue("ty").getDouble(0.00);
	}

	/**
	 * Area that the detected target takes up in total camera FOV (0% to 100%).
	 * 
	 * @return Area of target.
	 */
	public static double getTa() {
		return getValue("ta").getDouble(0.00);
	}

	/**
	 * Gets target skew or rotation (-90 degrees to 0 degrees).
	 * 
	 * @return Target skew.
	 */
	public static double getTs() {
		return getValue("ts").getDouble(0.00);
	}

	/**
	 * Gets target latency (ms).
	 * 
	 * @return Target latency.
	 */
	public static double getTl() {
		return getValue("tl").getDouble(0.00);
	}

	/**
	 * Sets camera mode for Limelight.
	 * 
	 * @param mode
	 *            Camera mode for Limelight.
	 */
	public static void setCameraMode(CameraMode mode) {
		getValue("camMode").setNumber(mode.ordinal());
	}

	/**
	 * Sets pipeline number (0-9 value).
	 * 
	 * @param number
	 *            Pipeline number (0-9).
	 */
	public static void setPipeline(int number) {
		getValue("pipeline").setNumber(number);
	}

    /**
	 * Helper method to get an entry from the Limelight NetworkTable.
	 * 
	 * @param key
	 *            Key for entry.
	 * @return NetworkTableEntry of given entry.
	 */
	private static NetworkTableEntry getValue(String key) {
		if (table == null) {
			table = NetworkTableInstance.getDefault();
		}

		return table.getTable("limelight").getEntry(key);
	}


}