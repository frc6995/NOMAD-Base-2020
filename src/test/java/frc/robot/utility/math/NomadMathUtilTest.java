/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utility.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Add your docs here.
 * 
 */

public class NomadMathUtilTest {
    @Test   
    public void LerpTest(){
        assertEquals(150.0, NomadMathUtil.lerp(0, 100, 0.5), 0.01);
        assertEquals(100.0, NomadMathUtil.lerp(0, 50, 2), 0.01);
    }
}
