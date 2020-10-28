/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import frc.wrappers.Limelight;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Add your docs here.
 */
public class LimelightUnitTest {

    @Test
    public void testConstructors(){
        Limelight tester = new Limelight();

        Limelight tester2 = new Limelight("name", false);
    }
}
