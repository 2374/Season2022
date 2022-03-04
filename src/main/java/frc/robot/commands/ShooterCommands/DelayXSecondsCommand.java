package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Fire a ball at an acquired target using a combination of turret/indexer/shooter subsystems
 */
public class DelayXSecondsCommand extends CommandBase{
    private int m_numberOfSeconds;
    /** 
     * Delay a given number of seconds
     * @param numberOfSeconds
     */
    public DelayXSecondsCommand(int numberOfSeconds){
       m_numberOfSeconds = numberOfSeconds;
    }

    /**
     * hang out for numberOfSeconds
     */
    @Override
    public void execute() {
        // use a wait() because it can be interrupted if needbe
        try {
            wait(1000*m_numberOfSeconds);
        } catch (InterruptedException e) {
            //  Auto-generated catch block
            e.printStackTrace();
        }
        // long sTime1 = System.currentTimeMillis();
        // while ((sTime1 + 1000*m_numberOfSeconds) > System.currentTimeMillis()) { }
    }

    
    /** 
     * Do nothing
     * @param interrupted
     */
    @Override
    public void end(boolean interrupted) {
    }
}
