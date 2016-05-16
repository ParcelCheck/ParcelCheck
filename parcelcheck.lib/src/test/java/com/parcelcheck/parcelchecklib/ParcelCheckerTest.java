package com.parcelcheck.parcelchecklib;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParcelCheckerTest {

    @Test
    public void stringsAreFilledWithRandomValues() throws Exception {
        RandomFieldGenerator randomFieldGenerator = mock(RandomFieldGenerator.class);
        when(randomFieldGenerator.getNextString()).thenReturn("string1", "string2", "string3", "string4", "string5");
        ParcelChecker parcelChecker = new ParcelChecker(randomFieldGenerator);

        Object filledObject = parcelChecker.createFilledObject(BillingAddressForm.class);

        assertTrue(filledObject instanceof BillingAddressForm);
        BillingAddressForm actual = (BillingAddressForm) filledObject;

        assertNotNull(actual);
        assertEquals("string1", actual.address1);
        assertEquals("string2", actual.city);
        assertEquals("string3", actual.phone);
        assertEquals("string4", actual.postalCode);
        assertEquals("string5", actual.state);
    }
}