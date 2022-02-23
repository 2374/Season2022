package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class FollowTrajectoryCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private final Trajectory trajectory;
    private ProfiledPIDController thetaController;
    private SwerveControllerCommand swerveControllerCommand;

    public FollowTrajectoryCommand(DrivetrainSubsystem drivetrain, Trajectory trajectory) {
        this.drivetrain = drivetrain;
        this.trajectory = trajectory;

        addRequirements(drivetrain);
    }

    public Command getCommand() {
        System.out.println("FTCE enter ="+swerveControllerCommand);
        if (swerveControllerCommand == null) {
            initialize();
        }
        return swerveControllerCommand;
    }

    @Override
    public void initialize() {
        thetaController = new ProfiledPIDController(Constants.PTHETACONTROLLER, 0, 0,
                Constants.PTHETACONTROLLERCONSTRAINTS);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        swerveControllerCommand = new SwerveControllerCommand(
                trajectory,
                drivetrain::getPose,
                DrivetrainSubsystem.getKinematics(),
                new PIDController(Constants.PXCONTROLLER, 0, 0), new PIDController(Constants.PYCONTROLLER, 0, 0),
                thetaController,
                drivetrain::setModuleStates,
                drivetrain);
        System.out.println("ROSS INIT="+swerveControllerCommand);
        
    }

    @Override
    public void execute() {
        System.out.println("execute CONFIG time=" + trajectory.getTotalTimeSeconds());

        // new SequentialCommandGroup(
        //         new InstantCommand(() -> drivetrain.resetOdometry(trajectory.getInitialPose())),
        //         swerveControllerCommand,
        //         new InstantCommand(() -> drivetrain.stopModules()));
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stopModules();
    }

    // @Override
    // public boolean isFinished() {
    // return drivetrain.getFollower().getCurrentTrajectory().isEmpty();
    // }
}
