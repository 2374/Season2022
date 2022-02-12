package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.vision.Limelight;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// The cake is a lie
// SPACE! SPACE? SPACE!
// This is the part where he kills you. *Chapter 9: THE PART WHERE HE KILLS YOU*

public class ShooterSubsystem extends SubsystemBase {
    private WPI_TalonFX leftMotor;
    private WPI_TalonFX rightMotor;
    private Limelight limelight;
    private WPI_TalonFX topMotor;
    private WPI_TalonFX bottomMotor;

    private MotorControllerGroup indexGroup;

    // BLOOD FOR THE BLOOD GOD!

    private MotorControllerGroup mainGroup;

    private ShooterSubsystem instance;
    private double power;

    public ShooterSubsystem() {
        limelight = new Limelight();

        leftMotor = new WPI_TalonFX(Constants.LEFT_SHOOTER);
        rightMotor = new WPI_TalonFX(Constants.RIGHT_SHOOTER);
        topMotor = new WPI_TalonFX(Constants.TOP_INDEX_MOTOR);
        bottomMotor = new WPI_TalonFX(Constants.BOTTOM_INDEX_MOTOR);
        
        rightMotor.setInverted(true);
        leftMotor.follow(rightMotor);   

        indexGroup = new MotorControllerGroup(topMotor, bottomMotor);
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
        mainGroup.set(-0.95);
        indexGroup.set(0.2);
        double d = limelight.calculateDistance();
        power = -19.6*Math.pow(d, 2)/(d*1.73205080757 - 2.4384);
        // mainGroup.set(power);
    }

    public void stop(){
        indexGroup.set(0);
        mainGroup.set(0);
    }
    
    public double getPower(){
        return power;
    }

    public void on(){
        indexGroup.set(0.2);
    }

    public void off(){
        indexGroup.set(0);
    }
}