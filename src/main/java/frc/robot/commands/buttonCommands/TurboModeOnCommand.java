package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * Command to engage turbo (full speed) mode when needed
 */
public class TurboModeOnCommand extends CommandBase{
    private final DrivetrainSubsystem m_drivetrainSubsystem;

    /** 
     * Enable the turbo mode for a given drivetrain to allow
     * the robot to move at full power.
     * @param drivetrainSubsystem The drivetrain which will have turbo engaged
     */
    public TurboModeOnCommand(DrivetrainSubsystem drivetrainSubsystem) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(drivetrainSubsystem);
    }

    /**
     * While the command is running, enable the Turbo Mode
     */
    @Override
    public void execute() { 
        m_drivetrainSubsystem.changeTurboModeTrue();
    }

    
    /** 
     * Turn off the turbo mode when the command ends
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.changeTurboModeFalse();
    }
}
