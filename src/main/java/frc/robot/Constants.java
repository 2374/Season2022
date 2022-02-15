// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

 // The only constant is change
public final class Constants {

    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.52705; // NAV Measure and set trackwidth
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.46355; // NAV Measure and set wheelbase



    public static final double DRIVESPEED_MODIFIER = 0.5;
    public static final double PXCONTROLLER = 1.0;
    public static final double PYCONTROLLER = 1.0;
    public static final double PTHETACONTROLLER = 1.0;
    public static final TrapezoidProfile.Constraints PTHETACONTROLLERCONSTRAINTS =
       new TrapezoidProfile.Constraints(DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, 
       DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND);

    public static final int DRIVETRAIN_PIGEON_ID = 0; // NAV Set Pigeon ID

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 1; // NAV Set front left module drive motor ID
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 11; // NAV Set front left module steer motor ID
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 21; // NAV Set front left steer encoder ID
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(-170.155906); // NAV Measure and set front left steer offset

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 2; // NAV Set front right drive motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 12; // NAV Set front right steer motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 22; // NAV Set front right steer encoder ID
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(35.156179); // NAV Measure and set front right steer offset

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 3; // NAV Set back left drive motor ID
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 13; // NAV Set back left steer motor ID
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 23; // NAV Set back left steer encoder ID
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(125.859121); // NAV Measure and set back left steer offset

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 4; // NAV Set back right drive motor ID
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 14; // NAV Set back right steer motor ID
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 24; // NAV Set back right steer encoder ID
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(-25.751901); // NAV Measure and set back right steer offset

    public static final int LEFT_SHOOTER = 5;
    public static final int RIGHT_SHOOTER = 6;                                                                                                                                                    
    public static final int INTAKE_MOTOR = 7;
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final int XBOX_2_CONTROLLER_PORT = 1;

    public static final int LEFT_FIXED_MOTOR = 15;
    public static final int RIGHT_FIXED_MOTOR = 16;
    public static final int LEFT_ARTICULATING_MOTOR = 17;
    public static final int RIGHT_ARTICULATING_MOTOR = 18;

    public static final int TOP_INDEX_MOTOR = 19;
    public static final int BOTTOM_INDEX_MOTOR = 20;

    public static final PneumaticsModuleType PCM1 = null;
    public static final PneumaticsModuleType PCM2 = null;
    public static final PneumaticsModuleType PCM = null;

    public static int FIXED_ARM_UPPER_LIMIT = 24576;
    public static int FIXED_ARM_LOWER_LIMIT = 0;
    public static int ARTICULATING_ARM_UPPER_LIMIT = 24576;
    public static int ARTICULATING_ARM_LOWER_LIMIT = 0;

	public static final double LIMELIGHT_TOLERANCE = 0.03;
    public static final PneumaticsModuleType PCM_INTAKE = null;
    public static final int SPINNY_MOTOR = 8;

    public static int FORWARD_CHANNEL = 2000;
    public static int REVERSE_CHANNEL = 2000;
}
