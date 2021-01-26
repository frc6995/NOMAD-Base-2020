/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.template;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.lib.auto.NomadAutoCommandGenerator;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.constants.DriverStationConstants;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;
import frc.template.auto.Trajectories;
import frc.template.commands.drivebase.DrivebaseArcadeDriveStickC;
import frc.template.constants.AutoConstantsDemoAuto;
import frc.template.constants.DriveConstantsDemoAuto;
import frc.template.constants.DriverStationConstantsTemplate;
import frc.template.subsystems.AutonomousDrivebaseS;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Constants Files
  private AutoConstants autoConstants;
  private DriveConstants driveConstants;
  private DriverStationConstants driverStationConstants;
  // Subsystems
  private AutonomousDrivebaseS drivebaseS;
  // Commands
  private DrivebaseArcadeDriveStickC drivebaseArcadeDriveStickC;

  // private NomadMappedGenericHID driverController;

  private boolean init = false;

  /**
   * The container for the robot. Contains constant files, controllers, subsystems, trajectories, commands,
   * and default command bindings, to be created in that order.
   */
  public RobotContainer() {
    createConstantsFiles();
    createControllers(driveConstants, driverStationConstants, NomadMappingEnum.DEFAULT_DRIVE);
    createSubsystems();
    Trajectories.createTrajectories(autoConstants.getTrajectoryConfig());
    createCommands();
    configureDefaultCommands();
    init = true;
  }

  /**
   * Creates the constants files for each subsystem.
   */
  private void createConstantsFiles() {
    driveConstants = new DriveConstantsDemoAuto();
    autoConstants = new AutoConstantsDemoAuto(driveConstants);
    driverStationConstants = new DriverStationConstantsTemplate();
  }

  /**
   * Creates the subsystems.
   */
  private void createSubsystems() {
    drivebaseS = new AutonomousDrivebaseS(driveConstants, autoConstants);

  }

  /**
   * Creates the commands that will be started. By creating them once and reusing
   * them, we should save on garbage collection.
   */
  private void createCommands() {
    drivebaseArcadeDriveStickC = new DrivebaseArcadeDriveStickC(drivebaseS, driveConstants);
  }

  /**
   * Configures the default Commands for the subsystems.
   */
  private void configureDefaultCommands() {
    drivebaseS.setDefaultCommand(drivebaseArcadeDriveStickC);
  }

  /**
   * Creates the operator console. In actual use, this method would have more constants files for other subsystems.
   * @param driveConstants Drivebase constants to use in the input map creation.
   * @param map The map from NomadInputMaps to select.
   */
  private void createControllers(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadMappingEnum map) {
    TemplateNomadInputMaps.createMaps(driveConstants, driverStationConstants);
    NomadOperatorConsole.setMap(map);
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
    RamseteCommand ramseteCommand = NomadAutoCommandGenerator.createRamseteCommand(Trajectories.exampleTrajectory,
        drivebaseS, driveConstants, autoConstants);
    // Reset odometry to starting pose of trajectory.
    drivebaseS.resetOdometry(Trajectories.exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> drivebaseS.tankDriveVolts(0, 0));
  }
  /**
   * Update the telemetry. This method in RobotContainer is mostly provided for quick testing. Most telemetry should be in subsystems. 
   */
  public void updateTelemetry() {
    if (init) {
      SmartDashboard.putNumber("driveFwdBack",
          NomadOperatorConsole.getRawAxis(driveConstants.getDriveControllerFwdBackAxis()));
      SmartDashboard.putString("Driver Map", NomadOperatorConsole.getSelectedMap().toString());
    }
  }



}
