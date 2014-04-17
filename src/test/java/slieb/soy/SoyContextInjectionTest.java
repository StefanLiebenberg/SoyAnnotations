package slieb.soy;


import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static slieb.soy.Loader.*;


public class SoyContextInjectionTest {

    @Test
    public void testBasicInjection() {
        assertNotNull(getBasicSoyDataContext());
    }

    @Test
    public void testFullInjection() {
        assertNotNull(getFullSoyDataContext());
    }

    @Test
    public void testLazyInjection() {
        assertNotNull(getLazySoyDataContext());
    }
}
