// Jacob was here (Hello there, General Kenobi!)
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.util.AutonomousChooser;
import frc.robot.util.AutonomousTrajectories;
import frc.robot.commands.buttonCommands.*;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final TurretSubsystem m_turretSubsystem = new TurretSubsystem();

  private static XboxController m_controller = new XboxController(Constants.XBOX_CONTROLLER_PORT);
  private static XboxController m_ord = new XboxController(Constants.XBOX_2_CONTROLLER_PORT); //at the speed of gene

  private AutonomousTrajectories autonomousTrajectories;
  private final AutonomousChooser autonomousChooser;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    try {
      autonomousTrajectories = new AutonomousTrajectories(DrivetrainSubsystem.TRAJECTORY_CONSTRAINTS);
    } catch (IOException e) {
      e.printStackTrace();
    }
    autonomousChooser = new AutonomousChooser(autonomousTrajectories);

    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(m_controller.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_controller.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_ord.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));

    // Configure the button bindings
    configureButtonBindings();
  }

  
// private boolean getOrdLeftTrigger() {
//   return m_ord.getLeftTriggerAxis() > 0.05;
// }
// private boolean getOrdRightTrigger() {
//   return m_ord.getRightTriggerAxis() > 0.05;
// }
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {

    // Back Button zeroes the gyroscope
    //  m_controller.getBackButton().whenPressed(()->m_drivetrainSubsystem.zeroGyroscope());
    new JoystickButton(m_controller, Constants.CONTROLLER_BACK_BUTTON_ID).whenPressed(new GyroResetCommand(m_drivetrainSubsystem));
    // new Button(m_controller::getAButtonPressed).whenPressed(m_drivetrainSubsystem::changeTurboModeTrue);
    new JoystickButton(m_controller, Constants.CONTROLLER_A_BUTTON_ID).whenHeld(new TurboModeOnCommand(m_drivetrainSubsystem));
    // new Button(m_controller::getAButtonReleased).whenReleased(m_drivetrainSubsystem::changeTurboModeFalse);
    // new Button(m_ord::getXButtonPressed).whenPressed(m_turretSubsystem::rotateLeft);
    new JoystickButton(m_ord, Constants.CONTROLLER_X_BUTTON_ID).whenHeld(new RotateLeftCommand(m_turretSubsystem));
    // new Button(m_ord::getXButtonReleased).whenReleased(m_turretSubsystem::rotateStop);
    // new Button(m_ord::getBButtonPressed).whenPressed(m_turretSubsystem::rotateRight);
    new JoystickButton(m_ord, Constants.CONTROLLER_B_BUTTON_ID).whenHeld(new RotateRightCommand(m_turretSubsystem));
    // new Button(m_ord::getBButtonReleased).whenReleased(m_turretSubsystem::rotateStop);
    new JoystickButton(m_ord, Constants.CONTROLLER_A_BUTTON_ID).whenHeld(new IntakeOnCommand(m_intakeSubsystem));
    new JoystickButton(m_ord, Constants.CONTROLLER_Y_BUTTON_ID).whenHeld(new IndexOnCommand(m_shooterSubsystem));
    new JoystickButton(m_ord, Constants.CONTROLLER_RIGHT_BUMPER_ID).whenHeld(new ShootCommand(m_shooterSubsystem));
    new JoystickButton(m_ord, Constants.CONTROLLER_LEFT_BUMPER_ID).whenHeld(new FullShootCommand(m_shooterSubsystem, m_intakeSubsystem));
    new JoystickButton(m_ord, Constants.CONTROLLER_START_BUTTON_ID).whenPressed(new SpinToTargetCommand(m_turretSubsystem));

    // new Button(m_ord::getAButtonPressed).whenPressed(m_turretSubsystem::spinToTarget);
    // new Button(m_ord::getRightBumperPressed).whenPressed(m_shooterSubsystem::shoot);
    // new Button(m_ord::getRightBumperReleased).whenReleased(m_shooterSubsystem::stop);
    /*
    new Button(m_ord::getYButton).whenPressed(m_climberSubsystem::extendFixedArms);
    new Button(m_ord::getBButton).whenPressed(m_climberSubsystem::extendArticulatingArms);
    new Button(m_ord::getAButton).whenPressed(m_climberSubsystem::retractArticulatingArms);
    new Button(m_ord::getXButton).whenPressed(m_climberSubsystem::retractFixedArms);
    new Button(m_controller::getBButton).whenPressed(m_intakeSubsystem::start);
    new Button(m_controller::getAButton).whenPressed(m_intakeSubsystem::retractPistons);
    new Button(m_controller::getYButton).whenPressed(m_intakeSubsystem::extendPistons); 
    */
  }

public void executeAutoCommands(){

}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonomousChooser.getCommand(this);
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);
    
    // TURBO MODE!
    if (m_controller.getRightBumper()) {
      return value;
    } else {
      return 0.5*value;
    }
  }

  public DrivetrainSubsystem getDrivetrainSubsystem() {
    return m_drivetrainSubsystem;
  }

  public AutonomousChooser getAutonomousChooser() {
    return autonomousChooser;
  }

}