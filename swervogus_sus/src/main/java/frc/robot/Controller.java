// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
 
import edu.wpi.first.wpilibj.Joystick;


/** Add your docs here. */
public class Controller {
    private Joystick controller;
    public Controller() {
        
        this.controller = new Joystick(Constants.CONTROLLER_PORT);
    }
   /* public double getJoystickLeftX() { return deadZone(controller.getRawAxis(Constants.CONTROLLER_LEFT_AXIS_X), 0.3); }
     
    public double getJoystickLeftY() { return deadZone(controller.getRawAxis(Constants.CONTROLLER_LEFT_AXIS_Y), 0.3); }

    public double getJoystickRightX() { return deadZone(controller.getRawAxis(Constants.CONTROLLER_RIGHT_AXIS_X), 0.5); }
    
    public double getJoystickRightY() { return deadZone(controller.getRawAxis(Constants.CONTROLLER_RIGHT_AXIS_Y), 0.3); }

    public double getLeftTrigger() { return deadZone(controller.getRawAxis(Constants.CONTROLLER_LEFT_TRIGGER), 0.5); }

    public double getRightTrigger() { return deadZone(controller.getRawAxis(Constants.CONTROLLER_RIGHT_TRIGGER), 0.5); }
    
    public boolean getLeftBumper() { return controller.getRawButton(Constants.CONTROLLER_LEFT_BUMPER); }

    public boolean getRightBumper() { return controller.getRawButton(Constants.CONTROLLER_RIGHT_BUMPER); }

    public boolean getButtonA() { return controller.getRawButton(Constants.CONTROLLER_BUTTON_A); }
    
    public boolean getButtonB() { return controller.getRawButton(Constants.CONTROLLER_BUTTON_B); }

    public boolean getButtonX() { return controller.getRawButton(Constants.CONTROLLER_BUTTON_X); }

    public boolean getButtonY() { return controller.getRawButton(Constants.CONTROLLER_BUTTON_Y); }

    public boolean getButtonBack() { return controller.getRawButton(Constants.CONTROLLER_BUTTON_BACK); }
    
    public boolean getButtonStart() { return controller.getRawButton(Constants.CONTROLLER_BUTTON_START); }

    public boolean getButtonM1() { return controller.getRawButton(Constants.CONTROLLER_BUTTON_M1); }

    public boolean getButtonM2() { return controller.getRawButton(Constants.CONTROLLER_BUTTON_M2); }
    
    public Joystick getController() { return controller; }

    public static double deadZone(double axisValue, double deadValue) {
        return axisValue;
    */

}
