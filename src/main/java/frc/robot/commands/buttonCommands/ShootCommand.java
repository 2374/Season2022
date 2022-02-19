package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends CommandBase{
    private final ShooterSubsystem m_shooterSubsystem;

    public ShootCommand(ShooterSubsystem shooterSubsystem) {
        this.m_shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        m_shooterSubsystem.shoot();
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.stop();
    }
}
