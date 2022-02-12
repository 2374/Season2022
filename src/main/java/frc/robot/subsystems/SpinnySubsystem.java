package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.vision.Limelight;

// A GOD DOES NOT FEAR DEATH

public class SpinnySubsystem {
    private WPI_TalonFX spinner;
    private Limelight limelight;

    public SpinnySubsystem() {
        spinner = new WPI_TalonFX(Constants.SPINNY_MOTOR);
    }

    public void rotateLeft() {
        spinner.set(-0.1);
    }

    public void rotateRight() {
        spinner.set(0.1);
    }

    public void spinToTarget() {
        while (limelight.hasValidTarget()) {
            if (Math.abs(limelight.getHorizontalTargetAngle()) > Constants.LIMELIGHT_TOLERANCE) {
                if (limelight.getTurnValue() > 0) {
                    spinner.set(0.05);
                } else if (limelight.getTurnValue() < 0) {
                    spinner.set(-0.05);
                } else {
                    spinner.set(0.0);
                }
            }
        }
    }
}
