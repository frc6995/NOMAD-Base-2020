/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.wrappers.InputDevices;

import edu.wpi.first.wpilibj.Joystick;
import frc.utility.inputs.DriverJoystickProfile;

/**
 * A joystick wrapper specifically for the driver controller.
 * Provides convenience methods that get the correct axis/button value for the role (fwdBackAxis, etc), based on the passed in DriverJoystickProfile.
 */
public class NomadDriverJoystick extends Joystick {
    DriverJoystickProfile joystickProfile;
    public NomadDriverJoystick(DriverJoystickProfile profile){
        super(profile.usbPort);
        joystickProfile = profile;

    }

    public double getFwdBackAxis(){
        return getRawAxis(joystickProfile.fwdBackAxis);
    }

    public double getLeftRightAxis(){
        return getRawAxis(joystickProfile.leftRightAxis);
    }
}
