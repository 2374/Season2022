package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoIndexOffCommand extends CommandBase{
    private ShooterSubsystem m_shooterSubsystem;

    public AutoIndexOffCommand(ShooterSubsystem shooterSubsystem){
        this.m_shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    public void execute(){
        m_shooterSubsystem.indexerOff();
    }

    public void end(){
        
    }
}