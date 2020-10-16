/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands.drivebase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.DrivebaseS;
import frc.wrappers.InputDevices.NomadDriverJoystick;
import frc.utility.drivebase.DrivebaseWheelPercentages;

public class DrivebaseArcadeDriveStickC extends CommandBase {
  DrivebaseS drivebaseS;
  NomadDriverJoystick driveStick;
  /**
   * Creates a new DrivebaseArcadeDriveStick.
   */
  public DrivebaseArcadeDriveStickC(DrivebaseS drivebase, NomadDriverJoystick stick) {
    drivebaseS = drivebase;
    addRequirements(drivebaseS);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //A compounded function
    //processOutputs(calculateOutputs(getInputs()))
    drivebaseS.drivePercentages(drivebaseS.leftTalon, drivebaseS.rightTalon, drivebaseS.arcadeDriveController(driveStick.getFwdBackAxis(), driveStick.getLeftRightAxis()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
