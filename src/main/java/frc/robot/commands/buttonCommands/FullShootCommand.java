package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Perform a full cycle shooting operationg, intake a ball, move it through the indexer and fire
 * it at the target.
 */
public class FullShootCommand extends CommandBase{
    private final ShooterSubsystem m_shooterSubsystem;
    private final IntakeSubsystem m_intakeSubsystem;

    /** 
     * FullShootCommand performs a full cycle from ground to target cycle of a  ball
     * @param shooterSubsystem the shooter that will target lock and fire
     * @param intakeSubsystem the intake that will acquire the ball from the field
     */
    public FullShootCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
        this.m_shooterSubsystem = shooterSubsystem;
        this.m_intakeSubsystem = intakeSubsystem;
        addRequirements(shooterSubsystem);
        addRequirements(intakeSubsystem);
    }

    /**
     * Wehn the command is running it needs to start the intake to feed balls through
     * initialize the shooter and finally turn on the shooter to fire the ball at the 
     * target
     */
    @Override
    public void execute() {
        m_intakeSubsystem.enableIntake();
        m_shooterSubsystem.shootBallAtCurrentAcquiredTarget();
        m_shooterSubsystem.indexerOn();
    }

    
    /** 
     * When the command is terminated we need to stop the intake and te shooter
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.stop();
        m_shooterSubsystem.stop();
        m_shooterSubsystem.indexerOff();
    }
}