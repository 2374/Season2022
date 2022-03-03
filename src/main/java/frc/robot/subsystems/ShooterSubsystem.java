package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.vision.Limelight;
import frc.robot.vision.Limelight.LightMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class ShooterSubsystem extends SubsystemBase {
    private WPI_TalonFX leftMotor;
    private WPI_TalonFX rightMotor;
    private Limelight limelight;
    
    private MotorControllerGroup mainGroup;

    private ShooterSubsystem instance;
    private double power;

    public ShooterSubsystem() {
        limelight = new Limelight();

        leftMotor = new WPI_TalonFX(Constants.LEFT_SHOOTER, Constants.CANIVORE_CAN_BUS_NAME);
        rightMotor = new WPI_TalonFX(Constants.RIGHT_SHOOTER, Constants.CANIVORE_CAN_BUS_NAME);
        
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

    /**
     * Shoot the ball using the current values from the limeligth to track the
     * target distance.
     * 
     * @param ignoreTracking just shoot the ball at 70% power
     */
    public void shootBallAtCurrentAcquiredTarget(Boolean ignoreTracking) {
        power = 0.0;
        // double velocity;
        System.out.println("locked?="+limelight.isLockedOn());
        limelight.updateTracking();
        if (!ignoreTracking && limelight.isLockedOn()) {
            // double d = limelight.calculateDistance()/100;
            // System.out.println("TARGET DIST=" + d);
            // velocity = Math.sqrt(19.6 * Math.pow(d, 2) / (d * 1.73205080757 - (limelight.getDifferenceDistanceCM()/100)));
            // System.out.println("Velocity=" + velocity);
            // double RPM = velocity/0.05/0.10472;
            // System.out.println("RPM=" + RPM);
            // power = RPM/6380;
            // System.out.println("power=" + power);
            double distanceCm = limelight.calculateDistance();
            System.out.println("Distance="+distanceCm);
            if (distanceCm > 0.0) {
                if (distanceCm <= 250) {
                    power = 0.6;
                } else if (distanceCm > 250 && distanceCm < 280 ) {
                    power = .7;
                } else if (distanceCm >= 280 && distanceCm < 310 ) {
                    power = .80;
                } else if (distanceCm >= 310 && distanceCm < 370 ) {
                    power = .85;
                } else if (distanceCm >= 370 && distanceCm < 430 ) {
                    power = .88;
                } else if (distanceCm >= 430 && distanceCm < 460 ) {
                    power = 1.0;
                } else if (distanceCm >= 460 && distanceCm < 500 ) {
                    power = 1.0;
                } else if (distanceCm >= 500 && distanceCm < 550 ) {
                    power = 1.0;
                } else {
                    power = 1.0;
                }
            }
        } else {
            // default shot is assuming about 8'
            power = .7;
        }
        // System.out.println("POWER="+power);
        mainGroup.set(-1 * power); // motors run in the negative direction so return a negative number
    
    }

    public void dribble(){
        mainGroup.set(-.4);
    }

    public void stop(){
        mainGroup.set(0);
        Limelight.setLedMode(LightMode.eOff); // can't turn off the limelight until shooting is done
    }
    
    public double getPower(){
        return power;
    }

}