package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class UnDeployFixedArmsCommand extends CommandBase{
    private final ClimberSubsystem m_climberSubsystem;

    /** 
     * Turn on the indexer for a given climber subsystem for the length of
     * the command.
     * @param climberSubsystem Which climber subsystem should the indexer be engaged for?
     */
    public UnDeployFixedArmsCommand(ClimberSubsystem climberSubsystem) {
        this.m_climberSubsystem = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    /**
     * Turn on the indexer for the climber subsystem
     */
    @Override
    public void execute() {
        m_climberSubsystem.undeployFixedArms();
    }

    
    /** 
     * Turn the indexer off when the command has finished
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_climberSubsystem.stopFixedArms();
    }
}