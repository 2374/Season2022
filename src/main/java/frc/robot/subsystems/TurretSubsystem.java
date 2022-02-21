package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.Limelight.LightMode;

public class TurretSubsystem extends SubsystemBase {
    private WPI_TalonFX spinnerMotor;
    private Limelight limelight = new Limelight();
    private TurretSubsystem instance;

    public TurretSubsystem() {
        spinnerMotor = new WPI_TalonFX(Constants.TURRET_MOTOR); // must be on the RIO
    }

    
    /** 
     * Provide the current instance of the turret subsystem.
     * @return TurretSubsystem
     */
    public TurretSubsystem getTurretInstance() {
        if (instance == null) {
            instance = new TurretSubsystem();
        }
        
        return instance;
    }

    /**
     * Rotate the turret Left
     */
    public void rotateLeft() {
        spinnerMotor.set(-Constants.TURRET_POWER);
        System.out.println("Rotating Left");
    }

    /**
     * Rotate the turret Right
     */
    public void rotateRight() {
        spinnerMotor.set(Constants.TURRET_POWER);
        System.out.println("Rotating Right");
    }

    /**
     * Stop the current rotation of the turret
     */
    public void rotateStop() {
        stop();
        System.out.println("Stopping");
    }
    /**
     * Find the rotation amount to center the turret
     * rotate towards target until method determines
     * that the current alignment is with in tolerance
     * then return to caller allowing them to know
     * that we are aligned to the target
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
        // Limelight.setLedMode(LightMode.eOff);
    }

    /**
     * stop the turrent from moving
     */
    public void stop() {
        spinnerMotor.set(0.0);
    }
}
