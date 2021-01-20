// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.template.constants;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import frc.lib.constants.DriveConstants;

/** Add your docs here. */
public class DriveConstantsDemoAuto extends DriveConstants {

    @Override
    public int getDriveControllerFwdBackAxis() {
        
        return 33;
    }

    @Override
    public int getDriveControllerLeftRightAxis() {
        
        return 34;
    }

    @Override
    public int getCanIDLeftDriveMaster() {
        
        return 0;
    }

    @Override
    public int getCanIDRightDriveMaster() {
        
        return 2;
    }

    @Override
    public int getCanIDLeftDriveFollower() {
        
        return 1;
    }

    @Override
    public int getCanIDRightDriveFollower() {
        
        return 3;
    }

    @Override
    public boolean getGyroReversed() {
        
        return true;
    }

    @Override
    public double getEncoderCountsPerEncoderRevolution() {
        
        return 1024;
    }

    @Override
    public double getKsVolts() {
        
        return 0.22;
    }

    @Override
    public double getKvVoltSecondsPerMeter() {
        
        return 1.98;
    }

    @Override
    public double getKaVoltSecondsSquaredPerMeter() {
        
        return 0.2;
    }

    @Override
    public double getkWheelDiameter() {
        
        return 0.15;
    }

    @Override
    public double getkPDriveVel() {
        
        return 8.5;
    }

    @Override
    public double getkPDriveVelLeft() {
        
        return 8.5;
    }

    @Override
    public double getkPDriveVelRight() {
        
        return 8.5;
    }

    @Override
    public double getkTrackWidthMeters() {
        
        return 0.69;
    }

    @Override
    public boolean getLeftEncoderReversed() {
        
        return false;
    }

    @Override
    public boolean getRightEncoderReversed() {
        
        return true;
    }

    @Override
    public double getEncoderRevolutionsPerWheelRevolution() {
        
        return 8;
    }

    @Override
    public double getKvVoltSecondsPerRadian() {
        
        return 1.5;
    }

    @Override
    public double getKaVoltSecondsSquaredPerRadian() {
        
        return 0.3;
    }

    @Override
    public DCMotor getDriveGearbox() {
        
        return DCMotor.getCIM(2);
    }
}
