package frc.robot.subsystems;

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
        // is either sensor set then there is work to do
        while (intakeSensor.get() || shooterSensor.get()){
            indexerOn();
        }
        stop();
    }
    
    public void stop(){
        indexerOff();
    }
    
    public void indexerOn(){
        topMotor.set(0.5);
        bottomMotor.set(0.5);
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

    public boolean getIntakeSensor(){
        return intakeSensor.get();
    }
}