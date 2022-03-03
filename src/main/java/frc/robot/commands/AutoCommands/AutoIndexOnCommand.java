package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;

public class AutoIndexOnCommand extends CommandBase{
    private IndexerSubsystem m_indexerSubsystem;

    public AutoIndexOnCommand(IndexerSubsystem indexerSubsystem){
        this.m_indexerSubsystem = indexerSubsystem;
        addRequirements(indexerSubsystem);
    }

    public void execute(){
        m_indexerSubsystem.indexerOn();
    }

    public void end(){
        
    }
}
