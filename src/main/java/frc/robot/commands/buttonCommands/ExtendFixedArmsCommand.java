package frc.robot.commands.buttonCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ExtendFixedArmsCommand extends CommandBase{
    private final ClimberSubsystem m_climberSubsystem;

    /** 
     * Turn on the climber for a given climber subsystem for the length of
     * the command.
     * @param climberSubsystem Which climber subsystem should the climber be engaged for?
     */
    public ExtendFixedArmsCommand(ClimberSubsystem climberSubsystem) {
        this.m_climberSubsystem = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    /**
     * Turn on the climber for the climber subsystem
     */
    @Override
    public void execute() {
        m_climberSubsystem.extendFixedArms();
    }

    
    /** 
     * Turn the climber off when the command has finished
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
        m_climberSubsystem.stopFixedArms();
    }
}
