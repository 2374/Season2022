package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Fire a ball at an acquired target using a shooter subsystem
 */
public class StartShooterCommand extends CommandBase{
    private final ShooterSubsystem m_shooterSubsystem;
    private final Boolean m_justShootDontAlign;
    
    /** 
     * Assuming a given shooter is aligned to the target and
     * a ball is ready to be shot, instruct the shooter to
     * fire the ball at the target using the ball.
     * @param shooterSubsystem
     */
    public StartShooterCommand(ShooterSubsystem shooterSubsystem, Boolean justShootDontAlign) {
        this.m_shooterSubsystem = shooterSubsystem;
        this.m_justShootDontAlign = justShootDontAlign;
        addRequirements(shooterSubsystem);
    }

    /**
     * Start the shooter and constantly adjust speed as distance changes
     * assume the target is locked.
     */
    @Override
    public void execute() {
        m_shooterSubsystem.shootBallAtCurrentAcquiredTarget(m_justShootDontAlign); // this will start the shooter at the desired speed
    }

    
    /** 
     * Turn off the shooter once we are done shooting
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.stop(); // make sure we stop the shooter from spinning
    }

    /**
     * Never be finished until this command is interrupted
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
