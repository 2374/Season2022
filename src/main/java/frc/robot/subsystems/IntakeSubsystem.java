package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    private WPI_TalonFX motor;
    private Compressor compressor;
    private DoubleSolenoid leftPiston;
    private DoubleSolenoid rightPiston;

    private IntakeSubsystem instance;

    public IntakeSubsystem(){
        motor = new WPI_TalonFX(Constants.INTAKE_MOTOR, Constants.CANIVORE_CAN_BUS_NAME);
        compressor = new Compressor(Constants.PCM_INTAKE, Constants.PCM_TYPE);
        leftPiston = new DoubleSolenoid(Constants.PCM_INTAKE, Constants.PCM_TYPE, Constants.FORWARD_CHANNEL_LEFT, Constants.REVERSE_CHANNEL_LEFT);
        rightPiston = new DoubleSolenoid(Constants.PCM_INTAKE, Constants.PCM_TYPE, Constants.FORWARD_CHANNEL_RIGHT, Constants.REVERSE_CHANNEL_RIGHT);
        compressor.enableDigital();
    }
    
    public IntakeSubsystem getIntakeInstance() {
        if (instance == null) {
            instance = new IntakeSubsystem();
        }
        
        return instance;
    }

    public void enableIntake() {
        motor.set(0.4);
    }

    public void stop() {
        motor.set(0);
    }

    public void extendPistons() {
        leftPiston.set(DoubleSolenoid.Value.kForward);
        rightPiston.set(DoubleSolenoid.Value.kForward);
    }

    public void retractPistons() {
        leftPiston.set(DoubleSolenoid.Value.kReverse);
        rightPiston.set(DoubleSolenoid.Value.kReverse);
    }

}