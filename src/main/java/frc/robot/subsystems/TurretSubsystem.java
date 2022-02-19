package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.Limelight.LightMode;

public class TurretSubsystem extends SubsystemBase {
    private WPI_TalonFX spinner;
    private Limelight limelight = new Limelight();
    private TurretSubsystem instance;

    public TurretSubsystem() {
        spinner = new WPI_TalonFX(Constants.TURRET_MOTOR); // must be on the RIO
    }

    public TurretSubsystem getTurretInstance() {
        if (instance == null) {
            instance = new TurretSubsystem();
        }
        
        return instance;
    }

    public void rotateLeft() {
        spinner.set(-Constants.TURRET_POWER);
        System.out.println("Rotating Left");
    }

    public void rotateRight() {
        spinner.set(Constants.TURRET_POWER);
        System.out.println("Rotating Right");
    }

    public void rotateStop() {
        stop();
        System.out.println("Stopping");
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
