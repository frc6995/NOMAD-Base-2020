/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickC;
import frc.robot.constants.AutoConstants;
import frc.robot.constants.AutoConstantsKRen;
import frc.robot.constants.AutoConstantsSim;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.DriveConstantsKRen;
import frc.robot.constants.DriveConstantsSim;
import frc.robot.controllerprofiles.OGXboxControllerTriggerDriveProfile;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.wrappers.inputdevices.NomadDriverController;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Constants Files
  //Subsystems
  private DrivebaseS drivebaseS;

  private DriveConstants driveConstants;
  private AutoConstants autoConstants;
  //Commands
  private DrivebaseArcadeDriveStickC drivebaseArcadeDriveStickC;
  //private ControllerProfile driverUsb0ControllerProfile = new ControllerProfile(0);
  //Controller Profiles
  private OGXboxControllerTriggerDriveProfile ogXboxControllerTriggerDriveProfile;
  //Controllers
  private NomadDriverController driverController;
  /**
   * The container for the robot.  Contains constant files, subsystems, commands, controller profiles, and controllers, to be created in that order.
   */
  public RobotContainer() {
    createConstantsFiles();
    createSubsystems();
    createControllerProfiles();
    createControllers();
    createCommands();
    configureButtonBindings();
    configureDefaultCommands();
  }

  /**
   * Creates the constants files for each subsystem.
   */
  private void createConstantsFiles() {
    driveConstants = new DriveConstantsKRen();
    autoConstants = new AutoConstantsKRen(driveConstants);
  }
  /**
   * Creates the subsystem.
   */
  private void createSubsystems() {
    drivebaseS = new DrivebaseS(driveConstants, autoConstants);
  }
  /**
   * Creates the commands that will be started. By creating them once and reusing them, we should save on garbage collection.
   */
  private void createCommands() {
    drivebaseArcadeDriveStickC = new DrivebaseArcadeDriveStickC(drivebaseS, driverController);
    //agitatorSpinC = new AgitatorSpinC(agitatorS, driverController);
  }
  /**
   * Configures the default Commands for the subsystems.
   */
  private void configureDefaultCommands() {
   // drivebaseS.setDefaultCommand(drivebaseArcadeDriveStickC);
    //agitatorS.setDefaultCommand(agitatorSpinC);
  }
  /**
   * Instantiates the various controller profiles for optional use.
   */
  private void createControllerProfiles() {
    ogXboxControllerTriggerDriveProfile = new OGXboxControllerTriggerDriveProfile();
  }
  /**
   * Creates the user controllers.
   */
  private void createControllers() {
    //driverController = new NomadDriverController(driverControllerProfile, ogXboxControllerTriggerDriveProfile);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                DriveConstantsSim.ksVolts,
                DriveConstantsSim.kvVoltSecondsPerMeter,
                DriveConstantsSim.kaVoltSecondsSquaredPerMeter),
            DriveConstantsSim.kDriveKinematics,
            7);

    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(
                AutoConstantsSim.kMaxSpeedMetersPerSecond,
                AutoConstantsSim.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstantsSim.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory =
        TrajectoryGenerator.generateTrajectory(
            // Start at (1, 2) facing the +X direction
            new Pose2d(1, 2, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(2, 3), new Translation2d(3, 1)),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(4, 2, new Rotation2d(0)),
            // Pass config
            config);

    RamseteCommand ramseteCommand =
        new RamseteCommand(
            exampleTrajectory,
            drivebaseS::getPose,
            new RamseteController(
                AutoConstantsSim.kRamseteB, AutoConstantsSim.kRamseteZeta),
            new SimpleMotorFeedforward(
                DriveConstantsSim.ksVolts,
                DriveConstantsSim.kvVoltSecondsPerMeter,
                DriveConstantsSim.kaVoltSecondsSquaredPerMeter),
            DriveConstantsSim.kDriveKinematics,
            drivebaseS::getWheelSpeeds,
            new PIDController(DriveConstantsSim.kPDriveVel, 0, 0),
            new PIDController(DriveConstantsSim.kPDriveVel, 0, 0),
            // RamseteCommand passes volts to the callback
            drivebaseS::tankDriveVolts,
            drivebaseS);

    // Reset odometry to starting pose of trajectory.
    drivebaseS.resetOdometry(exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> drivebaseS.tankDriveVolts(0, 0));
  }

}
