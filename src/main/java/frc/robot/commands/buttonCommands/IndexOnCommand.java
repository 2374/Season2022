package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Turn on the indexer for the shooter
 */
public class IndexOnCommand extends CommandBase{
    private final ShooterSubsystem m_shooterSubsystem;

    /** 
     * Turn on the indexer for a given shooter subsystem for the length of
     * the command.
     * @param shooterSubsystem Which shooter subsystem should the indexer be engaged for?
     */
    public IndexOnCommand(ShooterSubsystem shooterSubsystem) {
        this.m_shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    /**
     * Turn on the indexer for the shooter subsystem
     */
    @Override
    public void execute() {
        m_shooterSubsystem.indexerOn();
    }

    
    /** 
     * Turn the indexer off when the command has finished
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.indexerOff();
    }
}
