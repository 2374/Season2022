package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class RetractIntakeCommand extends CommandBase{
    private final IntakeSubsystem m_intakeSubsystem;

    /** 
     * Turn on the indexer for a given Intake subsystem for the length of
     * the command.
     * @param IntakeSubsystem Which Intake subsystem should the indexer be engaged for?
     */
    public RetractIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.m_intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    /**
     * Turn on the indexer for the Intake subsystem
     */
    @Override
    public void initialize() {
        m_intakeSubsystem.retractPistons();
    }

    @Override
    public void end(boolean interrupted) {
    }
}
