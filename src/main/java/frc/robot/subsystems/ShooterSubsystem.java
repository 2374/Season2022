package frc.robot.subsystems;
import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.vision.Limelight;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class ShooterSubsystem extends SubsystemBase {
    private WPI_TalonFX leftMotor;
    private WPI_TalonFX rightMotor;
    private Limelight limelight;
    private WPI_TalonFX topMotor;
    private WPI_TalonFX bottomMotor;

    private MotorControllerGroup indexGroup;
    private MotorControllerGroup mainGroup;

    private ShooterSubsystem instance;
    private double power;

    public ShooterSubsystem() {
        limelight = new Limelight();

        leftMotor = new WPI_TalonFX(Constants.LEFT_SHOOTER, Constants.CANIVORE_CAN_BUS_NAME);
        rightMotor = new WPI_TalonFX(Constants.RIGHT_SHOOTER, Constants.CANIVORE_CAN_BUS_NAME);
        topMotor = new WPI_TalonFX(Constants.TOP_INDEX_MOTOR, Constants.CANIVORE_CAN_BUS_NAME);
        bottomMotor = new WPI_TalonFX(Constants.BOTTOM_INDEX_MOTOR, Constants.CANIVORE_CAN_BUS_NAME);
        
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

    public void shoot() {
        mainGroup.set(-0.85);
        power = -.2;
        // if (limelight.isLockedOn()) {
            double d = limelight.calculateDistance();
            System.out.println("TARGET DIST=" + d);
            power = -19.6 * Math.pow(d, 2) / (d * 1.73205080757 - 2.4384);
            System.out.println("power=" + power);
        // }
        // mainGroup.set(power);
    }

    public void stop(){
        indexGroup.set(0);
        mainGroup.set(0);
    }
    
    public double getPower(){
        return power;
    }

    public void indexerOn(){
        System.out.println("index on");
        indexGroup.set(0.2);
    }

    public void indexerOff(){
        System.out.println("index off");
        indexGroup.set(0);
    }
}