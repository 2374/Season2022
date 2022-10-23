package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class IndexerSubsystem extends SubsystemBase {
    private WPI_TalonFX topMotor;
    private WPI_TalonFX bottomMotor;
    private DigitalInput intakeSensor = new DigitalInput(0);
    private DigitalInput shooterSensor = new DigitalInput(1);

    private IndexerSubsystem instance;
    
    public IndexerSubsystem() {
        topMotor = new WPI_TalonFX(Constants.TOP_INDEX_MOTOR, Constants.CANIVORE_CAN_BUS_NAME);
        bottomMotor = new WPI_TalonFX(Constants.BOTTOM_INDEX_MOTOR, Constants.CANIVORE_CAN_BUS_NAME);
    }

    public IndexerSubsystem getIndexerInstance() {
        if (instance == null) {
            instance = new IndexerSubsystem();
        }
        
        return instance;
    }

    //control classes

    public void sendTheBalls() {
        indexerOn();
        // is either sensor set then there is work to do
        do {
            System.out.println("SENDING THE BALLS "+intakeSensorNoBall()+" "+shooterSensorNoBall());
            // wait 3 seconds before testing both sensors again
            // this should give the ball enough time to clear the system
            try {
                wait(3000);
            } catch (Exception e) {
                // Auto-generated catch block
                // being interrupted is a good thing means we are done. e.printStackTrace();
            }
        } while (intakeSensorNoBall() || shooterSensorNoBall());
        indexerOff();
    }
    
    public void stop(){
        indexerOff();
    }
    
    public void indexerOn(){
        topMotor.set(0.3);
        bottomMotor.set(0.3);
    }

    public void indexerRev(){
        topMotor.set(-0.3);
        bottomMotor.set(-0.3);
    }

    public void indexerOff(){
        // System.out.println("index off");
        topMotor.set(0.0);
        bottomMotor.set(0.0);
    }

    public boolean intakeSensorNoBall(){
        return intakeSensor.get(); 
    }

    public boolean shooterSensorNoBall(){
        return shooterSensor.get();
    }

    public BooleanSupplier intakeSensorsSeeNoBalls = () -> (shooterSensorNoBall() && intakeSensorNoBall());
}