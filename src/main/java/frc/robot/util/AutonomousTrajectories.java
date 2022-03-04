package frc.robot.util;

// import java.io.FileNotFoundException;
import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.MaxVelocityConstraint;
import edu.wpi.first.math.trajectory.constraint.TrajectoryConstraint;
import frc.robot.subsystems.DrivetrainSubsystem;

// import org.frcteam2910.common.control.MaxAccelerationConstraint;
// import org.frcteam2910.common.control.MaxVelocityConstraint;
// import org.frcteam2910.common.control.Path;
// import org.frcteam2910.common.control.SimplePathBuilder;
// import org.frcteam2910.common.control.Trajectory;
// import org.frcteam2910.common.control.TrajectoryConstraint;
// import org.frcteam2910.common.io.PathReader;
// import org.frcteam2910.common.math.Rotation2;
// import org.frcteam2910.common.math.Vector2;

public class AutonomousTrajectories {

        // private static final double SAMPLE_DISTANCE = 0.1;

        // private static final String EIGHT_BALL_AUTO_PART_ONE_NAME = "autos/8BallAuto/8BallAutoPart1.path";
        // private static final String EIGHT_BALL_AUTO_PART_TWO_NAME = "autos/8BallAuto/8BallAutoPart2.path";
        // private static final String TEN_BALL_AUTO_PART_ONE_NAME = "autos/10BallAuto/10BallAutoPart1.path";
        // private static final String TEN_BALL_AUTO_PART_TWO_NAME = "autos/10BallAuto/10BallAutoPart2.path";

        // private static final Path EIGHT_BALL_COMPATIBLE_PART_ONE = new
        // SimplePathBuilder(new Vector2(511.75, -148.0),
        // Rotation2.ZERO)
        // .lineTo(new Vector2(475.75, -134.25), Rotation2.fromDegrees(18.83))
        // .build();
        // private static final Path EIGHT_BALL_COMPATIBLE_PART_TWO = new
        // SimplePathBuilder(new Vector2(475.75, -134.25),
        // Rotation2.ZERO)
        // .lineTo(new Vector2(324.0, -134.25), Rotation2.ZERO)
        // .build();
        // private static final Path EIGHT_BALL_COMPATIBLE_PART_THREE = new
        // SimplePathBuilder(new Vector2(324.0, -134.25),
        // Rotation2.ZERO)
        // .lineTo(new Vector2(474.0, -114.25), Rotation2.fromDegrees(14.0))
        // .build();
        // private static final Path EIGHT_BALL_COMPATIBLE_PART_FOUR = new
        // SimplePathBuilder(new Vector2(474.0, -114.25),
        // Rotation2.fromDegrees(14.0))
        // .lineTo(new Vector2(324.0, -134.25), Rotation2.fromDegrees(0.0))
        // .build();

        private Trajectory twoMetersBack;
        private Trajectory farLeftAutoPartOne;
        private Trajectory farLeftAutoPartTwo;
        // private Trajectory eightBallAutoPartFour;
        // private Trajectory tenBallAutoPartOne;
        // private Trajectory tenBallAutoPartTwo;
        // private Trajectory circuitTenBallAutoPartOne;
        // private Trajectory circuitTenBallAutoPartTwo;

        // private final Trajectory eightBallCompatiblePartOne;
        // private final Trajectory eightBallCompatiblePartTwo;
        // private final Trajectory eightBallCompatiblePartThree;
        // private final Trajectory eightBallCompatiblePartFour;

        // private final Trajectory simpleShootThree;

        
        public AutonomousTrajectories(TrajectoryConstraint[] trajectoryConstraints) throws IOException {
                TrajectoryConstraint[] slowConstraints = Arrays.copyOf(trajectoryConstraints,
                                trajectoryConstraints.length + 1);
                slowConstraints[slowConstraints.length - 1] = new MaxVelocityConstraint(6.0 * 12.0);
                // slowConstraints[slowConstraints.length - 2] = new
                // MaxAccelerationConstraint(4.0 * 12.0);

                TrajectoryConfig config = new TrajectoryConfig(
                                DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND / 2.0,
                                DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND / 4.0)
                                                // Add kinematics to ensure max speed is actually obeyed
                                                .setKinematics(DrivetrainSubsystem.getKinematics());

                farLeftAutoPartOne = TrajectoryGenerator.generateTrajectory(
                                // Start at the origin facing the +X direction
                                new Pose2d(0, 0, new Rotation2d(0)),
                                // Pass through these two interior waypoints, making an 's' curve path
                                List.of(
                                                // new Rotation2d(2.705),
                                                // new AutoIntakeOnCommand(m_intakeSubsystem),
                                                // new Translation2d(1.93, new Rotation2d(2.705)),
                                                // new AutoIntakeOffCommand(m_intakeSubsystem),
                                                // new Rotation2d(-.57),
                                                // new SpinToTargetCommand(m_turretSubsystem),
                                                // new AutoShootCommand(m_shooterSubsystem),
                                                // new WaitCommand(3),
                                                // new AutoShootStopCommand(m_shooterSubsystem)
                                                new Translation2d(.1, new Rotation2d(-1.68122))

                                ),
                                new Pose2d(.2, 0, new Rotation2d(-3.28122)), config);

                farLeftAutoPartTwo = TrajectoryGenerator.generateTrajectory(
                                // Start at the origin facing the +X direction
                                new Pose2d(0, 0, new Rotation2d(0)),
                                // Pass through these two interior waypoints, making an 's' curve path
                                List.of(
                                                new Translation2d(1.8304, 0)),
                                new Pose2d(1.9304, 0, new Rotation2d()), config);

                twoMetersBack = TrajectoryGenerator.generateTrajectory(
                                // Start at the origin facing the +X direction
                                new Pose2d(0, 0, new Rotation2d(0)),
                                List.of(
                                     new Translation2d(1.0, 0.0)),
                                new Pose2d(0.0, 2.0, new Rotation2d()), config);

                // eightBallAutoPartTwo = new Trajectory(
                // new SimplePathBuilder(new Vector2(468.0, -67.34), Rotation2.ZERO)
                // .lineTo(new Vector2(459.23, -111.87))
                // .arcTo(new Vector2(432.0, -134.25), new Vector2(432.0, -106.5))
                // .lineTo(new Vector2(324.0, -134.25), Rotation2.fromDegrees(0.0))
                // .build(),
                // slowConstraints, SAMPLE_DISTANCE
                // );
                // eightBallAutoPartThree = new Trajectory(
                // new SimplePathBuilder(new Vector2(324.0, -134.25),
                // Rotation2.fromDegrees(0.0))
                // .arcTo(new Vector2(468.0, -67.34), new Vector2(324.0, 54.16))
                // .build(),
                // trajectoryConstraints, SAMPLE_DISTANCE
                // );
                // eightBallAutoPartFour = new Trajectory(
                // new SimplePathBuilder(new Vector2(468.0, -67.34), Rotation2.fromDegrees(0.0))
                // .arcTo(new Vector2(324, -134.25), new Vector2(324.0, 54.16))
                // .build(),
                // trajectoryConstraints, SAMPLE_DISTANCE
                // );
                // tenBallAutoPartOne = new Trajectory(getPath(TEN_BALL_AUTO_PART_ONE_NAME),
                // trajectoryConstraints, SAMPLE_DISTANCE);
                // tenBallAutoPartTwo = new Trajectory(getPath(TEN_BALL_AUTO_PART_TWO_NAME),
                // trajectoryConstraints, SAMPLE_DISTANCE);

                // circuitTenBallAutoPartOne = new Trajectory(
                // new SimplePathBuilder(new Vector2(509.0, -162.0), Rotation2.ZERO)
                // .lineTo(new Vector2(385.51, -99.31), Rotation2.fromDegrees(290.0))
                // .arcTo(new Vector2(385.55, -77.48), new Vector2(390.51, -88.41))
                // .lineTo(new Vector2(418.33, -62.60))
                // .build(),
                // slowConstraints, SAMPLE_DISTANCE
                // );
                // circuitTenBallAutoPartTwo = new Trajectory(
                // new SimplePathBuilder(new Vector2(418.33, -62.60),
                // Rotation2.fromDegrees(290.0))
                // .lineTo(new Vector2(413.52, -66.18), Rotation2.fromDegrees(290.0))
                // .arcTo(new Vector2(435.87, -94.38), new Vector2(424.28, -80.61))
                // .lineTo(new Vector2(468.0, -67.34), Rotation2.ZERO)
                // .build(),
                // trajectoryConstraints, SAMPLE_DISTANCE
                // );

                // eightBallCompatiblePartOne = new Trajectory(EIGHT_BALL_COMPATIBLE_PART_ONE,
                // trajectoryConstraints, SAMPLE_DISTANCE);
                // eightBallCompatiblePartTwo = new Trajectory(EIGHT_BALL_COMPATIBLE_PART_TWO,
                // slowConstraints, SAMPLE_DISTANCE);
                // eightBallCompatiblePartThree = new
                // Trajectory(EIGHT_BALL_COMPATIBLE_PART_THREE, trajectoryConstraints,
                // SAMPLE_DISTANCE);
                // eightBallCompatiblePartFour = new Trajectory(EIGHT_BALL_COMPATIBLE_PART_FOUR,
                // trajectoryConstraints, SAMPLE_DISTANCE);

                // simpleShootThree = new Trajectory(
                // new SimplePathBuilder(Vector2.ZERO, Rotation2.ZERO)
                // .lineTo(new Vector2(40.0, 0.0))
                // .build(),
                // trajectoryConstraints, SAMPLE_DISTANCE
                // );
        }

        // private Path getPath(String name) throws IOException {
        // InputStream in = getClass().getClassLoader().getResourceAsStream(name);
        // if (in == null) {
        // throw new FileNotFoundException("Path file not found: " + name);
        // }

        // try (PathReader reader = new PathReader(new InputStreamReader(in))) {
        // return reader.read();
        // }
        // }

        public Trajectory farLeftAutoPartOne() {
                return farLeftAutoPartOne;
        }

        public Trajectory farLeftAutoPartTwo() {
                return farLeftAutoPartTwo;
        }

        public Trajectory twoMetersBack() {
                return twoMetersBack;
        }


}
