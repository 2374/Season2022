package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

// Welp, I'm going to grillby's. Papyrus, do you want anything?

public class InteractiveDriveCommand extends CommandBase {
    private ShooterSubsystem m_shooter;
    // private ClimberSubsystem m_climber;
    private XboxController xbox = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    
    // Hey, you, you're finally awake

    public InteractiveDriveCommand(ShooterSubsystem shooter /*, ClimberSubsystem climber*/) {
        m_shooter = shooter;
        //m_climber = climber;
        
        addRequirements(shooter);
        //addRequirements(climber);
    }

    @Override
    public void execute() {

        SmartDashboard.putNumber("Shooter Power", m_shooter.getPower());

        if (xbox.getAButton()) {
            m_shooter.shoot();
        } else {    
            m_shooter.stop();
        }

        /*
        if (xbox.getBButton()) {
            m_climber.retractFixedArms();
        } else {
            m_climber.stopFixedArms();

        }
        *\
        
        /* if (xbox.getAButton()) {
            m_climber.retractFixedArms();
        } else {
            m_climber.stopFixedArms();
        }

        if (xbox.getBButton()) {
            m_climber.(); 
        } else {
            m_climber.stopFixedGroup();
        } */
    }
        
    }

