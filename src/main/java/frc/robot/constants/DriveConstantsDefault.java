/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.constants;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * Add your docs here.
 */
public class DriveConstantsDefault extends DriveConstants {

    @Override
    public int getCanIDLeftDriveMaster() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCanIDRightDriveMaster() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCanIDLeftDriveFollower() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCanIDRightDriveFollower() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean getGyroReversed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double getEncoderCountsPerEncoderRevolution() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getEncoderCountsPerWheelRevolution() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getKsVolts() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getKvVoltSecondsPerMeter() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getKaVoltSecondsSquaredPerMeter() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getkWheelDiameter() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getkPDriveVel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getkPDriveVelLeft() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getkPDriveVelRight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getkTrackWidthMeters() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public DifferentialDriveKinematics getDifferentialDriveKinematics() {
        // TODO Auto-generated method stub
        return new DifferentialDriveKinematics(getkTrackWidthMeters());
    }
}
