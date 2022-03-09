package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.vision.Limelight;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class ShooterSubsystem extends SubsystemBase {
    private WPI_TalonFX leftMotor;
    private WPI_TalonFX rightMotor;
    private Limelight limelight;
    private double motorPowerAdjustmentValue = 1.0;
    
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
        // System.out.println("locked?="+limelight.isLockedOn());
        limelight.updateTracking();
        if (!ignoreTracking && limelight.isLockedOn()) {
            // double d = limelight.calculateDistance()/100;
            // System.out.println("TARGET DIST=" + d);
            // velocity = Math.sqrt(19.6 * Math.pow(d, 2) / (d * 1.73205080757 - (limelight.getDifferenceDistanceCM()/100)));
            // System.out.println("Velocity=" + velocity);
            // double RPM = velocity/0.05/0.10472;
            // System.out.println("RPM=" + RPM);
            // power = 2.1*RPM/6380;
            // System.out.println("power=" + power);
            double distanceCm = limelight.calculateDistance();
            distanceCm = 455;
            // System.out.println("Distance="+distanceCm);
            if (distanceCm > 0.0) {
                if (distanceCm <= 250) {
                    power = 0.8;
                } else if (distanceCm > 250 && distanceCm < 280 ) {
                    power = .85;
                } else if (distanceCm >= 280 && distanceCm < 310 ) {
                    power = .9;
                } else if (distanceCm >= 320 && distanceCm <= 350) {
                    power = .775;
                } else if (distanceCm > 350 && distanceCm <= 380) {
                    power = .805;
                } else if (distanceCm > 380 && distanceCm <= 410) {
                    power = .845;
                } else if (distanceCm > 410 && distanceCm <= 440) {
                    power = .895;
                } else if (distanceCm > 440 && distanceCm <= 470) {
                    power = .955;
                } else {
                    power = 1.0;
                }
                
            }
        } else {
            // default shot is assuming about 8'
            power = 0.78;        }
        // System.out.println("POWER="+power);
        mainGroup.set(-1 * Math.min(1.0, power * motorPowerAdjustmentValue)); // motors run in the negative direction so return a negative number
    
    }

    public void dribble(){
        mainGroup.set(-.4);
    }

    public void stop(){
        mainGroup.set(0);
        //Limelight.setLedMode(LightMode.eOff); // can't turn off the limelight until shooting is done
    }
    
    public double getPower(){
        return Math.min(1.0, power * motorPowerAdjustmentValue);
    }

    public void increasePower() {
        if (motorPowerAdjustmentValue < 1.0 ) 
            motorPowerAdjustmentValue += 0.01; 
    }
    public void decreasePower() {
        if (motorPowerAdjustmentValue > 0.0 ) {
            motorPowerAdjustmentValue -= 0.01;
        }
     }

}