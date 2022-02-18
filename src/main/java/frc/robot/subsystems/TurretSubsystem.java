package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.Limelight.LightMode;

public class TurretSubsystem {
    private WPI_TalonFX spinner;
    private Limelight limelight = new Limelight();

    public TurretSubsystem() {
        spinner = new WPI_TalonFX(Constants.TURRET_MOTOR); // must be on the RIO
    }

    public void rotateLeft() {
        spinner.set(-Constants.TURRET_POWER);
    }

    public void rotateRight() {
        spinner.set(Constants.TURRET_POWER);
    }

    public void rotateStop() {
        stop();
    }
    /*
    find rotation amount to center
    rotate
    check if within tolerance
    if within -> shoot
    else go to top
    */
    public void spinToTarget() {
        limelight.updateTracking();
        Limelight.setLedMode(LightMode.eOn);
        // while (limelight.hasValidTarget()) {
        //     if (Math.abs(limelight.getHorizontalTargetAngle()) > Constants.LIMELIGHT_TOLERANCE) { // make tolerance dynamic
        //         if (limelight.getTurnValue() > 0) {
        //             spinner.set(0.05);
        //         } else if (limelight.getTurnValue() < 0) {
        //             spinner.set(-0.05);
        //         } else {
        //             spinner.set(0.0);
        //         }
        //     }
        // }
        
        System.out.println("angle: " + limelight.getHorizontalTargetAngle());
        Limelight.setLedMode(LightMode.eOff);
    }

    public void stop() {
        spinner.set(0.0);
    }
}
