package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IndexerSubsystem;
// import frc.robot.subsystems.ShooterSubsystem;

/**
 * fire balls at target, assumes target is already acquired
 * and shooter is running at correct speed. Ideally these
 * two prereqs are constantly running in another thread keeping
 * the target locked and the shooter spun up.
 */
public class ShootProcessCommand extends SequentialCommandGroup {
     /**
     * This is a blocking command that will start feeding balls
     * to the shooter until empty and then stop the shooter
     * @param shooterSubsystem the shooter to fire at target
     */
    // public ShootProcessCommand(ShooterSubsystem shooterSubsystem, IndexerSubsystem indexerSubsystem) {
    //     addRequirements(shooterSubsystem);
    //     addCommands(
    //         // Wait until shooter has spun up completely THIS SHOULD CHANGE TO SOME INTERLOCK FROM THE SHOOTER that says "GOOD to FEED"
    //         new DelayXSecondsCommand(2),
    //         // Feed Balls until indexer is empty
    //         new FeedBallsCommand(indexerSubsystem)
    //         // Stop Shooter this should cause the ShootCommand group to terminate
    //         //new StopShooterCommand(shooterSubsystem) // this should happen automatically when the FeedBallsCommand finishes it will exit the command group
    //     );
    // }

    public ShootProcessCommand(IndexerSubsystem indexerSubsystem) {
        addCommands(
            // Wait until shooter has spun up completely THIS SHOULD CHANGE TO SOME INTERLOCK FROM THE SHOOTER that says "GOOD to FEED"
            new DelayXSecondsCommand(2),
            // Feed Balls until indexer is empty
            new FeedBallsCommand(indexerSubsystem)
        );
    }

}
