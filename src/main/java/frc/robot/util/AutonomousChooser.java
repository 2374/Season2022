package frc.robot.util;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
// import frc.robot.commands.*;
// import org.frcteam2910.common.control.Trajectory;
// import org.frcteam2910.common.math.RigidTransform2;
// import org.frcteam2910.common.math.Rotation2;
import frc.robot.commands.FollowTrajectoryCommand;

public class AutonomousChooser {
    private final AutonomousTrajectories trajectories;

    private SendableChooser<AutonomousMode> autonomousModeChooser = new SendableChooser<>();

    public AutonomousChooser(AutonomousTrajectories trajectories) {
        this.trajectories = trajectories;

        autonomousModeChooser.setDefaultOption("FAR LEFT", AutonomousMode.POSITION_ONE);
        autonomousModeChooser.addOption("LEFT", AutonomousMode.POSITION_TWO);
        autonomousModeChooser.addOption("RIGHT", AutonomousMode.POSITION_THREE);
        autonomousModeChooser.addOption("FAR RIGHT", AutonomousMode.POSITION_FOUR);

        SmartDashboard.putData(autonomousModeChooser);
    }

    public SendableChooser<AutonomousMode> getAutonomousModeChooser() {
        return autonomousModeChooser;
    }

    // private SequentialCommandGroup get10BallAutoCommand(RobotContainer container) {
    //     SequentialCommandGroup command = new SequentialCommandGroup();

    //     resetRobotPose(command, container, trajectories.getTenBallAutoPartOne());
    //     followAndIntake(command, container, trajectories.getTenBallAutoPartOne());
    //     shootAtTarget(command, container);
    //     //command.addCommands(new FollowTrajectoryCommand(drivetrainSubsystem, trajectories.getTenBallAutoPartTwo()));
    //     //command.addCommands(new TargetWithShooterCommand(shooterSubsystem, visionSubsystem, xboxController));

    //     return command;
    // }

    private Command positionOneAutoCommand(RobotContainer container) {
        SequentialCommandGroup command = new SequentialCommandGroup();

        //reset robot pose
        // resetRobotPose(command, container, trajectories.farLeftAutoPartOne());
        // command.addCommands(new HomeHoodMotorCommand(container.getShooterSubsystem()));
        //follow first trajectory and shoot
        follow(command, container, trajectories.farLeftAutoPartOne());
        // shootAtTarget(command, container, 1.5);
        // //follow second trajectory and shoot
        // followAndIntake(command, container, trajectories.farLeftAutoPartTwo());

        // follow(command, container, trajectories.farLeftAutoPartThree());
        // shootAtTarget(command, container, 1.5);
        
        return command;
    }

    

    public Command getCommand(RobotContainer container) {
        switch (autonomousModeChooser.getSelected()) {
            case POSITION_ONE:
                return positionOneAutoCommand(container);
            case POSITION_TWO:
                return positionOneAutoCommand(container);
            case POSITION_THREE:
                return positionOneAutoCommand(container);
            case POSITION_FOUR:
                return positionOneAutoCommand(container);
        }

        return positionOneAutoCommand(container);
    }

    // private void shootAtTarget(SequentialCommandGroup command, RobotContainer container) {
    //     // shootAtTarget(command, container, 2.5);
    // }

    private void shootAtTarget(SequentialCommandGroup command, RobotContainer container, double timeToWait) {
        // command.addCommands(
        //         new TargetWithShooterCommand(container.getShooterSubsystem(), container.getVisionSubsystem(), container.getPrimaryController())
        //                 .alongWith(new VisionRotateToTargetCommand(container.getDrivetrainSubsystem(), container.getVisionSubsystem(), () -> 0.0, () -> 0.0))
        //                 .alongWith(
        //                         new WaitCommand(0.1).andThen(new AutonomousFeedCommand(container.getShooterSubsystem(), container.getFeederSubsystem(), container.getVisionSubsystem())))
        //                 .withTimeout(timeToWait));
    }

    private void follow(SequentialCommandGroup command, RobotContainer container, Trajectory trajectory) {
        // System.out.println("ROSS="+container.getDrivetrainSubsystem());
        command.addCommands(new FollowTrajectoryCommand(container.getDrivetrainSubsystem(), trajectory).getCommand());

                // command.addCommands(new FollowTrajectoryCommand(container.getDrivetrainSubsystem(), trajectory)
                // .deadlineWith(new TargetWithShooterCommand(container.getShooterSubsystem(), container.getVisionSubsystem(), container.getPrimaryController()))
                // .alongWith(new PrepareBallsToShootCommand(container.getIntakeSubsystem(), 1.0)));
    }

    private void followAndIntake(SequentialCommandGroup command, RobotContainer container, Trajectory trajectory) {
        // command.addCommands(new InstantCommand(() -> container.getIntakeSubsystem().setTopExtended(true)));
        // command.addCommands(
        //         new FollowTrajectoryCommand(container.getDrivetrainSubsystem(), trajectory)
        //                 .deadlineWith(
        //                         new IntakeCommand(container.getIntakeSubsystem(), container.getFeederSubsystem(), -1.0).withTimeout(0.25)
        //                                 .andThen(
        //                                         new IntakeCommand(container.getIntakeSubsystem(), container.getFeederSubsystem(), 1.0)
        //                                                 .alongWith(
        //                                                         new FeederIntakeWhenNotFullCommand(container.getFeederSubsystem(), 1.0)
        //                                                 ))));
        // command.addCommands(new InstantCommand(() -> container.getIntakeSubsystem().setTopExtended(false)));
    }

    private void resetRobotPose(SequentialCommandGroup command, RobotContainer container, Trajectory trajectory) {
        command.addCommands(new InstantCommand(() -> container.getDrivetrainSubsystem().zeroGyroscope()));
        command.addCommands(new InstantCommand(() -> container.getDrivetrainSubsystem().resetOdometry(
                new Pose2d(0,0,container.getDrivetrainSubsystem().getGyroscopeRotation()))));
    }

    private enum AutonomousMode {
        POSITION_ONE,
        POSITION_TWO,
        POSITION_THREE,
        POSITION_FOUR
    }
}
