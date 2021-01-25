// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.template.constants;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpiutil.math.VecBuilder;
import edu.wpi.first.wpiutil.math.Vector;
import edu.wpi.first.wpiutil.math.numbers.N7;
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

        return 10;
    }

    @Override
    public int getCanIDRightDriveMaster() {

        return 11;
    }

    @Override
    public int getCanIDLeftDriveFollower() {

        return 12;
    }

    @Override
    public int getCanIDRightDriveFollower() {

        return 13;
    }

    @Override
    public boolean getGyroReversed() {

        return true;
    }

    @Override
    public double getEncoderCountsPerEncoderRevolution() {

        return 8192;
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
    public double getKvVoltSecondsPerRadian() {

        return 1.5;
    }

    @Override
    public double getKaVoltSecondsSquaredPerRadian() {

        return 0.3;
    }

    @Override
    public double getkWheelDiameter() {

        return Units.inchesToMeters(6);
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
    public DCMotor getDriveGearbox() {

        return DCMotor.getCIM(2);
    }

    @Override
    public int[] getLeftEncoderPorts() {
        int[] ports = { 0, 1 };
        return ports;
    }

    @Override
    public int[] getRightEncoderPorts() {
        int[] ports = { 2, 3 };
        return ports;
    }

    @Override
    public double getDriveGearingRatio() {
        return 7.29;
    }

    @Override
    public Vector<N7> getSimEncoderStdDev() {
        // The standard deviations for measurement noise:
        // x and y: 0.001 m
        // heading: 0.001 rad
        // l and r velocity: 0.1 m/s
        // l and r position: 0.005 m
        return VecBuilder.fill(0.0001, 0.0001, 0.0001, 0.01, 0.01, 0.0005, 0.0005);
    }
}
