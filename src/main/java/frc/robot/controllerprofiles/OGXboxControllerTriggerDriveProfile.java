/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllerprofiles;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.utility.inputs.DriverControllerProfile;
import frc.robot.utility.math.NomadMathUtil;

/**
 * An example driver controller profile that shows 
 */
public class OGXboxControllerTriggerDriveProfile extends DriverControllerProfile {
    private final int usbPort = 0;
    private final int fwdBackAxis = 1; //TODO update with actual mappings
    private final int leftRightAxis = 0;
    private final int leftTriggerAxis = 4;
    private final int rightTriggerAxis = 5;
    @Override
    public double getFwdBackAxisValue(GenericHID controller){
        return NomadMathUtil.lerp(controller.getRawAxis(leftTriggerAxis), -1.0, 1.0, 0.0, 1.0) 
             - NomadMathUtil.lerp(controller.getRawAxis(rightTriggerAxis), -1.0, 1.0, 0.0, 1.0);
    }
    @Override
    public double getLeftRightAxisValue(GenericHID controller) { //For This controller, the left joystick x axis (ID 0) needs to be inverted.
        return -super.getLeftRightAxisValue(controller);
    }

    @Override
    public int getUsbPort() {
        return usbPort;
    }

    @Override
    public int getFwdBackAxisID() {
        return fwdBackAxis;
    }

    @Override
    public int getLeftRightAxisID() {
        return leftRightAxis;
    }
}
