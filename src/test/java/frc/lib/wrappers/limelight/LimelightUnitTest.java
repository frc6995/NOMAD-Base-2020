/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.wrappers.limelight;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import frc.lib.wrappers.limelight.Limelight.LedState;

/**
 * Limelight unit test
 */
public class LimelightUnitTest {

    /**
     * Test to see if the Limelight's hasTarget method works.
     * A non-existent (fake) Limelight should not have a target.
     */
    @Test
    public void targetTest(){
        
        Limelight tester = new Limelight("name");

        assertEquals(false, tester.hasTarget(), "Has target test");        
    }
    
    /**
     * Test the getXOffset() method.
     * A non-existent (fake) Limelight should have no x-offset
     */
    @Test
    public void offsetTest(){
        Limelight tester = new Limelight("name");        

        assertEquals(0, tester.getXOffset(), 0.1, "X offset test");
    }

    /**
     * Test the Limelight's LED Mode by setting it to Blink.
     * The result should be the LED Mode equaling Blink.
     */
    @Test
    public void testLEDModesValid(){
        Limelight tester = new Limelight("name");

        tester.setLedMode(Limelight.LedState.Blink);

        assertEquals(LedState.Blink, tester.getLEDMode(), "LED Mode test (valid)");
    }

    /**
     * Tests the Limelight's LED Mode by setting it to null.
     * The result should be the mode getting set to Off instead.
     */
    @Test
    public void testLEDModesInvalid1(){
        Limelight tester = new Limelight("name");

        tester.setLedMode(null);

        assertEquals(LedState.Off, tester.getLEDMode(), "Null LED Mode test");
    }

    /**
     * Tests the Limelight's LED Mode by setting it to a non-existent state.
     * The result should be the mode getting set to Off instead.
     */
    @Test
    public void testLEDModesInvalid2(){
        Limelight tester = new Limelight("name");

        tester.setLedMode(LedState.getState(15));

        assertEquals(LedState.Off, tester.getLEDMode(), "Out of bounds LED Mode test");
    }
}
