package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.buttonCommands.IndexOnCommand;
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
    public ShootProcessCommand(IndexerSubsystem indexerSubsystem) {
        addCommands(
            // Wait until shooter has spun up completely THIS SHOULD CHANGE TO SOME INTERLOCK FROM THE SHOOTER that says "GOOD to FEED"
            new WaitCommand(2.0),
            // new IndexOnCommand(indexerSubsystem).until(indexerSubsystem.intakeSensorsSeeNoBalls),
            new IndexOnCommand(indexerSubsystem).withTimeout(1.5),
            new WaitCommand(1.5)
        );
    }

}
