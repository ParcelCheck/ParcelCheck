package com.parcelcheck.parcelchecklib;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class ReflectiveEqualCheckerTest {

    private ReflectiveEqualChecker testObject;

    @Before
    public void setUp() throws Exception {
        testObject = new ReflectiveEqualChecker();
    }

    @Test
    public void primitivesAreChecked() throws Exception {
        assertTrue(testObject.checkEquality(1234, 1234));
        assertFalse(testObject.checkEquality(1234, 4321));

        assertTrue(testObject.checkEquality(new Integer(1234), new Integer(1234)));
        assertFalse(testObject.checkEquality(new Integer(1234), new Integer(4321)));

        assertTrue(testObject.checkEquality("string1", "string1"));
        assertTrue(testObject.checkEquality("string1", "string" + 1));
        assertFalse(testObject.checkEquality("string1", "string2"));
        assertFalse(testObject.checkEquality(null, "string2"));
        assertFalse(testObject.checkEquality("string1", null));

        Boolean nullBool = null;
        assertTrue(testObject.checkEquality(true, true));
        assertTrue(testObject.checkEquality(false, false));
        assertTrue(testObject.checkEquality(Boolean.TRUE, Boolean.TRUE));
        assertTrue(testObject.checkEquality(true, Boolean.TRUE));
        assertTrue(testObject.checkEquality(Boolean.FALSE, Boolean.FALSE));
        assertTrue(testObject.checkEquality(false, Boolean.FALSE));
        assertTrue(testObject.checkEquality(nullBool, nullBool));
        assertFalse(testObject.checkEquality(true, false));
        assertFalse(testObject.checkEquality(nullBool, Boolean.FALSE));
        assertFalse(testObject.checkEquality(Boolean.TRUE, nullBool));

        Double nullDouble = null;
        assertTrue(testObject.checkEquality(1234.567d, 1234.567d));
        assertTrue(testObject.checkEquality(1234.567d, new Double(1234.567)));
        assertTrue(testObject.checkEquality(new Double(1234.567), new Double(1234.567)));
        assertTrue(testObject.checkEquality(nullDouble, nullDouble));

        assertFalse(testObject.checkEquality(123.45d, 123.456d));
        assertFalse(testObject.checkEquality(123.45d, new Double(123.456)));
        assertFalse(testObject.checkEquality(new Double(123.45), new Double(123.456)));
        assertFalse(testObject.checkEquality(nullDouble, new Double(123.456)));

        Float nullFloat = null;
        assertTrue(testObject.checkEquality(1234.567f, 1234.567f));
        assertTrue(testObject.checkEquality(1234.567f, new Float(1234.567)));
        assertTrue(testObject.checkEquality(new Float(1234.567), new Float(1234.567)));
        assertTrue(testObject.checkEquality(nullFloat, nullFloat));

        assertFalse(testObject.checkEquality(123.45f, 123.456f));
        assertFalse(testObject.checkEquality(123.45f, new Float(123.456)));
        assertFalse(testObject.checkEquality(new Float(123.45), new Float(123.456)));
        assertFalse(testObject.checkEquality(nullFloat, new Float(123.456)));

        CharSequence sequence1 = "foo";
        CharSequence sequence2 = "foo";
        assertTrue(testObject.checkEquality(sequence1, sequence2));
    }

    @Test
    public void bigDecimalChecked() throws Exception {
        assertTrue(testObject.checkEquality(BigDecimal.TEN, BigDecimal.TEN));
        assertTrue(testObject.checkEquality(BigDecimal.ONE, BigDecimal.ONE));
        assertTrue(testObject.checkEquality(new BigDecimal("12345.678901"), new BigDecimal("12345.678901")));

        assertFalse(testObject.checkEquality(null, BigDecimal.ONE));
        assertFalse(testObject.checkEquality(BigDecimal.ONE, null));
        assertFalse(testObject.checkEquality(BigDecimal.ONE, BigDecimal.TEN));
        assertFalse(testObject.checkEquality(BigDecimal.ONE, BigDecimal.ZERO));
    }

    @Test
    public void bigIntegerChecked() throws Exception {
        assertTrue(testObject.checkEquality(BigInteger.ONE, BigInteger.ONE));
        assertTrue(testObject.checkEquality(BigInteger.TEN, BigInteger.TEN));
        assertTrue(testObject.checkEquality(new BigInteger("987654321000"), new BigInteger("987654321000")));

        assertFalse(testObject.checkEquality(null, BigInteger.ONE));
        assertFalse(testObject.checkEquality(BigInteger.ONE, null));
        assertFalse(testObject.checkEquality(BigInteger.ONE, BigInteger.TEN));
        assertFalse(testObject.checkEquality(BigInteger.ONE, BigInteger.ZERO));
    }
}