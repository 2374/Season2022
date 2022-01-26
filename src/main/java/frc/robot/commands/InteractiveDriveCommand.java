package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class InteractiveDriveCommand extends CommandBase {
    private ShooterSubsystem m_shooter;
    private ClimberSubsystem m_climber;
    private XboxController xbox = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    

    public InteractiveDriveCommand(ShooterSubsystem shooter, ClimberSubsystem climber) {
        m_shooter = shooter;
        m_climber = climber;
        
        addRequirements(shooter);
    }

    @Override
    public void execute() {

        if (xbox.getAButton()) {
            m_shooter.shoot();
        } else {
            m_shooter.stop();
        }

        if (xbox.getBButton()) {
            m_climber.retract();
        } else {
            m_climber.stop();
        }
        
    
    }
        
    }
