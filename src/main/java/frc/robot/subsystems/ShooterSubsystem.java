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
    private double motorPowerAdjustmentValue = 1;
    
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
            System.out.println("Distance="+distanceCm);
            if (distanceCm > 0.0) {
                if (distanceCm <= 210) {
                    power = 0.4; // to close shouldn't even fire
                } else if (distanceCm >= 210 && distanceCm <= 260) {
                    power = .62;
                } else if (distanceCm >= 260 && distanceCm <= 290) {
                    power = .65;
                } else if (distanceCm >= 290 && distanceCm <= 320) {
                    power = .70;
                    // below here may fire short
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
            power = 1.0;        }
        // System.out.println("POWER="+power);
        mainGroup.set(-1 * Math.min(1.0, power * motorPowerAdjustmentValue)); // motors run in the negative direction so return a negative number
    
    }

    public void dribble(){
        mainGroup.set(-.4);
    }

    public void stop(){
        mainGroup.set(0);
        // Limelight.setLedMode(LightMode.eOff); // can't turn off the limelight until
        // shooting is done
    }

    // What is the real adjusted power of the shooter (1.0 is max)
    public double getPower() {
        return Math.min(1.0, power * motorPowerAdjustmentValue);
    }

    // Increases the shooter power by 1%
    public void increasePower() {
        if (motorPowerAdjustmentValue < 1.2)
            motorPowerAdjustmentValue += 0.01;
    }

    // Decreases the shooter power by 1%
    public void decreasePower() {
        if (motorPowerAdjustmentValue > 0.8) {
            motorPowerAdjustmentValue -= 0.01;
        }
    }

    // What is the current percentage adjustment to power 1.0 == 100%
    public double currentPowerAdjustmentValue() {
        return motorPowerAdjustmentValue;
    }

    public double getPotentialPower(){
        double Ppower = 0.0;
        // double velocity;
        // System.out.println("locked?="+limelight.isLockedOn());
        limelight.updateTracking();
        if (limelight.isLockedOn()) {
            // double d = limelight.calculateDistance()/100;
            // System.out.println("TARGET DIST=" + d);
            // velocity = Math.sqrt(19.6 * Math.pow(d, 2) / (d * 1.73205080757 - (limelight.getDifferenceDistanceCM()/100)));
            // System.out.println("Velocity=" + velocity);
            // double RPM = velocity/0.05/0.10472;
            // System.out.println("RPM=" + RPM);
            // power = 2.1*RPM/6380;
            // System.out.println("power=" + power);
            double distanceCm = limelight.calculateDistance();
            if (distanceCm > 0.0) {
                if (distanceCm <= 210) {
                    Ppower = 0.4; // to close shouldn't even fire
                } else if (distanceCm >= 210 && distanceCm <= 260) {
                    Ppower = .62;
                } else if (distanceCm >= 260 && distanceCm <= 290) {
                    Ppower = .65;
                } else if (distanceCm >= 290 && distanceCm <= 320) {
                    Ppower = .70;
                    // below here may fire short
                } else if (distanceCm >= 320 && distanceCm <= 350) {
                    Ppower = .775;
                } else if (distanceCm > 350 && distanceCm <= 380) {
                    Ppower = .805;
                } else if (distanceCm > 380 && distanceCm <= 410) {
                    Ppower = .845;
                } else if (distanceCm > 410 && distanceCm <= 440) {
                    Ppower = .895;
                } else if (distanceCm > 440 && distanceCm <= 470) {
                    Ppower = .955;
                } else {
                    Ppower = 1.0;
                }
                
            }
        }
        return Ppower;
    }

}