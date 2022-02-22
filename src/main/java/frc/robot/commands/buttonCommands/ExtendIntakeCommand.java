package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ExtendIntakeCommand extends CommandBase{
    private final IntakeSubsystem m_intakeSubsystem;

    /** 
     * Turn on the intake for a given Intake subsystem for the length of
     * the command.
     * @param IntakeSubsystem Which Intake subsystem should the intake be engaged for?
     */
    public ExtendIntakeCommand(IntakeSubsystem intakeSubsystem) {
        this.m_intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    /**
     * Turn on the intake for the Intake subsystem
     */
    @Override
    public void execute() {
        m_intakeSubsystem.extendPistons();
    }

    @Override
    public void end(boolean interrupted) {
    }
}
