package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// talons are FX

public class ShooterSubsystem extends SubsystemBase {
    private WPI_TalonSRX leftMotor;
    private WPI_TalonSRX rightMotor;


    private MotorControllerGroup mainGroup;

    private ShooterSubsystem instance;

    public ShooterSubsystem() {
        leftMotor = new WPI_TalonSRX(Constants.LEFT_SHOOTER);
        rightMotor = new WPI_TalonSRX(Constants.RIGHT_SHOOTER);
        

        rightMotor.setInverted(true);

        leftMotor.follow(rightMotor);

        mainGroup = new MotorControllerGroup(rightMotor, leftMotor);
    }

    public ShooterSubsystem getShooterInstance() {
        if (instance == null) {
            instance = new ShooterSubsystem();
        }
        
        return instance;
    }

    //control classes

    public void shoot(){
        mainGroup.set(1.0);
    }

    public void stop(){
        mainGroup.set(0);
    }


}