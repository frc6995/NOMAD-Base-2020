package frc.lib.constants;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.lib.constants.DriveConstants;

/**
 * The constants for the Autonomous
 * 
 * @author Sammcdo, EliSauder, JoeyFabel, Shueja, AriShashivkopanazak
 */
public abstract class AutoConstants {
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
      public AutoConstants(DriveConstants drivebaseConstants){
            driveConstants = drivebaseConstants;
      }
      /**
       * The maximum acceleration, in m/s^2
       * @return The max acceleration
       */
      public abstract double getkMaxAccelerationMetersPerSecondSquared();
      public abstract double getkMaxSpeedMetersPerSecond();
      public abstract double getkRamseteB();
      public abstract double getkRamseteZeta();

      /**
       * The SimpleMotorFeedForward object for trajectory generation on the talon.
       */
      public SimpleMotorFeedforward getTrajectoryFeedForward() {
            return TRAJECTORY_FEED_FORWARD;
      }
      
      public DifferentialDriveVoltageConstraint getAutoVoltageConstraint() {
            return AUTO_VOLTAGE_CONSTRAINT;
      }
      
      public CentripetalAccelerationConstraint getAutoCentripetalConstraint() {
            return CENTRIPETAL_ACCELERATION_CONSTRAINT;
      }
      
      public TrajectoryConfig getTrajectoryConfig() {
            return TRAJECTORY_CONFIG;
      }
      
      public RamseteController getRamseteController() {
            return RAMSETE_CONTROLLER;
      }
}
