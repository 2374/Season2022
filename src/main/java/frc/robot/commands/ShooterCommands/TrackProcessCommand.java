package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

/**
 * Spin the turrent to align to the target for the shooter
 * to launch the ball at the target
 */
public class TrackProcessCommand extends CommandBase {
    private final TurretSubsystem m_turretSubsystem;

     /**
     * This is a blocking command that will spin the turret to align
     * with the target within a given error percent from center
     * @param turretSubsystem the turret to align on target
     */
    public TrackProcessCommand(TurretSubsystem turretSubsystem) {
        this.m_turretSubsystem = turretSubsystem;
        addRequirements(turretSubsystem);
    }

    /**
     * This is a blocking command that will spin the turret to align
     * with the target within a given error percent from center
     */
    @Override
    public void execute() {
        m_turretSubsystem.spinToTarget();
    }

     /** 
     * When the command is terminated we do nothing
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
    }
}
