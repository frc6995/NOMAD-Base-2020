package frc.template.constants;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;

/**
 * The constants for the Autonomous
 * 
 * @author Sammcdo, EliSauder, JoeyFabel, Shueja, AriShashivkopanazak
 */
public class AutoConstantsKRen implements AutoConstants{

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

      public AutoConstantsKRen(final DriveConstants drivebaseConstants) {
            driveConstants = drivebaseConstants;
            //Create the objects to be returned.
            TRAJECTORY_FEED_FORWARD = new SimpleMotorFeedforward(
                  driveConstants.getKsVolts(), driveConstants.getKvVoltSecondsPerMeter(),
                  driveConstants.getKaVoltSecondsSquaredPerMeter());
            AUTO_VOLTAGE_CONSTRAINT = new DifferentialDriveVoltageConstraint(
                  getTrajectoryFeedForward(),
                  driveConstants.getDifferentialDriveKinematics(), 10);
            CENTRIPETAL_ACCELERATION_CONSTRAINT = new CentripetalAccelerationConstraint(2);
            TRAJECTORY_CONFIG = new TrajectoryConfig(
                  getkMaxSpeedMetersPerSecond(), getkMaxAccelerationMetersPerSecondSquared())
                              // Add kinematics to ensure max speed is actually obeyed
                              .setKinematics(driveConstants.getDifferentialDriveKinematics())
                              // Apply the voltage constraint
                              .addConstraint(getAutoVoltageConstraint()).addConstraint(getAutoCentripetalConstraint());
            RAMSETE_CONTROLLER = new RamseteController(getkRamseteB(),
            getkRamseteZeta());
            
      }
      @Override
      public double getkMaxAccelerationMetersPerSecondSquared() {
            return 0.5;
      }

      @Override
      public double getkMaxSpeedMetersPerSecond() {
            return 1;
      }

      @Override
      public double getkRamseteB() {
            return 2.0;
      }

      @Override
      public double getkRamseteZeta() {
            return 0.7;
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
