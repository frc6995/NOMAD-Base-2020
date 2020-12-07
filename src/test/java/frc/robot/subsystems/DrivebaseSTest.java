/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import frc.robot.utility.drivebase.DrivebaseWheelPercentages;
import frc.robot.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.wrappers.motorcontrollers.NomadTalonSRX;

/**
 * Add your docs here.
 */
public class DrivebaseSTest {
    NomadSparkMax leftTalonSRX = Mockito.mock(NomadSparkMax.class);
    NomadSparkMax rightTalonSRX = Mockito.mock(NomadSparkMax.class);
    
    
    DrivebaseS drivebaseSTest;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setup(){
        drivebaseSTest = new DrivebaseS(leftTalonSRX, rightTalonSRX);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void arcadeDriveControllerInsideRangeTest(){
        assertEquals(1.0, drivebaseSTest.arcadeDriveController(1, 0).getLeftPercentage(), 0.001);
        assertEquals(1.0, drivebaseSTest.arcadeDriveController(1, 0).getRightPercentage(), 0.001);
    }

    @Test
    public void arcadeDriveControllerOutsideRangeTest(){
        assertEquals(2.0, drivebaseSTest.arcadeDriveController(1, 1).getLeftPercentage(), 0.001); //Value is unclamped intentionally.
        assertEquals(0.0, drivebaseSTest.arcadeDriveController(1, 1).getRightPercentage(), 0.001);
    }

    @Test
    public void drivePercentageTest(){
        DrivebaseWheelPercentages testWheelPercentages = new DrivebaseWheelPercentages();
        testWheelPercentages.setLeftPercentage(0.69).setRightPercentage(0.95);
        reset(leftTalonSRX, rightTalonSRX);
        //doNothing().when(leftTalonSRX).set(Mockito.eq(ControlMode.PercentOutput), anyDouble());
        //doNothing().when(rightTalonSRX).set(Mockito.eq(ControlMode.PercentOutput), anyDouble());
        drivebaseSTest.drivePercentages(testWheelPercentages);
        verify(leftTalonSRX).set(testWheelPercentages.getLeftPercentage());
        verify(rightTalonSRX).set(testWheelPercentages.getRightPercentage());
        verifyNoMoreInteractions(leftTalonSRX, rightTalonSRX);
    }


}
