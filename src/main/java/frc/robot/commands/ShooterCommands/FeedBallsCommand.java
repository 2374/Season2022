package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;

/**
 * Fire a ball at an acquired target using a combination of turret/indexer/shooter subsystems
 */
public class FeedBallsCommand extends CommandBase{
    private final IndexerSubsystem m_indexerSubsystem;
    /** 
     * Assuming a given shooter is close to being aligned to the target and
     * a ball is ready to be shot, instruct the turret to align and the shooter to
     * fire the ball at the target using the ball, and then shut everything down.
     * @param shooterSubsystem
     */
    public FeedBallsCommand(IndexerSubsystem indexerSubsystem) {
        m_indexerSubsystem = indexerSubsystem;
        addRequirements(indexerSubsystem); 
    }

    /**
     * Turn on the indexer for the shooter subsystem
     */
    @Override
    public void execute() {
        m_indexerSubsystem.sendTheBalls();
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
