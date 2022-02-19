package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class FullShootCommand extends CommandBase{
    private final ShooterSubsystem m_shooterSubsystem;
    private final IntakeSubsystem m_intakeSubsystem;

    public FullShootCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.m_shooterSubsystem = shooterSubsystem;
        this.m_intakeSubsystem = intakeSubsystem;
        addRequirements(shooterSubsystem);
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        m_intakeSubsystem.start();
        m_shooterSubsystem.shoot();
        m_shooterSubsystem.on();
    }

    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stop();
        m_shooterSubsystem.stop();
        m_shooterSubsystem.off();
    }
}