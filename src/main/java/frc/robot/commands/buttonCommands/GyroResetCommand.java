package frc.robot.commands.buttonCommands;

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
        m_drivetrainSubsystem.zeroGyroscope();
        cancel();
    }

    @Override
    public void end(boolean interrupted) {
    }
}
