package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Does whatever a spider can

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
    
    public ClimberSubsystem() {
        leftFixed = new WPI_TalonFX(Constants.LEFT_FIXED_MOTOR);
        rightFixed = new WPI_TalonFX(Constants.RIGHT_FIXED_MOTOR);
        leftArticulating = new WPI_TalonFX(Constants.LEFT_ARTICULATING_MOTOR);
        rightArticulating = new WPI_TalonFX(Constants.RIGHT_ARTICULATING_MOTOR);

        leftFixed.setNeutralMode(NeutralMode.Brake);
        rightFixed.setNeutralMode(NeutralMode.Brake);
        leftArticulating.setNeutralMode(NeutralMode.Brake);
        rightArticulating.setNeutralMode(NeutralMode.Brake);

        leftPiston = new DoubleSolenoid(Constants.PCM_INTAKE, Constants.PCM_TYPE, Constants.FORWARD_CHANNEL_LEFT_ARM, Constants.REVERSE_CHANNEL_LEFT_ARM);
        rightPiston = new DoubleSolenoid(Constants.PCM_INTAKE, Constants.PCM_TYPE, Constants.FORWARD_CHANNEL_RIGHT_ARM, Constants.REVERSE_CHANNEL_RIGHT_ARM);
        
        leftPiston.set(DoubleSolenoid.Value.kReverse);
        rightPiston.set(DoubleSolenoid.Value.kReverse);

        fixedGroup = new MotorControllerGroup(leftFixed, rightFixed);
        articulatingGroup = new MotorControllerGroup(leftArticulating, rightArticulating);


    }

    public ClimberSubsystem getClimberInstance() {
        if (instance == null) {
            instance = new ClimberSubsystem();
        }
        
        return instance;
    }

    public void extendFixedArms(){
        if (leftFixed.getSelectedSensorPosition() <= Constants.FIXED_ARM_UPPER_LIMIT){
            fixedGroup.set(0.4);
        } else {
            fixedGroup.set(0);
        }
    }

    public void retractFixedArms(){
        if (leftFixed.getSelectedSensorPosition() >= Constants.FIXED_ARM_LOWER_LIMIT) {
            fixedGroup.set(-1.0);
        } else {
            fixedGroup.set(0);
        }
    }

    /* public void stretchFixedArms() {
        while (leftFixed.getSelectedSensorPosition() <= Constants.FIXED_ARM_UPPER_LIMIT) {
            fixedGroup.set(1.0);
        }

        fixedGroup.set(0.0);
    }

    public void withdrawFixedArms() {
        while(leftFixed.getSelectedSensorPosition() >= Constants.FIXED_ARM_LOWER_LIMIT) {
            fixedGroup.set(-1.0);
        }

        fixedGroup.set(0.0);
    } */

    public void extendArticulatingArms(){
        if (leftArticulating.getSelectedSensorPosition() <= Constants.ARTICULATING_ARM_UPPER_LIMIT) {
            articulatingGroup.set(0.1);
        } else {
            articulatingGroup.set(0);
        }
    }

    public void retractArticulatingArms(){
        if (leftArticulating.getSelectedSensorPosition() >= Constants.ARTICULATING_ARM_LOWER_LIMIT) {
            articulatingGroup.set(-0.1);
        } else {
            articulatingGroup.set(0);
        }
    }

    /* public void stretchArticulatingArms() {
        while (leftArticulating.getSelectedSensorPosition() <= Constants.ARTICULATING_ARM_UPPER_LIMIT) {
            articulatingGroup.set(1.0);
        }

        articulatingGroup.set(0.0);
    }
    
    public void withdrawArticulatingArms() {
        while (leftArticulating.getSelectedSensorPosition()>= Constants.ARTICULATING_ARM_LOWER_LIMIT){
            articulatingGroup.set(-1.0);
        }
        articulatingGroup.set(0.0);
    } */
    
    
    // moves articulating arms forward
    // public void angleArticulatingArms() {
    //     leftPiston.set(DoubleSolenoid.Value.kForward);
    //     rightPiston.set(DoubleSolenoid.Value.kForward);
    // }

    // // moves articulating arms in reverse
    // public void reverseArticulatingArms() {
    //     leftPiston.set(DoubleSolenoid.Value.kReverse);
    //     rightPiston.set(DoubleSolenoid.Value.kReverse);
    // }

    // public void stopPistons() {
    //     leftPiston.set(DoubleSolenoid.Value.kOff);
    //     rightPiston.set(DoubleSolenoid.Value.kOff);
    // } 
    
    public void stopFixedArms(){
        fixedGroup.set(0);
    }

    public void stopArticulatingArms() {
        articulatingGroup.set(0);
    }

}
