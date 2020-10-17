/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllerprofiles;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.utility.inputs.DriverControllerProfile;

/**
 * An example driver controller profile that shows 
 */
public class XboxControllerTriggerDriveProfile extends DriverControllerProfile {
    public int usbPort = 0;
    public int fwdBackAxis = 1; //TODO update with actual mappings
    public int leftRightAxis = 2;
    public int leftTriggerAxis = 3;
    public int rightTriggerAxis = 4;
    public double customFwdBackAxis(GenericHID controller){
        return controller.getRawAxis(leftTriggerAxis) - controller.getRawAxis(rightTriggerAxis);
    }
}
