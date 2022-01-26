package frc.robot.subsystems;

import java.util.Objects;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.common.drivers.Gyroscope;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.common.drivers.SwerveModule;
import frc.robot.common.math.Rotation2;
import frc.robot.common.math.Vector2;
import frc.robot.common.robot.drivers.SwerveModuleMK3;
import frc.robot.Constants;
import frc.robot.commands.DrivetrainCommand;

public class DrivetrainSubsystem extends SubsystemBase {
    private static final double TRACKWIDTH = 19.5;
    private static final double WHEELBASE = 23.5;

    private static final double FRONT_LEFT_ANGLE_OFFSET = -Math.toRadians(0.0);
    private static final double FRONT_RIGHT_ANGLE_OFFSET = -Math.toRadians(0.0);
    private static final double BACK_LEFT_ANGLE_OFFSET = -Math.toRadians(0.0);
    private static final double BACK_RIGHT_ANGLE_OFFSET = -Math.toRadians(0.0);

    private TalonFX frontLeftMotor = new TalonFX(Constants.FRONT_LEFT_DRIVE_MOTOR);
    private TalonFX frontRightMotor = new TalonFX(Constants.FRONT_RIGHT_DRIVE_MOTOR);
    private TalonFX backLeftMotor = new TalonFX(Constants.BACK_LEFT_DRIVE_MOTOR);
    private TalonFX backRightMotor = new TalonFX(Constants.BACK_RIGHT_DRIVE_MOTOR);

    private TalonFX frontLeftAngle = new TalonFX(Constants.FRONT_LEFT_ANGLE_MOTOR);
    private TalonFX frontRightAngle = new TalonFX(Constants.FRONT_RIGHT_ANGLE_MOTOR);
    private TalonFX backLeftAngle = new TalonFX(Constants.BACK_LEFT_ANGLE_MOTOR);
    private TalonFX backRightAngle = new TalonFX(Constants.BACK_RIGHT_ANGLE_MOTOR);

    public static double kMaxSpeed = 10;

    private static DrivetrainSubsystem instance;

    private final SwerveModuleMK3 frontLeftModule = new SwerveModuleMK3(frontLeftMotor, frontLeftAngle, new CANCoder(Constants.FRONT_LEFT_CANCODER), new Rotation2d());
    private final SwerveModuleMK3 frontRightModule = new SwerveModuleMK3(frontRightMotor, frontRightAngle, new CANCoder(Constants.FRONT_RIGHT_CANCODER), new Rotation2d());
    private final SwerveModuleMK3 backLeftModule = new SwerveModuleMK3(backLeftMotor, backLeftAngle, new CANCoder(Constants.BACK_LEFT_CANCODER), new Rotation2d());
    private final SwerveModuleMK3 backRightModule = new SwerveModuleMK3(backRightMotor, backRightAngle, new CANCoder(Constants.BACK_RIGHT_CANCODER), new Rotation2d());

    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
            new Translation2d(TRACKWIDTH / 2.0, WHEELBASE / 2.0),
            new Translation2d(TRACKWIDTH / 2.0, -WHEELBASE / 2.0),
            new Translation2d(-TRACKWIDTH / 2.0, WHEELBASE / 2.0),
            new Translation2d(-TRACKWIDTH / 2.0, -WHEELBASE / 2.0)
    );

    private final AHRS ahrs = new AHRS(SPI.Port.kMXP);

    public DrivetrainSubsystem() {
        ahrs.calibrate();
        //ahrs.setInverted(true); // You might not need to invert the gyro

        // frontLeftModule.setName("Front Left");
        // frontRightModule.setName("Front Right");
        // backLeftModule.setName("Back Left");
        // backRightModule.setName("Back Right");
    }

    public static DrivetrainSubsystem getInstance() {
        if (instance == null) {
            instance = new DrivetrainSubsystem();
        }

        return instance;
    }

    @Override
    public void periodic() {
        // frontLeftModule.updateSensors();
        // frontRightModule.updateSensors();
        // backLeftModule.updateSensors();
        // backRightModule.updateSensors();

        SmartDashboard.putNumber("Front Left Module Angle", frontLeftModule.getAngle().getDegrees());
        SmartDashboard.putNumber("Front Right Module Angle", frontRightModule.getAngle().getDegrees());
        SmartDashboard.putNumber("Back Left Module Angle", backLeftModule.getAngle().getDegrees());
        SmartDashboard.putNumber("Back Right Module Angle", backRightModule.getAngle().getDegrees());

        SmartDashboard.putNumber("Gyroscope Angle", ahrs.getAngle());

        // frontLeftModule.set(TimedRobot.kDefaultPeriod);
        // frontRightModule.updateState(TimedRobot.kDefaultPeriod);
        // backLeftModule.updateState(TimedRobot.kDefaultPeriod);
        // backRightModule.updateState(TimedRobot.kDefaultPeriod);
    }

    public void drive(Translation2d translation, double rotation, boolean fieldOriented) {
        rotation *= 2.0 / Math.hypot(WHEELBASE, TRACKWIDTH);
        ChassisSpeeds speeds;
        if (fieldOriented) {
            speeds = ChassisSpeeds.fromFieldRelativeSpeeds(translation.getX(), translation.getY(), rotation,
                    Rotation2d.fromDegrees(ahrs.getAngle()));
        } else {
            speeds = new ChassisSpeeds(translation.getX(), translation.getY(), rotation);
        }

        SwerveModuleState[] states = kinematics.toSwerveModuleStates(speeds);
        frontLeftModule.setDesiredState(states[0]);
        frontRightModule.setDesiredState(states[1]);
        backLeftModule.setDesiredState(states[2]);
        backRightModule.setDesiredState(states[3]);
    }

    public void resetGyroscope() {
        ahrs.setAngleAdjustment(ahrs.getAngle());
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new DrivetrainCommand(new DrivetrainSubsystem()));
    }
}