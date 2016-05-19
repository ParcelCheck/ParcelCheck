package com.parcelcheck.parcelchecklib;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParcelCheckerTest {

    private RandomFieldGenerator randomFieldGenerator;
    private ParcelChecker testObject;

    @Before
    public void setUp() throws Exception {
        randomFieldGenerator = mock(RandomFieldGenerator.class);
        when(randomFieldGenerator.getNextString()).thenReturn("string1", "string2", "string3", "string4", "string5");
        when(randomFieldGenerator.getNextInt()).thenReturn(1, 2, 3, 4, 5);
        when(randomFieldGenerator.getNextLong()).thenReturn(1L, 2L, 3L, 4L, 5L);
        when(randomFieldGenerator.getNextBoolean()).thenReturn(true);
        when(randomFieldGenerator.getNextDouble()).thenReturn(1.0, 2.0, 3.0, 4.0, 5.0);
        when(randomFieldGenerator.getNextFloat()).thenReturn(1.0f, 2.0f, 3.0f, 4.0f, 5.0f);
        when(randomFieldGenerator.getNextBigDecimal()).thenReturn(new BigDecimal(99));
        when(randomFieldGenerator.getNextBigInteger()).thenReturn(new BigInteger("101"));
        when(randomFieldGenerator.getNextByte()).thenReturn((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        when(randomFieldGenerator.getNextShort()).thenReturn((short) 1, (short) 2, (short) 3, (short) 4, (short) 5);

        testObject = new ParcelChecker(randomFieldGenerator);
    }

    @Test
    public void stringsAreFilledWithRandomValues() throws Exception {
        Object filledObject = testObject.createFilledObject(BillingAddressForm.class);

        assertTrue(filledObject instanceof BillingAddressForm);
        BillingAddressForm actual = (BillingAddressForm) filledObject;

        assertNotNull(actual);
        assertEquals("string1", actual.address1);
        assertEquals("string2", actual.city);
        assertEquals("string3", actual.phone);
        assertEquals("string4", actual.postalCode);
        assertEquals("string5", actual.state);
    }

    @Test
    public void primitivesAreFilledWithRandomValues() throws Exception {
        Object filledObject = testObject.createFilledObject(PrimitiveFilledObject.class);

        PrimitiveFilledObject actual = (PrimitiveFilledObject) filledObject;

        assertNotNull(actual);

        assertEquals("string1", actual.getString());

        assertEquals(1, actual.getInteger());
        assertEquals(2, actual.getClassInt().intValue());

        assertTrue(actual.isPrimitiveBoolean());
        assertTrue(actual.getClassBoolean());

        assertEquals(1.0, actual.getPrimitiveDouble(), 0.00001);
        assertEquals(2.0, actual.getClassDouble().doubleValue(), 0.00001);

        assertEquals(1.0F, actual.getPrimitiveFloat(), 0.00001);
        assertEquals(2.0F, actual.getClassFloat().floatValue(), 0.00001);

        assertEquals(1L, actual.getPrimitiveLong());
        assertEquals(2L, actual.getClassLong().intValue());

        assertEquals(99, actual.getBigDecimal().intValue());
        assertEquals(101, actual.getBigInteger().intValue());

        assertEquals((byte) 1, actual.getPrimitiveByte());
        assertEquals((byte) 2, actual.getClassByte().byteValue());

        assertEquals((short) 1, actual.getPrimitiveShort());
        assertEquals((short) 2, actual.getClassShort().shortValue());
    }
}