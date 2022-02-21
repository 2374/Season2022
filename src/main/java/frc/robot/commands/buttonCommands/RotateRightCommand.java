package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

/**
 * Rotate the turret subsystem right
 */
public class RotateRightCommand extends CommandBase{
    private final TurretSubsystem m_turretSubsystem;

    /** 
     * Allows the manual moving of the turret subsystem in the right direction
     * @param turretSubsystem which turret subsystem should be rotated right?
     */
    public RotateRightCommand(TurretSubsystem turretSubsystem) {
        this.m_turretSubsystem = turretSubsystem;
        addRequirements(turretSubsystem);
    }

    /**
     * Start moving the turret to the Right
     */
    @Override
    public void execute() {
        m_turretSubsystem.rotateRight();
    }

    
    /** 
     * Stop moving the turret subsystem
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_turretSubsystem.stop();
    }
}
