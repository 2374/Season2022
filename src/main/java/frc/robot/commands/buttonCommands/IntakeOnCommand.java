package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Turn on the intake Subsystem to feed balls to the indexer
 */
public class IntakeOnCommand extends CommandBase{
    private final IntakeSubsystem m_intakeSubsystem;

    /** 
     * Enable the given intake subsysem for the length of the command.
     * This will allow for acquiring balls from the field and sending the
     * to the indexer.
     * @param intakeSubsystem The intake subsystem to turn on
     */
    public IntakeOnCommand(IntakeSubsystem intakeSubsystem) {
        this.m_intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    /**
     * Turn on the intake subsystem to start feeding balls from the field into the indexer
     */
    @Override
    public void execute() {
        m_intakeSubsystem.enableIntake();
    }

    
    /** 
     * Turn the intake off when command finishes
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stop();
    }
}
