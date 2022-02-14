// Jacob was here (Hello there, General Kenobi!)
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// when i started this project, only God and I knew what we were doing.
// now, only I know

package frc.robot;

import java.io.IOException;

//it's a me, a Mario 
// I am vengeance
import edu.wpi.first.wpilibj.GenericHID;
// I'm not coming back for 30%, I'm coming back for everything!
// For Gotham!
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
//DOWNLOAD THE MUSTACHE SYMBOL
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
//beautiful
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
//put a marker through it
import frc.robot.subsystems.SpinnySubsystem;
import frc.robot.util.AutonomousChooser;
import frc.robot.util.AutonomousTrajectories;
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final SpinnySubsystem m_spinnySubsystem = new SpinnySubsystem();

  // When Leonardo DiCaprio can't fit through a door:(

  private static XboxController xbox = new XboxController(Constants.XBOX_CONTROLLER_PORT);
  private static XboxController beanspeed = new XboxController(Constants.XBOX_2_CONTROLLER_PORT); //at the speed of gene

  private AutonomousTrajectories autonomousTrajectories;
  private final AutonomousChooser autonomousChooser;

  //so... I was bored
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  //greatest statement of all time
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
    //hello Mithra Karamaehcduo
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(xbox.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(xbox.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(xbox.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));
//headsmash *gftrgftrg*


    // Configure the button bindings
    configureButtonBindings();
  }

  
private boolean getBeanspeedLeftTrigger() {
  return beanspeed.getLeftTriggerAxis() > 0.05;
}
private boolean getBeanspeedRightTrigger() {
  return beanspeed.getRightTriggerAxis() > 0.05;
}
//do you organize your apps by color?
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  // don't believe me, I will
  // On your right

  private void configureButtonBindings() {

    // Back Button zeroes the gyroscope
    new Button(xbox::getBackButton).whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    
    
    /* 
    new Button(beanspeed::getYButton).whenPressed(m_climberSubsystem::extendFixedArms);
    new Button(beanspeed::getBButton).whenPressed(m_climberSubsystem::extendArticulatingArms);
    new Button(beanspeed::getAButton).whenPressed(m_climberSubsystem::retractArticulatingArms);
    new Button(beanspeed::getXButton).whenPressed(m_climberSubsystem::retractFixedArms);
    new Button(beanspeed::getLeftBumper).whenPressed(m_spinnySubsystem::spinToTarget);
    new Button(beanspeed::getRightBumper).whenPressed(m_shooterSubsystem::shoot);
    new Button(this::getBeanspeedLeftTrigger).whenPressed(m_spinnySubsystem::rotateLeft);
    new Button(this::getBeanspeedRightTrigger).whenPressed(m_spinnySubsystem::rotateRight);
    new Button(xbox::getBButton).whenPressed(m_intakeSubsystem::start);
    new Button(xbox::getAButton).whenPressed(m_intakeSubsystem::retractPistons);
    new Button(xbox::getYButton).whenPressed(m_intakeSubsystem::extendPistons); 
    */
  }
//it's to improve your social skills

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
    if (xbox.getRightBumper()) {
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