/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.wrappers.InputDevices;

import java.util.function.DoubleConsumer;

import edu.wpi.first.wpilibj.GenericHID;

import frc.robot.utility.inputs.DriverControllerProfile;

/**
 * A controller wrapper specifically for the driver controller.
 * Provides convenience methods that get the correct axis/button value for the role (fwdBackAxis, etc), based on the passed in DrivercontrollerProfile.
 */
public class NomadDriverController extends GenericHID {
    /**
     * The DriverControllerProfile for the driver controller in use.
     */
    DriverControllerProfile controllerProfile;

    /**
     * Creates a new GenericHID on the specified profile's USB port.
     * @param profile the DriverControllerProfile for the driver controller in use.
     */
    public NomadDriverController(DriverControllerProfile profile){
        super(profile.usbPort);
        controllerProfile = profile;

    }

    /**
     * Asks the controller profile for the result of its custom defined behavior. By default, this is simply getRawAxis on the corresponding axis, but can be overridden.
     * @return the result of the controller profile's customFwdBackAxis method.
     */
    public double getFwdBackAxis(){
        return controllerProfile.customFwdBackAxis(this);
    }

    /**
     * Asks the controller profile for the result of its custom defined behavior. By default, this is simply getRawAxis on the corresponding axis, but can be overridden.
     * @return the result of the controller profile's customLeftRightAxis method.
     */
    public double getLeftRightAxis(){
        return controllerProfile.customLeftRightAxis(this);
    }

    /**
     * DO NOT USE!
     */
    @Override
    public double getX(Hand hand) {
        return 0;
    }
    /**
     * DO NOT USE!
     */
    @Override
    public double getY(Hand hand) {
        return 0;
    }

}
