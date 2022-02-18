package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;


public class GyroResetCommand extends CommandBase {
    private final DrivetrainSubsystem m_drivetrainSubsystem;
    
    

    public GyroResetCommand(DrivetrainSubsystem drivetrainSubsystem) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        m_drivetrainSubsystem.zeroGyroscope();
    }

    @Override
    public void end(boolean interrupted) {
    }
}
