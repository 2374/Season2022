// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.InteractiveDriveCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
// import frc.robot.subsystems.CanonSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final InteractiveDriveCommand drivetrainCommand = new InteractiveDriveCommand(m_shooterSubsystem, m_climberSubsystem);

  /*
  jdhdajvjdkjdhfduiuceiygiufryugfeyugdsnjfdsakjjdasdsajdksalkfekisldalansfdoankjffsugsnvjsndoijakjdkjskjhdsakjhsjqwkjshdaoiupwmjoidadlksafcm fknfdabchsanbjalkdsn
  ksdkjsbassandadlksalidhlksndlohsalbhdsajndlkhadsknsadhsa fdsnfkns,mfdnlkdsnf
  lihfslkns
  lhsadjsad
  d
  am hecker
  watch meh
  /e *boom*
  see
  told ye
  am hecker
  de supreme hecker
  best in da wurld
  none come close to meh
  obserbve
  /e *boom*
  /e *boom*
  /e *boom*
  /e *boom*
  /e *boom*
  b0w d0wn bef0re me4
  f0r am supr3m3 43ck3r
  d3str0y3r 0f c0mput3rs
  while (true) {
    /e
  }
  */
  
  //private final DrivetrainCommand m_idriveCommand = new DrivetrainCommand(m_drivetrainSubsystem);

  // private final DriveCommand m_driveCommand = new DriveCommand(m_drivetrainSubsystem);
  
  private XboxController xbox = new XboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_shooterSubsystem.setDefaultCommand(drivetrainCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // System.out.println("ROSS init buttons");
    //  new JoystickButton(xbox, 1).whenPressed(new InstantCommand(() -> m_canonSubsystem.Fire()));
    //  new JoystickButton(xbox, 5).whenPressed(new InstantCommand(() -> m_canonSubsystem.Elevate()));
    //  new JoystickButton(xbox, 6).whenPressed(new InstantCommand(() -> m_canonSubsystem.Depress()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return drivetrainCommand;
  }
}
