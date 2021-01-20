// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.template.constants;

import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

/** Add your docs here. */
public class AutoConstantsDemoAuto extends AutoConstants {
    public AutoConstantsDemoAuto(DriveConstants driveConstants) {
        super(driveConstants);
        TRAJECTORY_FEED_FORWARD = new SimpleMotorFeedforward(
            driveConstants.getKsVolts(),
            driveConstants.getKvVoltSecondsPerMeter(),
            driveConstants.getKaVoltSecondsSquaredPerMeter());

        AUTO_VOLTAGE_CONSTRAINT =
        new DifferentialDriveVoltageConstraint(
            TRAJECTORY_FEED_FORWARD,
            driveConstants.getDifferentialDriveKinematics(),
            7);

    // Create config for trajectory
        TRAJECTORY_CONFIG =
        new TrajectoryConfig(
                getkMaxSpeedMetersPerSecond(),
                getkMaxAccelerationMetersPerSecondSquared())
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(driveConstants.getDifferentialDriveKinematics())
            // Apply the voltage constraint
            .addConstraint(AUTO_VOLTAGE_CONSTRAINT);

    // An example trajectory to follow.  All units in meters.
    
    }



    

    @Override
    public double getkMaxAccelerationMetersPerSecondSquared() {
        
        return 3;
    }

    @Override
    public double getkMaxSpeedMetersPerSecond() {
        
        return 3;
    }

    @Override
    public double getkRamseteB() {
        
        return 2;
    }

    @Override
    public double getkRamseteZeta() {
        
        return 0.7;
    }

}
