package frc.template.constants;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.system.LinearSystem;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.system.plant.LinearSystemId;
import edu.wpi.first.wpiutil.math.Vector;
import edu.wpi.first.wpiutil.math.numbers.N2;
import edu.wpi.first.wpiutil.math.numbers.N7;
import frc.lib.constants.DriveConstants;

/**
 * Drive Constants for our 2020 bot KRen
 */
public final class DriveConstantsKRen implements DriveConstants {
    /**
     * The DifferentialDriveKinematics for the drivebase, based on the track width.
     */
    protected DifferentialDriveKinematics kDifferentialDriveKinematics = new DifferentialDriveKinematics(getkTrackWidthMeters());
    /**
     * The drivetrain model, based on the characterization constants.
     */
    protected LinearSystem<N2, N2, N2> kDrivetrainPlant = 
        LinearSystemId.identifyDrivetrainSystem(
        getKvVoltSecondsPerMeter(),
        getKaVoltSecondsSquaredPerMeter(),
        getKvVoltSecondsPerRadian(),
        getKaVoltSecondsSquaredPerRadian());
    @Override
    /** The CAN ID for the left master motor controller. */
    public int getCanIDLeftDriveMaster() {
        return 10;
    }

    @Override
    /** The CAN ID for the right master motor controller. */
    public int getCanIDRightDriveMaster() {
        return 11;
    }

    @Override
    public int getCanIDLeftDriveFollower() {
        return 13;
    }

    @Override
    public int getCanIDRightDriveFollower() {
        return 12;
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
    public double getEncoderCountsPerWheelRevolution() {

        return getEncoderCountsPerEncoderRevolution() * 7.0 / 3.0;
    }

    @Override
    public double getKsVolts() {

        return 1.26;
    }

    @Override
    public double getKvVoltSecondsPerMeter() {
        return 2.19;
    }

    @Override
    public double getKaVoltSecondsSquaredPerMeter() {
        return 0.683;
    }

    @Override
    public double getkWheelDiameter() {
        return 0.1524;
    }

    @Override
    public double getkPDriveVel() {
        return 0.0405;
    }

    @Override
    public double getkPDriveVelLeft() {
        return 0.0405;
    }

    @Override
    public double getkPDriveVelRight() {
        return 0.0405;
    }

    @Override
    public double getkTrackWidthMeters() {
        return 0.6032;
    }

    @Override
    public DifferentialDriveKinematics getDifferentialDriveKinematics() {
        return kDifferentialDriveKinematics;
    }

    @Override
    public int getDriveControllerFwdBackAxis() {

        return 33;
    }

    @Override
    public int getDriveControllerLeftRightAxis() {

        return 34;
    }

    @Override
    public boolean getLeftEncoderReversed() {

        return false;
    }

    @Override
    public boolean getRightEncoderReversed() {

        return false;
    }

    @Override
    public double getEncoderRevolutionsPerWheelRevolution() {

        return 0;
    }

    @Override
    public double getKvVoltSecondsPerRadian() {

        return 0;
    }

    @Override
    public double getKaVoltSecondsSquaredPerRadian() {

        return 0;
    }

    @Override
    public DCMotor getDriveGearbox() {

        return DCMotor.getCIM(2);
    }

    @Override
    public int[] getLeftEncoderPorts() {
        return null;
    }

    @Override
    public int[] getRightEncoderPorts() {
        return null;
    }

    @Override
    public Vector<N7> getSimEncoderStdDev() {
        return null;
    }

    @Override
    public double getEncoderDistancePerPulse() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public LinearSystem<N2, N2, N2> getDrivetrainPlant() {
        // TODO Auto-generated method stub
        return kDrivetrainPlant;
    }

    @Override
    public int getDriveControllerFwdBackAxisMultiplier() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public int getDriveControllerLeftRightAxisMultiplier() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public boolean getDrivebaseRightSideInverted() {
        // TODO Auto-generated method stub
        return false;
    }
}
