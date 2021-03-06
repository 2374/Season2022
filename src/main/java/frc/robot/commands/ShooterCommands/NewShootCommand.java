package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

/**
 * Fire a ball at an acquired target using a combination of turret/indexer/shooter subsystems
 */
public class NewShootCommand extends ParallelRaceGroup{
    /** 
     * Assuming a given shooter is close to being aligned to the target and
     * a ball is ready to be shot, instruct the turret to align and the shooter to
     * fire the ball at the target using the ball, and then shut everything down.
     * @param shooterSubsystem
     * @param turretSubsystem
     * @param indexerSubsystem
     * @param justShootDontAlign
     */
    public NewShootCommand(ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem, IndexerSubsystem indexerSubsystem,
            boolean justShootDontAlign) {
        if (justShootDontAlign) {
            addCommands(
                    // start the shooting
                    new StartShooterCommand(shooterSubsystem, justShootDontAlign), // should run until interrupted
                    // Feed balls into shooter and then stop
                    new ShootProcessCommand(indexerSubsystem));
        } else {
            addCommands(
                    // start the auto tracking
                    new TrackProcessCommand(turretSubsystem), // should run until interrupted
                    // start the shooting
                    new StartShooterCommand(shooterSubsystem, justShootDontAlign), // should run until interrupted
                    // Feed balls into shooter and then stop
                    new ShootProcessCommand(indexerSubsystem));
        }
    }
}
