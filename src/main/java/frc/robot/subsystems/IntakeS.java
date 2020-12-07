/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.wrappers.motorcontrollers.NomadSparkMax;

public class IntakeS extends SubsystemBase {
  private NomadSparkMax intakeMotor;
  /**
   * Creates a new IntakeS.
   */
  public IntakeS() {
    intakeMotor = new NomadSparkMax(21);
  }

  public void setSpeed(double speed) {
    intakeMotor.set(speed);
  }

  @Override
  public void periodic() {
  }
}
