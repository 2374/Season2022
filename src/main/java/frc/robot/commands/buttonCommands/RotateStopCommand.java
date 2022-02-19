package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class RotateStopCommand extends CommandBase{
    private final TurretSubsystem m_turretSubsystem;

    public RotateStopCommand(TurretSubsystem turretSubsystem) {
        this.m_turretSubsystem = turretSubsystem;
        addRequirements(turretSubsystem);
    }

    @Override
    public void execute() {
        m_turretSubsystem.rotateStop();
    }

    @Override
    public void end(boolean interrupted) {
    }
}
