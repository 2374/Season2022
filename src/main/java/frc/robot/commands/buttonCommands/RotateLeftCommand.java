package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class RotateLeftCommand extends CommandBase{
    private final TurretSubsystem m_turretSubsystem;

    public RotateLeftCommand(TurretSubsystem turretSubsystem) {
        this.m_turretSubsystem = turretSubsystem;
        addRequirements(turretSubsystem);
    }

    @Override
    public void execute() {
        m_turretSubsystem.rotateLeft();
    }

    @Override
    public void end(boolean interrupted) {
    }
}
