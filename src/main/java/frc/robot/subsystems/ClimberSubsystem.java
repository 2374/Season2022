package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

    public WPI_TalonFX leftFixed;
    public WPI_TalonFX rightFixed;
    public WPI_TalonFX leftArticulating;
    public WPI_TalonFX rightArticulating;

    public Compressor compressor;
    public DoubleSolenoid leftPiston;
    public DoubleSolenoid rightPiston;

    public MotorControllerGroup fixedGroup;
    public MotorControllerGroup articulatingGroup;
    
    private ClimberSubsystem instance;
    // public TalonFX rightFrontMotor;
    // public TalonFX rightBackMotor;

    

    // public Solenoid leftSolenoid;
    // public Solenoid rightSolenoid;
    
    public ClimberSubsystem() {
        leftFixed = new WPI_TalonFX(Constants.LEFT_FIXED_MOTOR);
        rightFixed = new WPI_TalonFX(Constants.RIGHT_FIXED_MOTOR);
        leftArticulating = new WPI_TalonFX(Constants.LEFT_ARTICULATING_MOTOR);
        rightArticulating = new WPI_TalonFX(Constants.RIGHT_ARTICULATING_MOTOR);

        leftFixed.setNeutralMode(NeutralMode.Brake);
        rightFixed.setNeutralMode(NeutralMode.Brake);
        leftArticulating.setNeutralMode(NeutralMode.Brake);
        rightArticulating.setNeutralMode(NeutralMode.Brake);

        compressor = new Compressor(Constants.PCM);
        leftPiston = new DoubleSolenoid(30, ModuleType.kCTRE, Constants.FORWARD_CHANNEL, reverseChannel);
        rightPiston = new DoubleSolenoid(30, ModuleType.kCTRE, Constants.FORWARD_CHANNEL, reverseChannel);
d
        fixedGroup = new MotorControllerGroup(leftFixed, rightFixed);
        articulatingGroup = new MotorControllerGroup(leftArticulating, rightArticulating);
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

    public void extendFixedArms(){
        if (leftFixed.getSelectedSensorPosition() <= Constants.FIXED_ARM_UPPER_LIMIT){
            fixedGroup.set(0.1);
        } else {
            fixedGroup.set(0);
        }
    }

    public void retractFixedArms(){
        if (leftFixed.getSelectedSensorPosition() >= Constants.FIXED_ARM_LOWER_LIMIT) {
            fixedGroup.set(-0.1);
        } else {
            fixedGroup.set(0);
        }
    }
}
