/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.wrappers.InputDevices.NomadDriverController;

public class DrivebaseArcadeDriveStickC extends CommandBase {
  DrivebaseS drivebaseS;
  NomadDriverController driveStick;
  /**
   * Creates a new DrivebaseArcadeDriveStick.
   */
  public DrivebaseArcadeDriveStickC(DrivebaseS drivebase, NomadDriverController stick) {
    drivebaseS = drivebase;
    addRequirements(drivebaseS);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    //A compounded function: processOutputs(calculateOutputs(getInputs()))
    drivebaseS.drivePercentages(
      drivebaseS.leftTalon, 
      drivebaseS.rightTalon, 
      drivebaseS.arcadeDriveController(
        driveStick.getFwdBackAxis(), 
        driveStick.getLeftRightAxis()));
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
