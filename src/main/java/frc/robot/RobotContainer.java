/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AgitatorSpinC;
import frc.robot.commands.IntakeSpinC;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickC;
import frc.robot.constants.AutoConstants;
import frc.robot.constants.AutoConstantsKRen;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.DriveConstantsKRen;
import frc.robot.controllerprofiles.OGXboxControllerTriggerDriveProfile;
import frc.robot.controllerprofiles.Usb0ControllerProfile;
import frc.robot.controllerprofiles.XboxControllerTriggerDriveProfile;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.IntakeS;
import frc.robot.wrappers.inputdevices.NomadDriverController;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Constants Files
  private AutoConstants autoConstants;
  private DriveConstants driveConstants;
  //Subsystems
  private DrivebaseS drivebaseS;
  private AgitatorS agitatorS;
  private IntakeS intakeS;
  //Commands
  private DrivebaseArcadeDriveStickC drivebaseArcadeDriveStickC;
  private AgitatorSpinC agitatorSpinC;
  private IntakeSpinC intakeSpinC;
  private Usb0ControllerProfile driverControllerProfile = new Usb0ControllerProfile();
  //Controller Profiles
  private OGXboxControllerTriggerDriveProfile ogXboxControllerTriggerDriveProfile;
  private XboxControllerTriggerDriveProfile triggerDriveProfile;
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
    //agitatorS = new AgitatorS();
    intakeS = new IntakeS();
  }
  /**
   * Creates the commands that will be started. By creating them once and reusing them, we should save on garbage collection.
   */
  private void createCommands() {
    drivebaseArcadeDriveStickC = new DrivebaseArcadeDriveStickC(drivebaseS, driverController);
    //agitatorSpinC = new AgitatorSpinC(agitatorS, driverController);
    intakeSpinC = new IntakeSpinC(intakeS, driverController);
  }
  /**
   * Configures the default Commands for the subsystems.
   */
  private void configureDefaultCommands() {
    drivebaseS.setDefaultCommand(drivebaseArcadeDriveStickC);
    //agitatorS.setDefaultCommand(agitatorSpinC);
    intakeS.setDefaultCommand(intakeSpinC);
  }
  /**
   * Instantiates the various controller profiles for optional use.
   */
  private void createControllerProfiles() {
    ogXboxControllerTriggerDriveProfile = new OGXboxControllerTriggerDriveProfile();
    triggerDriveProfile = new XboxControllerTriggerDriveProfile();
  }
  /**
   * Creates the user controllers.
   */
  private void createControllers() {
    driverController = new NomadDriverController(driverControllerProfile, triggerDriveProfile);
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
    return new DrivebaseArcadeDriveStickC(drivebaseS, driverController);
  }

}
