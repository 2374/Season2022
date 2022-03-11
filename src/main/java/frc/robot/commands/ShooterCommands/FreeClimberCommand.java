package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.buttonCommands.RotateLeftCommand;
import frc.robot.subsystems.TurretSubsystem;

/**
 * fire balls at target, assumes target is already acquired
 * and shooter is running at correct speed. Ideally these
 * two prereqs are constantly running in another thread keeping
 * the target locked and the shooter spun up.
 */
public class FreeClimberCommand extends ParallelRaceGroup {
     /**
     * This is a blocking command that will start feeding balls
     * to the shooter until empty and then stop the shooter
     * @param shooterSubsystem the shooter to fire at target
     */
    public FreeClimberCommand(TurretSubsystem turretSubsystem) {
        addCommands(
            // Wait until shooter has spun up completely THIS SHOULD CHANGE TO SOME INTERLOCK FROM THE SHOOTER that says "GOOD to FEED"
            new RotateLeftCommand(turretSubsystem),
            new WaitCommand(0.5)
        );
    }

}
