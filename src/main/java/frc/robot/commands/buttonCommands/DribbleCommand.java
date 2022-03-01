package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Fire a ball at an acquired target using a shooter subsystem
 */
public class DribbleCommand extends CommandBase{
    private final ShooterSubsystem m_shooterSubsystem;

    /** 
     * Assuming a given shooter is aligned to the target and
     * a ball is ready to be shot, instruct the shooter to
     * fire the ball at the target using the ball.
     * @param shooterSubsystem
     */
    public DribbleCommand(ShooterSubsystem shooterSubsystem) {
        this.m_shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    /**
     * Shoot a ball at the currently acquired target. This
     * assume a ball is ready and the target is locked.
     */
    @Override
    public void execute() {
        m_shooterSubsystem.dribble();
    }

    
    /** 
     * Turn off the shooter once we are done shooting
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.stop();
    }
}
