package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Scarlet Witch: You intook everything from me!
// Thanos: I don't even know who you are.

public class IntakeSubsystem extends SubsystemBase {
    private WPI_TalonFX motor;
    private Compressor compressor;
    private DoubleSolenoid leftPiston;
    private DoubleSolenoid rightPiston;

    private IntakeSubsystem instance;

    public IntakeSubsystem(){
        motor = new WPI_TalonFX(Constants.INTAKE_MOTOR);
        compressor = new Compressor(Constants.PCM_INTAKE);
        leftPiston = new DoubleSolenoid(30, PneumaticsModuleType.CTREPCM, Constants.FORWARD_CHANNEL, Constants.REVERSE_CHANNEL);
        rightPiston = new DoubleSolenoid(30, PneumaticsModuleType.CTREPCM, Constants.FORWARD_CHANNEL, Constants.REVERSE_CHANNEL);
    }
    
    public IntakeSubsystem getIntakeInstance() {
        if (instance == null) {
            instance = new IntakeSubsystem();
        }
        
        return instance;
    }

    public void start() {
        motor.set(0.5);
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