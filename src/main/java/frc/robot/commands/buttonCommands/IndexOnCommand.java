package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;

/**
 * Turn on the indexer for the shooter
 */
public class IndexOnCommand extends CommandBase{
    private final IndexerSubsystem m_indexerSubsystem;

    /** 
     * Turn on the indexer for a given shooter subsystem for the length of
     * the command.
     * @param indexerSubsystem Which shooter subsystem should the indexer be engaged for?
     */
    public IndexOnCommand(IndexerSubsystem indexerSubsystem) {
        this.m_indexerSubsystem = indexerSubsystem;
        addRequirements(indexerSubsystem);
    }

    /**
     * Turn on the indexer for the shooter subsystem
     */
    @Override
    public void execute() {
        m_indexerSubsystem.indexerOn();
    }

    
    /** 
     * Turn the indexer off when the command has finished
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_indexerSubsystem.indexerOff();
    }
}
