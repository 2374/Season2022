package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.AS5600EncoderPwm;
import frc.robot.vision.Limelight;

public class TurretSubsystem extends SubsystemBase {
    private WPI_TalonFX spinnerMotor;
    private Limelight limelight = new Limelight();
    private TurretSubsystem instance;
    // private double currentTickCount = 0;
    // private final AS5600EncoderPwm encoder = new AS5600EncoderPwm(spinnerMotor.getSensorCollection());

    public TurretSubsystem() {
        spinnerMotor = new WPI_TalonFX(Constants.TURRET_MOTOR); // must be on the RIO
        // currentTickCount = 0;
       
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

    // public double getCurrentTickCount() {
    //     return currentTickCount;
    // }

    /**
     * Rotate the turret Left
     */
    public void rotateLeft() {
        // System.out.println("Left="+spinnerMotor.getSensorCollection().getIntegratedSensorPosition());
        // if (currentTickCount < 1000 )
            spinnerMotor.set(-Constants.TURRET_POWER);
        // System.out.println("Rotating Left");
    }

    /**
     * Rotate the turret Right
     */
    public void rotateRight() {
        // System.out.println("Right="+spinnerMotor.getSelectedSensorPosition(0));
        // if (currentTickCount > -1000) 
            spinnerMotor.set(Constants.TURRET_POWER);
        // System.out.println("Rotating Right");
    }

    /**
     * Stop the current rotation of the turret
     */
    public void rotateStop() {
        // currentTickCount = spinnerMotor.getSelectedSensorPosition(0);
        //spinnerMotor.
        stop();
        // System.out.println("Stopping="+currentTickCount);
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
        // long sTime1 = System.currentTimeMillis();
        // System.out.println("Target Valid="+limelight.hasValidTarget());
        while (limelight.hasValidTarget()){//&& sTime1 + 1000 > System.currentTimeMillis()) {
            // System.out.println("TURN VALUE="+limelight.getTurnValue());
            if (limelight.getTurnValue() > Constants.LIMELIGHT_TOLERANCE) {
                spinnerMotor.set(0.15);
            } else if (limelight.getTurnValue() < -Constants.LIMELIGHT_TOLERANCE) {
                spinnerMotor.set(-0.15);
            } else {
                spinnerMotor.set(0.0);
                break;
                // retrun;
            }
            limelight.updateTracking();
            
        }
        // System.out.println("time");
        // Limelight.setLedMode(LightMode.eOff);
    }

    /**
     * stop the turrent from moving
     */
    public void stop() {
        spinnerMotor.set(0.0);
    }
}
