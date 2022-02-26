package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoIndexOnCommand extends CommandBase{
    private ShooterSubsystem m_shooterSubsystem;

    public AutoIndexOnCommand(ShooterSubsystem shooterSubsystem){
        this.m_shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    public void execute(){
        m_shooterSubsystem.indexerOn();
    }

    public void end(){
        
    }
}
