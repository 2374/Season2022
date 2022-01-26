package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;

public class ClimberSubsystem {

    public WPI_TalonFX leftFrontMotor;
    public WPI_TalonFX leftBackMotor;

    public MotorControllerGroup mainGroup;
    
    private ClimberSubsystem instance;
    // public TalonFX rightFrontMotor;
    // public TalonFX rightBackMotor;

    

    // public Solenoid leftSolenoid;
    // public Solenoid rightSolenoid;
    
    public ClimberSubsystem() {
        leftFrontMotor = new WPI_TalonFX(Constants.FRONT_LEFT_CLIMBER_PORT);
        leftBackMotor = new WPI_TalonFX(Constants.BACK_LEFT_CLIMBER_PORT);

        mainGroup = new MotorControllerGroup(leftFrontMotor, leftBackMotor);
        // rightFrontMotor = new TalonFX(Constants.FRONT_RIGHT_CLIMBER_PORT);
        // rightBackMotor = new TalonFX(Constants.BACK_RIGHT_CLIMBER_PORT);

        // leftSolenoid = new Solenoid(Constants.PCM1, 0);
        // rightSolenoid = new Solenoid(Constants.PCM2, 0);


    }

    public ClimberSubsystem getShooterInstance() {
        if (instance == null) {
            instance = new ClimberSubsystem();
        }
        
        return instance;
    }
    public void retract() {
        mainGroup.set(-0.1);
    }

    public void stop() {
        mainGroup.set(0.0);
    }
}
