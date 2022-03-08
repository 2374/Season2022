package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Turn on the indexer for the shooter
 */
public class IntakeRevCommand extends CommandBase{
    private final IntakeSubsystem m_intakeSubsystem;

    /** 
     * Turn on the indexer for a given shooter subsystem for the length of
     * the command.
     * @param intakeSubsystem Which intake subsystem should the indexer be engaged for?
     */
    public IntakeRevCommand(IntakeSubsystem intakeSubsystem) {
        this.m_intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    /**
     * Turn on the indexer for the shooter subsystem
     */
    @Override
    public void execute() {
        m_intakeSubsystem.intakeRev();
    }

    
    /** 
     * Turn the indexer off when the command has finished
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stop();
    }
}
