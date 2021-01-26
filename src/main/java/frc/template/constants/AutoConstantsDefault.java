/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.template.constants;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;

/**
 * Add your docs here.
 */
public class AutoConstantsDefault implements AutoConstants {
        /**
       * The SimpleMotorFeedForward for the drivebase gearboxes.
       */
      protected SimpleMotorFeedforward TRAJECTORY_FEED_FORWARD;
      /**
       * The voltage constraint for the drive motors.
       */
      protected DifferentialDriveVoltageConstraint AUTO_VOLTAGE_CONSTRAINT;
      /**
       * The centripetal acceleration constraint for the drive motors.
       */
      protected CentripetalAccelerationConstraint CENTRIPETAL_ACCELERATION_CONSTRAINT;
      /**
       * The trajectory following config for the drivebase
       */
      protected TrajectoryConfig TRAJECTORY_CONFIG;
      /**
       * The Ramsete controller for trajectory following.
       */
      protected RamseteController RAMSETE_CONTROLLER;
      /**
       * The DriveConstants to provide info about the drivebase.
       */
      protected DriveConstants driveConstants;

      /**
       * Creates a new AutoConstants class
       * @param drivebaseConstants The drive constants to use.
       */

    public AutoConstantsDefault(DriveConstants drivebaseConstants) {
        // TODO Auto-generated constructor stub
        driveConstants = drivebaseConstants;
    }

    @Override
    public double getkMaxAccelerationMetersPerSecondSquared() {

        return 0;
    }

    @Override
    public double getkMaxSpeedMetersPerSecond() {

        return 0;
    }

    @Override
    public double getkRamseteB() {

        return 0;
    }

    @Override
    public double getkRamseteZeta() {

        return 0;
    }

    @Override
    public SimpleMotorFeedforward getTrajectoryFeedForward() {
        // TODO Auto-generated method stub
        return TRAJECTORY_FEED_FORWARD;
    }

    @Override
    public DifferentialDriveVoltageConstraint getAutoVoltageConstraint() {
        // TODO Auto-generated method stub
        return AUTO_VOLTAGE_CONSTRAINT;
    }

    @Override
    public CentripetalAccelerationConstraint getAutoCentripetalConstraint() {
        // TODO Auto-generated method stub
        return CENTRIPETAL_ACCELERATION_CONSTRAINT;
    }

    @Override
    public TrajectoryConfig getTrajectoryConfig() {
        // TODO Auto-generated method stub
        return TRAJECTORY_CONFIG;
    }

    @Override
    public RamseteController getRamseteController() {
        // TODO Auto-generated method stub
        return RAMSETE_CONTROLLER;
    }
}
