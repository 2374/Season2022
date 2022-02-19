package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeOnCommand extends CommandBase{
    private final IntakeSubsystem m_intakeSubsystem;

    public IntakeOnCommand(IntakeSubsystem intakeSubsystem) {
        this.m_intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        m_intakeSubsystem.start();
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stop();
    }
}
