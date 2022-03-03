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
        // this should be run once and then it will exit
        //
        // another way to do this would be to have sendTheBalls just run
        // the motors and we can override the isFinished() method to
        // check the sensors and we are not finished if either of the
        // sensors has more work to do.
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
