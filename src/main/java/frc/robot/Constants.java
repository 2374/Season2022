// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int LEFT_SHOOTER = 11;
    public static final int RIGHT_SHOOTER = 6;
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final int CONTROLLER_PORT = 0;

    public static final int LEFT_ARTICULATING_MOTOR = 17;
    public static final int RIGHT_ARTICULATING_MOTOR = 18;
    public static final int LEFT_FIXED_MOTOR = 15;
    public static final int RIGHT_FIXED_MOTOR = 16;

    public static final PneumaticsModuleType PCM1 = null;
    public static final PneumaticsModuleType PCM2 = null;
    public static final PneumaticsModuleType PCM = null;

    public static int FIXED_ARM_UPPER_LIMIT = 200;
    public static int FIXED_ARM_LOWER_LIMIT = 0;
    public static int ARTICULATING_ARM_UPPER_LIMIT = 200;
    public static int ARTICULATING_ARM_LOWER_LIMIT = 0;

	public static final double LIMELIGHT_TOLERANCE = 0.03;

    public static int FORWARD_CHANNEL = 2000;
    public static int REVERSE_CHANNEL = 2000;
}
